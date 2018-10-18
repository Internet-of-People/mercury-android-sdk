package world.libertaria.mercury.mercuryconnectsdk.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import world.libertaria.mercury.mercuryconnectsdk.R;
import world.libertaria.mercury.sdk.DAppEndpoint;
import world.libertaria.mercury.sdk.RustAPI;

public class MainActivity extends AppCompatActivity {
    private static final RustAPI rustApi=new RustAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DAppEndpoint endpoint=new DAppEndpoint();

    }

    @Override
    protected void onPause() {
        super.onPause();
        rustApi.jniStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        rustApi.jniStart();
    }
}
