package mercury.connect.sdk;

import android.app.job.JobParameters;
import android.app.job.JobService;

class DAppService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        throw new UnsupportedOperationException();
    }
}
