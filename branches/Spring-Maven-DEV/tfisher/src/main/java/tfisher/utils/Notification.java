package tfisher.utils;


import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.stereotype.Component;
import tfisher.dao.Keywords;

/**
 *
 * @author Theodore Chrysochoidis
 */
@Component
public class Notification
{
     static Image image = Toolkit.getDefaultToolkit().getImage("notify-pic.png");
     static TrayIcon trayIcon = new TrayIcon(image, "Tweets downloaded");
     public static int  stopper = 0;
     private Keywords _keywords;
     
     public void setKeywords ( Keywords keywords )
     {
         _keywords = keywords;
     }
     
     public String concatKeyword ( ArrayList<String> keywords )
     {     
         String concatKeywords = new String();
        Iterator<String> it = keywords.iterator();
        while(it.hasNext())
        {
            String keyword = it.next();
            concatKeywords += keyword;
            concatKeywords += " ";            
        }
        return concatKeywords;
         
     }
     
     public void showToTask( final ArrayList<String> keywords )
     {
          if (SystemTray.isSupported() )        
            {         
                SystemTray tray = SystemTray.getSystemTray();      
                trayIcon.setImageAutoSize(true);                
                trayIcon.addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {         
                        trayIcon.displayMessage("Tweet-Fisher", "Some events for keywords "+concatKeyword(keywords), TrayIcon.MessageType.INFO);
                    }
                });

                try { 
                    if ( stopper == 0 )
                    {
                        tray.add(trayIcon);               
                        stopper = 1;
                    }
                    if ( stopper != 0 )
                    {
                        tray.remove(trayIcon);
                        tray.add(trayIcon);  
                         trayIcon.displayMessage("Tweet-Fisher", "Some new events just downloaded", TrayIcon.MessageType.INFO);                

                    }
                } catch (AWTException e) {
                  System.err.println("TrayIcon could not be added.");
                }
              }       
     }
   
}
