/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Theodore Chrysochoidis
 */
public class HistoryParser 
{
    private JSONParser _parser = new JSONParser();
  
    private ArrayList < String > _keywords = new ArrayList <String>();
    public ArrayList < String > parseKeywords() throws IOException, ParseException
    {
            FileReader fileReader = new FileReader("keywords.json");
            Object obj = _parser.parse(fileReader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray keywordsJSON = new JSONArray();
            keywordsJSON.clear();
            _keywords.clear();
            keywordsJSON = (JSONArray) jsonObject.get("Keywords");
            for ( int currentKeyword = 0; currentKeyword < keywordsJSON.size(); currentKeyword++ )
            {
               _keywords.add(String.valueOf(keywordsJSON.get(currentKeyword)));               
            }       
            fileReader.close();            
            return _keywords;	
    }
}
