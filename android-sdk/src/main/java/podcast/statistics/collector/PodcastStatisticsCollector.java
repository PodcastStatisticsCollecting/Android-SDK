package podcast.statistics.collector;

import android.content.Context;
import android.content.SharedPreferences;
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
import java.util.UUID;

/**
 * Singleton service, which provide API for podcast statistics collection.
 */
public class PodcastStatisticsCollector {

    private final static Gson GSON = GsonCreator.getGSON();

    private static final String PREF_PODCAST_STAT_UNIQUE_ID = "PREF_PODCAST_STAT_UNIQUE_ID";

    private static String uniqueID = null;

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
        initId(context);
    }

    /**
     * Indicate, that play event occurs
     * @param playEventBuilder - payload of event
     */
    public static void play(@NonNull PlayEvent.Builder playEventBuilder) {
        Objects.requireNonNull(playEventBuilder, "playEventBuilder");

        playEventBuilder.setUserId(uniqueID);

        getInstance().getJobManager().addJobInBackground(new PostEventJob(
                GSON.toJson(Collections.singletonList(playEventBuilder.create()))));
    }

    /**
     * Indicate, that ended event occurs
     * @param endedEventBuilder - payload of event
     */
    public static void ended(@NonNull EndedEvent.Builder endedEventBuilder) {
        Objects.requireNonNull(endedEventBuilder, "endedEventBuilder");

        endedEventBuilder.setUserId(uniqueID);

        getInstance().getJobManager().addJobInBackground(new PostEventJob(
                GSON.toJson(Collections.singletonList(endedEventBuilder.create()))));
    }

    /**
     * Indicate, that pause event occurs
     * @param pauseEventBuilder - payload of event
     */
    public static void pause(@NonNull PauseEvent.Builder pauseEventBuilder) {
        Objects.requireNonNull(pauseEventBuilder, "pauseEventBuilder");

        pauseEventBuilder.setUserId(uniqueID);

        getInstance().getJobManager().addJobInBackground(new PostEventJob(
                GSON.toJson(Collections.singletonList(pauseEventBuilder.create()))));
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

    private synchronized static String initId(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_PODCAST_STAT_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_PODCAST_STAT_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_PODCAST_STAT_UNIQUE_ID, uniqueID);
                editor.commit();
            }
        }
        return uniqueID;
    }

    private static class Holder {
        private static final PodcastStatisticsCollector INSTANCE = new PodcastStatisticsCollector();
    }
}
