/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.dao;

import java.util.ArrayList;
import java.util.Observable;
import org.springframework.stereotype.Component;
import tfisher.interfaces.IKeywords;

/**
 * This class keeps the user's keywords
 * @author Theodoros Chrysochoidis
 */
@Component
public class Keywords extends Observable implements IKeywords{
    
    private final ArrayList< String > _keywords = new ArrayList< String >();
    private int  _occurences;
    private int _timeInterval;
    private String _state = null;
    private String _keywordToDelete = null;
    
    public Keywords() {}
    public boolean setKeyword( String keyword )
    {      
            if( keyword.isEmpty() )
                return false;
            _keywords.add( keyword );
            _state = "add";
            changeState(); 
            return true;
    }   
    public ArrayList<String> getKeywords()
    {
        return _keywords;
    }
    /**
     * Notify all observers that the state changed
     */
    public void changeState() 
    {      
        setChanged();
        notifyObservers();
    }
   /**
    * Get the new keyword
    * @return 
    */
    public String getState() 
    {
        return _state;      
    }
    public String getLatestKeyword()
    {
        
         return _keywords.get( _keywords.size() - 1 );       
    }
    public String getKeywordToDelete()
    {
        return _keywordToDelete;
    }
    
    /**
     * Remove the given keyword
     * @param keyword
     * @return true/false
     */
    public boolean removeKeyword( String keyword )
    {         
        boolean tf;
        if ( _keywords.isEmpty() )
        {
            tf = false;
        }
        else
        {
            _keywordToDelete = keyword;
            tf = _keywords.remove(keyword);
        }
        _state = "remove";
        changeState();
        return tf;
    }
    
   /**
    * Make an array of keywords
    * @return 
    */
    public String[] getArrayOfKeywords()
    {
        String[] keywordsArray = new String[ _keywords.size() ];
        return _keywords.toArray( keywordsArray );
    }
    
    public boolean keywordExist( String keyword )
    {
       return _keywords.contains(keyword);
    }
    public boolean checkKeywordPattern( String keyword )
    {
        String pattern = "[a-zA-Z0-9 #:)(?]*";
        if ( keyword.matches(pattern) )
            return true;
        return false;
    }
   public boolean setOccurences( int occurences )
   {
        if ( occurences > 0 )
        {
            _occurences = occurences;
            return true;
        }
        return false;
    }
   public boolean setTimeInterval ( int timeInterval )        
   {
        if ( timeInterval > 0 )
        {
            _timeInterval = timeInterval * 60;
            return true;
        }
        return false;
   }
   public int getOccurences()
   {
       return _occurences;
   }
   
   public int getTimeInterval()
   {
       return _timeInterval;
   }
        
}
