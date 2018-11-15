package global.iop.mercury.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import global.iop.mercury.sdk.DAppEndpoint;
import global.iop.mercury.sdk.api.GetSessionRequest;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final DAppEndpoint endpoint = new DAppEndpoint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Log.d(TAG, "Initialising endpoint");

        endpoint.connect();
        endpoint.getSession(new GetSessionRequest("app", new ArrayList<>())).thenAccept(session -> {
            Log.d(TAG, String.format("Got session with profile %s", session.getProfileId()));

            endpoint.getSelectedProfile().thenAccept(selectedProfile -> {
                Log.d(TAG, String.format("Selected profile is: %s", selectedProfile.getProfileId()));

                endpoint.getContactsByProfile().thenAccept(contacts -> {
                    Log.d(TAG, String.format("Got contacts: %s, disconnecting...", Arrays.toString(Objects.requireNonNull(contacts.getContacts().toArray()))));
                    endpoint.disconnect();
                    Log.d(TAG, "Disconnected");
                });
            });
        });
    }
}
