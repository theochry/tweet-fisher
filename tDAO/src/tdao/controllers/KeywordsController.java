/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.controllers;

import DTO.TweetDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import tdao.dao.Keywords;
import tdao.session.TwitterDownloader;
import tdao.views.KeywordsForm;
import tdao.views.MainForm;

/**
 *
 * @author theodore */
public class KeywordsController implements ActionListener{    

   // private TwitterDownloader psstream = new TwitterDownloader();
   
    private Keywords _model;
    private KeywordsForm _view;   
    public KeywordsController(){}
    
    public KeywordsController(Keywords model, KeywordsForm view) {
        _model = model;
        _view = view;
        _model.addObserver((Observer) view);
    }
    public void setKeyword( String keyword )
    {        
       _model.setKeyword(keyword);
    }
    public ArrayList<String> getKeywords()
    {
        return _model.getKeywords();
    }       

    @Override
    public void actionPerformed(ActionEvent e) 
    {   
         String src = e.getActionCommand();
        if ( src.equals("ok"))
        {
            _model.setKeyword( _view.getLatestKeyword());  
        }
        else if (src.equals("remove"))
        {            
            _model.removeKeyword(_view.getKeywordToDelete());
        }
    }
}
