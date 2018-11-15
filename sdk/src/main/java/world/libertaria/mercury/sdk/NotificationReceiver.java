package world.libertaria.mercury.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

public class NotificationReceiver extends Activity {
    private static final String TAG = NotificationReceiver.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        if (Objects.requireNonNull(intent.getAction()).equals(Notification.PAIRING_RESPONSE)) {
            Log.d(TAG, "Received PAIRING_RESPONSE!");
        }

        finish();
    }
}
