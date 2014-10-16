/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.views;


import DTO.TweetDTO;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static org.hibernate.util.CollectionHelper.arrayList;

import tdao.controllers.KeywordsController;
import tdao.controllers.Stream;
import tdao.dao.Keywords;
import twitter4j.Status;
import twitter4j.TwitterException;
/**
 *
 * @author theodore
 */
public class KeywordsForm extends javax.swing.JFrame implements Observer{  
  private String _latestKeyword;
  private String _keywordToDelete;
  private ArrayList<String> _keywordsArrayList = new ArrayList<String>();

  public String getKeywordToDelete()
  {
      return _keywordToDelete;
  }
  public KeywordsForm() 
    {        
        initComponents();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        ok.setVisible(false);
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addKeywordBtn = new javax.swing.JButton();
        keywordsJtxt = new javax.swing.JTextField();
        keywordsList = new java.awt.List();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
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

        ok.setText("ok");

        removeBtn.setText("remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(keywordsList, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(removeBtn)
                        .addGap(68, 68, 68)
                        .addComponent(ok))
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(keywordsJtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addKeywordBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2))
                .addContainerGap(94, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ok)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(keywordsList, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );

        addKeywordBtn.getAccessibleContext().setAccessibleName("addKeywordBtn");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addKeywordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addKeywordBtnActionPerformed
       _latestKeyword = keywordsJtxt.getText();
       keywordsList.add(keywordsJtxt.getText());
       _keywordsArrayList.add(keywordsJtxt.getText());
       keywordsJtxt.setText("");
       ok.doClick();
    }//GEN-LAST:event_addKeywordBtnActionPerformed

    private void addKeywordBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addKeywordBtnMouseClicked
     
       
    }//GEN-LAST:event_addKeywordBtnMouseClicked

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
       String item = keywordsList.getSelectedItem();
       if ( item != null )
       {
            _keywordToDelete = item;
            keywordsList.remove(item);
       }
       else
       {
          JOptionPane.showMessageDialog(null,"You didn't choose any keywords","Choose a keyword",JOptionPane.WARNING_MESSAGE);
       }
       
    }//GEN-LAST:event_removeBtnActionPerformed

    
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
                new KeywordsForm().setVisible(true);
                
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addKeywordBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField keywordsJtxt;
    private java.awt.List keywordsList;
    private javax.swing.JButton ok;
    private javax.swing.JButton removeBtn;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable obs, Object obj) {
       System.out.println("update called!"+((Keywords) obs).getState());
    }

    public void addController(ActionListener controller) {
        ok.addActionListener(controller);
        removeBtn.addActionListener(controller);
    }    
       
    public ArrayList<String> getKeywords()
    {
        return _keywordsArrayList;
    }
    public String getLatestKeyword()
    {
        return _latestKeyword;
    }
  
   
}
