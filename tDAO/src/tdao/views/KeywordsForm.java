/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.views;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

import tdao.controllers.KeywordsController;
import tdao.dao.Keywords;
/**
 *
 * @author theodore
 */
public class KeywordsForm extends javax.swing.JFrame implements Observer, ActionListener{  
  public String _latestKeyword;
  public String _keywordToDelete;
  public ArrayList<String> _keywordsArrayList = new ArrayList<String>();
  
  
    public KeywordsController _keywordsController;
    public void addController( KeywordsController controller )
    {
        _keywordsController = controller;
    }

  public String getKeywordToDelete()
  {
      return _keywordToDelete;
  }
  public KeywordsForm() 
    {        
        initComponents();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        createControls();       
    } 
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addKeywordBtn = new javax.swing.JButton();
        keywordsJtxt = new javax.swing.JTextField();
        keywordsList = new java.awt.List();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        removeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addKeywordBtn.setText("add");
        addKeywordBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addKeywordBtnMouseClicked(evt);
            }
        });
        addKeywordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addKeywordBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Add a keyword and press add");

        jLabel2.setText("Added keywords");

        removeBtn.setText("remove");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(keywordsList, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeBtn))
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(keywordsJtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addKeywordBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2))
                .addContainerGap(196, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keywordsJtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addKeywordBtn)
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keywordsList, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeBtn))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        addKeywordBtn.getAccessibleContext().setAccessibleName("addKeywordBtn");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addKeywordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addKeywordBtnActionPerformed
       
    }//GEN-LAST:event_addKeywordBtnActionPerformed

    private void addKeywordBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addKeywordBtnMouseClicked
     
       
    }//GEN-LAST:event_addKeywordBtnMouseClicked

    
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
            java.util.logging.Logger.getLogger(KeywordsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KeywordsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KeywordsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KeywordsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {              
                
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addKeywordBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField keywordsJtxt;
    private java.awt.List keywordsList;
    private javax.swing.JButton removeBtn;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable obs, Object obj) {
       System.out.println("KEYWORDS FORM update called!"+((Keywords) obs).getState());      
    }   
       
    public ArrayList<String> getKeywords()
    {
        return _keywordsArrayList;
    }
    public String getLatestKeyword()
    {
        return _latestKeyword;
    }
    
    public void createControls()
    {      
      removeBtn.addActionListener(new RemoveKeywordListener() );      
      keywordsJtxt.addActionListener(new AddKeywordListener());  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    class RemoveKeywordListener implements ActionListener
    {       
        public void actionPerformed(ActionEvent e) 
        {
           
            String item = keywordsList.getSelectedItem();
            if ( item != null )
            {
                 _keywordToDelete = item;
                 keywordsList.remove(item);
                 _keywordsController.removeKeyword( _keywordToDelete );
            }
            else
            {
               JOptionPane.showMessageDialog(null,"You didn't choose any keywords","Choose a keyword",JOptionPane.WARNING_MESSAGE);
            }
        }        
    }
    
    class AddKeywordListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            _latestKeyword = keywordsJtxt.getText();
                
            if ( _keywordsController.keywordExist( _latestKeyword ) )
            {
                JOptionPane.showMessageDialog(null,"The keyword " +_latestKeyword +" already exist","Keyword Already exist",JOptionPane.WARNING_MESSAGE);
                keywordsJtxt.setText("");
                return;
            } 
            else if ( !_keywordsController.keywordExist( _latestKeyword )  )
            {
                if (  _keywordsController.checkKeywordPattern( _latestKeyword ) )
                {
                    keywordsList.add(keywordsJtxt.getText());
                    _keywordsArrayList.add(keywordsJtxt.getText());
                    keywordsJtxt.setText("");
                    _keywordsController.setKeyword( _latestKeyword );
                }             
                else
                {
                    keywordsJtxt.setText("");
                    JOptionPane.showMessageDialog(null,"The keyword " +_latestKeyword +" contains no english characters","Try another keyword",JOptionPane.WARNING_MESSAGE);

                }
            }       
        }
        
    }
  


}
