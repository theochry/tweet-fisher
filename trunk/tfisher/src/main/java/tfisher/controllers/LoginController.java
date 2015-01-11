/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import tfisher.auth.TwitterAuth;
import tfisher.views.Login;
import twitter4j.TwitterException;
import org.springframework.stereotype.Component;
/**
 *
 * @author Theodore Chrysochoidis
 */
@Component
public class LoginController 
{
    private TwitterAuth _twitterAuth;
    private Login _login;
    
    public LoginController(){};
    public LoginController( TwitterAuth twitterAuth, Login login) 
    {
        _twitterAuth = twitterAuth;
        _login = login;
    }
     public void setDependencies(TwitterAuth twitterAuth, Login login )
    {
        _twitterAuth = twitterAuth;
        _login = login;
    }
    public String authenticateConsumer (  String consumerKey, String consumerSecret  ) throws TwitterException
    {
        return  _twitterAuth.authenticateConsumer(consumerKey, consumerSecret);
    }
    public TwitterAuth getTwitterAuth()
    {
        return _twitterAuth;
    }   
    
    public void setPin( String pin)
    {
        _twitterAuth.setPin(pin);
    }
    
    public String getPin()
    {
       return _twitterAuth.getPin();       
    }
   
}
