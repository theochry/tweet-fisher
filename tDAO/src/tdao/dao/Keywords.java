/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.dao;

import java.util.ArrayList;
import java.util.Observable;

/**
 * This class keeps the user's keywords
 * @author Theodoros Chrysochoidis
 */
public class Keywords extends Observable{
    
    private ArrayList<String> _keywords = new ArrayList<String>();
    private String _state;
    private String _keywordToDelete = new String();
    
    public Keywords() {}
    public void setKeyword( String keyword )
    {
        _state = null;
        _keywords.add( keyword );
        _state = "add";
        changeState();
    }
    public ArrayList<String> getKeywords()
    {
        return _keywords;
    }
    /**
     * Notify all observers that the state changed
     */
    public void changeState() {
       
        setChanged();
        notifyObservers();
    }
   /**
    * Get the new keyword
    * @return 
    */
    public String getState() {
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
        _state = null;
        _keywordToDelete = null;      
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
  
}
