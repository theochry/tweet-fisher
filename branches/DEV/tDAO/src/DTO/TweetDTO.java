/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Observable;

import tdao.entities.Tweet;
import twitter4j.User;

/**
 *
 * @author Theodore Chrysochoidis
 */
public class TweetDTO extends Observable 
{  
    private Multimap<String, TweetDTO> _keywordsTweetDTOHashmap = HashMultimap.create();
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
    
    public void addTweetDTO( String keyword, TweetDTO tweetDTO )
    {
        _keywordsTweetDTOHashmap.put(keyword, tweetDTO);
        
    }
    public void clearTheTweetDTO()
    {
        _keywordsTweetDTOHashmap.clear();
    }
    public void notifyAllObservers()
    {
        changeState();
    }
    public TweetDTO getTweetDTO()
    {
        return this;
    }
    public  Multimap<String, TweetDTO> getTweetDtoMultiHash()
    {
        return _keywordsTweetDTOHashmap;
    }
    public void changeState() {
       
        setChanged();
        notifyObservers();
    }
    
    
    
    //XWRIS getState()
}
