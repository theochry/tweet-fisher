/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import java.util.Observable;
import java.util.Observer;
import org.springframework.stereotype.Component;
import tfisher.entities.User;
import tfisher.session.UserModelHibernateImpl;

/**
 *
 * @author Theodore Chrysochoidis
 */
@Component
public class StoreUserController implements Observer
{
    private UserModelHibernateImpl _userManager = new UserModelHibernateImpl();    
    public void update(Observable o, Object arg) 
    {
        User user = new User();
        user.setCreatedAt(((User) o).getCreatedAt());
        user.setFollowersCount(((User) o).getFollowersCount());
        user.setFriendsCount(((User) o).getFriendsCount());
        user.setIdStr(((User) o).getIdStr());
        user.setLang(((User) o).getLang());
        user.setNTimeZone(((User) o).getNTimeZone());
        user.setName(((User) o).getName());
        user.setScreenName(((User) o).getScreenName());
        user.setStatusesCount(((User) o).getStatusesCount());  
        System.out.println("STOREUSERCONTROLLER ID IS: "+user.getIdStr());
        _userManager.saveNewUser(user);
        
    }
    
}
