import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;
import play.db.jpa.JPA;

public class BasicTest extends UnitTest {

    @After
    public void rollbackTx() {
        JPA.setRollbackOnly();
    }
    
    @Test
    public void testCreateAndRetrieveUser() {
        String userName = "nik-test";
        User user = new User(userName);
        user.save();
        List<Tweet> tweets = Arrays.asList(new Tweet("Hello, playframework!", new Date(), user), 
            new Tweet("It's my second post.", new Date(), user));
        for (Tweet tweet : tweets) {
            tweet.save();
        }
        
        JPA.em().clear();
        User nik = User.find("byName", userName).first();
        assertNotNull(nik);
        assertEquals("nik-test", nik.name);
        
        List<Tweet> actualTweets = Tweet.find("author.name = ?", userName).fetch();
        assertEquals(tweets.size(), actualTweets.size());
        assertEquals(user.id, tweets.get(0).author.id);
        
        System.out.println("asd");
        
        assertNotNull(nik.tweets);
        assertEquals(nik.tweets.size(), actualTweets.size());
        assertEquals(nik.tweets.get(0).author, actualTweets.get(0).author);
    }
}
