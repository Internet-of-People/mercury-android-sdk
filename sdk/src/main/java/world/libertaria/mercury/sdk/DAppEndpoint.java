package world.libertaria.mercury.sdk;

import android.util.Log;

import java.util.UUID;

public class DAppEndpoint {
    private static final String LOG_TAG = DAppEndpoint.class.getSimpleName();
    private DAppSession session;

    public DAppSession connect(UUID applicationId) {
        Log.d(LOG_TAG, String.format("Connection with app %s", applicationId));

        session = new DAppSession(); //TODO do not return until its really connected

        return session;
    }

    public void disconnect() {
        Log.d(LOG_TAG, "Disconnecting...");
        session = null;
    }

    public boolean isConnected() {
        throw new UnsupportedOperationException();
    }
}
