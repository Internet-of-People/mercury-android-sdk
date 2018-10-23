package world.libertaria.mercury.example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.UUID;

import world.libertaria.mercury.sdk.DAppContact;
import world.libertaria.mercury.sdk.DAppEndpoint;

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

    @SuppressLint("CheckResult")
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        Log.d(TAG, "Initialising endpoint");

        endpoint.getSession(UUID.randomUUID()).subscribe(dAppSession -> {
            Log.d(TAG, String.format("Got session with profile %s", dAppSession.getSelectedProfileId()));

            dAppSession.getContacts().subscribe(dAppContacts -> {
                for (DAppContact dAppContact : dAppContacts) {
                    Log.d(TAG, String.format("Contact: %s", dAppContact.getProfileId()));
                }
            });

            //Log.d(TAG, "Sending ping...");
            //endpoint.sendTestPing();
        }, Throwable::printStackTrace);
    }
}
