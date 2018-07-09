/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Caregiver;

/**
 *
 * @author cx738
 */
@Stateless
public class CaregiverFacade extends AbstractFacade<Caregiver> implements CaregiverFacadeLocal {
    private Caregiver caregiver;
    @PersistenceContext(unitName = "da17-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaregiverFacade() {
        super(Caregiver.class);
    }

    @Override
    public boolean loginCheck(String username, String password) {
        try{
            Caregiver c = em.createNamedQuery("Caregiver.control",Caregiver.class).setParameter("username",username).setParameter("password", password).getSingleResult();
            if(c !=null){
                this.caregiver = c;
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public Caregiver getCaregiver() {
        return caregiver;
    }
    @Override
    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }
    
    
}
