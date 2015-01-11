/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.views;

import DTO.TweetDTO;
import com.google.common.collect.Multimap;
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
import javax.swing.table.DefaultTableModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import tfisher.auth.TwitterAuth;
import tfisher.controllers.DownloadController;
import tfisher.controllers.KeywordsController;
import tfisher.controllers.LoginController;
import tfisher.controllers.Notifier;
import tfisher.controllers.ResultsController;
import tfisher.controllers.StoreTweetController;
import tfisher.controllers.StoreUserController;
import tfisher.dao.Keywords;
import tfisher.entities.Tweet;
import tfisher.entities.User;
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
    
    //controllers
    public static DownloadController _downloadController;  
    public static LoginController _loginController;
    public static KeywordsController _keywordsController;
    public static StoreUserController _storeUserController;
    public static StoreTweetController _storeTweetController;
    public static ResultsController _resultsController;
    public static Notifier _notifier;
    
    //others
   
     public static TwitterAuth _twitterAuth;
     
    
       MainForm(Keywords keywords){
        this._keywordsModel = keywords;
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
    
 
    public int getSeconds()
    {
        return ( Integer ) secondsSpinner.getValue();
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
        windowTimePanel = new java.awt.Panel();
        secondsSpinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, keywordsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(71, 71, 71))
            .addGroup(keywordsPanelLayout.createSequentialGroup()
                .addGroup(keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        secondsSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(60), Integer.valueOf(1), null, Integer.valueOf(1)));

        jLabel2.setText("Give a window time in seconds [ default is 60 ]:");

        javax.swing.GroupLayout windowTimePanelLayout = new javax.swing.GroupLayout(windowTimePanel);
        windowTimePanel.setLayout(windowTimePanelLayout);
        windowTimePanelLayout.setHorizontalGroup(
            windowTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, windowTimePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(windowTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(windowTimePanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(windowTimePanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(secondsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        windowTimePanelLayout.setVerticalGroup(
            windowTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(windowTimePanelLayout.createSequentialGroup()
                .addGroup(windowTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(secondsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(keywordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(startBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(windowTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(windowTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startBtn)
                .addGap(19, 19, 19)
                .addComponent(keywordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
     _storeUserController = (StoreUserController)context.getBean("storeUserController"); 
     _storeTweetController = (StoreTweetController)context.getBean("storeTweetController"); 
     _resultsController = (ResultsController)context.getBean("resultsController"); 
     _notifier = (Notifier)context.getBean("notifier"); 
     
     user.addObserver(_storeUserController);
     tweet.addObserver(_storeTweetController);
     tweet.addObserver(_notifier);
     
     _keywordsController.setDependencies(_keywordsModel, _keywordsFormView);
     _loginController.setDependencies(_twitterAuth, _loginFormView);   
     _downloadController.setDependencies(_keywordsModel, _tweetDTO, _twitterAuth, user, tweet);       
     _resultsController.setDependencies(tweet, _resultsFormView);
    
     
     _loginFormView.addController(_loginController );         
     _keywordsFormView.addController( _keywordsController );  
     _settingsFormView.addController( _keywordsController );  
     _resultsFormView.addController(_resultsController);
     _keywordsModel.addObserver( _mainForm );   
     
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private java.awt.List keywordsList;
    private java.awt.Panel keywordsPanel;
    private javax.swing.JMenuItem loginJmenu;
    private javax.swing.JRadioButton orRB;
    private javax.swing.JMenuItem resultsJmenu;
    private javax.swing.JMenu searchJmenu;
    private javax.swing.JMenuItem searchKeywordsJmenu;
    private javax.swing.JSpinner secondsSpinner;
    private javax.swing.JMenuItem settingsJmenu;
    private javax.swing.JButton startBtn;
    private java.awt.Panel windowTimePanel;
    // End of variables declaration//GEN-END:variables


    //auto to update kaleitai gia kathe notify apo opoiondhpote typo antikeimenou
    public void update(Observable obs, Object x) {
         MainForm mf = MainForm.getSingletonInstance();
        if( obs instanceof Keywords && "add".equals(((Keywords) obs).getState()) )
        {                
             mf.keywordsList.add(((Keywords) obs).getLatestKeyword());               
        }
        else if ( obs instanceof Keywords && "remove".equals(((Keywords) obs).getState()))
        {           
            mf.keywordsList.remove(((Keywords) obs).getKeywordToDelete());
        }
        if ( obs instanceof TweetDTO)
        {                  
          
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

class DownloadListener implements ActionListener {
   
                
               
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
                _downloadController.startDownload( mf.getSeconds() * 1000, keywords );
            } catch (TwitterException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if ( andRB.isSelected() )
        {            
            try { 
                _downloadController.startDownload( mf.getSeconds() * 1000, AndOperator( keywords ) );
                
            } catch (TwitterException ex) {
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
        MainForm mf = MainForm.getSingletonInstance();
        //mf.getLoginFormView().setVisible(true);   
        //GetTokens gt = new GetTokens();
         (new Thread(new GetAccessToken())).start();
        // gt.storeProperties();
        //gt.logindo();     
        //JOptionPane.showMessageDialog(null,"The program will close now, open it again and you are ready!","Program will close",JOptionPane.WARNING_MESSAGE);     
       // System.exit(0);
    }

        
    }



}
