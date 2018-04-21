package podcast.statistics.collector.api;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Test;

import podcast.statistics.collector.GsonCreator;

public class PlayEventTest {

    private final static Gson GSON = GsonCreator.getGSON();

    @Test
    public void testMinSerialization() {
        PlayEvent event = PlayEvent.builder()
                .setTitle("Episode 134")
                .setPodcastName("Radiot podcast name")
                .setDuration(63.2)
                .setCurrentTime(0.01)
                .create();

        String json = GSON.toJson(event);
        Assert.assertNotNull(json);

        PlayEvent playEvent = GSON.fromJson(json, PlayEvent.class);
        Assert.assertEquals(event.getName(), "media.play");
        Assert.assertEquals(event.getTitle(), playEvent.getTitle());
        Assert.assertEquals(event.getPodcastName(), playEvent.getPodcastName());
        Assert.assertEquals(event.getDuration(), playEvent.getDuration());
        Assert.assertEquals(event.getCurrentTime(), playEvent.getCurrentTime());
    }

    @Test
    public void testMaxSerialization() {
        PlayEvent event = PlayEvent.builder()
                .setTitle("Episode 134")
                .setPodcastName("Radiot podcast name")
                .setDuration(63.2)
                .setCurrentTime(0.01)
                .setAuthor("Author")
                .setClient("Client")
                .setVolume(23.3)
                .setPublisher("Publisher")
                .setPlaybackRate(1.5)
                .setPaused(Boolean.TRUE)
                .setMuted(Boolean.TRUE)
                .create();

        String json = GSON.toJson(event);
        Assert.assertNotNull(json);

        PlayEvent playEvent = GSON.fromJson(json, PlayEvent.class);
        Assert.assertEquals(event.getName(), "media.play");
        Assert.assertEquals(event.getTitle(), playEvent.getTitle());
        Assert.assertEquals(event.getPodcastName(), playEvent.getPodcastName());
        Assert.assertEquals(event.getDuration(), playEvent.getDuration());
        Assert.assertEquals(event.getAuthor(), playEvent.getAuthor());
        Assert.assertEquals(event.getClient(), playEvent.getClient());
        Assert.assertEquals(event.getVolume(), playEvent.getVolume());
        Assert.assertEquals(event.getPublisher(), playEvent.getPublisher());
        Assert.assertEquals(event.getPlaybackRate(), playEvent.getPlaybackRate());
        Assert.assertEquals(event.getPaused(), playEvent.getPaused());
        Assert.assertEquals(event.getMuted(), playEvent.getMuted());
        Assert.assertEquals(event.getCurrentTime(), playEvent.getCurrentTime());
        Assert.assertNotNull(playEvent.getNonce());
    }
}