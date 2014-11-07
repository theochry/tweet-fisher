//https://dev.twitter.com/docs/streaming-apis/parameters#locations get any geotagged tweet
//https://raw.githubusercontent.com/yusuke/twitter4j/master/twitter4j-examples/src/main/java/twitter4j/examples/stream/PrintSampleStream.java
//http://stackoverflow.com/questions/11554672/getting-all-tweets-from-a-country-within-a-time-period-at-java
//http://stackoverflow.com/questions/18016532/stop-the-twitter-stream-and-return-list-of-status-with-twitter4j


package tdao.session;
import DTO.TweetDTO;
import twitter4j.*;



/** This class is responsible for download the stream of public tweets
 * 
 * @author Theodore Chrysochoidis
 * 
 */
public class TwitterDownloader
{       
    private final Object lock = new Object();  
    /**
     * This method downloads the public sample of tweets
     * @param keywords
     *      Keywords to monitor (String[])
     * @param tweetDTO
     *      At the end of the download contains the results (TweetDTO)
     * @param twitterStream
     *      The stream of public tweets (TwitterStream)
     * @param miliseconds
     *      Time window (int)
     * @return TweetDTO with downloaded tweets
     */
    public TweetDTO download( String[] keywords, final TweetDTO tweetDTO, TwitterStream twitterStream, final int miliseconds )
    {        
            StatusListener listener = new StatusListener() 
            {              
               long t= System.currentTimeMillis();          
               long end = t+miliseconds;
               
                @Override         
                public void onStatus(Status status) 
                {                  
                   //for testing purpose
                   System.out.println("Twitter User: " + status.getUser().getName() + " Tweet Text: " + status.getText() );
                   
                   
                   TweetDTO tempT = new TweetDTO();
                   tempT.setTweetText( status.getText() );
                   tempT.setCreator( status.getUser().getName() );
                   tweetDTO.addTweetDTO( tempT );                 
                   
                   //if the time window expires then stop streaming
                   if( System.currentTimeMillis() > end ) 
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
           e.printStackTrace();
         }
         System.out.println("returning statuses");
         twitterStream.shutdown();
         tweetDTO.notifyAlls();
         return tweetDTO;
    }// end of download    
}//end of TwitterDownload