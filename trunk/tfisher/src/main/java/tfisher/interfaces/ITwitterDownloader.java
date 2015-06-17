/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.interfaces;

import tfisher.dao.Keywords;
import tfisher.entities.Media;
import tfisher.entities.Tweet;
import tfisher.entities.User;
import twitter4j.TwitterStream;

/**
 *
 * @author Theodore Crysochoidis
 */
public interface ITwitterDownloader 
{    
    boolean download ( TwitterStream twitterStream, final Keywords keywords, User user, Tweet tweet , Media media );    
}
