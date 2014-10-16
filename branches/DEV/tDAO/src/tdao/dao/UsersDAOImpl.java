/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.dao;

/**
 *
 * @author theodore
 */


import tdao.entities.Users;
import tdao.utils.HibernateUtil;
import java.math.BigDecimal;
import org.hibernate.Query;
 

public class UsersDAOImpl extends HibernateDAO<Users, BigDecimal> implements UsersDAO {
    public Users findByName(String name) {
        Users user = null;
        String sql = "SELECT p FROM Users p WHERE p.name = :name";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("name", name);
        user = findOne(query);
        return user;
    }
   
}