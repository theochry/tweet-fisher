/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import tfisher.dao.HibernateUtil;
import tfisher.dao.TweetDAO;
import tfisher.dao.TweetDAOHibernateImpl;
import tfisher.entities.Tweet;

/**
 *
 * @author Theodore Chrysochoidis
 */
public class TweetModelHibernateImpl implements TweetModelInterface
{
    private TweetDAO tweetDAO = new TweetDAOHibernateImpl();
    public Tweet findByKeyword(String keyword) 
    {
        Tweet tweet = null;
        try {
            HibernateUtil.beginTransaction();
            tweet = tweetDAO.findByKeyword(keyword);
            HibernateUtil.commitTransaction();            
        } catch (NonUniqueResultException ex) {
            System.out.println("1");
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
           ex.printStackTrace();
        }
        return tweet;
        
    }

    public List<Tweet> loadAllTweets() 
    {
        List<Tweet> allTweets = new ArrayList<Tweet>();
        try {
            HibernateUtil.beginTransaction();
            allTweets = tweetDAO.findAll(Tweet.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Cant load all tweets");
        }
        return allTweets;
    }

    public void saveNewTweet(Tweet tweet) 
    {
         try {
            HibernateUtil.beginTransaction();
            tweetDAO.save(tweet);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("saveNewTweetException");
            HibernateUtil.rollbackTransaction();
        }
    }

    public Tweet findTweetById(BigDecimal id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteTweet(Tweet tweet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
