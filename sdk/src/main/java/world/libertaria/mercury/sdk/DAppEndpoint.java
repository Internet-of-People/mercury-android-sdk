package world.libertaria.mercury.sdk;

import android.util.Log;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Completable;
import io.reactivex.Single;

public class DAppEndpoint {
    private static final String TAG = DAppEndpoint.class.getSimpleName();
    private static final RustAPI rustApi = new RustAPI();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private DAppSession session;

    public Single<DAppSession> getSession(UUID applicationId) {
        Log.d(TAG, String.format("Connecting with app %s", applicationId));
        return Single.fromFuture(executor.submit(() -> {
            Log.d(TAG, String.format("Connected with app %s", applicationId));
            session = new DAppSession(rustApi);
            return session;
        }));
    }

    public Completable disconnect() {
        Log.d(TAG, "Disconnecting...");
        session = null;

        throw new UnsupportedOperationException();
    }

    public boolean isConnected() {
        throw new UnsupportedOperationException();
    }

    public void sendTestPing() {
        throw new UnsupportedOperationException();
    }
}
