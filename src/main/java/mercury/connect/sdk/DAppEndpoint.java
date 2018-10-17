package mercury.connect.sdk;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DAppEndpoint {
    private static final String LOG_TAG = DAppEndpoint.class.getSimpleName();
    private final JobScheduler jobScheduler;
    private final Context context;
    private Integer jobId;
    private DAppSession session;

    DAppEndpoint(JobScheduler jobScheduler, Context context) {
        this.jobScheduler = jobScheduler;
        this.context = context;
    }

    public DAppSession connect(UUID applicationId) {
        Log.d(LOG_TAG, String.format("Connection with app %s", applicationId));
        scheduleAndService();

        session = new DAppSession(); //TODO do not return until its really connected


        return session;
    }

    public void disconnect() {
        Log.d(LOG_TAG, "Disconnecting...");
        context.stopService(new Intent(context, DAppService.class));

        if (jobId != null) {
            jobScheduler.cancel(jobId);
            jobId = null;
        }

        session = null;
    }

    public boolean isConnected() {
        throw new UnsupportedOperationException();
    }

    private void scheduleAndService() {
        Log.d(LOG_TAG, "scheduleAndService called");
        context.startService(new Intent(context, DAppService.class));

        JobInfo.Builder builder = new JobInfo.Builder(
                1,
                new ComponentName(context, DAppService.class)
        );

        JobInfo jobInfo = builder
                .setPeriodic(TimeUnit.MINUTES.toMillis(15))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true).build();

        jobId = Objects.requireNonNull(jobScheduler).schedule(jobInfo);
    }
}
