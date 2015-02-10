//https://dev.twitter.com/docs/streaming-apis/parameters#locations get any geotagged tweet
//https://raw.githubusercontent.com/yusuke/twitter4j/master/twitter4j-examples/src/main/java/twitter4j/examples/stream/PrintSampleStream.java
//http://stackoverflow.com/questions/11554672/getting-all-tweets-from-a-country-within-a-time-period-at-java
//http://stackoverflow.com/questions/18016532/stop-the-twitter-stream-and-return-list-of-status-with-twitter4j


package tfisher.session;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.HashMap;
import java.util.Iterator;
import org.springframework.stereotype.Component;
import tfisher.interfaces.ITwitterDownloader;
import tfisher.dao.Keywords;
import tfisher.entities.Media;
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
    private long startTime;        
    private long endTime;
    private long timeWindow;   
    private final FilterQuery filterQuery = new FilterQuery();   
    
    //_keywordStatusMap περιέχει ολόκληρο το status για το συγκεκριμένο keyword, ένα keyword μπορεί να υπάρχει πολλές φορές με διαφορετικό status
    private Multimap<String, Status> _keywordStatusMap = HashMultimap.create();
    private final HashMap<String, Integer> counterKeywords = new HashMap<String, Integer>();   
    
    //For run()  
    private TwitterStream _twitterStream;   
    private  Keywords _keywords;
    private User _user;
    private Tweet _tweet;
    private Media _media;
  
    public TwitterDownloader(){}
    public TwitterDownloader( TwitterStream twitterStream, final Keywords keywords, User user, Tweet tweet, Media media )
    {
         _twitterStream = twitterStream;  _keywords = keywords; _user = user;
        _tweet = tweet; _media = media;
    }
    
    /**
     * This method downloads the public sample of tweets     
     * @param twitterStream
     *      The stream of public tweets (TwitterStream)
     * @param keywords
     * @param user
     * @param tweet
     * @param media
     
     */   
    public boolean download(  TwitterStream twitterStream, final Keywords keywords,final User user, final Tweet tweet, final Media media ) 
    {  
                
        initCounterOfKeywords (keywords);
        startTime = System.currentTimeMillis();     
        timeWindow = startTime + keywords.getTimeInterval();           
        StatusListener listener = new StatusListener() 
        {
            @Override         
            public void onStatus(Status status) 
            {           
               //Για κάθε νέο keyword και κάθε φορά που λήγει το παράθυρο, ανανεώνονται τα keywords που δεν έφτασαν το όριο των εμφανίσεων
               for( int currentKeyword = 0; currentKeyword < keywords.getKeywords().size(); currentKeyword++ )
               {
                   String keyword = keywords.getKeywords().get( currentKeyword );
                   if ( null == counterKeywords.get(keyword))
                   {
                       addNewKeyword(keyword);                      
                   }                   
               }                      
                //for testing purpose
                System.out.println("Twitter User: " + status.getUser().getName() + " Tweet Text: " + status.getText() + "Created at: "+status.getCreatedAt() );
                String containedKeyword = tweetContainsKeyword( keywords,status.getText() );    
                _keywordStatusMap.put( containedKeyword, status );       
                 
                //Κάθε φορά που λήγει το παράθυρο, έλεγξε ποια keywords έφτασαν τον αριθμό εμφανίσεων και αποθήκευσέ τα.
                //Μετά ανανέωσε το παράθυρο.
                if ( System.currentTimeMillis() > timeWindow )
                {     
                    for ( int curKeyword = 0; curKeyword < keywords.getKeywords().size(); curKeyword++ )
                    {
                        String check = checkOccurences(keywords.getOccurences());
                        System.out.println("keyword check is: "+check);
                        if ( null != check )
                        {
                            manageStoreStatuses(check, user, tweet, media); 
                            counterKeywords.remove( check );
                            _keywordStatusMap.removeAll(check);
                        }   
                    }    
                    timeWindow = System.currentTimeMillis() + keywords.getTimeInterval();                    
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
                if (ex.getMessage().startsWith("420")) 
                {                 
                     
                }
            }       
            
        };// end of statusListener                  
     
        twitterStream.addListener(listener);            
        String[] lang = { "en" };
        filterQuery.language(lang); 
        String [] criteria = getArrayOfKeywords( keywords );
        filterQuery.track( criteria );   
        twitterStream.filter(filterQuery);          
        return true;
         
    }// end of download 
    
   /**
    * This method manages the store proccess of statuses
    * @param keyword
    * @param user
    * @param tweet
    * @param media 
     * @return  true/false
    */ 
    public boolean manageStoreStatuses(String keyword, User user, Tweet tweet, Media media)
    {       
        if ( keyword.isEmpty() || user == null || tweet == null || media == null )
        {
            return false;
        }
        
        for(Status value : _keywordStatusMap.get(keyword)) 
        {           
            storeUser(value,user);   
            storeTweet(value,tweet,user);  
             if ( value.getMediaEntities().length >= 1 )
             {                 
                storeMediaEntities(value, media, tweet);
             }
        }       
        _keywordStatusMap.removeAll(keyword);
        counterKeywords.remove(keyword);
       
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++ COUNTER");
                for ( String value: counterKeywords.keySet() )
                {
                    System.out.println("counterKeywords are "+value);
                }
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++ keywordsMap");
                for ( String value: _keywordStatusMap.keySet() )
                {
                    System.out.println("keywords map are "+value);
                }
        return true;
    }

    
    public boolean addNewKeyword( String keyword )
    {
        if ( keyword.isEmpty() )
        {
            return false;
        }
        counterKeywords.put(keyword, 0); 
        return true;
       
    }
    public boolean storeUser ( Status status, User user )
    {
        if ( status.getText().isEmpty() || user == null )
        {
            return false;
        }
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
        return true;
    }
    
    public boolean storeTweet(Status status, Tweet tweet, User user)
    {    
        if ( status.getText().isEmpty() || user == null || tweet == null )
        {
            return false;
        }
        tweet.setCreatedAt(String.valueOf(status.getCreatedAt()));       
        tweet.setIdStr(String.valueOf(status.getId()));
        tweet.setNLang(status.getLang());
        tweet.setRetweetCount(status.getRetweetCount());
        tweet.setSource(status.getSource());
        tweet.setText(status.getText());
        tweet.setUser(user);
        tweet.changeState();
        return true;
    }
    
    public boolean storeMediaEntities( Status status, Media media, Tweet tweet )
    {      
        if ( status.getText().isEmpty() || media == null || tweet == null )
        {
            return false;
        }
         for (MediaEntity mediaEntity : status.getMediaEntities()) 
         {          
             media.setMediaUrl(mediaEntity.getMediaURL()); 
             media.setIdStr(String.valueOf(mediaEntity.getId()));            
             media.setType( mediaEntity.getType());  
             media.setTweet(tweet);
             media.changeState();
         }  
         return true;
    }
    
    public String checkOccurences( int occurences )
    {
        Iterator<String> counterKeywordsIterator = counterKeywords.keySet().iterator();
        while( counterKeywordsIterator.hasNext() )
        {
          String key = counterKeywordsIterator.next();  
          System.out.println("To keyword "+key+" exei emfaniseis # "+counterKeywords.get(key));
          if ( counterKeywords.get(key) >= occurences )
          {         
             return key;
          }         
        }
        return null;
    }     
    
    /**
     * This method search for given keywords inside a twitter status
     * @param keywords
     *         Keywords to monitor (String[])
     * @param status
     *          The twitter Status (string)
     *          
     * @return String [the contained keyword]
     */  
    public String tweetContainsKeyword( Keywords keywords, String status  )
    { 
        
         String keyword = "";
         int currentKeyword;  
         String statusCheck = status.toLowerCase(); 
         //Έλεγχος εισόδου
         if ( 0 == keywords.getKeywords().size()|| 0 == status.length()   )
         {
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
     * This method initialize the hashmap ( counter )of keywords with 0
     * @param keywords
     */
    public boolean initCounterOfKeywords( Keywords keywords )
    {
        if ( keywords.getArrayOfKeywords().length == 0 )
        {
            return false;
        }
        String[] keywordsArray = getArrayOfKeywords ( keywords );        
        for ( int kword = 0; kword < keywordsArray.length; kword++ )
        {
            counterKeywords.put(keywordsArray[kword], 0);            
        }
        return true;
    }
    
    public String[] getArrayOfKeywords( Keywords keywordsObject )
    {       String[] keywordsArray = new String[ keywordsObject.getKeywords().size() ];
        return keywordsObject.getKeywords().toArray( keywordsArray );
    }    
    
    
    @Override
    public void run() 
    {
        download( _twitterStream, _keywords, _user, _tweet, _media);
    } 
}//end of TwitterDownload