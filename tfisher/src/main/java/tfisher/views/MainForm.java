/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.views;


import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import tfisher.auth.TwitterAuth;
import tfisher.controllers.DownloadController;
import tfisher.controllers.KeywordsController;
import tfisher.controllers.ResultsController;
import tfisher.dao.Keywords;
import tfisher.entities.Media;
import tfisher.utils.Notification;
import tfisher.entities.Tweet;
import tfisher.entities.User;
import tfisher.utils.History;
import tfisher.utils.KeywordsReloader;

import twitter4j.TwitterException;


/**
 *
 * @author  Theodore Chrysochoidis
 */
 
public class MainForm extends javax.swing.JFrame implements Observer  {
  
    //views   
    private static MainForm _mainForm;
    public static KeywordsForm _keywordsFormView;    
    public static Settings _settingsFormView;   
   
    public static ResultsForm _resultsFormView;
    public static LoginForm _loginForm;
    
    
    //models
 
    public  static Keywords _keywordsModel;
    public static User user;
    public static Tweet tweet;
    public static Media media;
    
    //controllers
    public static DownloadController _downloadController;    
    public static KeywordsController _keywordsController;       
    public static ResultsController _resultsController;
    public static Notification _notification;
    public static History _keywordsHistory;
   
    //others
   
     public static TwitterAuth _twitterAuth;
     public boolean _isRunning = false;
     private long _startTime, _timeWindow;
     public static KeywordsReloader _keywordsReloader;
     
     public boolean isRunning = false;
    
       MainForm(Keywords keywords)
       {
                 
            this._keywordsModel = keywords;     
        }
    
       public void setRunning(boolean isRunning)
        {
            _isRunning = isRunning;
        }
     private void centerFrame() 
    {
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        setLocation(dx, dy);
        setResizable(false);
    }     
    public MainForm() 
    {
        initComponents();       
        createControls();
        centerFrame();
         setTitle(" Tweet-fisher "); 
         setResizable(false);
    }
    
   public void createControls()
  {       
        searchKeywordsJmenu.addActionListener(new MenuKeywordListener());      
        settingsJmenu.addActionListener( new MenuSettingsListener());
        startBtn.addActionListener ( new DownloadListener() );     
        loginJmenu.addActionListener( new MenuLoginListener() );
        resultsJmenu.addActionListener(new ResultsListener());
        exitJmenu.addActionListener(new ExitListener());
   }
   
    public Keywords getKeywords()
    {
        return _keywordsModel;        
    }
    
    public KeywordsForm getKeywordsFormView()
    {
        return _keywordsFormView;
    }  
    
    public Settings getSettingsFormView()
    {
        return _settingsFormView;
    } 
    
    public ResultsForm getResultsFormView()
    {
        return _resultsFormView;
    } 
    public LoginForm getLoginFormView()
    {
        return _loginForm;
    }
   
