package podcast.statistics.collector.api;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Test;

import podcast.statistics.collector.GsonCreator;


public class PauseEventTest {

    private final static Gson GSON = GsonCreator.getGSON();

    @Test
    public void testMinSerialization() {
        PauseEvent event = PauseEvent.builder()
                .setTitle("Episode 134")
                .setPodcastName("Radiot podcast name")
                .setDuration(63.2)
                .setCurrentTime(0.01)
                .create();

        String json = GSON.toJson(event);
        Assert.assertNotNull(json);

        PauseEvent pauseEvent = GSON.fromJson(json, PauseEvent.class);
        Assert.assertEquals(event.getName(), "media.pause");
        Assert.assertEquals(event.getTitle(), pauseEvent.getTitle());
        Assert.assertEquals(event.getPodcastName(), pauseEvent.getPodcastName());
        Assert.assertEquals(event.getDuration(), pauseEvent.getDuration());
        Assert.assertEquals(event.getCurrentTime(), pauseEvent.getCurrentTime());
    }

    @Test
    public void testMaxSerialization() {
        PauseEvent event = PauseEvent.builder()
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

        PauseEvent pauseEvent = GSON.fromJson(json, PauseEvent.class);
        Assert.assertEquals(event.getName(), "media.pause");
        Assert.assertEquals(event.getTitle(), pauseEvent.getTitle());
        Assert.assertEquals(event.getPodcastName(), pauseEvent.getPodcastName());
        Assert.assertEquals(event.getDuration(), pauseEvent.getDuration());
        Assert.assertEquals(event.getAuthor(), pauseEvent.getAuthor());
        Assert.assertEquals(event.getClient(), pauseEvent.getClient());
        Assert.assertEquals(event.getVolume(), pauseEvent.getVolume());
        Assert.assertEquals(event.getPublisher(), pauseEvent.getPublisher());
        Assert.assertEquals(event.getPlaybackRate(), pauseEvent.getPlaybackRate());
        Assert.assertEquals(event.getPaused(), pauseEvent.getPaused());
        Assert.assertEquals(event.getMuted(), pauseEvent.getMuted());
        Assert.assertEquals(event.getCurrentTime(), pauseEvent.getCurrentTime());
        Assert.assertNotNull(pauseEvent.getNonce());
    }

}