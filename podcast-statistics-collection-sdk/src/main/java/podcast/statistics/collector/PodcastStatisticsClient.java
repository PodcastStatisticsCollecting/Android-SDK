package podcast.statistics.collector;

import android.util.Log;

import java.util.Objects;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Client for communication with statistics server
 */
class PodcastStatisticsClient {

    private static final String TAG = "PodcastStatisticsClient";

    private static final String API_URL = "https://putsreq.com/hGHaSO7Ed1yEei8R0cV8";

    private final static OkHttpClient client = new OkHttpClient();

    private final String apiKey;

    PodcastStatisticsClient(String apiKey) {
        this.apiKey = Objects.requireNonNull(apiKey, "apiKey");
    }

    void publishEvent(String jsonEvent) {
        RequestBody formBody = new FormBody.Builder()
                .add("d", Base64Coder.encode(jsonEvent))
                .add("k", apiKey)
                .build();

        Request request = new Request.Builder()
                .url(API_URL)
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                Log.w(TAG, "Send of event to podcast statistics server is not successful");
            }
        } catch (Exception e) {
            Log.e(TAG, "Cannot send event to podcast statistics server", e);
        }
    }
}
