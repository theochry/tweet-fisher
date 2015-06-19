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
    private static int start = 0, end = 10;   
      public List<Tweet> findByKeyword(String keyword, boolean sticky, int start, int end ) 
    {       
        Tweet tweet = null;       
        String sql = "SELECT p FROM Tweet p WHERE "
                + "p.stickyBit is (:sticky) AND UPPER (p.text) LIKE UPPER (:keyword) ";         
        Query query = HibernateUtil.getSession().createQuery(sql)
        .setParameter("keyword", "%"+keyword+"%")
        .setParameter("sticky", sticky);     
        query.setFirstResult( start );
        query.setMaxResults( end );      
        return findMany(query); 
    }
    
      
      
      public void updateStickyBit(String keyword, List <Tweet> tweets )
    {
         for (Tweet tweet : tweets) 
         {
             String sqlUpdate = "UPDATE Tweet Set stickyBit = true where UPPER (text) LIKE UPPER (:keyword) AND id_str = :id_str";
             Query queryUpdate = HibernateUtil.getSession().createQuery(sqlUpdate).setParameter("keyword", "%"+keyword+"%").setParameter("id_str", tweet.getIdStr());        
             queryUpdate.executeUpdate();             
         }
        
    }
}
