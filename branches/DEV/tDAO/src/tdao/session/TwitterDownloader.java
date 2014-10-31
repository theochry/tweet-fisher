//https://dev.twitter.com/docs/streaming-apis/parameters#locations get any geotagged tweet
//https://raw.githubusercontent.com/yusuke/twitter4j/master/twitter4j-examples/src/main/java/twitter4j/examples/stream/PrintSampleStream.java

package tdao.session;
import DTO.TweetDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import tdao.entities.Users;
import twitter4j.*;
import tdao.controllers.KeywordsController;
import tdao.dao.Keywords;
import twitter4j.conf.ConfigurationBuilder;
import tdao.entities.Tweet;

//http://stackoverflow.com/questions/11554672/getting-all-tweets-from-a-country-within-a-time-period-at-java
//http://stackoverflow.com/questions/18016532/stop-the-twitter-stream-and-return-list-of-status-with-twitter4j


public class TwitterDownloader 
{       
        
        private final Object lock = new Object();    
      
//    public boolean estabConnection()
//    {
//         _cb.setDebugEnabled(true);
//         _cb.setOAuthConsumerKey("VwbqJqEWEGPLI9ZeaeRv8g");
//         _cb.setOAuthConsumerSecret("WumU4D31KZSaESmq0ju82bXSnvC1e7Q64AV6GmBDCGo");
//         _cb.setOAuthAccessToken("988434943-rIdQJQUPnSgRCsoQlbYJCOOAfZ0pe1XUaz8H2RVp");
//         _cb.setOAuthAccessTokenSecret("fftTwvK3K0Ixejhc4007JV6yaXv3KscFpkNsEN6Zc");
//         return true;
//    }
    
    
    public TweetDTO download( String[] keywords, final TweetDTO tweetDTO, TwitterStream twitterStream,final int miliseconds )//?d? ?a d?µ?????e?ta? t? tweetDTO, p?? ?a pe????e? t? tweet, user name etc
    {    	
            
            StatusListener listener = new StatusListener() { 
               
               long t= System.currentTimeMillis();          
               long end = t+miliseconds;
                @Override         
                public void onStatus(Status status) {  
                   
                    TweetDTO tempT = new TweetDTO();
//                    UsersManager usermanager = new UsersManagerImpl();//Database
//                    Users users = new Users();
//                    usermanager.saveNewPerson(users);
                    System.out.println("@=======================>" + status.getUser().getName() + " - " + status.getText() );

                   tempT.setTweetText(status.getText());
                   tempT.setCreator(status.getUser().getName());
                   tweetDTO.addTweetDTO(tempT);                 
                   
                   if(System.currentTimeMillis() > end) 
                   {
                    synchronized (lock) 
                    {
                       lock.notify();
                    }       
                   }      
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }       
            
        };// end of statusListener
       
   twitterStream.addListener(listener);   
   FilterQuery filterQuery = new FilterQuery();
   filterQuery.track(keywords);   
   twitterStream.filter(filterQuery);
     
    try {
      synchronized (lock) {
        lock.wait();
       
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("returning statuses");
    twitterStream.shutdown();
    tweetDTO.notifyAlls();
   System.out.println( "END AT :"+System.currentTimeMillis() );
   return tweetDTO;
    }
    
  
}