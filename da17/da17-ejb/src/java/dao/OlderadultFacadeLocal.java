/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import model.Olderadult;

/**
 *
 * @author cx738
 */
@Local
public interface OlderadultFacadeLocal {

    void create(Olderadult olderadult);

    void edit(Olderadult olderadult);

    void remove(Olderadult olderadult);

    Olderadult find(Object id);

    List<Olderadult> findAll();

    List<Olderadult> findRange(int[] range);

    int count();
    
}
