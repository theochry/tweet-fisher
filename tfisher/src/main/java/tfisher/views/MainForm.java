/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.views;

import DTO.TweetDTO;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import tfisher.auth.TwitterAuth;
import tfisher.controllers.DownloadController;
import tfisher.controllers.KeywordsController;
import tfisher.controllers.LoginController;
import tfisher.controllers.ResultsController;
import tfisher.controllers.StoreTweetController;
import tfisher.controllers.StoreUserController;
import tfisher.controllers.StoreMediaController;
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
    public static Login _loginFormView;
    public static ResultsForm _resultsFormView;
    
    
    //models
    public static TweetDTO _tweetDTO;  
    public  static Keywords _keywordsModel;
    public static User user;
    public static Tweet tweet;
    public static Media media;
    
    //controllers
    public static DownloadController _downloadController;  
    public static LoginController _loginController;
    public static KeywordsController _keywordsController;
    public static StoreUserController _storeUserController;
    public static StoreTweetController _storeTweetController;
    public static StoreMediaController _storeMediaController;
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
        ButtonGroup buttonGroup = new ButtonGroup();        
        buttonGroup.add(orRB);
        buttonGroup.add(andRB);
        setResizable(false);
    }
    
   public void createControls()
  {       
        searchKeywordsJmenu.addActionListener(new MenuKeywordListener());      
        settingsJmenu.addActionListener( new MenuSettingsListener());
        startBtn.addActionListener ( new DownloadListener() );     
        loginJmenu.addActionListener( new MenuLoginListener() );
        resultsJmenu.addActionListener(new ResultsListener());
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
    public Login getLoginFormView()
    {
        return _loginFormView;
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
        startBtn = new javax.swing.JButton();
        keywordsPanel = new java.awt.Panel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        orRB = new javax.swing.JRadioButton();
        andRB = new javax.swing.JRadioButton();
        keywordsList = new java.awt.List();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        loginJmenu = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        searchJmenu = new javax.swing.JMenu();
        searchKeywordsJmenu = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        settingsJmenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        resultsJmenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startBtn.setText("Start Download");

        jLabel1.setText("Added Keywords");

        orRB.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        orRB.setSelected(true);
        orRB.setText("Search with [ OR ] operator");

        andRB.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        andRB.setText("Search with [ AND ] operator");

        keywordsList.setMultipleMode(true);

        javax.swing.GroupLayout keywordsPanelLayout = new javax.swing.GroupLayout(keywordsPanel);
        keywordsPanel.setLayout(keywordsPanelLayout);
        keywordsPanelLayout.setHorizontalGroup(
            keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keywordsPanelLayout.createSequentialGroup()
                .addGroup(keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(keywordsPanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel1))
                    .addGroup(keywordsPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(andRB)
                            .addComponent(orRB)))
                    .addGroup(keywordsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(keywordsPanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(keywordsList, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        keywordsPanelLayout.setVerticalGroup(
            keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keywordsPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keywordsList, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(orRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(andRB)
                .addGap(51, 51, 51))
        );

        jMenu1.setText("File");

        loginJmenu.setText("Login");
        jMenu1.add(loginJmenu);

        jMenuItem2.setText("About");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Exit");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        searchJmenu.setText("Search");
        searchJmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJmenuActionPerformed(evt);
            }
        });

        searchKeywordsJmenu.setText("Keywords");
        searchJmenu.add(searchKeywordsJmenu);

        jMenuItem5.setText("Topics");
        searchJmenu.add(jMenuItem5);

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
                .addGap(0, 74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keywordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(startBtn))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(startBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keywordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
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
      _tweetDTO = (TweetDTO)context.getBean("tweetDTO");
      _keywordsFormView = (KeywordsForm)context.getBean("keywordsForm");     
      _resultsFormView = (ResultsForm)context.getBean("resultsForm");
      
      _twitterAuth = (TwitterAuth)context.getBean("twitterAuth");
      _settingsFormView = (Settings)context.getBean("settings");  
     _loginController = (LoginController)context.getBean("loginController"); 
     _downloadController = (DownloadController)context.getBean("downloadController"); 
     _keywordsController = (KeywordsController)context.getBean("keywordsController"); 
     _loginFormView = (Login)context.getBean("login"); 
     user = (User)context.getBean("user"); 
     tweet = (Tweet)context.getBean("tweet"); 
     //media = (Media)context.getBean("media"); 
     _storeUserController = (StoreUserController)context.getBean("storeUserController"); 
     _storeTweetController = (StoreTweetController)context.getBean("storeTweetController"); 
     _storeMediaController = (StoreMediaController)context.getBean("storeMediaController"); 
     _resultsController = (ResultsController)context.getBean("resultsController"); 
     _keywordsReloader = (KeywordsReloader)context.getBean("keywordsReloader");
     _keywordsHistory = (History)context.getBean("history"); 
     _notification = (Notification)context.getBean("notification"); 
    
     media = new Media();
     user.addObserver(_storeUserController);
     tweet.addObserver(_storeTweetController);
     media.addObserver(_storeMediaController);
     _storeTweetController.addObserver(_notification);
     
     
     _keywordsController.setDependencies(_keywordsModel, _keywordsFormView);
     _loginController.setDependencies(_twitterAuth, _loginFormView);   
     _downloadController.setDependencies(_keywordsModel, _twitterAuth, user, tweet, media, _keywordsReloader);       
     _resultsController.setDependencies(_resultsFormView);
    
     
     _loginFormView.addController(_loginController );         
     _keywordsFormView.addController( _keywordsController );  
     _settingsFormView.addController( _keywordsController );  
     _resultsFormView.addController(_resultsController);
     _keywordsModel.addObserver( _mainForm );   
     _keywordsModel.addObserver(_keywordsHistory);
     _keywordsReloader.addObserver(_downloadController);
     
     _tweetDTO.addObserver( _mainForm );        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {              
                 MainForm mf = MainForm.getSingletonInstance();
                 mf.setVisible(true);       
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton andRB;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.List keywordsList;
    private java.awt.Panel keywordsPanel;
    private javax.swing.JMenuItem loginJmenu;
    private javax.swing.JRadioButton orRB;
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
                 System.out.println("COUNT IS: "+keywordsList.getItemCount());
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
        if ( keywords.length < 1 )
        {
            JOptionPane.showMessageDialog(null,"You didn't choose any keywords","Choose a keyword",JOptionPane.WARNING_MESSAGE);
            return;
        }
           
        if ( orRB.isSelected() )
        {         
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
        else if ( andRB.isSelected() )
        {            
            try {            
                _downloadController.startDownload(  keywords );    
                setRunning(true);
                _keywordsFormView.setIsRunning(true);
                 disableComponents();
                 JOptionPane.showMessageDialog(null,"The downloading process has just started, you will be informed once collected results","Downloading Started",JOptionPane.INFORMATION_MESSAGE);
            } catch (TwitterException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
              
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


class MenuLoginListener implements ActionListener {
    
  public void actionPerformed(ActionEvent e) 
   {       
        //MainForm mf = MainForm.getSingletonInstance();
        //mf.getLoginFormView().setVisible(true);   
        
         //(new Thread(new GetAccessToken())).start();
         String[] arguments = new String[] {"VwbqJqEWEGPLI9ZeaeRv8g","WumU4D31KZSaESmq0ju82bXSnvC1e7Q64AV6GmBDCGo"};
      //GetAccessToken.main(arguments);
          
        //JOptionPane.showMessageDialog(null,"The program will close now, open it again and you are ready!","Program will close",JOptionPane.WARNING_MESSAGE);     
       // System.exit(0);
    }

        
    }



}
