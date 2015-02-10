/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.session;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import tfisher.dao.HibernateUtil;
import tfisher.dao.MediaDAO;
import tfisher.dao.MediaDAOHibernateImpl;
import tfisher.entities.Media;

/**
 *
 * @author Theodore Chrysochoidis
 */
@Component
public class MediaModelHibernateImpl implements MediaModelInterface {

     private MediaDAO mediaDAO = new MediaDAOHibernateImpl();
    public void saveNewMedias(Media media) 
    {
          try {
            HibernateUtil.beginTransaction();
            mediaDAO.save(media);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("save New media exeption MediaMOdelHibernateImpl");
            ex.printStackTrace();
            ex.getMessage();
            
            HibernateUtil.rollbackTransaction();
        }
    }

    public void saveNewMedia(Media media) {
try {
            HibernateUtil.beginTransaction();
            mediaDAO.save(media);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("save New media exeption MediaMOdelHibernateImpl");
            ex.printStackTrace();
            ex.getMessage();
            HibernateUtil.rollbackTransaction();
        }    }
    
}
