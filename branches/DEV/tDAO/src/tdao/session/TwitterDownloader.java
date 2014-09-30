//https://dev.twitter.com/docs/streaming-apis/parameters#locations get any geotagged tweet
//https://raw.githubusercontent.com/yusuke/twitter4j/master/twitter4j-examples/src/main/java/twitter4j/examples/stream/PrintSampleStream.java

package tdao.session;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tdao.entities.Users;
import twitter4j.*;
import tdao.controllers.KeywordsController;
import twitter4j.conf.ConfigurationBuilder;
import tdao.entities.Tweet;


public class TwitterDownloader {
    
        private List<Users> users = new ArrayList<Users>();
        private List<Tweet> tweets = new ArrayList<Tweet>();
    
        private ConfigurationBuilder _cb = new ConfigurationBuilder();
        private  TwitterStream _twitterStream = new TwitterStreamFactory(_cb.build()).getInstance();
        

      
    public boolean estabConnection()
    {
         _cb.setDebugEnabled(true);
         _cb.setOAuthConsumerKey("VwbqJqEWEGPLI9ZeaeRv8g");
         _cb.setOAuthConsumerSecret("WumU4D31KZSaESmq0ju82bXSnvC1e7Q64AV6GmBDCGo");
         _cb.setOAuthAccessToken("988434943-rIdQJQUPnSgRCsoQlbYJCOOAfZ0pe1XUaz8H2RVp");
         _cb.setOAuthAccessTokenSecret("fftTwvK3K0Ixejhc4007JV6yaXv3KscFpkNsEN6Zc");
         return true;
    }
    public void download( List<String> keywords )
    {
         //Twitter twitter = new TwitterFactory().getInstance();   
        
          
            StatusListener listener = new StatusListener() {
                @Override
                public void onStatus(Status status) {               
                    UsersManager usermanager = new UsersManagerImpl();
                    Users users = new Users();
                    usermanager.saveNewPerson(users);
                    System.out.println("@=======================>" + status.getUser().getScreenName() + " - " + status.getText() );

                    users.setContributorsEnabled(status.getUser().isContributorsEnabled());
                    users.setId(String.valueOf(status.getUser().getId()));
                    users.setName(status.getUser().getName());
                    users.setDefaultProfile(status.getUser().isDefaultProfile());
                    users.setCreatedAt(status.getUser().getCreatedAt().toString());
                    users.setFavouritesCount(status.getUser().getFavouritesCount());
                    users.setFollowersCount(status.getUser().getFollowersCount());
                    users.setFriendsCount(status.getUser().getFriendsCount());
                    users.setGeoEnabled(status.getUser().isGeoEnabled());
                    users.setIsTranslator(status.getUser().isTranslator());
                    users.setLang(status.getUser().getLang());
                    users.setListedCount(status.getUser().getListedCount());
                    users.setNDescription(status.getUser().getDescription());
                    users.setNFollowRequestSent(status.getUser().isFollowRequestSent());
                    users.setNLocation(status.getUser().getLocation());
                    //TODO Notifications Deprecated, delete from database
                    //users.setNNotifications(status.getUser().);
                    users.setNTimeZone(status.getUser().getTimeZone());
                    users.setNUrl(status.getUser().getURL());
                    users.setNUtcOffset(status.getUser().getUtcOffset());
                    users.setProfileBackgroundColor(status.getUser().getProfileBackgroundColor());
                    users.setProfileBackgroundImageUrl(status.getUser().getProfileBackgroundImageURL());
                    users.setProfileBackgroundImageUrlHttps(status.getUser().getProfileBackgroundImageUrlHttps());
                    //TODO delete from DB
                   // users.setProfileBackgroundTile(status.getUser().getProfileB);
                    users.setProfileBannerUrl(status.getUser().getProfileBannerURL());
                    users.setProfileImageUrl(status.getUser().getProfileImageURL());
                    users.setProfileImageUrlHttps(status.getUser().getProfileImageURLHttps());
                    users.setProfileLinkColor(status.getUser().getProfileLinkColor());
                    users.setProfileSidebarBorderColor(status.getUser().getProfileSidebarBorderColor());
                    users.setProfileSidebarFillColor(status.getUser().getProfileSidebarFillColor());
                    users.setProfileTextColor(status.getUser().getProfileTextColor());
                    //TODO does not exist on twitter4j
                   // users.setProfileUseBackgroundImage(status.getUser().getProfileUs);
                    users.setProtected_(status.getUser().isProtected());
                    users.setScreenName(status.getUser().getScreenName());
                    users.setStatusesCount(status.getUser().getStatusesCount());
                   // users.setTweets(null); ??
                    
                    users.setVerified(status.getUser().isVerified());                   
                    usermanager.saveNewPerson(users);     
                    
            
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
       
   _twitterStream.addListener(listener); 
   String[] stringArray = keywords.toArray(new String[keywords.size()]);
   //String[] keywordsArray = { "greece", "augh" };
   FilterQuery filterQuery = new FilterQuery();
   filterQuery.track(stringArray);   
   _twitterStream.filter(filterQuery);
   
    }
    
  
}