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
import model.Telephone;

/**
 *
 * @author cx738
 */
@Stateless
public class TelephoneFacade extends AbstractFacade<Telephone> implements TelephoneFacadeLocal {

    @PersistenceContext(unitName = "da17-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelephoneFacade() {
        super(Telephone.class);
    }

    @Override
    public Telephone findLargestId() {
                try{
            Telephone t = em.createNamedQuery("Telephone.findLargestId",Telephone.class).getSingleResult();
            if(t!=null){
                return t;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }


    
}
