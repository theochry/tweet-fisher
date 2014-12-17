/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import DTO.TweetDTO;
import java.util.Observer;
import tfisher.interfaces.ITwitterDownloaderService;
import tfisher.auth.TwitterAuth;
import tfisher.dao.Keywords;
import tfisher.session.TwitterDownloader;
import twitter4j.TwitterException;
import org.springframework.stereotype.Component;
import tfisher.views.KeywordsForm;

/**
 * This class controls the download process 
 * @author Theodore Chrysochoidis
 */
@Component
public class DownloadController
{ 
   private ITwitterDownloaderService _idownloader;
   private Keywords _keywords;  
   private TweetDTO _tweetDTO;
   private TwitterAuth _twitterAuth;
   private String _pin;
   
   public DownloadController(){};
   public DownloadController(Keywords keywords,  TweetDTO tweetDTO, TwitterAuth twitterAuth ) 
   {
       _keywords = keywords;         
       tweetDTO.clearTheTweetDTO();
       _tweetDTO = tweetDTO;   
       _twitterAuth = twitterAuth;
       _idownloader = new TwitterDownloader();
    }   
     
    public void setDependencies(Keywords keywords, TweetDTO tweetDTO, TwitterAuth twitterAuth )
    {
        _keywords = keywords;   tweetDTO.clearTheTweetDTO();  _tweetDTO = tweetDTO;   
        _twitterAuth = twitterAuth;  _idownloader = new TwitterDownloader(); //TODO
    }
   public void setDownloader (ITwitterDownloaderService downloader)
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
     * @param miliseconds The given window time (int)
     * @param keywords Keywords to monitor (String[])
     * @return true/false
     */
    public boolean startDownload( int miliseconds, String[] keywords ) throws TwitterException 
   {     
       if ( miliseconds < 1 || 0 == keywords.length )
       {
           return false;
       }
      
       _tweetDTO.clearTheTweetDTO();
      Thread t = new Thread(new TwitterDownloader( _tweetDTO, _twitterAuth.getTwitterStream(), miliseconds, _keywords ) );
      t.start();
       //_tweetDTO = _idownloader.download( _tweetDTO, _twitterAuth.getTwitterStream(), miliseconds, _keywords); 
       return true;
   }    

}//end of DownloadController
