package podcast.statistics.collector;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Base64CoderTest {

    public Base64CoderTest() {

    }

    @Test
    public void decodeTest() {
        String payload = Base64Coder.decode("W3sibmFtZSI6Im1lZGlhLnBsYXkiLCJhdXRob3IiOiJKb25hdGhhbiBHaWxsIiwidGl0bGUiOiJFeGFtcGxlIFRpdGxlIiwicGxheWJhY2tSYXRlIjoxLCJ2b2x1bWUiOjEsIm11dGVkIjpmYWxzZSwicGF1c2VkIjpmYWxzZSwiY3VycmVudFRpbWUiOjIuOTk3NzI5LCJkdXJhdGlvbiI6MzU5OS41NzQ1MTMsImN1c3RvbV9wcm9wZXJ0eTEiOiJBbnl0aGluZyB5b3Ugd2FudCIsImN1c3RvbV9wcm9wZXJ0eTIiOjMzLjMzLCJ0aW1lIjoiMjAxNi0xMC0xNlQwMDoyMzoxMy40MTFaIn0seyJuYW1lIjoibWVkaWEudGltZXVwZGF0ZSIsImF1dGhvciI6IkpvbmF0aGFuIEdpbGwiLCJ0aXRsZSI6IkV4YW1wbGUgVGl0bGUiLCJwbGF5YmFja1JhdGUiOjEsInZvbHVtZSI6MSwibmV0d29ya1N0YXRlIjoxLCJyZWFkeVN0YXRlIjo0LCJtdXRlZCI6ZmFsc2UsInBhdXNlZCI6ZmFsc2UsImN1cnJlbnRUaW1lIjozLjE1NDQzNCwiZHVyYXRpb24iOjM1OTkuNTc0NTEzLCJ0aW1lIjoiMjAxNi0xMC0xNlQwMDoyMzoxMy40MTFaIn1d");
        Assert.assertEquals("[{\"name\":\"media.play\",\"author\":\"Jonathan Gill\",\"title\":\"Example Title\",\"playbackRate\":1,\"volume\":1,\"muted\":false,\"paused\":false,\"currentTime\":2.997729,\"duration\":3599.574513,\"custom_property1\":\"Anything you want\",\"custom_property2\":33.33,\"time\":\"2016-10-16T00:23:13.411Z\"},{\"name\":\"media.timeupdate\",\"author\":\"Jonathan Gill\",\"title\":\"Example Title\",\"playbackRate\":1,\"volume\":1,\"networkState\":1,\"readyState\":4,\"muted\":false,\"paused\":false,\"currentTime\":3.154434,\"duration\":3599.574513,\"time\":\"2016-10-16T00:23:13.411Z\"}]", payload);
    }

    @Test
    public void encodeTest() {
        String base64 = Base64Coder.encode("[{\"name\":\"media.play\",\"author\":\"Jonathan Gill\",\"title\":\"Example Title\",\"playbackRate\":1,\"volume\":1,\"muted\":false,\"paused\":false,\"currentTime\":2.997729,\"duration\":3599.574513,\"custom_property1\":\"Anything you want\",\"custom_property2\":33.33,\"time\":\"2016-10-16T00:23:13.411Z\"},{\"name\":\"media.timeupdate\",\"author\":\"Jonathan Gill\",\"title\":\"Example Title\",\"playbackRate\":1,\"volume\":1,\"networkState\":1,\"readyState\":4,\"muted\":false,\"paused\":false,\"currentTime\":3.154434,\"duration\":3599.574513,\"time\":\"2016-10-16T00:23:13.411Z\"}]");
        Assert.assertEquals("W3sibmFtZSI6Im1lZGlhLnBsYXkiLCJhdXRob3IiOiJKb25hdGhhbiBHaWxsIiwidGl0bGUiOiJFeGFtcGxlIFRpdGxlIiwicGxheWJhY2tSYXRlIjoxLCJ2b2x1bWUiOjEsIm11dGVkIjpmYWxzZSwicGF1c2VkIjpmYWxzZSwiY3VycmVudFRpbWUiOjIuOTk3NzI5LCJkdXJhdGlvbiI6MzU5OS41NzQ1MTMsImN1c3RvbV9wcm9wZXJ0eTEiOiJBbnl0aGluZyB5b3Ugd2FudCIsImN1c3RvbV9wcm9wZXJ0eTIiOjMzLjMzLCJ0aW1lIjoiMjAxNi0xMC0xNlQwMDoyMzoxMy40MTFaIn0seyJuYW1lIjoibWVkaWEudGltZXVwZGF0ZSIsImF1dGhvciI6IkpvbmF0aGFuIEdpbGwiLCJ0aXRsZSI6IkV4YW1wbGUgVGl0bGUiLCJwbGF5YmFja1JhdGUiOjEsInZvbHVtZSI6MSwibmV0d29ya1N0YXRlIjoxLCJyZWFkeVN0YXRlIjo0LCJtdXRlZCI6ZmFsc2UsInBhdXNlZCI6ZmFsc2UsImN1cnJlbnRUaW1lIjozLjE1NDQzNCwiZHVyYXRpb24iOjM1OTkuNTc0NTEzLCJ0aW1lIjoiMjAxNi0xMC0xNlQwMDoyMzoxMy40MTFaIn1d", base64);
    }
}