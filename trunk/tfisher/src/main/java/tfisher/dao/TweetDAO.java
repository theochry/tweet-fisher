/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.dao;

import java.math.BigDecimal;
import tfisher.entities.Tweet;

/**
 *
 * @author Theodore Chrysochoidis
 */
public interface TweetDAO extends GenericDAO<Tweet, BigDecimal> 
{
    public Tweet findByKeyword(String keyword);
}
