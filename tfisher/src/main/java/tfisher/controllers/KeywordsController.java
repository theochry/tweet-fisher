/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import java.util.ArrayList;
import java.util.Observer;
import tfisher.dao.Keywords;
import tfisher.views.KeywordsForm;
import org.springframework.stereotype.Component;
/** 
 * This class is the controller of keywords model
 *
 * @author Theodore Chrysochoidis 
 */
@Component
public class KeywordsController
{ 
    private Keywords _keywords;
    private KeywordsForm _keywordsForm;  
    private boolean isRunning;
    public KeywordsController(){}
    
    public KeywordsController(Keywords keywords, KeywordsForm keywordsForm) 
    {
        _keywords = keywords;
        _keywordsForm = keywordsForm;
        _keywords.addObserver((Observer) keywordsForm);
    }
    public void setDependencies(Keywords keywords, KeywordsForm keywordsForm )
    {
        _keywords = keywords; _keywordsForm = keywordsForm;
        _keywords.addObserver((Observer) keywordsForm);
    }
    public boolean setKeyword( String keyword )
    { 
        
       if ( _keywords.checkKeywordPattern( keyword ) )
       {
            _keywords.setKeyword(keyword);
            return true;
       }
       return false;
    }
    public void setIsRunning(boolean running)
    {
        isRunning = running;
    }
    
    public ArrayList<String> getKeywords()
    {
        return _keywords.getKeywords();
    }       

    /**
     * Removes the given keyword
     * @param keyword
     * @return true/false
     */
    public boolean removeKeyword ( String keyword )
    {
       return _keywords.removeKeyword(keyword);
    } 
    /**
     * Checks if the given keyword already exist
     * @param keyword
     *      The keyword to be checked (String)
     * @return true/false
     */
    public boolean keywordExist( String keyword )
    {
        return _keywords.keywordExist(keyword);
    }
    public boolean checkKeywordPattern ( String keyword )
    {
        return _keywords.checkKeywordPattern( keyword );
    }
    
    public boolean setOccurences ( int occurences )
    {
        return _keywords.setOccurences ( occurences );
    }
    
    public boolean setTimeInterval ( int timeInterval )
    {
        return _keywords.setTimeInterval ( timeInterval );
    }
    public int getTimeInteval()
    {
        return _keywords.getTimeInterval();
    }
    public int getOccurences()
    {
        return _keywords.getOccurences();
    }
}
