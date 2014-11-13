/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.controllers;

import java.util.ArrayList;
import java.util.Observer;
import tdao.dao.Keywords;
import tdao.views.KeywordsForm;

/** 
 * This class is the controller of keywords model
 *
 * @author Theodore Chrysochoidis 
 */
public class KeywordsController
{ 
    private Keywords _keywords;
    private KeywordsForm _keywordsForm;   
    public KeywordsController(){}
    
    public KeywordsController(Keywords model, KeywordsForm view) 
    {
        _keywords = model;
        _keywordsForm = view;
        _keywords.addObserver((Observer) view);
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
    
}
