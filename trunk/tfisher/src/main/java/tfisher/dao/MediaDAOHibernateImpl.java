/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.dao;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import tfisher.entities.Media;

/**
 *
 * @author Theodore Chrysochoidis
 */
@Component
public class MediaDAOHibernateImpl extends HibernateDAO<Media, BigDecimal> implements MediaDAO {

    public void saveNewMedia(Media media) 
    {
        throw new UnsupportedOperationException("save new media ex"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
