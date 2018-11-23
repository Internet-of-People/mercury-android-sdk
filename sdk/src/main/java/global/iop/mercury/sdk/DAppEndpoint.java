package global.iop.mercury.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import global.iop.mercury.sdk.api.GetContactsByProfileRequest;
import global.iop.mercury.sdk.api.GetContactsByProfileResult;
import global.iop.mercury.sdk.api.GetSelectedProfileRequest;
import global.iop.mercury.sdk.api.GetSelectedProfileResult;
import global.iop.mercury.sdk.api.GetSessionRequest;
import global.iop.mercury.sdk.api.GetSessionResult;
import java9.util.concurrent.CompletableFuture;

public class DAppEndpoint {
    private static final String TAG = DAppEndpoint.class.getSimpleName();
    private static final int SOCKET_TIMEOUT = 10;
    private SocketConnector socketConnector;
    private CountDownLatch waitForSocketConnectionLatch = new CountDownLatch(1);
    private DAppSession currentSession;

    public DAppEndpoint(Context context) {
        PackageManager mPm = context.getPackageManager();
        PackageInfo info;
        try {
            info = mPm.getPackageInfo("global.iop.mercury.connect", 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new APIException(e);
        }

        if (info == null) {
            throw new APIException("Mercury Connect is not installed");
        }
    }

    public CompletableFuture<Void> connect() {
        Log.d(TAG, "Starting socket connector thread");

        socketConnector = new SocketConnector();
        return socketConnector
                .connectToSocket()
                .thenAcceptAsync(socketConnector::readSocket)
                .thenRunAsync(waitForSocketConnectionLatch::countDown);
    }

    public void disconnect() {
        currentSession = null;
        waitForSocketConnectionLatch = new CountDownLatch(1);
        socketConnector.disconnect();
    }

    public CompletableFuture<GetSessionResult> getSession(GetSessionRequest request) throws APIException {
        expectSocket();

        CompletableFuture<GetSessionResult> cf = new CompletableFuture<>();

        Log.d(TAG, String.format("Getting session with app %s", request.getApplicationId()));
        socketConnector.sendRequestAsync(request, result -> {
            GetSessionResult sessionResult = (GetSessionResult) result;
            currentSession = new DAppSession(sessionResult.getSessionId(), sessionResult.getProfileId(), request.getApplicationId());
            cf.complete(sessionResult);
        }, GetSessionResult.class);

        return cf;
    }

    public CompletableFuture<GetSelectedProfileResult> getSelectedProfile() throws APIException {
        expectSocket();
        expectSession();

        CompletableFuture<GetSelectedProfileResult> cf = new CompletableFuture<>();
        Log.d(TAG, String.format("Getting selected profile with app %s", currentSession.getApplicationId()));

        socketConnector.sendRequestAsync(
                new GetSelectedProfileRequest(currentSession.getSessionId()),
                result -> cf.complete((GetSelectedProfileResult) result),
                GetSelectedProfileResult.class
        );
        return cf;
    }

    public CompletableFuture<GetContactsByProfileResult> getContactsByProfile() throws APIException {
        expectSocket();
        expectSession();

        CompletableFuture<GetContactsByProfileResult> cf = new CompletableFuture<>();
        Log.d(TAG, String.format("Getting contacts by profile %s with app %s", currentSession.getProfileId(), currentSession.getApplicationId()));

        socketConnector.sendRequestAsync(
                new GetContactsByProfileRequest(currentSession.getSessionId(), currentSession.getProfileId()),
                result -> cf.complete((GetContactsByProfileResult) result),
                GetContactsByProfileResult.class
        );
        return cf;
    }

    private void expectSession() throws APIException {
        if (currentSession == null) {
            throw new APIException("Session was expected");
        }
    }

    private void expectSocket() {
        try {
            if (!waitForSocketConnectionLatch.await(SOCKET_TIMEOUT, TimeUnit.SECONDS)) {
                throw new APIException("Could not connect to socket, timed out");
            }
        } catch (InterruptedException e) {
            throw new APIException(e);
        }
    }
}