    public static MainForm getSingletonInstance()
    {
        if ( null == _mainForm )
        {
            _mainForm = new MainForm();
        }
        return _mainForm;
    }
    public void disableComponents()
    {
         startBtn.setEnabled(false);
         //settingsJmenu.setEnabled(false);
         //searchKeywordsJmenu.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        startBtn = new javax.swing.JButton();
        keywordsPanel = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        keywordsList = new java.awt.List();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        loginJmenu = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        exitJmenu = new javax.swing.JMenuItem();
        searchJmenu = new javax.swing.JMenu();
        searchKeywordsJmenu = new javax.swing.JMenuItem();
        settingsJmenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        resultsJmenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startBtn.setText("Start Download");

        jLabel1.setText("Added Keywords");

        keywordsList.setMultipleMode(true);

        javax.swing.GroupLayout keywordsPanelLayout = new javax.swing.GroupLayout(keywordsPanel);
        keywordsPanel.setLayout(keywordsPanelLayout);
        keywordsPanelLayout.setHorizontalGroup(
            keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keywordsPanelLayout.createSequentialGroup()
                .addGroup(keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(keywordsList, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        keywordsPanelLayout.setVerticalGroup(
            keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keywordsPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keywordsList, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("File");

        loginJmenu.setText("Credentials");
        jMenu1.add(loginJmenu);

        jMenuItem2.setText("About");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        exitJmenu.setText("Exit");
        jMenu1.add(exitJmenu);

        jMenuBar1.add(jMenu1);

        searchJmenu.setText("Search");
        searchJmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJmenuActionPerformed(evt);
            }
        });

        searchKeywordsJmenu.setText("Keywords");
        searchJmenu.add(searchKeywordsJmenu);

        settingsJmenu.setText("Settings");
        searchJmenu.add(settingsJmenu);

        jMenuBar1.add(searchJmenu);

        jMenu3.setText("Results");

        resultsJmenu.setText("Results Form");
        jMenu3.add(resultsJmenu);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(keywordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(startBtn))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(startBtn)
            .addComponent(keywordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void searchJmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJmenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchJmenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
         ApplicationContext context = new FileSystemXmlApplicationContext("resources/app-config.xml");
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       _mainForm = new MainForm();
        //Initialize beans
      _keywordsModel = (Keywords)context.getBean("keywords");
      _keywordsFormView = (KeywordsForm)context.getBean("keywordsForm");     
      _resultsFormView = (ResultsForm)context.getBean("resultsForm");
      
      _twitterAuth = (TwitterAuth)context.getBean("twitterAuth");
      _settingsFormView = (Settings)context.getBean("settings");      
     _downloadController = (DownloadController)context.getBean("downloadController"); 
     _keywordsController = (KeywordsController)context.getBean("keywordsController"); 
     _loginForm = (LoginForm)context.getBean("loginForm"); 
     
     user = (User)context.getBean("user"); 
     tweet = (Tweet)context.getBean("tweet"); 
     //media = (Media)context.getBean("media"); 
     media = new Media();     
     _resultsController = (ResultsController)context.getBean("resultsController"); 
     _keywordsReloader = (KeywordsReloader)context.getBean("keywordsReloader");
     _keywordsHistory = (History)context.getBean("history"); 
     _notification = (Notification)context.getBean("notification");   
     _keywordsController.setDependencies(_keywordsModel, _keywordsFormView);       
     _downloadController.setDependencies(_keywordsModel, _twitterAuth, user, tweet, media, _keywordsReloader);       
     _resultsController.setDependencies(_resultsFormView);
     
     _keywordsFormView.addController( _keywordsController );  
     _settingsFormView.addController( _keywordsController );  
     _resultsFormView.addController(_resultsController);
     _keywordsModel.addObserver( _mainForm );   
     _keywordsModel.addObserver(_keywordsHistory);
     _keywordsReloader.addObserver(_downloadController);
     
            
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {              
                 MainForm mf = MainForm.getSingletonInstance();
                 mf.setVisible(true);       
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem exitJmenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.List keywordsList;
    private java.awt.Panel keywordsPanel;
    private javax.swing.JMenuItem loginJmenu;
    private javax.swing.JMenuItem resultsJmenu;
    private javax.swing.JMenu searchJmenu;
    private javax.swing.JMenuItem searchKeywordsJmenu;
    private javax.swing.JMenuItem settingsJmenu;
    private javax.swing.JButton startBtn;
    // End of variables declaration//GEN-END:variables


    
    //auto to update kaleitai gia kathe notify apo opoiondhpote typo antikeimenou
    public void update(Observable obs, Object x) {
         MainForm mf = MainForm.getSingletonInstance();
         
        if( obs instanceof Keywords && "add".equals(((Keywords) obs).getState()) )
        {                
             mf.keywordsList.add(((Keywords) obs).getLatestKeyword());      
             String[] keywordsArray = _keywordsModel.getArrayOfKeywords();
             _notification.setKeywords(_keywordsModel);
              if ( _isRunning == false ){ }
             else if ( _isRunning == true )
             {            
                 try {
                     _downloadController.startDownload(keywordsArray);
                 } catch (TwitterException ex) {
                     Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             
        }
        else if ( obs instanceof Keywords && "remove".equals(((Keywords) obs).getState()))
        {           
         
            mf.keywordsList.remove(((Keywords) obs).getKeywordToDelete()); 
               String[] keywordsArray  = ((Keywords) obs).getArrayOfKeywords();
               _notification.setKeywords(_keywordsModel);
             if ( _isRunning == false ){ }
             else if ( _isRunning == true )
             { 
                
                 if ( keywordsList.getItemCount() == 1 )
                {
                    JOptionPane.showMessageDialog(null,"Must be at least one keyword","At least one keyword",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                 try {
                     _downloadController.startDownload(keywordsArray);
                 } catch (TwitterException ex) {
                     Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        }        
    }
   
   
    //http://stackoverflow.com/questions/19407038/java-actionlistener-actionperformed-in-different-class
    
class MenuKeywordListener implements ActionListener {
    
  public void actionPerformed(ActionEvent e) 
   {       
        MainForm mf = MainForm.getSingletonInstance();
        mf.getKeywordsFormView().setVisible(true);       
    }   
}

class ResultsListener implements ActionListener {
    
  public void actionPerformed(ActionEvent e) 
   {       
        MainForm mf = MainForm.getSingletonInstance();
        mf.getResultsFormView().setVisible(true);   
        _resultsFormView.populateComboKeywords(_keywordsModel);
    }   
}

class DownloadListener implements ActionListener 
{       
    public void actionPerformed(ActionEvent e) 
    {   
        String[] keywords = keywordsList.getSelectedItems();
        MainForm mf = MainForm.getSingletonInstance();  
        File f = new File("twitter4j.properties");
        if(!f.exists()) 
        { 
            JOptionPane.showMessageDialog(null,"You have to login first","No credentials",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ( keywords.length < 1 )
        {
            JOptionPane.showMessageDialog(null,"You didn't choose any keywords","Choose a keyword",JOptionPane.WARNING_MESSAGE);
            return;
        }               
            try {             
                _downloadController.startDownload(  keywords );            
                setRunning(true);               
               disableComponents();
               _keywordsFormView.setIsRunning(true);
               JOptionPane.showMessageDialog(null,"The downloading process has just started, you will be informed once collected results","Downloading Started",JOptionPane.INFORMATION_MESSAGE);

            } catch (TwitterException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }      
     } 
    public String[] AndOperator ( String[] keywords )
    {
        String concatKeywords = new String();
        String[] keywordsArray = new String[1];
        for( int i = 0; i < keywords.length; i++)
        {
            concatKeywords = concatKeywords +" "+ keywords[i];
        }
        keywordsArray[0] = concatKeywords;      
        return keywordsArray;
    }
    }

class MenuSettingsListener implements ActionListener {
    
  public void actionPerformed(ActionEvent e) 
   {       
        MainForm mf = MainForm.getSingletonInstance();
        mf.getSettingsFormView().setVisible(true);       
    }   
}


class MenuLoginListener implements ActionListener 
{
    
  public void actionPerformed(ActionEvent e) 
   {       
        MainForm mf = MainForm.getSingletonInstance();
        LoginForm lf = mf.getLoginFormView();
        lf.setLoginForm(lf);
        lf.setVisible(true);       
    }
}

class ExitListener implements ActionListener
{
    public void actionPerformed(ActionEvent e) 
    {
        System.exit(0);
    }    
}
}