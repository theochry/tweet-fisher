/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.dao;

import java.math.BigDecimal;
import org.hibernate.Query;
import tfisher.entities.Tweet;

/**
 *
 * @author Theodore Chrysochoidis
 */
public class TweetDAOHibernateImpl extends HibernateDAO<Tweet, BigDecimal> implements TweetDAO
{

    public Tweet findByKeyword(String keyword) 
    {
        Tweet tweet = null;
        String sql = "SELECT p FROM Tweet p WHERE p.text like :keyword";         
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("keyword", "%"+keyword.toLowerCase()+"%");
        tweet = (Tweet) findMany(query);
        return tweet;
    }
    
}
