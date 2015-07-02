/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.utils;

import org.springframework.stereotype.Component;
import tfisher.entities.Media;
import tfisher.entities.Tweet;
import tfisher.entities.User;
import tfisher.session.MediaModelHibernateImpl;
import tfisher.session.TweetModelHibernateImpl;
import tfisher.session.UserModelHibernateImpl;

/**
 *
 * @author Theodore Chrysochoidis
 */
@Component
public class StoreManager implements Runnable
{
    private TweetModelHibernateImpl _tweetManager = new TweetModelHibernateImpl();
    private UserModelHibernateImpl _userManager = new UserModelHibernateImpl();
    private MediaModelHibernateImpl _mediaManager = new MediaModelHibernateImpl(); 

    private User _user;
    private Tweet _tweet;
    private Media _media;

    public StoreManager(){}
    public StoreManager ( User user, Tweet tweet, Media media )
    {
        _user = user; _tweet = tweet; _media = media;
    }
    public void run() 
    {
        _userManager.saveNewUser(_user);        
        _tweetManager.saveNewTweet(_tweet);
        if ( _media.getIdStr()!= null )
        {  
           _mediaManager.saveNewMedia(_media);   
        }
    }
}