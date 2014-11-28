/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.auth;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author  Theodore Chrysochoidis
 */
public class TwitterAuth 
{
    
    private ConfigurationBuilder _conficurationBuilder = new ConfigurationBuilder();
    private  TwitterStream _twitterStream = new TwitterStreamFactory().getInstance();;
    private static TwitterAuth _twitterAuth;  
    private AccessToken _accessToken = null;
    private RequestToken _requestToken;
    private Twitter _twitter;
  
    public static TwitterAuth getSingletonInstance()
    {
        if ( null == _twitterAuth )
        {
            _twitterAuth = new TwitterAuth();             
        }
        return _twitterAuth;
    }  
     
    public TwitterStream getTwitterStream()
    {
        return _twitterStream;
    }  
    
    public String authenticateConsumer ( String consumerKey, String consumerSecret ) throws TwitterException
    {
        String message = new String();
        ConfigurationBuilder setOAuthConsumerSecret = _conficurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret);
        TwitterFactory tf = new TwitterFactory( _conficurationBuilder.build());
        _twitter = tf.getInstance();
        try 
        {     
            _requestToken = _twitter.getOAuthRequestToken();
            if (null == _accessToken) 
            {               
                message =_requestToken.getAuthorizationURL();
            }
        } catch (TwitterException te )
        {
            message=null;
        }
        return message;
    }
    
    public void getTwitterAccessToken( RequestToken requestToken, String pin )
    {
        try {
            _accessToken = _twitter.getOAuthAccessToken(requestToken, pin);            
        } catch (TwitterException ex) 
        {
            if (!_twitter.getAuthorization().isEnabled()) 
            {

            }
        }       
    }
    
    public RequestToken getRequestToken()
    {
        return _requestToken;
    }
    
    public AccessToken getAccessToken()
    {
        return _accessToken;
    }
}
