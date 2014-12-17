/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.session;

/**
 *
 * @author  Theodore Chrysochoidis
 */

import tfisher.entities.Users;
import java.math.BigDecimal;
import java.util.List;
 

public interface UsersModelInterface {
 
    public Users findByPersonName(String name);
 
    public List<Users> loadAllPersons();
 
    public void saveNewPerson(Users user);
 
    public Users findPersonById(BigDecimal id);
 
    public void deletePerson(Users user);
}