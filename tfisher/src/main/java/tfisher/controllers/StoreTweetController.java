/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import java.util.Observable;
import java.util.Observer;
import org.springframework.stereotype.Component;
import tfisher.entities.Tweet;
import tfisher.session.TweetModelHibernateImpl;

/**
 *
 * @author Theodore Chrysochoidis
 */
@Component
public class StoreTweetController implements Observer
{
    private TweetModelHibernateImpl _tweetManager = new TweetModelHibernateImpl();    

    public void update(Observable o, Object arg) 
    {
       Tweet tweet = new Tweet();
       tweet.setCreatedAt(((Tweet) o).getCreatedAt());
       tweet.setIdStr(((Tweet) o).getIdStr());
       tweet.setMedias(((Tweet) o).getMedias());
       tweet.setNLang(((Tweet) o).getNLang());
       tweet.setRetweetCount(((Tweet) o).getRetweetCount());
       tweet.setSource(((Tweet) o).getSource());
       tweet.setText(((Tweet) o).getText());
       tweet.setUser(((Tweet) o).getUser());
       
       _tweetManager.saveNewTweet(tweet);
    }
}
