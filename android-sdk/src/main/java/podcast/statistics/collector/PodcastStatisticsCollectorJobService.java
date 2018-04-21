package podcast.statistics.collector;


import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;

public class PodcastStatisticsCollectorJobService extends FrameworkJobSchedulerService {
    @NonNull
    @Override
    protected JobManager getJobManager() {
        return PodcastStatisticsCollector.getInstance().getJobManager();
    }
}
