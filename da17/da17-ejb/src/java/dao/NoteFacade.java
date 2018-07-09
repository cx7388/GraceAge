/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Note;

/**
 *
 * @author cx738
 */
@Stateless
public class NoteFacade extends AbstractFacade<Note> implements NoteFacadeLocal {

    @PersistenceContext(unitName = "da17-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoteFacade() {
        super(Note.class);
    }
    
}
