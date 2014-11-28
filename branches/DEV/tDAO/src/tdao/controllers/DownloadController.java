/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.controllers;

import DTO.TweetDTO;
import tdao.auth.TwitterAuth;
import tdao.dao.Keywords;
import tdao.session.TwitterDownloader;


/**
 * This class controls the download process 
 * @author Theodore Chrysochoidis
 */
public class DownloadController 
{
   
   private TwitterDownloader _twitterDownloader = new TwitterDownloader();
   private Keywords _keywords;  
   private TweetDTO _tweetDTO;
   private TwitterAuth _twitterAuth;
   
   
   public DownloadController(Keywords keywords,  TweetDTO tweetDTO, TwitterAuth twitterAuth ) 
   {
       _keywords = keywords;         
        tweetDTO.clearTheTweetDTO();
        _tweetDTO = tweetDTO;   
        _twitterAuth = twitterAuth;
    }   
     
   /**
    * 
    * @param miliseconds
    *       The given window time (int)
    * @param keywords 
    *       Keywords to monitor (String[])
    * @return true/false
    */
   
   public boolean startDownload( int miliseconds, String[] keywords ) 
   {     
       if ( miliseconds < 1 || 0 == keywords.length )
       {
           return false;
       }
       _tweetDTO.clearTheTweetDTO();
       _tweetDTO = _twitterDownloader.download( _tweetDTO, _twitterAuth.getTwitterStream(), miliseconds, _keywords ); 
       return true;
   }    
}//end of DownloadController
