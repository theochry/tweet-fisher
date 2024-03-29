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
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import org.hibernate.Query;
 
public interface GenericDAO<T, ID extends Serializable> {
 
    public void save(T entity);
 
    public void merge(T entity);
 
    public void delete(T entity);
 
    public List<T> findMany(Query query);
 
    public T findOne(Query query);
 
    public List findAll(Class clazz);
 
    public T findByID(Class clazz, BigDecimal id);
    
    public void update ( String field, T entity );
}