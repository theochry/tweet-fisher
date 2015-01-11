//https://dev.twitter.com/docs/streaming-apis/parameters#locations get any geotagged tweet
//https://raw.githubusercontent.com/yusuke/twitter4j/master/twitter4j-examples/src/main/java/twitter4j/examples/stream/PrintSampleStream.java
//http://stackoverflow.com/questions/11554672/getting-all-tweets-from-a-country-within-a-time-period-at-java
//http://stackoverflow.com/questions/18016532/stop-the-twitter-stream-and-return-list-of-status-with-twitter4j


package tfisher.session;

import DTO.TweetDTO;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.springframework.stereotype.Component;
import tfisher.interfaces.ITwitterDownloader;
import tfisher.dao.Keywords;
import tfisher.entities.Tweet;
import twitter4j.*;
import twitter4j.Status;
import tfisher.entities.User;



/** This class is responsible for download the stream of public tweets
 * 
 * @author Theodore Chrysochoidis
 * 
 */
@Component
public class TwitterDownloader implements Runnable, ITwitterDownloader
{       
    private final Object lock = new Object();     
    private long startTime;        
    private long endTime;
    private long timeWindow; 
    private  TweetDTO statuses;
    private final FilterQuery filterQuery = new FilterQuery();
    private UserModelHibernateImpl userManager = new UserModelHibernateImpl();
    private TweetModelHibernateImpl tweetManager = new TweetModelHibernateImpl();
    
    //περιέχει ολόκληρο το status για το συγκεκριμένο keyword, ένα keyword μπορεί να υπάρχει πολλές φορές με διαφορετικό status
    private Multimap<String, Status> _keywordStatusMap = HashMultimap.create();
    private final HashMap<String, Integer> counterKeywords = new HashMap<String, Integer>();
   
    
    //For run()    
    private  TweetDTO _tweetDTO;
    private TwitterStream _twitterStream;
    private  int _miliseconds;
    private  Keywords _keywords;
    private User _user;
    private Tweet _tweet;
  
    public TwitterDownloader(){}
    public TwitterDownloader( final TweetDTO tweetDTO, TwitterStream twitterStream, final int miliseconds, final Keywords keywords, User user, Tweet tweet )
    {
        _tweetDTO = tweetDTO; _twitterStream = twitterStream; _miliseconds = miliseconds; _keywords = keywords; _user = user;
        _tweet = tweet;
    }
    
    /**
     * This method downloads the public sample of tweets     
     * @param tweetDTO
     *      At the end of the download contains the results (TweetDTO)
     * @param twitterStream
     *      The stream of public tweets (TwitterStream)
     * @param miliseconds
     *      Time window (int)
     * @param keywords
     * @param user
     * @param tweet
     * @return TweetDTO with downloaded tweets
     */   
    public boolean download(  final TweetDTO tweetDTO, TwitterStream twitterStream, final int miliseconds, final Keywords keywords,final User user, final Tweet tweet ) 
    {      
        initHashMapOfKeywords (keywords);
        startTime = System.currentTimeMillis();          
        endTime = startTime + miliseconds;
        timeWindow = startTime + keywords.getTimeInterval();       
           
        StatusListener listener = new StatusListener() 
        {
            @Override         
            public void onStatus(Status status) 
            { 
                ArrayList<String> reachedKeywords = new ArrayList<String>();                  
                //for testing purpose
                //System.out.println("Twitter User: " + status.getUser().getName() + " Tweet Text: " + status.getText() + "Created at: "+status.getCreatedAt() );
               
                
                String containedKeyword = tweetContainsKeyword( keywords,status.getText() );    
                _keywordStatusMap.put(containedKeyword, status);       
                  
                if ( System.currentTimeMillis() > timeWindow )
                {     
                    for ( int i = 0; i < keywords.getKeywords().size(); i++ )
                    {
                        String check = checkOccurences(keywords.getOccurences());
                        if ( null != check )
                        {
                            storeStatuses(check, user, tweet);
                            
                        }
                    }                   
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
         //reachedKeywordsTweetDto ( tweetDTO, keywords.getOccurences() );       
         //tweetDTO.notifyAllObservers();        
         return true;
         
    }// end of download 
    
    
    public void storeStatuses(String keyword, User user, Tweet tweet)
    {       
        for(Status value : _keywordStatusMap.get(keyword)) 
        {
            storeUser(value,user);   
            storeTweet(value,tweet,user);
        }
    }

    public void storeUser ( Status status, User user )
    {
        user.setCreatedAt(status.getUser().getCreatedAt().toString());
        user.setFollowersCount(status.getUser().getFollowersCount());
        user.setFriendsCount(status.getUser().getFriendsCount());
        user.setIdStr(String.valueOf(status.getUser().getId()));
        user.setLang(status.getUser().getLang());
        user.setNTimeZone(status.getUser().getTimeZone());
        user.setNUtcOffset(1);              
        user.setName(status.getUser().getName());              
        user.setScreenName(status.getUser().getScreenName());
        user.setStatusesCount(status.getUser().getStatusesCount()); user.setNTimeZone(status.getUser().getTimeZone());              
        user.changeState();              
    }
    
    public void storeTweet(Status status, Tweet tweet, User user)
    {
        tweet.setCreatedAt(String.valueOf(status.getCreatedAt()));
        tweet.setIdStr(String.valueOf(status.getId()));
        tweet.setNLang(status.getLang());
        tweet.setRetweetCount(status.getRetweetCount());
        tweet.setSource(status.getSource());
        tweet.setText(status.getText());
        tweet.setUser(user);
        tweet.changeState();
    }
    
    public String checkOccurences(int occurences)
    {
        Iterator<String> counterKeywordsIterator = counterKeywords.keySet().iterator();
        while( counterKeywordsIterator.hasNext() )
        {
          String key = counterKeywordsIterator.next();          
          if ( counterKeywords.get(key) <= occurences )
          {        
             return key;
          }         
        }
        return null;
    }
    
   
    
    
    public void deleteDuplicateStatuses( Multimap<String, Status> keywordStatusMap, HashMap<String, Integer> counterKeywords, int occurences )
    {
        Iterator<String> counterKeywordsIterator = counterKeywords.keySet().iterator();
        while( counterKeywordsIterator.hasNext() )
        {
          String key = counterKeywordsIterator.next();          
          if ( counterKeywords.get(key) < occurences )
          {        
             keywordStatusMap.removeAll(key);
          }         
        }
    }
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
    }//end of reachedKeywordsTweetDto
    
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
    
    
    @Override
    public void run() {
        download(  _tweetDTO,_twitterStream,_miliseconds, _keywords, _user, _tweet);
    }
  
}//end of TwitterDownload