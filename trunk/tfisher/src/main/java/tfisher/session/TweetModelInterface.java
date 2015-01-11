/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.session;

import java.util.List;
import java.math.BigDecimal;
import tfisher.entities.Tweet;

/**
 *
 * @author Theodore Chrysochoidis
 */
public interface TweetModelInterface 
{    
    public Tweet findByKeyword(String keyword); 
    
    public List<Tweet> loadAllTweets();
    
    public void saveNewTweet(Tweet tweet);
 
    public Tweet findTweetById(BigDecimal id);
 
    public void deleteTweet(Tweet tweet);
}
