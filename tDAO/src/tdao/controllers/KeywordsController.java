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
 *
 * @author theodore */
public class KeywordsController{ 
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

    public boolean removeKeyword ( String keyword )
    {
       return _model.removeKeyword(keyword);
    }  
    
}
