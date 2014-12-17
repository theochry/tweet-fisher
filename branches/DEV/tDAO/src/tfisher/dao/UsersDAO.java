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

import tfisher.entities.Users;
import java.math.BigDecimal; 

public interface UsersDAO extends GenericDAO<Users, BigDecimal> {
    public Users findByName(String name);
    
}