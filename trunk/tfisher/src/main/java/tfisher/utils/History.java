/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import tfisher.dao.Keywords;


/**
 *
 * @author Theodore Chrysochoidis
 */
@Component
public class History implements Observer
{
    private JSONObject _JSONObject = new JSONObject( );
    private JSONArray _keywordsJSON = new JSONArray();
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    private Date date = new Date();
    private HistoryParser _historyParser = new HistoryParser();
    private ArrayList< String> keywords = new ArrayList <String>();
    public void storeKeywordsToJson()
    {
        
    }

    public void update(Observable o, Object arg) 
    {
        if("add".equals(((Keywords) o).getState()) )
        {      
            File file = new File("keywords.json");  
            try {       
                    if(!file.exists())
                    {
                            _keywordsJSON.add(  ((Keywords) o).getLatestKeyword() );
                            _JSONObject.put("Keywords",_keywordsJSON);                            
                            FileWriter fileWriter = new FileWriter("keywords.json");
                            fileWriter.write(_JSONObject.toString());
                            fileWriter.flush();
                            fileWriter.close();                           
                    }
                    else if ( file.exists() )
                    {
                          keywords.clear();
                          _JSONObject.clear();
                          keywords = _historyParser.parseKeywords();                      
                          FileWriter fileWriter = new FileWriter("keywords.json");
                          if ( !keywordExist (keywords,  ((Keywords) o).getLatestKeyword()) )
                          {
                            keywords.add(((Keywords) o).getLatestKeyword());                   
                            _JSONObject.put("Keywords",keywords);
                            fileWriter.write(_JSONObject.toString());
                            fileWriter.flush();
                            fileWriter.close();  
                          }
                          else if ( keywordExist (keywords,  ((Keywords) o).getLatestKeyword()) )
                          {                                               
                            _JSONObject.put("Keywords",keywords);
                            fileWriter.write(_JSONObject.toString());
                            fileWriter.flush();
                            fileWriter.close(); 
                          }
                    }        
            
                }   
            catch (IOException ex) 
            {
                Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
            }
        if("remove".equals(((Keywords) o).getState()) )
        {
            
        }
    }
    
    }
    
    public boolean keywordExist( ArrayList< String> keywordsArray, String newKeyword )
    {
       for (String s : keywordsArray)
       {
        if ( s.equals(newKeyword) )
        {
            return true;
        }
            
       }
       return false;
    }
}
