package podcast.statistics.collector.api;

import android.support.annotation.NonNull;

import java.util.UUID;

class NonceGenerator {

    @NonNull
    static String generate() {
        return UUID.randomUUID().toString();
    }
}
