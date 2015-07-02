/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.dao;

import java.math.BigDecimal;
import tfisher.entities.Media;


/**
 *
 * @author Theodore Chrysochoidis
 */
public interface MediaDAO extends GenericDAO<Media, BigDecimal> {
    
    public void saveNewMedia(Media media);
    public Media getMediaURL ( String tweet_id );
    
}
