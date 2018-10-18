package world.libertaria.mercury.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.UUID;
import java.util.concurrent.Future;

import world.libertaria.mercury.sdk.DAppEndpoint;
import world.libertaria.mercury.sdk.DAppSession;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final DAppEndpoint endpoint = new DAppEndpoint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Initialising endpoint");
        Future<DAppSession> dAppSessionFuture = endpoint.connect(UUID.randomUUID());

        while (!dAppSessionFuture.isDone()) {
            Log.d(TAG, "Sending ping...");
            //endpoint.sendTestPing(); TODO
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
}
