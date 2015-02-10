/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.controllers;

import java.util.Observable;
import java.util.Observer;
import org.springframework.stereotype.Component;
import tfisher.entities.Media;
import tfisher.session.MediaModelHibernateImpl;

/**
 *
 * @author Theodore Chrysochoidis
 */
@Component
public class StoreMediaController implements Observer
{
     private MediaModelHibernateImpl _mediaManager = new MediaModelHibernateImpl(); 

    public void update(Observable o, Object arg) 
    {        
        Media media = new Media();
        media.setIdStr(((Media)o).getIdStr());
        media.setMediaUrl(((Media)o).getMediaUrl());
        media.setType(((Media)o).getType());
        media.setTweet(((Media)o).getTweet());
        _mediaManager.saveNewMedia(media);        
    }
    
}
