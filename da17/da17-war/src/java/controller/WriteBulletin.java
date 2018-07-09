/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.RESTfulClient;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import model.Caregiver;
import service.Bulletin;

/**
 *
 * @author cx738
 */
@Named(value = "writeBulletin")
@SessionScoped
public class WriteBulletin implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/TimeIndicate/TimeIndicate.wsdl")
    private TimeIndicate_Service service;

    private Bulletin bulletin;
    private Caregiver caregiver;
    private RESTfulClient client = new RESTfulClient();
    private List<Bulletin> bulletinList;
    private String content = "";

    /**
     * Creates a new instance of WriteBulletin
     */
    public WriteBulletin() {
    }

    public String goToBulletin(Caregiver c) {
        this.setCaregiver(c);
//        Bulletin response = client.find_JSON(Bulletin.class, "1");
        this.getBulletinList();
        return "bulletinPage";
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public void id() {
        String response = client.find_JSON(String.class, "1");

        System.out.println("response is: " + response);
    }

    public List<Bulletin> getBulletinList() {
        GenericType<List<Bulletin>> gType = new GenericType<List<Bulletin>>() {
        };
        this.setBulletinList(client.findAll_XML(gType));
        return bulletinList;
    }

    public void setBulletinList(List<Bulletin> bulletinList) {
        this.bulletinList = bulletinList;
    }

    public Bulletin getBulletin() {
        return bulletin;
    }

    public void setBulletin(Bulletin bulletin) {
        this.bulletin = bulletin;
    }

    public void deleteBulletin(int i) {
        client.remove(String.valueOf(i));
        this.getBulletinList();
    }

    public String publish() {
        XMLGregorianCalendar xgc=generateTime();
        Date timestamp=xgc.toGregorianCalendar().getTime();
        Bulletin newB = new Bulletin();
        newB.setContent(this.content);
        newB.setPublisher(this.caregiver.getUsername());
        newB.setTime(timestamp);
        this.client.create_JSON(newB);
        this.content = "";
        return "secured/writeBulletin.xhtml?faces-redirect=true";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private XMLGregorianCalendar generateTime() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        controller.TimeIndicate port = service.getTimeIndicatePort();
        return port.generateTime();
    }
}
