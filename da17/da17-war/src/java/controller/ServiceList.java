/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OlderadultFacadeLocal;
import dao.ServiceFacadeLocal;
import intercepter.Loggable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import model.Olderadult;
import model.Service;

/**
 *
 * @author cx738
 */
@Named(value = "serviceList")
@SessionScoped
@Loggable
public class ServiceList implements Serializable {

    private Olderadult olderadult;
    private Service service;
    private List<Service> serviceList;
    private List<Service> olderServices;
    @EJB
    private ServiceFacadeLocal serviceDao;
    @EJB
    private OlderadultFacadeLocal olderadultDao;

    /**
     * Creates a new instance of ServiceList
     */
    public ServiceList() {
    }

    public String gotoServicePage(Olderadult o) {
        this.olderadult = o;
        return "servicePage";
    }

    public Olderadult getOlderadult() {
        return olderadult;
    }

    public void setOlderadult(Olderadult olderadult) {
        this.olderadult = olderadult;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServiceList() {
        this.serviceList = this.serviceDao.findAll();
        for(int i=0; i<this.olderServices.size();i++){
            this.serviceList.remove(this.olderServices.get(i));
        }
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public ServiceFacadeLocal getServiceDao() {
        return serviceDao;
    }

    public void setServiceDao(ServiceFacadeLocal serviceDao) {
        this.serviceDao = serviceDao;
    }

    public List<Service> getOlderServices() {
        this.olderServices = this.olderadult.getServiceCollection();
        return olderServices;
    }

    public void setOlderServices(List<Service> olderServices) {
        this.olderServices = olderServices;
    }

    public OlderadultFacadeLocal getOlderadultDao() {
        return olderadultDao;
    }

    public void setOlderadultDao(OlderadultFacadeLocal olderadultDao) {
        this.olderadultDao = olderadultDao;
    }

    public void addService(Service s) {
            this.olderServices.add(s);
            this.olderadult.setServiceCollection(olderServices);
            this.olderadultDao.edit(olderadult);
            this.serviceList.remove(s);
    }

    public void deleteService(Service s) {
        this.olderServices.remove(s);
        this.olderadult.setServiceCollection(olderServices);
        this.olderadultDao.edit(olderadult);
        this.serviceList.add(s);
    }

}
