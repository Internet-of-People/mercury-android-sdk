package mercury.connect.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.Settings;

import java.util.Objects;

public class SDKTools {
    private static final String ASKED_BATTERY_OPTIMIZTAIONS_PREF = "askedBatteryOptimizations";

    public static void askForBatteryOptinmizationOptOut(PowerManager powerManager, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        if (
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        !preferences.getBoolean(ASKED_BATTERY_OPTIMIZTAIONS_PREF, false) &&
                        !Objects.requireNonNull(powerManager).isIgnoringBatteryOptimizations(context.getPackageName())
        ) {
            saveAskedBatteryOptimizations(preferences);
            context.startActivity(new Intent(
                    Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                    Uri.parse("package:" + context.getPackageName())
            ));
        }
    }

    private static void saveAskedBatteryOptimizations(SharedPreferences preferences) {
        preferences.edit().putBoolean(ASKED_BATTERY_OPTIMIZTAIONS_PREF, true).apply();
    }
}
