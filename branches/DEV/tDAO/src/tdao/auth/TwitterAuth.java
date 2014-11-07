/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.auth;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author 13
 */
public class TwitterAuth 
{
    
    private ConfigurationBuilder _conficurationBuilder = new ConfigurationBuilder();
    private  TwitterStream _twitterStream = new TwitterStreamFactory(_conficurationBuilder.build()).getInstance(); 
    private static TwitterAuth _twitterAuth;
    
    
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
}
