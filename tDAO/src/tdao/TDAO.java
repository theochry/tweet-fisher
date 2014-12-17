/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao;

import tfisher.entities.Users;
import tfisher.session.UsersModelInterface;
import tfisher.session.UsersModelHibernateImpl;

/**
 *
 * @author Theodore Chrysochoidis
 */
public class TDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        UsersModelInterface usermanager = new UsersModelHibernateImpl();
 
Users wanted = usermanager.findByPersonName("Eneng");
 System.out.println( wanted.getId());
   
    }
    
}
