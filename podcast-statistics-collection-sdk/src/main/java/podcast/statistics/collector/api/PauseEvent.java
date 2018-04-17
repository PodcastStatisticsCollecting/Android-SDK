package podcast.statistics.collector.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

/**
 * Pause event
 */
public class PauseEvent extends Event {

    /**
     * Name of event.
     */
    @SerializedName("name")
    @NonNull
    private final String name;

    /**
     * Check value on uniqueness of an event.
     * When specified the value shall be an arbitrary string and functions as as a nonce.
     * The property is also useful in retry scenarios.
     */
    @SerializedName("nonce")
    @NonNull
    private final String nonce;

    /**
     * Unique identifier or name of the client or media player
     * performing the action related to the request.
     */
    @SerializedName("client")
    @Nullable
    private final String client;

    /**
     * Name of the author or artist of the media/work.
     */
    @SerializedName("author")
    @Nullable
    private final String author;

    /**
     * Title of the media/work.
     * Example: Episode #123
     */
    @SerializedName("title")
    @NonNull
    private final String title;

    /**
     * Title of the media/work.
     * Example: Radio-t
     */
    @SerializedName("podcastName")
    @NonNull
    private final String podcastName;

    /**
     * Name of the publisher of the media/work related to the request
     * (this may be different than the author).
     */
    @SerializedName("publisher")
    @Nullable
    private final String publisher;

    /**
     * A number like 1 or 1.5 that indicates the relative speed of playback of the media,
     * where 1 = normal speed and values above or below 1 indicate a speed/rate change
     * in relation to the normal value of 1.
     * Zero (0) is not a valid value.
     */
    @SerializedName("playbackRate")
    @Nullable
    private final Double playbackRate;

    /**
     * A number between 0 and 1 (where 0 = 0% and 1 = 100%) that indicates
     * the volume setting of the media. Example: 0.75 = 75% volume.
     */
    @SerializedName("volume")
    @Nullable
    private final Double volume;

    /**
     * Indication of if the media is muted.
     * For example the usual volume setting of the media may be at 1 (100%),
     * however the client has the media muted.
     */
    @NonNull
    @SerializedName("muted")
    private final Boolean muted;

    /**
     * Indication of if the media is paused.
     */
    @Nullable
    @SerializedName("paused")
    private final Boolean paused;

    /**
     * Current time of the client/user in media playback in seconds.
     */
    @NonNull
    @SerializedName("currentTime")
    private final Double currentTime;

    /**
     * Length of in media in seconds.
     */
    @NonNull
    @SerializedName("duration")
    private final Double duration;

    /**
     * An ISO 8601 compatible date/time stamp in UTC of the time the event occurred.
     * Example: 2016-10-16T00:23:13.411Z
     */
    @NonNull
    @SerializedName("time")
    private final Date time;

    private PauseEvent(@Nullable String client,
                       @Nullable String author,
                       @NonNull String title,
                       @NonNull String podcastName,
                       @Nullable String publisher,
                       @Nullable Double playbackRate,
                       @Nullable Double volume,
                       @Nullable Boolean muted,
                       @Nullable Boolean paused,
                       @NonNull Double currentTime,
                       @NonNull Double duration) {
        this.name = "media.pause";
        this.nonce = NonceGenerator.generate();
        this.client = client;
        this.author = author;
        this.title = Objects.requireNonNull(title, "title");
        this.podcastName = Objects.requireNonNull(podcastName, "podcastName");
        this.publisher = publisher;
        this.playbackRate = playbackRate != null ? playbackRate : 1;
        this.volume = volume != null ? volume : 1;
        this.muted = muted != null ? muted : Boolean.FALSE;
        this.paused = paused != null ? paused : Boolean.FALSE;
        this.currentTime = Objects.requireNonNull(currentTime, "currentTime");
        this.duration = Objects.requireNonNull(duration, "duration");
        this.time = new Date();
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getNonce() {
        return nonce;
    }

    @Nullable
    public String getClient() {
        return client;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getPodcastName() {
        return podcastName;
    }

    @Nullable
    public String getPublisher() {
        return publisher;
    }

    @Nullable
    public Double getPlaybackRate() {
        return playbackRate;
    }

    @Nullable
    public Double getVolume() {
        return volume;
    }

    @NonNull
    public Boolean getMuted() {
        return muted;
    }

    @Nullable
    public Boolean getPaused() {
        return paused;
    }

    @NonNull
    public Double getCurrentTime() {
        return currentTime;
    }

    @NonNull
    public Double getDuration() {
        return duration;
    }

    @NonNull
    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "PauseEvent{" +
                "name='" + name + '\'' +
                ", nonce='" + nonce + '\'' +
                ", client='" + client + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", podcastName='" + podcastName + '\'' +
                ", publisher='" + publisher + '\'' +
                ", playbackRate=" + playbackRate +
                ", volume=" + volume +
                ", muted=" + muted +
                ", paused=" + paused +
                ", currentTime=" + currentTime +
                ", duration=" + duration +
                ", time=" + time +
                '}';
    }

    /**
     * @return builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder
     */
    public static class Builder {
        private String client;
        private String author;
        private String title;
        private String podcastName;
        private String publisher;
        private Double playbackRate;
        private Double volume;
        private Boolean muted;
        private Boolean paused;
        private Double currentTime;
        private Double duration;

        private Builder() {

        }

        public Builder setClient(String client) {
            this.client = client;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setTitle(@NonNull String title) {
            this.title = title;
            return this;
        }

        public Builder setPodcastName(@NonNull String podcastName) {
            this.podcastName = podcastName;
            return this;
        }

        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder setPlaybackRate(Double playbackRate) {
            this.playbackRate = playbackRate;
            return this;
        }

        public Builder setVolume(Double volume) {
            this.volume = volume;
            return this;
        }

        public Builder setMuted(Boolean muted) {
            this.muted = muted;
            return this;
        }

        public Builder setPaused(Boolean paused) {
            this.paused = paused;
            return this;
        }

        public Builder setCurrentTime(@NonNull Double currentTime) {
            this.currentTime = currentTime;
            return this;
        }

        public Builder setDuration(@NonNull Double duration) {
            this.duration = duration;
            return this;
        }

        public PauseEvent create() {
            return new PauseEvent(client, author, title, podcastName, publisher, playbackRate, volume,
                    muted, paused, currentTime, duration);
        }
    }
}
