/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.dao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.Timer;

/**
 *
 * @author 13
 */
public class Keywords extends Observable{
    
    private ArrayList<String> _keywords = new ArrayList<String>();
  
    public Keywords() {}
    public void setKeyword( String keyword )
    {
        _keywords.add(keyword);
        changeState();
    }
    public ArrayList<String> getKeywords()
    {
        return _keywords;
    }
    
    public void changeState() {
       
        setChanged();
        notifyObservers();
    }
    public String getState() {
        return _keywords.get(_keywords.size()-1);        
    }
    
    //Επιστρέφει true εάν το δοσμένο στρινγκ υπάρχει
    public boolean removeKeyword( String keyword )
    {
        boolean tf;
        if ( _keywords.isEmpty() )
        {
            tf = false;
        }
        else
        {
            tf = _keywords.remove(keyword);
        }
        return tf;
    }
    
    public String[] getArrayOfKeywords()
    {
        String[] keywordsArray = new String[_keywords.size()];
        return _keywords.toArray(keywordsArray);
    }
  
}
