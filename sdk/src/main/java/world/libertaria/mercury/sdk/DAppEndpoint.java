package world.libertaria.mercury.sdk;

import android.util.Log;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DAppEndpoint {
    private static final String TAG = DAppEndpoint.class.getSimpleName();
    private static final RustAPI rustApi = new RustAPI();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private DAppSession session;

    public Future<DAppSession> connect(UUID applicationId) {
        Log.d(TAG, String.format("Connecting with app %s", applicationId));

        return executor.submit(() -> {
            Log.d(TAG, String.format("Connected with app %s", applicationId));
            session = new DAppSession();
            return session;
        });
    }

    public void disconnect() {
        Log.d(TAG, "Disconnecting...");
        session = null;
    }

    public boolean isConnected() {
        throw new UnsupportedOperationException();
    }

    public void sendTestPing() {
        rustApi.jniPing();
    }
}
