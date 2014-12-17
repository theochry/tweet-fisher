/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.interfaces;

import DTO.TweetDTO;
import tfisher.dao.Keywords;
import twitter4j.TwitterStream;

/**
 *
 * @author Theodore Chrysochoidis
 */
public interface TwitterConsumer {
    void processDownload(final TweetDTO tweetDTO, TwitterStream twitterStream, final int miliseconds, final Keywords keywords);
}
