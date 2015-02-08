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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import tfisher.controllers.ResultsController;
import tfisher.dao.Keywords;
import tfisher.entities.Tweet;
import tfisher.utils.HistoryParser;

/**
 *
 * @author Theodore Crysochoidis
 */
@Component
public class ResultsForm extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form ResultsForm
     */
      DefaultTableModel _model; 
    public ResultsForm() {
        super("Results");
        initComponents();
        centerFrame(); 
        createControls();
        _model = (DefaultTableModel) tweetsTable.getModel(); 
        
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    public ResultsController _resultsController;   
    
    public void addController( ResultsController controller )
    {
        _resultsController = controller;
    }

    public void createControls()
    {      
      fetchResults.addActionListener(new FetchTweetsListener());
      historyBtn.addActionListener(new FetchHistoryKeywords());
      oldTweetsBtn.addActionListener(new FetchTweetsListener());
    }
    public void populateComboKeywords( Keywords keywords )
    {
        String [] keywordsArray = keywords.getArrayOfKeywords();      
        keywordsCombo.removeAllItems();       
        for ( int i = 0; i < keywordsArray.length; i++ )
        { 
             keywordsCombo.addItem(keywordsArray[i]);
        }       
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        keywordsCombo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        fetchResults = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        panel1 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tweetsTable = new javax.swing.JTable();
        historyCobmo = new javax.swing.JComboBox();
        historyBtn = new javax.swing.JButton();
        oldTweetsBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        waitingResultsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        keywordsCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel1.setText("New keywords");

        fetchResults.setText("Fetch New Tweets");

        tweetsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Text", "Created at"
            }
        ));
        jScrollPane1.setViewportView(tweetsTable);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        historyBtn.setText("Fetch All Keywords");

        oldTweetsBtn.setText("Fetch All Tweets");

        jLabel2.setText("All keywords (history) ");

        waitingResultsLabel.setText("asd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(keywordsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fetchResults))
                            .addComponent(jLabel1))
                        .addGap(183, 183, 183)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(historyCobmo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(oldTweetsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(historyBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(waitingResultsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keywordsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fetchResults)
                    .addComponent(historyCobmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oldTweetsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waitingResultsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(ResultsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResultsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResultsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResultsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fetchResults;
    private javax.swing.JButton historyBtn;
    private javax.swing.JComboBox historyCobmo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox keywordsCombo;
    private javax.swing.JButton oldTweetsBtn;
    private java.awt.Panel panel1;
    private javax.swing.JTable tweetsTable;
    private javax.swing.JLabel waitingResultsLabel;
    // End of variables declaration//GEN-END:variables

    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class FetchTweetsListener implements ActionListener
    {     
        
        public void actionPerformed(ActionEvent e) 
        {
            waitingResultsLabel.setText("Loading results, please wait...");       
            
            String selectedItem = new String();
           
            _model.setRowCount(0);  
            if (e.getSource() == oldTweetsBtn)
            {               
                
                selectedItem = String.valueOf(historyCobmo.getSelectedItem());
                List <Tweet> tweets = _resultsController.findTweetsByKeyword(selectedItem,true);// ta tweets pou exw dei                  
                if ( tweets.isEmpty() )
                {
                    JOptionPane.showMessageDialog(null,"No results for this keyword","No results",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int i = 0;            
                for (Tweet tweet : tweets) 
                {    
                    i++;
                    _model.insertRow(_model.getRowCount(), new Object[]{ tweet.getText(), tweet.getCreatedAt()});
                }        
                //waitingResultsLabel.setText("");
                resizeColumnWidth(tweetsTable);
            }
            else if ( e.getSource() == fetchResults )
            {               
                 selectedItem = String.valueOf(keywordsCombo.getSelectedItem());
                 List <Tweet> tweets = _resultsController.findTweetsByKeyword(selectedItem,false);// ta tweets pou DEN exw dei
                 _resultsController.updateStickyBit(selectedItem);
                
                
                 if ( tweets.isEmpty() )
                {
                    JOptionPane.showMessageDialog(null,"No results for this keyword","No results",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int i = 0;
                //waitingResultsLabel.setText("Loading new results, please wait...");
                for (Tweet tweet : tweets) 
                {    
                    i++;
                    _model.insertRow(_model.getRowCount(), new Object[]{ tweet.getText(), tweet.getCreatedAt()});               
                }  
                System.out.println("results are: "+i);
                resizeColumnWidth(tweetsTable);
                waitingResultsLabel.setText("");
            }          
        }   
    }
    
    
    class FetchHistoryKeywords implements ActionListener
    {
        HistoryParser historyParser = new HistoryParser();
        public void actionPerformed(ActionEvent e) 
        {          
            ArrayList < String > historyKeywords;
            historyCobmo.removeAllItems();  
            try {
                    historyKeywords = historyParser.parseKeywords();
                         
                    for ( int i = 0; i < historyKeywords.size(); i++ )
                    { 
                         historyCobmo.addItem(historyKeywords.get(i));
                    }
            } catch (IOException ex) {
                Logger.getLogger(ResultsForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ResultsForm.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
    }
    
    public void resizeColumnWidth(JTable table) {
    final TableColumnModel columnModel = table.getColumnModel();
    for (int column = 0; column < table.getColumnCount(); column++) {
        int width = 50; // Min width
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer renderer = table.getCellRenderer(row, column);
            java.awt.Component comp = table.prepareRenderer(renderer, row, column);
            width = Math.max(comp.getPreferredSize().width, width);
        }
        columnModel.getColumn(column).setPreferredWidth(width);
    }
}
}