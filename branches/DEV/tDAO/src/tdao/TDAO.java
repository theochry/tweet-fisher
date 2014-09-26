/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao;

import tdao.entities.Users;
import tdao.session.UsersManager;
import tdao.session.UsersManagerImpl;

/**
 *
 * @author fiontor
 */
public class TDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        UsersManager usermanager = new UsersManagerImpl();
 
Users wanted = usermanager.findByPersonName("Eneng");
 System.out.println( wanted.getId());
   
    }
    
}
