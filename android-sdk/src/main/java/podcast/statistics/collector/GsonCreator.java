package podcast.statistics.collector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

final public class GsonCreator {

    private final static Gson GSON = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    private GsonCreator() {

    }

    public static Gson getGSON() {
        return GSON;
    }
}
