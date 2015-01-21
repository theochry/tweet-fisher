/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.dao;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import tfisher.entities.Tweet;

/**
 *
 * @author Theodore Chrysochoidis
 */
public class TweetDAOHibernateImpl extends HibernateDAO<Tweet, BigDecimal> implements TweetDAO
{
 //https://community.oracle.com/thread/425742
    public List<Tweet> findByKeyword(String keyword) 
    {
        Tweet tweet = null;
       
        String sql = "SELECT p FROM Tweet p WHERE UPPER (p.text) LIKE UPPER (:keyword) ";         
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("keyword", "%"+keyword+"%");         
        return findMany(query);
        
    }
    
}
