/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

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

/**
 *
 * @author Theodore Chrysochoidis 
 */
@Component
public class Notifier implements Observer
{
    static Image image = Toolkit.getDefaultToolkit().getImage("notify-pic.png");

    static TrayIcon trayIcon = new TrayIcon(image, "Tweets downloaded");


    public void update(Observable o, Object arg) 
    {
        System.out.println("tweet noti");
        if (SystemTray.isSupported()) {
      SystemTray tray = SystemTray.getSystemTray();
      trayIcon.displayMessage("Tweets", "Έχουν κατέβει tweets", TrayIcon.MessageType.INFO);
      trayIcon.setImageAutoSize(true);
      trayIcon.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("In here");
          trayIcon.displayMessage("Tester!", "Some tweets downloaded", TrayIcon.MessageType.INFO);
        }
      });

      try {
        tray.add(trayIcon);
      } catch (AWTException e) {
        System.err.println("TrayIcon could not be added.");
      }
    }
        
    }
    
}
