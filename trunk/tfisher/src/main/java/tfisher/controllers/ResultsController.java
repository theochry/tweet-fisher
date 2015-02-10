/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import java.util.List;
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
    
    public ResultsController( ResultsForm resultsForm ) 
    { 
       rf = resultsForm;
    }
     public boolean setDependencies( ResultsForm resultsForm )
    {
        if ( resultsForm == null )
            return false;
        rf = resultsForm;
        return true;
    }
     
     public List<Tweet> findTweetsByKeyword( String keyword, boolean sticky )
     {      
          if ( keyword.isEmpty() ){}
          return _tweetManager.findByKeyword(keyword, sticky);
     }
     
     public boolean updateStickyBit ( String keyword )
     {
         if ( keyword.isEmpty() )
             return false;
         _tweetManager.updateStickyBit(keyword);
         return true;
     }

     
    
}// end of ResultsController
