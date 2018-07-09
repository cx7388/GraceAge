/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import model.Telephone;

/**
 *
 * @author cx738
 */
@Local
public interface TelephoneFacadeLocal {

    void create(Telephone telephone);

    void edit(Telephone telephone);

    void remove(Telephone telephone);

    Telephone find(Object id);

    List<Telephone> findAll();

    List<Telephone> findRange(int[] range);

    int count();
    
    Telephone findLargestId();
    
}
