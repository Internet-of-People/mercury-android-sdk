package global.iop.mercury.sdk;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import global.iop.mercury.sdk.api.SocketRPCRequest;
import global.iop.mercury.sdk.api.SocketRPCResult;
import java9.util.concurrent.CompletableFuture;
import java9.util.function.Consumer;

class SocketConnector {
    private static final String TAG = SocketConnector.class.getSimpleName();
    private final JsonFactory jsonFactory = new JsonFactory();
    private final ObjectMapper jsonObjectMapper = new ObjectMapper();
    private final AtomicLong idIndex = new AtomicLong(0);
    private final HashMap<String, WaitingForResult> resultQueue = new HashMap<>();
    private final LocalSocket socket = new LocalSocket();
    private BufferedWriter rustSocketOutputStream;
    private volatile boolean connected;
    private volatile boolean interrupted;

    void disconnect() {
        interrupted = true;
        resultQueue.clear();
    }

    CompletableFuture<Void> sendRequestAsync(SocketRPCRequest request, Consumer<SocketRPCResult> consumer, Class<? extends SocketRPCResult> resultClass) throws APIException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String json = jsonObjectMapper.writeValueAsString(request);

                if (!isConnected()) {
                    throw new IOException("Could not write socket, not connected");
                }

                resultQueue.put(
                        String.valueOf(idIndex.incrementAndGet()),
                        new WaitingForResult(resultClass, consumer)
                );

                synchronized (socket) {
                    rustSocketOutputStream.write(json);
                    rustSocketOutputStream.flush();
                }
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
                throw new APIException(e);
            }

            return null;
        });
    }

    CompletableFuture<LocalSocket> connectToSocket() {
        return CompletableFuture.supplyAsync(() -> {
            Log.d(TAG, "Connecting to Mercury Socket...");
            LocalSocket socket = new LocalSocket();

            try {
                socket.connect(new LocalSocketAddress("/tmp/mercury"));
                Log.d(TAG, "Connected");
                connected = true;
            } catch (IOException e) {
                throw new APIException(e);
            }

            return socket;
        });
    }

    CompletableFuture<Void> readSocket(LocalSocket socket) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                rustSocketOutputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                JsonParser jsonParser = jsonFactory.createParser(socket.getInputStream());
                JsonToken token;

                Log.d(TAG, "Reading socket...");
                while ((token = jsonParser.nextToken()) != null) {
                    if (interrupted) {
                        break;
                    }

                    switch (token) {
                        case START_OBJECT:
                            JsonNode node = jsonObjectMapper.readTree(jsonParser);
                            if (node.has("id")) { // result
                                String id = node.get("id").asText();
                                WaitingForResult waitingForResult = resultQueue.remove(id);

                                if (waitingForResult == null) {
                                    Log.e(TAG, String.format("Got an RPC result with id %s, but it was not requested", id));
                                    continue;
                                }

                                waitingForResult.resultConsumer.accept(
                                        jsonObjectMapper.readValue(
                                                node.asText(),
                                                waitingForResult.resultClass
                                        )
                                );
                            }
                            break;
                    }
                }

                Log.d(TAG, "Socket reading finished");
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
                throw new APIException(e);
            } finally {
                try {
                    connected = false;
                    rustSocketOutputStream = null;
                    socket.close();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                }
            }
            return null;
        });
    }

    private boolean isConnected() {
        return connected && !socket.isClosed() && !socket.isOutputShutdown() && !socket.isInputShutdown();
    }

    private class WaitingForResult {
        private Class<? extends SocketRPCResult> resultClass;
        private Consumer<SocketRPCResult> resultConsumer;

        private WaitingForResult(Class<? extends SocketRPCResult> resultClass, Consumer<SocketRPCResult> resultConsumer) {
            this.resultClass = resultClass;
            this.resultConsumer = resultConsumer;
        }
    }
}
