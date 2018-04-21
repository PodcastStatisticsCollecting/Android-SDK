package podcast.statistics.collector;

import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.CancelReason;
import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

/**
 * Publish events to the server
 */
class PostEventJob extends Job {

    private static final int PRIORITY = 1;

    private String payload;

    PostEventJob(String payload) {
        // This job requires network connectivity,
        // and should be persisted in case the application exits before job is completed.
        super(new Params(PRIORITY).requireNetwork().persist());
        this.payload = payload;
    }

    @Override
    public void onAdded() {
    }

    @Override
    public void onRun() {
        PodcastStatisticsCollector.getInstance()
                .getApiClient()
                .publishEvent(payload);
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable,
                                                     int runCount,
                                                     int maxRunCount) {
        //Do not retry sending an event
        return RetryConstraint.CANCEL;
    }

    @Override
    protected void onCancel(@CancelReason int cancelReason, @Nullable Throwable throwable) {
    }
}
