/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.session;

/**
 *
 * @author  Theodore Chrysochoidis
 */


import tdao.dao.UsersDAOHibernateImpl;
import tdao.entities.Users;
import tdao.utils.HibernateUtil;
import tdao.dao.UsersDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
 

public class UsersModelHibernateImpl implements UsersModelInterface {
 
    private UsersDAO usersDAO = new UsersDAOHibernateImpl();
 
    public Users findByPersonName(String name) {
        Users person = null;
        try {
            HibernateUtil.beginTransaction();
            person = usersDAO.findByName(name);
            HibernateUtil.commitTransaction();            
        } catch (NonUniqueResultException ex) {
            System.out.println("Handle your error here");
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
        }
        return person;
    }
 
    public List<Users> loadAllPersons() {
        List<Users> allPersons = new ArrayList<Users>();
        try {
            HibernateUtil.beginTransaction();
            allPersons = usersDAO.findAll(Users.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
        }
        return allPersons;
    }
 
    public void saveNewPerson(Users user) {
        try {
            HibernateUtil.beginTransaction();
            usersDAO.save(user);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("saveNewPersonException");
            HibernateUtil.rollbackTransaction();
        }
    }
        
 
    public Users findPersonById(BigDecimal id) {
        Users user = null;
        try {
            HibernateUtil.beginTransaction();
            user = (Users) usersDAO.findByID(Users.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
        }
        return user;
    }
 
    public void deletePerson(Users user) {
        try {
            HibernateUtil.beginTransaction();
            usersDAO.delete(user);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
            HibernateUtil.rollbackTransaction();
        }
    }

   
}