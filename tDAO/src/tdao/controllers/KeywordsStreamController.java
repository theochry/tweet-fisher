/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.controllers;

import java.util.ArrayList;
import java.util.List;
import tdao.session.PrintSampleStream;

/**
 *
 * @author theodore */
public class KeywordsStreamController extends PrintSampleStream {
    
    private List<String> _keywords = new ArrayList<String>();
    PrintSampleStream psstream = new PrintSampleStream();
    
    
    public void setKeyword( String keyword )
    {
       _keywords.add(keyword);
    }
    public List getKeywords()
    {
        return _keywords;
    }
    public void startDownload()
    {
        psstream.download( _keywords );
    }
}
