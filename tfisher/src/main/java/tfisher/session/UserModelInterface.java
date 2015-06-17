/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.session;

/**
 *
 * @author  Theodore Chrysochoidis
 */

import tfisher.entities.User;
import java.math.BigDecimal;
import java.util.List;
 

public interface UserModelInterface {
 
    public User findByUserName(String name);
 
    public List<User> loadAllUsers();

    public void saveNewUser(User user);
 
    public User findUserById(BigDecimal id);
 
    public void deleteUser(User user);
}