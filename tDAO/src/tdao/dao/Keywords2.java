/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.dao;

import java.util.ArrayList;

/**
 *
 * @author 13
 */
public class Keywords2 {
    private ArrayList<String> _keywords = new ArrayList<String>();
    
    
    public void setKeyword( String keyword )
    {      
        _keywords.add(keyword);      
    }
    public ArrayList<String> getKeywords()
    {
        return _keywords;
    }
}
