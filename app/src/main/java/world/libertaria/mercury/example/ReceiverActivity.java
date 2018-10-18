package world.libertaria.mercury.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Objects;

import world.libertaria.mercury.sdk.Action;

public class ReceiverActivity extends AppCompatActivity {
    private static final String TAG = ReceiverActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        Intent intent = getIntent();

        if (Objects.requireNonNull(intent.getAction()).equals(Action.PONG)) {
            Log.d(TAG, "Received PONG!");
        }
    }
}
