/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import DTO.TweetDTO;
import tfisher.interfaces.ITwitterDownloader;
import tfisher.auth.TwitterAuth;
import tfisher.dao.Keywords;
import tfisher.session.TwitterDownloader;
import twitter4j.TwitterException;
import org.springframework.stereotype.Component;
import tfisher.entities.Tweet;
import tfisher.entities.User;

/**
 * This class controls the download process 
 * @author Theodore Chrysochoidis
 */
@Component
public class DownloadController
{ 
   private ITwitterDownloader _idownloader;
   private Keywords _keywords;  
   private TweetDTO _tweetDTO;
   private TwitterAuth _twitterAuth;
   private String _pin;
   private User _user;
   private Tweet _tweet;
   
   public DownloadController(){};
   public DownloadController(Keywords keywords,  TweetDTO tweetDTO, TwitterAuth twitterAuth ) 
   {
       _keywords = keywords;         
       tweetDTO.clearTheTweetDTO();
       _tweetDTO = tweetDTO;   
       _twitterAuth = twitterAuth;
       _idownloader = new TwitterDownloader();
    }   
     
    public void setDependencies(Keywords keywords, TweetDTO tweetDTO, TwitterAuth twitterAuth, User user, Tweet tweet )
    {
        _keywords = keywords;   tweetDTO.clearTheTweetDTO();  _tweetDTO = tweetDTO;   
        _twitterAuth = twitterAuth;  _idownloader = new TwitterDownloader(); //TODO
        _user = user; _tweet = tweet;
    }
   public void setDownloader (ITwitterDownloader downloader)
   {
       _idownloader =new TwitterDownloader();
   }
   /**
    * 
    * @param miliseconds
    *       The given window time (int)
    * @param keywords 
    *       Keywords to monitor (String[])
    * @return true/false
    */

    /**
     *
     * @param keywords Keywords to monitor (String[])
     * @return true/false
     */
    public boolean startDownload(  String[] keywords ) throws TwitterException 
   {      
       _tweetDTO.clearTheTweetDTO();
      Thread t = new Thread(new TwitterDownloader( _tweetDTO, _twitterAuth.getTwitterStream(), _keywords, _user, _tweet ) );
      t.start();   
      return true;
   }    

}//end of DownloadController
