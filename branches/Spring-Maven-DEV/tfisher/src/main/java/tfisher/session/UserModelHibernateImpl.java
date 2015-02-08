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


import tfisher.dao.UserDAOHibernateImpl;
import tfisher.entities.User;
import tfisher.dao.HibernateUtil;
import tfisher.dao.UserDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
 
@Component
public class UserModelHibernateImpl implements UserModelInterface {
 
    private UserDAO UserDAO = new UserDAOHibernateImpl();
 
    public User findByUserName(String name) {
        User person = null;
        try {
            HibernateUtil.beginTransaction();
            person = UserDAO.findByName(name);
            HibernateUtil.commitTransaction();            
        } catch (NonUniqueResultException ex) {
            System.out.println("1");
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
           ex.printStackTrace();
        }
        return person;
    }
 
    public List<User> loadAllUsers() {
        List<User> allPersons = new ArrayList<User>();
        try {
            HibernateUtil.beginTransaction();
            allPersons = UserDAO.findAll(User.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Can't load all users");
        }
        return allPersons;
    }
 
    public void saveNewUser(User user) {
        try {
            HibernateUtil.beginTransaction();
            UserDAO.save(user);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("saveNewPersonException");
            //ex.printStackTrace();
            //ex.getMessage();
            HibernateUtil.rollbackTransaction();
        }
    }
        
 
    public User findUserById(BigDecimal id) {
        User user = null;
        try {
            HibernateUtil.beginTransaction();
            user = (User) UserDAO.findByID(User.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Cant't find user with id "+id);
        }
        return user;
    }
 
    public void deleteUser(User user) {
        try {
            HibernateUtil.beginTransaction();
            UserDAO.delete(user);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
            HibernateUtil.rollbackTransaction();
        }
    }


   
}