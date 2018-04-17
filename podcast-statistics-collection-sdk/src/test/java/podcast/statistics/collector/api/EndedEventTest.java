package podcast.statistics.collector.api;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Test;

import podcast.statistics.collector.GsonCreator;


public class EndedEventTest {

    private final static Gson GSON = GsonCreator.getGSON();

    @Test
    public void testMinSerialization() {
        EndedEvent event = EndedEvent.builder()
                .setTitle("Episode 134")
                .setPodcastName("Radiot podcast name")
                .setDuration(63.2)
                .setCurrentTime(0.01)
                .create();

        String json = GSON.toJson(event);
        Assert.assertNotNull(json);

        EndedEvent endedEvent = GSON.fromJson(json, EndedEvent.class);
        Assert.assertEquals(event.getName(), "media.ended");
        Assert.assertEquals(event.getTitle(), endedEvent.getTitle());
        Assert.assertEquals(event.getPodcastName(), endedEvent.getPodcastName());
        Assert.assertEquals(event.getDuration(), endedEvent.getDuration());
        Assert.assertEquals(event.getCurrentTime(), endedEvent.getCurrentTime());
    }

    @Test
    public void testMaxSerialization() {
        EndedEvent event = EndedEvent.builder()
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

        EndedEvent endedEvent = GSON.fromJson(json, EndedEvent.class);
        Assert.assertEquals(event.getName(), "media.ended");
        Assert.assertEquals(event.getTitle(), endedEvent.getTitle());
        Assert.assertEquals(event.getPodcastName(), endedEvent.getPodcastName());
        Assert.assertEquals(event.getDuration(), endedEvent.getDuration());
        Assert.assertEquals(event.getAuthor(), endedEvent.getAuthor());
        Assert.assertEquals(event.getClient(), endedEvent.getClient());
        Assert.assertEquals(event.getVolume(), endedEvent.getVolume());
        Assert.assertEquals(event.getPublisher(), endedEvent.getPublisher());
        Assert.assertEquals(event.getPlaybackRate(), endedEvent.getPlaybackRate());
        Assert.assertEquals(event.getPaused(), endedEvent.getPaused());
        Assert.assertEquals(event.getMuted(), endedEvent.getMuted());
        Assert.assertEquals(event.getCurrentTime(), endedEvent.getCurrentTime());
        Assert.assertNotNull(endedEvent.getNonce());
    }

}