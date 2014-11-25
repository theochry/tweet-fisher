//https://dev.twitter.com/docs/streaming-apis/parameters#locations get any geotagged tweet
//https://raw.githubusercontent.com/yusuke/twitter4j/master/twitter4j-examples/src/main/java/twitter4j/examples/stream/PrintSampleStream.java
//http://stackoverflow.com/questions/11554672/getting-all-tweets-from-a-country-within-a-time-period-at-java
//http://stackoverflow.com/questions/18016532/stop-the-twitter-stream-and-return-list-of-status-with-twitter4j


package tdao.session;

import DTO.TweetDTO;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import tdao.dao.Keywords;
import twitter4j.*;



/** This class is responsible for download the stream of public tweets
 * 
 * @author Theodore Chrysochoidis
 * 
 */
public class TwitterDownloader
{       
    private final Object lock = new Object(); 
    private final HashMap<String, Integer> counterKeywords = new HashMap<String, Integer>();
    private long startTime;        
    private long endTime;
    private long timeWindow; 
    private  TweetDTO statuses;
    private final FilterQuery filterQuery = new FilterQuery();

    /**
     * This method downloads the public sample of tweets     
     * @param tweetDTO
     *      At the end of the download contains the results (TweetDTO)
     * @param twitterStream
     *      The stream of public tweets (TwitterStream)
     * @param miliseconds
     *      Time window (int)
     * @return TweetDTO with downloaded tweets
     */   
    public TweetDTO download(  final TweetDTO tweetDTO, TwitterStream twitterStream, final int miliseconds, final Keywords keywords )
    {      
        initHashMapOfKeywords (keywords);
        startTime = System.currentTimeMillis();          
        endTime = startTime + miliseconds;
        timeWindow =  startTime + keywords.getTimeInterval();       
           
        StatusListener listener = new StatusListener() 
        {
            @Override         
            public void onStatus(Status status) 
            { 
                ArrayList<String> reachedKeywords = new ArrayList<String>();                  
                //for testing purpose
                //System.out.println("Twitter User: " + status.getUser().getName() + " Tweet Text: " + status.getText() + "Created at: "+status.getCreatedAt() );
                System.out.println( "Created at: "+status.getCreatedAt()+" Tweet Text: " + status.getText()  );              
                String containedKeyword = tweetContainsKeyword( keywords,status.getText() );             
               
                //Αρχικά μπαίνουν όλα τα statuses, μετά ελέγχεται ποια από αυτά έχουν τα ανάλογα occurences και όσα δεν έχουν, διαγράφονται
                //και παραμένουν μόνο αυτά που έχουν
                statuses  = new TweetDTO();
                statuses.setTweetText( status.getText() );
                statuses.setCreator( status.getUser().getName() );
                if ( containedKeyword!= "" )
                    tweetDTO.getTweetDtoMultiHash().put(containedKeyword, statuses);               
                
                  
                if ( System.currentTimeMillis() > timeWindow )
                {                                
                     timeWindow = System.currentTimeMillis() + keywords.getTimeInterval();                    
                }
                //for testing purpose
                if( System.currentTimeMillis() > endTime ) 
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
        
        String[] lang = { "en" };
        filterQuery.language(lang); 
        String [] criteria = getArrayOfKeywords( keywords );
        filterQuery.track( criteria );   
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
         reachedKeywordsTweetDto ( tweetDTO, keywords.getOccurences() );       
         tweetDTO.notifyAllObservers();        
         return tweetDTO;
         
    }// end of download 
    
    /**
     * This method get the TweetDTO object and the number of desired occurences
     * for each keyword. Then searches the TweetDTO MultiMap. If a key of the Multimap has less occurences, then the whole key deleted
     * @param tweetDto
     * @param occurences 
     */
    public void reachedKeywordsTweetDto( TweetDTO tweetDto, int occurences )
    {       
        Multimap<String, TweetDTO> tweetDtoIterator = tweetDto.getTweetDtoMultiHash();    
        Iterator<String> counterKeywordsIterator = counterKeywords.keySet().iterator();         
        while( counterKeywordsIterator.hasNext() )
        {
          String key = counterKeywordsIterator.next();          
          if ( counterKeywords.get(key) < occurences )
          {        
             tweetDtoIterator.removeAll(key);
          }         
        }
    }//end of reloadTweetDto
    
    /**
     * This method search for given keywords inside a twitter status
     * @param keywords
     *         Keywords to monitor (String[])
     * @param status
     *          The twitter Status (string)
     * @param keys
     *          
     * @return String [the contained keyword]
     */  
    public String tweetContainsKeyword( Keywords keywords, String status  )
    { 
         String pattern = "[a-zA-Z0-9#]*";
         String keyword = "";
         int currentKeyword;  
         String statusCheck = status.toLowerCase(); 
         //Έλεγχος εισόδου
         if ( 0 == keywords.getKeywords().size()|| 0 == status.length()   )
         {
             return "";
         }
         for ( int kword = 0; kword < keywords.getKeywords().size(); kword++ )
         {
             if ( !keywords.getKeywords().get(kword).matches( pattern ) )
                return "";
         }         
         for ( currentKeyword = 0 ; currentKeyword < keywords.getKeywords().size(); currentKeyword++ )
         {                                                          
            if ( statusCheck.contains( keywords.getKeywords().get( currentKeyword ).toLowerCase() ) )
            {    
                //Εάν υπάρχει το keyword στο status, τότε βάλ'το στη λίστα με τα keywords, και αύξησε το μετρητή του keyword
                keyword = keywords.getKeywords().get( currentKeyword );
                counterKeywords.put( keyword, counterKeywords.get(keyword) + 1);
            }           
          }
         return keyword;        
    }
    /**
     * This method initialize the hashmap of keywords
     * @param keywords 
     */
    
    public void initHashMapOfKeywords( Keywords keywords )
    {
        String[] keywordsArray = getArrayOfKeywords ( keywords );        
        for ( int kword = 0; kword < keywordsArray.length; kword++ )
        {
            counterKeywords.put(keywordsArray[kword], 0);
        }
    }
    
    public String[] getArrayOfKeywords( Keywords keywordsObject )
    {
        String[] keywordsArray = new String[ keywordsObject.getKeywords().size() ];
        return keywordsObject.getKeywords().toArray( keywordsArray );
    }       
  
}//end of TwitterDownload