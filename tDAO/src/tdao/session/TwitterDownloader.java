//https://dev.twitter.com/docs/streaming-apis/parameters#locations get any geotagged tweet
//https://raw.githubusercontent.com/yusuke/twitter4j/master/twitter4j-examples/src/main/java/twitter4j/examples/stream/PrintSampleStream.java
//http://stackoverflow.com/questions/11554672/getting-all-tweets-from-a-country-within-a-time-period-at-java
//http://stackoverflow.com/questions/18016532/stop-the-twitter-stream-and-return-list-of-status-with-twitter4j


package tdao.session;

import DTO.TweetDTO;
import java.util.ArrayList;
import twitter4j.*;



/** This class is responsible for download the stream of public tweets
 * 
 * @author Theodore Chrysochoidis
 * 
 */
public class TwitterDownloader
{       
    private final Object lock = new Object(); 
    public static int counter = 0;
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
    public TweetDTO download( final String[] keywords, final TweetDTO tweetDTO, TwitterStream twitterStream, final int miliseconds )
    {       
            final ArrayList< KeywordOccur > keys = new ArrayList<KeywordOccur>();
            
            for( int i = 0 ; i < keywords.length ; i++ )
            {
                KeywordOccur ko = new KeywordOccur();
                ko.setKeyword(keywords[i]);
                ko.setNumOfOccur(0);
                keys.add(ko);               
            }
            
            StatusListener listener = new StatusListener() 
            {              
               long t= System.currentTimeMillis();          
               long end = t+miliseconds;
              
       
                @Override         
                public void onStatus(Status status) 
                { 
                    
                   //for testing purpose
                   //System.out.println("Twitter User: " + status.getUser().getName() + " Tweet Text: " + status.getText() + "Created at: "+status.getCreatedAt() );
                   System.out.println( "Created at: "+status.getCreatedAt()+" Tweet Text: " + status.getText()  );                                
                 
                   
                   tweetContainsKeyword(keywords,status.getText(),keys );                 
                   
                   TweetDTO tempT = new TweetDTO();
                   tempT.setTweetText( status.getText() );
                   tempT.setCreator( status.getUser().getName() );
                   tweetDTO.addTweetDTO( tempT );                 
                   
                   //if the time window expires then stop streaming
                   //NEW: reload time window and check the number of occurences for each keyword
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
        String[] lang = { "en" };
        filterQuery.language(lang); 
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
         for ( int k = 0; k < keys.size(); k++ )
         {
              System.out.println("# "+keys.get(k).getKeyword()+"  is: "+ keys.get(k).getNumOfOccur());
         }
          System.out.println("Counter is: "+counter);
         return tweetDTO;
    }// end of download 
    
    /**
     * This method search for given keywords inside a twitter status
     * @param keywords
     *         Keywords to monitor (String[])
     * @param status
     *          The twitter Status (string)
     * @param keys
     *          
     * @return true/false
     */  
    public boolean tweetContainsKeyword( String[] keywords, String status, ArrayList< KeywordOccur >  keys  )
    { 
         String pattern = "[a-zA-Z0-9]*";
         //Έλεγχος εισόδου
         if ( 0 == keywords.length || 0 == status.length() || 0 == keys.size()  )
         {
             return false;
         }
         for ( int kword = 0; kword < keywords.length; kword++ )
         {
             if ( !keywords [kword].matches(pattern) )
                 return false;
         }
         
         int currentKeyword;  
         String statusCheck = status.toLowerCase(); 
         for ( currentKeyword = 0 ; currentKeyword < keywords.length; currentKeyword++ )
         {                                                          
            if ( statusCheck.contains( keywords[ currentKeyword ].toLowerCase() ) )
            {       
                if( keywords [ currentKeyword ].toLowerCase().contains( keys.get( currentKeyword ).getKeyword().toLowerCase() ) )
                { 
                    int occur = keys.get( currentKeyword ).getNumOfOccur();
                    occur++;
                    keys.get( currentKeyword ).setNumOfOccur( occur );                         
                }                                               
             }
            counter++;
          }
         return true;
    }
    
    public class KeywordOccur
    {
        String _keyword;
        int _numOfOccur;
        public void setKeyword( String keyword )
        {
            _keyword = keyword;
        }
        public void setNumOfOccur( int numOfOccur )
        {
            _numOfOccur = numOfOccur;
        }
        public String getKeyword()
        {
            return _keyword;
        }
        public int getNumOfOccur()
        {
            return _numOfOccur;
        }
    }
  
}//end of TwitterDownload