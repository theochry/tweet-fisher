/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.springframework.stereotype.Component;
import tfisher.entities.Tweet;
import tfisher.session.TweetModelHibernateImpl;
import tfisher.views.ResultsForm;

/**
 *
 * @author  Theodore Chrysochoidis
 */
@Component
public class ResultsController 
{
      
    private Tweet _tweet;
    private ResultsForm rf;
    private final TweetModelHibernateImpl _tweetManager = new TweetModelHibernateImpl();  
    public ResultsController(){}
    
    public ResultsController(Tweet tweet, ResultsForm resultsForm) {
        
       rf = resultsForm;
    }
     public void setDependencies(Tweet tweet, ResultsForm resultsForm )
    {
          rf = resultsForm;
    }
     
     public List<Tweet> findTweetsByKeyword( String keyword )
     {         
          return _tweetManager.findByKeyword(keyword);
     }

     
    
}// end of ResultsController