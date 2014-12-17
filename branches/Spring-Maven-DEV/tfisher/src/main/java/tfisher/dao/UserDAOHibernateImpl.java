/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.dao;

/**
 *
 * @author  Theodore Chrysochoidis
 */


import tfisher.entities.User;
import java.math.BigDecimal;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
 
@Component
public class UserDAOHibernateImpl extends HibernateDAO<User, BigDecimal> implements UserDAO {
    public User findByName(String name) {
        User user = null;
        String sql = "SELECT p FROM User p WHERE p.name = :name";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("name", name);
        user = findOne(query);
        return user;
    }
   
}