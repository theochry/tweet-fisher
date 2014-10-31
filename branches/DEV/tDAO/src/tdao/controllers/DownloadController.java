/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.controllers;

import DTO.TweetDTO;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import tdao.auth.TwitterAuth;
import tdao.dao.Keywords;
import tdao.session.TwitterDownloader;

import tdao.views.KeywordsForm;

/**
 *
 * @author theodore
 */
public class DownloadController 
{
   
   private TwitterDownloader _twitterDownloader = new TwitterDownloader();
   private Keywords _modelKeywords;  
   private TweetDTO _tweetDTO;
   private TwitterAuth _twitterAuth = TwitterAuth.getSingletonInstance();
   
   
   public DownloadController(Keywords model,  TweetDTO tweetDTO) 
   {
       _modelKeywords = model;         
        tweetDTO.clearTheTweetDTO();
        _tweetDTO = tweetDTO;       
    }   
      
   public void startDownload( int miliseconds, String[] keywords )
   {   
       _tweetDTO.clearTheTweetDTO();
       _tweetDTO=_twitterDownloader.download( keywords, _tweetDTO, _twitterAuth.getTwitterStream(), miliseconds );                
   }    
}//end of DownloadController
