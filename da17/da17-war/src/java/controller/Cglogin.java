/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import static com.sun.faces.el.FacesCompositeELResolver.ELResolverChainType.Faces;
import dao.CaregiverFacadeLocal;
import dao.LoginCounter;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.ws.WebServiceRef;
import model.Caregiver;
import org.primefaces.context.RequestContext;

/**
 *
 * @author cx738
 */
@Named(value = "cglogin")
@SessionScoped
public class Cglogin implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/TimeIndicate/TimeIndicate.wsdl")
    private TimeIndicate_Service service;


    @EJB
    private CaregiverFacadeLocal cgdao;
    @EJB 
    private LoginCounter loginCounter;
    private String username;
    private String password;
    private Caregiver caregiver;
    /**
     * Creates a new instance of Cglogin
     */
    public Cglogin() {
    }

    public LoginCounter getLoginCounter() {
        return loginCounter;
    }

    public void setLoginCounter(LoginCounter loginCounter) {
        this.loginCounter = loginCounter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if (this.cgdao.loginCheck(this.username, this.password)) {
            this.caregiver = this.cgdao.getCaregiver();
            System.out.println("Log at: "+showTime());
            this.loginCounter.incrementCount();
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("cglogin", this);
            return "secured/homepage.xhtml?faces-redirect=true";
        }
        
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Username or Password invaild!!!"));
        return "";
        //else return "cglogin";
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.loginCounter.decrementCount();
        this.caregiver = null;
        return "/cglogin.xhtml?faces-redirect=true";
    }

    private String showTime() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        controller.TimeIndicate port = service.getTimeIndicatePort();
        return port.showTime();
    }
    
    public String isLoggedInForwardHome() {
        if (isLoggedIn()) {
          return "secured/homepage.xhtml?faces-redirect=true";
        }
        return null;
    }
    
    public boolean isLoggedIn() {
        return caregiver != null;
    }
}
