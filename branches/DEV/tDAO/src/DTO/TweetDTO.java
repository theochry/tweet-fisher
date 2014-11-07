/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.util.ArrayList;
import java.util.Observable;
import tdao.entities.Tweet;
import twitter4j.User;

/**
 *
 * @author 13
 */
public class TweetDTO extends Observable {   
    ArrayList<TweetDTO> _tweetDTOArray = new ArrayList< TweetDTO >();
    private String _tweetText;
    private String _author;
    private Tweet _tweet;//TODO
    private User _user;//TODO
   
    
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
        
    }
    
    public void addTweetDTO( TweetDTO tweetDTO )
    {
        _tweetDTOArray.add(tweetDTO);
        
    }
    public void clearTheTweetDTO()
    {
        _tweetDTOArray.clear();
    }
    public void notifyAlls()
    {
        changeState();
    }
    public TweetDTO getTweetDTO()
    {
        return this;
    }
    public ArrayList< TweetDTO > getTweetDTOArray()
    {
        return _tweetDTOArray;
    }
    public void changeState() {
       
        setChanged();
        notifyObservers();
    }
    
    //XWRIS getState()
}
