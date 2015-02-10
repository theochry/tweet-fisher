/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.utils;


import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import org.springframework.stereotype.Component;
import tfisher.controllers.StoreTweetController;
import tfisher.dao.Keywords;

/**
 *
 * @author Theodore Chrysochoidis
 */
@Component
public class Notification implements Observer
{
     static Image image = Toolkit.getDefaultToolkit().getImage("notify-pic.png");
     static TrayIcon trayIcon = new TrayIcon(image, "Tweets downloaded");
     public static int  stopper = 0;
     private Keywords _keywords;
     
     public void setKeywords ( Keywords keywords )
     {
         _keywords = keywords;
     }
     
     public String extractKeyword ( String tweet )
     {
         String check = tweet.toLowerCase();
        
         for ( int i = 0; i < _keywords.getArrayOfKeywords().length; i++ )
         {                         
             String[] keywordsTable = _keywords.getArrayOfKeywords();            
             if ( check.contains(keywordsTable[i]))
             {
                 return keywordsTable[i];
             }
         }
         return null;
     }
    public void update(Observable o, Object arg) 
    {       
            if (SystemTray.isSupported() )        
            {         
                SystemTray tray = SystemTray.getSystemTray();   
                String key = extractKeyword(((StoreTweetController) o).getTweetText());
                trayIcon.displayMessage("Tweet-Fisher", "Έχουν αποθηκευτεί κάποια γεγονότα που σας ενδιαφέρουν για τη λέξη-κλειδί "+key, TrayIcon.MessageType.INFO);
                trayIcon.setImageAutoSize(true);
                trayIcon.addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {         
                        trayIcon.displayMessage("Tester!", "Some tweets downloaded", TrayIcon.MessageType.INFO);
                    }
                });

                try {
                      if (stopper==0) 
                      {
                        tray.add(trayIcon); 
                        stopper++;
                      }


                } catch (AWTException e) {
                  System.err.println("TrayIcon could not be added.");
                }
              }       
    }  
}
