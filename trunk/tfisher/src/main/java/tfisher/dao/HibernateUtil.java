/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.dao;

/**
 *
 * @author  Theodore Chrysochoidis
 */
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
 
@Component
public class HibernateUtil {
 
private static final SessionFactory sessionFactory = buildSessionFactory();
private static SessionFactory buildSessionFactory() {
		try {
			// load from different directory
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
 
			return sessionFactory;
 
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
 
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
 public static void shutdown() 
 {		
    getSessionFactory().close();
  }
  



 

 
public static Session beginTransaction() {
Session hibernateSession = HibernateUtil.getSession();
hibernateSession.beginTransaction();
return hibernateSession;
}
 
public static void commitTransaction() {
HibernateUtil.getSession().getTransaction().commit();
}
 
public static void rollbackTransaction() {
HibernateUtil.getSession().getTransaction().rollback();
}
 
public static void closeSession() {
HibernateUtil.getSession().close();
}
 
public static Session getSession() {
Session hibernateSession = sessionFactory.getCurrentSession();
return hibernateSession;
}
}