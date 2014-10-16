/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author 13
 */
public class TweetDTO extends Observable {   
    private String _tweetText;
    private String _author;
    
    public String getTweetText()
    {
        return _tweetText;
    }
    
    public String getCreator()
    {
        return _author;
    }
    
    public void setTweetText( String tweetText )
    {
        _tweetText = tweetText;        
    }
    
    public void setCreator( String author ) 
    {
        _author = author;
        changeState();
    }
    public void changeState() {
       
        setChanged();
        notifyObservers();
    }
    
    //XWRIS getState()
}
