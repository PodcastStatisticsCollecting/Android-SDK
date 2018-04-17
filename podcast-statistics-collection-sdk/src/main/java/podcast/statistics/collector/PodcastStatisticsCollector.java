package podcast.statistics.collector;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;

import podcast.statistics.collector.api.EndedEvent;
import podcast.statistics.collector.api.PauseEvent;
import podcast.statistics.collector.api.PlayEvent;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.Objects;

/**
 * Singleton service, which provide API for podcast statistics collection.
 */
public class PodcastStatisticsCollector {

    private final static Gson GSON = GsonCreator.getGSON();

    private String apiKey;

    private JobManager jobManager;

    private PodcastStatisticsClient apiClient;

    private PodcastStatisticsCollector() {

    }

    /**
     * Initialization of Podcast Statistics Collector.
     * You have to call it before using the library.
     * @param context - context of android application
     * @param apiKey - API key given by the service
     */
    public static void activate(@NonNull Context context, @Nullable String apiKey) {
        getInstance().apiKey = apiKey != null ? apiKey : "ANDROID_SDK_KEY";
        configureJobManager(Objects.requireNonNull(context, "context"));
        getInstance().apiClient = new PodcastStatisticsClient(getInstance().apiKey);
    }

    /**
     * Indicate, that play event occurs
     * @param playEvent - payload of event
     */
    public static void play(@NonNull PlayEvent playEvent) {
        Objects.requireNonNull(playEvent, "playEvent");
        getInstance().getJobManager()
                .addJobInBackground(new PostEventJob(GSON.toJson(Collections.singletonList(playEvent))));
    }

    /**
     * Indicate, that ended event occurs
     * @param endedEvent - payload of event
     */
    public static void ended(@NonNull EndedEvent endedEvent) {
        Objects.requireNonNull(endedEvent, "endedEvent");
        getInstance().getJobManager()
                .addJobInBackground(new PostEventJob(GSON.toJson(Collections.singletonList(endedEvent))));
    }

    /**
     * Indicate, that pause event occurs
     * @param pauseEvent - payload of event
     */
    public static void pause(@NonNull PauseEvent pauseEvent) {
        Objects.requireNonNull(pauseEvent, "pauseEvent");
        getInstance().getJobManager()
                .addJobInBackground(new PostEventJob(GSON.toJson(Collections.singletonList(pauseEvent))));
    }

    static PodcastStatisticsCollector getInstance() {
        return Holder.INSTANCE;
    }

    JobManager getJobManager() {
        return jobManager;
    }

    PodcastStatisticsClient getApiClient() {
        return apiClient;
    }

    private static void configureJobManager(Context context) {
        Configuration.Builder builder = new Configuration.Builder(context)
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";
                    @Override
                    public boolean isDebugEnabled() {
                        return false;
                    }

                    @Override
                    public void d(String text, Object... args) {
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }

                    @Override
                    public void v(String text, Object... args) {

                    }
                })
                .minConsumerCount(1) //always keep at least one consumer alive
                .maxConsumerCount(3) //up to 3 consumers at a time
                .loadFactor(3) //3 jobs per consumer
                .consumerKeepAlive(120) //wait 2 minute
                .scheduler(FrameworkJobSchedulerService
                        .createSchedulerFor(context, PodcastStatisticsCollectorJobService.class), true);

        getInstance().jobManager = new JobManager(builder.build());
    }

    private static class Holder {
        private static final PodcastStatisticsCollector INSTANCE = new PodcastStatisticsCollector();
    }
}
