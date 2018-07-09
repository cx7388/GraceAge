/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import model.Caregiver;

/**
 *
 * @author cx738
 */
@Local
public interface CaregiverFacadeLocal {

    void create(Caregiver caregiver);

    void edit(Caregiver caregiver);

    void remove(Caregiver caregiver);

    Caregiver find(Object id);

    List<Caregiver> findAll();

    List<Caregiver> findRange(int[] range);

    int count();
    boolean loginCheck(String username, String password);

    public Caregiver getCaregiver();

    public void setCaregiver(Caregiver caregiver);
}
