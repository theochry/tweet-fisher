/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.views;

import tfisher.controllers.KeywordsController;
import tfisher.dao.Keywords;

/**
 *
 * @author 13
 */
public class Main {

    public static void main(String[] args) {

        Keywords model = new Keywords();
        final KeywordsForm view = new KeywordsForm();
        KeywordsController controller = new KeywordsController(model, view);
        view.addController(controller);
       

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.setVisible(true);
            }
        });
    }

}