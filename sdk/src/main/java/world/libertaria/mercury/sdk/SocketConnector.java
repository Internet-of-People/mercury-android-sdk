package world.libertaria.mercury.sdk;

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
import java.util.Objects;

import java9.util.function.Consumer;

public class SocketConnector extends Thread {
    private static final String TAG = SocketConnector.class.getSimpleName();
    private final JsonFactory jsonFactory = new JsonFactory();
    private final ObjectMapper jsonObjectMapper = new ObjectMapper();
    private final Consumer<JsonNode> dataReceiver;
    private BufferedWriter rustSocketOutputStream;
    private boolean connected;

    public SocketConnector(Consumer<JsonNode> dataReceiver) {
        this.dataReceiver = dataReceiver;
    }

    @Override
    public void run() {
        LocalSocket socket = null;

        try {
            Log.d(TAG, "Connecting to Mercury Socket...");
            socket = new LocalSocket();
            socket.connect(new LocalSocketAddress("/tmp/mercury"));
            Log.d(TAG, "Connected");
            connected = true;

            rustSocketOutputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            JsonParser jsonParser = jsonFactory.createParser(socket.getInputStream());
            JsonToken token;

            Log.d(TAG, "Reading socket...");
            while ((token = jsonParser.nextToken()) != null) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }

                switch (token) {
                    case START_OBJECT:
                        JsonNode node = jsonObjectMapper.readTree(jsonParser);
                        dataReceiver.accept(node);
                        break;
                }
            }
            Log.d(TAG, "Socket reading finished");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connected = false;
                Objects.requireNonNull(socket).close();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void write() {
        // TODO: make it async
        rustSocketOutputStream.write();
    }
}
