/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Olderadult;

/**
 *
 * @author cx738
 */
@Stateless
public class OlderadultFacade extends AbstractFacade<Olderadult> implements OlderadultFacadeLocal {

    @PersistenceContext(unitName = "da17-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OlderadultFacade() {
        super(Olderadult.class);
    }
    
    @Schedule(second = "*/40", minute="*", hour="*", persistent=false)
    public void generateDormReport() {
        System.out.println(this.findAll());
    }
}
