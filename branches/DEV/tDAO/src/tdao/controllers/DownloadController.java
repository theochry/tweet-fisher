/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.controllers;

import DTO.TweetDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import tdao.dao.Keywords;
import tdao.session.TwitterDownloader;
import tdao.views.DownloadForm;
import tdao.views.KeywordsForm;

/**
 *
 * @author 13
 */
public class DownloadController implements ActionListener
{
   
   private TwitterDownloader _twitterDownloader = new TwitterDownloader();
   private Keywords _modelKeywords;
   private DownloadForm _view;
   private TweetDTO _tweetDTO;
 
   
   public DownloadController(Keywords model, DownloadForm view, TweetDTO tweetDTO) 
   {
        _modelKeywords = model;
        _view = view;  
        _tweetDTO = tweetDTO;
    }   
   
   @Override
    public void actionPerformed(ActionEvent e)
    {       
        _tweetDTO=_twitterDownloader.download( _modelKeywords, _tweetDTO );
        System.out.println("tweetDTO data"+_tweetDTO.getTweetText());
    } 
 
    
}//end of DownloadController
