/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import java.util.List;
import org.springframework.stereotype.Component;
import tfisher.entities.Media;
import tfisher.entities.Tweet;
import tfisher.session.MediaModelHibernateImpl;
import tfisher.session.TweetModelHibernateImpl;
import tfisher.views.ResultsForm;

/**
 *
 * @author  Theodore Chrysochoidis
 */
@Component
public class ResultsController 
{
      
    private ResultsForm rf;
    private final TweetModelHibernateImpl _tweetManager = new TweetModelHibernateImpl();  
    private final MediaModelHibernateImpl _mediaManager = new MediaModelHibernateImpl();  
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
     
     public List<Tweet> findTweetsByKeyword( String keyword, boolean sticky, int start, int end )
     {        
          return _tweetManager.findByKeyword(keyword, sticky, start, end);
     }
     
     public boolean updateStickyBit ( String keyword )
     {
         if ( keyword.isEmpty() )
             return false;
         _tweetManager.updateStickyBit(keyword);
         return true;
     }

     public Media getUrl( String tweet_id )
     {
         return _mediaManager.getMediaUrl(tweet_id);
     }
    
}// end of ResultsController
