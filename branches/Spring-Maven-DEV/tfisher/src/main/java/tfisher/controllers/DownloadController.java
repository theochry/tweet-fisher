/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import java.util.Observable;
import java.util.Observer;
import tfisher.interfaces.ITwitterDownloader;
import tfisher.auth.TwitterAuth;
import tfisher.dao.Keywords;
import tfisher.session.TwitterDownloader;
import twitter4j.TwitterException;
import org.springframework.stereotype.Component;
import tfisher.entities.Media;
import tfisher.entities.Tweet;
import tfisher.entities.User;
import tfisher.utils.KeywordsReloader;

/**
 * This class controls the download process 
 * @author Theodore Chrysochoidis
 */
@Component
public class DownloadController implements Observer
{ 
   private ITwitterDownloader _idownloader;
   private Keywords _keywords;    
   private TwitterAuth _twitterAuth;
   private String _pin;
   private User _user;
   private Tweet _tweet;
   private Media _media;
   private boolean isRunning = false;  
   private KeywordsReloader _keywordsReloader; 
   private long timeKeeper = 0;
  
   
   public DownloadController(){};
   public DownloadController(Keywords keywords, TwitterAuth twitterAuth ) 
   {
       _keywords = keywords;            
       _twitterAuth = twitterAuth;
       _idownloader = new TwitterDownloader();
    }   
     
    public boolean setDependencies(Keywords keywords,  TwitterAuth twitterAuth, User user, Tweet tweet, Media media, KeywordsReloader kl )
    {
        if ( keywords == null || twitterAuth == null || tweet == null || media == null || kl == null )
            return false;
        _keywords = keywords;  
        _twitterAuth = twitterAuth;  _idownloader = new TwitterDownloader(); //TODO
        _user = user; _tweet = tweet; _media = media; _keywordsReloader = kl;
        return true;
    }
   public void setDownloader (ITwitterDownloader downloader)
   {
       _idownloader = new TwitterDownloader();
   }
 

    /**
     *
     * @param keywords Keywords to monitor (String[])
     * @return true/false
     */
    public boolean startDownload(  String[] keywords ) throws TwitterException, InterruptedException 
   {      
       if ( keywords.length == 0 )
           return false;
       if ( isRunning == false )
       {          
            Thread t = new Thread(new TwitterDownloader(  _twitterAuth.getTwitterStream(), _keywords, _user, _tweet, _media ) );
            t.start();   
            isRunning = true; 
            timeKeeper = System.currentTimeMillis();
            return true;
       }
       else if ( isRunning == true )
       {  
            if ( System.currentTimeMillis() - timeKeeper < 40000 ){return false;}            
           _keywordsReloader.setTimer(60);
            timeKeeper = System.currentTimeMillis();           
           return true;
       }
       return false;
   }    

    public void update(Observable o, Object arg) 
    {       
         Thread t = new Thread(new TwitterDownloader( _twitterAuth.getTwitterStream(), _keywords, _user, _tweet, _media ) );
         t.start();  
         timeKeeper = System.currentTimeMillis();
    }

}//end of DownloadController
