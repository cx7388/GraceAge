/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import dao.OlderadultDaoLocal;
import dao.OlderadultFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import model.Olderadult;
import model.Dorm;
import intercepter.Loggable;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import model.Building;


@Named
@FlowScoped("registration")
public class OlderAdultRegistration implements Serializable {

    @Resource(mappedName = "jms/myQueue")
    private Queue myQueue;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    @EJB
    private OlderadultFacadeLocal olderadultDao;
    
    private Olderadult olderadult= new Olderadult();
    private Dorm dorm = new Dorm();
    
    @PostConstruct
    public void init() {
        System.out.println(this.getClass().getCanonicalName() + " initialized.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(this.getClass().getCanonicalName() + " destroyed.");
    }

    public Olderadult getOlderadult() {
        return olderadult;
    }

    public void setOlderadult(Olderadult olderadult) {
        this.olderadult = olderadult;
    }

    public Dorm getDorm() {
        return dorm;
    }

    public void setDorm(Dorm dorm) {
        this.dorm = dorm;
    }
    
    public Building[] getBuildings() {
        return Building.values();
    }
    
    public String navigateToConfirmation() {
        olderadult.setDorm(dorm);
        this.olderadultDao.create(olderadult);
        String serverInfo = this.olderadult.getFirstname()+" "+this.olderadult.getLastname()+" has been created!";
        this.sendJMSMessageToMyQueue(serverInfo);
        return "registration-return";
    }

    private void sendJMSMessageToMyQueue(String messageData) {
        context.createProducer().send(myQueue, messageData);
    }
    
    
}
