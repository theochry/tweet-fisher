/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.dao;

/**
 *
 * @author theodore
 */

import tdao.entities.Users;
import java.math.BigDecimal;
 
/**
 *
 * @author leonidas
 */
public interface UsersDAO extends GenericDAO<Users, BigDecimal> {
    public Users findByName(String name);
    
}