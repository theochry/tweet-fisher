/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.utils;

/**
 *
 * @author Theodore Chrysochoidis
 */
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.stereotype.Component;

@Component
public class KeywordsReloader extends Observable 
{
  Toolkit toolkit;
  Timer timer;
  private long timeKeeper = 0;

  public KeywordsReloader(){};
  public KeywordsReloader(int seconds) {
    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();
    timer.schedule(new RemindTask(), seconds * 1000);
  }

  class RemindTask extends TimerTask 
  {
    public void run() 
    {     
        System.out.println("I JUST CALLED TO SAY I LOVE YOU");        
        toolkit.beep();          
        changeState();      
    }
  }
  public void changeState() 
    {      
        setChanged();
        notifyObservers();
    }
   
  public static int randInt(int min, int max) 
  {   
    Random rand = new Random();  
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
}
  public void setTimer ( int seconds ) throws InterruptedException
  {
                
             
          seconds += randInt(40,90); 
         
      
    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();
   
    timer.schedule(new RemindTask(), seconds * 1000);
    timeKeeper = System.currentTimeMillis();
  }
 
}
