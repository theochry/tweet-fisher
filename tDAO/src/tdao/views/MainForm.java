/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.views;

import DTO.TweetDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.TimeZone;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import tdao.controllers.DownloadController;
import tdao.controllers.KeywordsController;
import tdao.controllers.ResultsController;
import tdao.dao.Keywords;


/**
 *
 * @author theodore
 */

 
public class MainForm extends javax.swing.JFrame implements Observer  {
  
    //views
    private static MainForm _mainForm;
    public static KeywordsForm _keywordsFormView = new KeywordsForm();   
    public static ResultsForm _resultsFormView = new ResultsForm();
    
    //models
    public static TweetDTO _tweetDTO = new TweetDTO();    
    public static Keywords _keywordsModel = new Keywords();
    
    //controllers
    public static DownloadController _downloadController;
    public static ResultsController _resultsController;
    
    //others
     DefaultTableModel _model;
    
       MainForm(Keywords keywords){
        this._keywordsModel = keywords;
    }
    
    
    
    public MainForm() 
    {
        initComponents();       
        createControls();
        _model = (DefaultTableModel) resultsTable.getModel();        
        ButtonGroup buttonGroup = new ButtonGroup();        
        buttonGroup.add(orRB);
        buttonGroup.add(andRB); 
    }
    
   public void createControls()
  {       
        searchKeywordsJmenu.addActionListener(new MenuKeywordListener());      
        resultsJmenu.addActionListener(new MenuResultsListener());
        startBtn.addActionListener ( new DownloadListener() );         
   }
   
    public Keywords getKeywords()
    {
        return _keywordsModel;        
    }
    
    public KeywordsForm getKeywordsFormView()
    {
        return _keywordsFormView;
    }  
    
    public ResultsForm getResultsFormView()
    {
        return _resultsFormView;
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
        resultsPanel = new java.awt.Panel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        searchJmenu = new javax.swing.JMenu();
        searchKeywordsJmenu = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        resultsJmenu = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Results");

        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Author", "Text"
            }
        ));
        jScrollPane1.setViewportView(resultsTable);

        javax.swing.GroupLayout resultsPanelLayout = new javax.swing.GroupLayout(resultsPanel);
        resultsPanel.setLayout(resultsPanelLayout);
        resultsPanelLayout.setHorizontalGroup(
            resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultsPanelLayout.createSequentialGroup()
                .addGroup(resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resultsPanelLayout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jLabel3))
                    .addGroup(resultsPanelLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        resultsPanelLayout.setVerticalGroup(
            resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem1.setText("Login");
        jMenu1.add(jMenuItem1);

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

        jMenuItem6.setText("Locations");
        searchJmenu.add(jMenuItem6);

        jMenuBar1.add(searchJmenu);

        jMenu3.setText("Results");

        resultsJmenu.setText("Show results");
        jMenu3.add(resultsJmenu);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Time window");

        jMenuItem9.setText("Set time");
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(keywordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(windowTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(resultsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(windowTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(keywordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
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
        KeywordsController keywordsController = new KeywordsController( _keywordsModel, _keywordsFormView );
         _downloadController = new DownloadController( _keywordsModel, _tweetDTO ); 
        _resultsController = new ResultsController ( _tweetDTO, _mainForm );   
        
        _keywordsFormView.addController( keywordsController );       
        _keywordsModel.addObserver( _mainForm );  
        _tweetDTO.addObserver( _mainForm );         
      
        final Date currentTime = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println("UTC time: " + sdf.format(currentTime));
        
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private java.awt.List keywordsList;
    private java.awt.Panel keywordsPanel;
    private javax.swing.JRadioButton orRB;
    private javax.swing.JMenuItem resultsJmenu;
    private java.awt.Panel resultsPanel;
    private javax.swing.JTable resultsTable;
    private javax.swing.JMenu searchJmenu;
    private javax.swing.JMenuItem searchKeywordsJmenu;
    private javax.swing.JSpinner secondsSpinner;
    private javax.swing.JButton startBtn;
    private java.awt.Panel windowTimePanel;
    // End of variables declaration//GEN-END:variables


    //auto to update kaleitai gia kathe notify apo opoiondhpote typo antikeimenou
    public void update(Observable obs, Object x) {
         MainForm mf = MainForm.getSingletonInstance();
        if( obs instanceof Keywords && ((Keywords) obs).getState() == "add" )
        {                
             mf.keywordsList.add(((Keywords) obs).getLatestKeyword());               
        }
        else if ( obs instanceof Keywords && ((Keywords) obs).getState() == "remove")
        {           
            mf.keywordsList.remove(((Keywords) obs).getKeywordToDelete());
        }
        if ( obs instanceof TweetDTO)
        {                  
           showResults();
        }
//        else 
//        {
//            System.out.println("change state tweetDTO");
//        }
    }
     public void showResults()
    {        
        
        ArrayList <TweetDTO> tempDTO =  _tweetDTO.getTweetDTOArray();
        if ( !tempDTO.isEmpty() )
        {       
            Iterator< TweetDTO > it = tempDTO.iterator();
             System.out.println("results are: "+tempDTO.size());
            _model.setRowCount(0);
            int currentElement = 0;           
            for ( currentElement = 0; currentElement < tempDTO.size(); currentElement++ )
           {
                _model.insertRow(_model.getRowCount(), new Object[]{ tempDTO.get(currentElement).getCreator(), tempDTO.get(currentElement).getTweetText()});  
           }
           
        }//end of if
        else
        {
           JOptionPane.showMessageDialog(null,"No results to show","No results",JOptionPane.WARNING_MESSAGE);
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

class MenuResultsListener implements ActionListener 
{
    public void showResults()
    {        
        resultsPanel.setVisible(true);
        ArrayList <TweetDTO> tempDTO =  _tweetDTO.getTweetDTOArray();  
        if ( !tempDTO.isEmpty() )
        {       
            Iterator< TweetDTO > it = tempDTO.iterator();
            _model.setRowCount(0);
            int currentElement = 0;
            System.out.println("results are: "+tempDTO.size());
            for ( currentElement = 0; currentElement < tempDTO.size(); currentElement++ )
           {
                _model.insertRow(_model.getRowCount(), new Object[]{ tempDTO.get(currentElement).getTweetText(), tempDTO.get(currentElement).getCreator()});  
           }
           
        }//end of if
        else
        {
           JOptionPane.showMessageDialog(null,"No results to show","No results",JOptionPane.WARNING_MESSAGE);
        }
       
    }
    public void actionPerformed(ActionEvent e) 
    {       
        showResults();
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
            _downloadController.startDownload( mf.getSeconds() * 1000, keywords );
        }
        else if ( andRB.isSelected() )
        {            
             _downloadController.startDownload( mf.getSeconds() * 1000, AndOperator( keywords ) ); 
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

}
