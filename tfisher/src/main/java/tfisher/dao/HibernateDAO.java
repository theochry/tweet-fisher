/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.dao;

/**
 *
 * @author theodore
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public abstract class HibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
 
    protected Session getSession() {
        return HibernateUtil.getSession();
    }
 
    public void save(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.saveOrUpdate(entity);        
    }
 
    public void merge(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }
 
    public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }
 
    public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.list();
        return t;
    }
 
    public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }
 
    public T findByID(Class clazz, BigDecimal id) {
        Session hibernateSession = this.getSession();
        T t = null;
        t = (T) hibernateSession.get(clazz, id);
        return t;
    }
 
    public List findAll(Class clazz) {
        Session hibernateSession = this.getSession();
        List T = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        T = query.list();
        return T;
    }
    
    public void update ( String field, T entity )
    {
        Session hibernateSession = this.getSession();
        hibernateSession.update(field, entity);
    }
    
}