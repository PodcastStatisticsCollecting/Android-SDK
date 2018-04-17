package podcast.statistics.collector;

import android.support.annotation.NonNull;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Base64Coder {

    public static String encode(@NonNull String payload) {
        Objects.requireNonNull(payload, "payload");
        return Base64.encodeToString(payload.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP);
    }

    public static String decode(@NonNull String base64String) {
        Objects.requireNonNull(base64String, "base64String");
        return new String(Base64.decode(base64String, Base64.DEFAULT), StandardCharsets.UTF_8);
    }
}
