/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OlderadultFacadeLocal;
import dao.TelephoneFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
//import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Building;
import model.Dorm;
import model.Olderadult;
import model.Telephone;

/**
 *
 * @author cx738
 */
@Named
@SessionScoped
public class Roster implements Serializable {

    @EJB
    private OlderadultFacadeLocal olderadultDao;
    private List<Olderadult> olders;
    @EJB//每个都要注入
    private TelephoneFacadeLocal phoneDao;
    private List<Telephone> phones;
    private Olderadult older;
//    private Integer olderId;
    private String firstName;
    private String lastName;
    private String email;

    private String address;
    private String city;
    private String zip;

    private Building building;
    private Integer floor;
    private Integer number;
    
    @PostConstruct
    public void init() {
        System.out.println(this.getClass().getCanonicalName() + " initialized.");

    }

    @PreDestroy
    public void destroy() {
        System.out.println(this.getClass().getCanonicalName() + " destroyed.");
    }

    public List<Olderadult> getOlders() {
//        this.olders = new ArrayList<Olderadult>();
        this.olders = this.olderadultDao.findAll();
        return olders;
    }

    public void setOlders(List<Olderadult> olders) {
        this.olders = olders;
    }

    public void deleteOlder(Olderadult olderadult) {
        List<Telephone> deletephones = olderadult.getTelephoneCollection();
        for (int i = 0; i < deletephones.size(); i++) {
            this.phoneDao.remove(deletephones.get(i));
        }
         this.olderadultDao.remove(olderadult);
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String edit() {
        Dorm dorm = new Dorm(this.building, this.floor, this.number);
        this.older.setFirstname(firstName);
        this.older.setLastname(lastName);
        this.older.setEmail(email);
        this.older.setAddress(address);
        this.older.setCity(city);
        this.older.setZip(zip);
        this.older.setDorm(dorm);
//        this.older = o;
        this.olderadultDao.edit(this.older);
        return "roster";
    }

    public String goToEdit(Olderadult o) {
        this.older = o;
        this.firstName = o.getFirstname();
        this.lastName = o.getLastname();
        this.email = o.getEmail();
        this.address = o.getAddress();
        this.city = o.getCity();
        this.zip = o.getZip();
        this.building = o.getDorm().getBuilding();
        this.floor = o.getDorm().getFloor();
        this.number = o.getDorm().getNumber();
        return "editpage";
    }

//    public String navigateToRoster() {
//        return "roster";
//    }
    public Olderadult getOlder() {
        return older;
    }

    public void setOlder(Olderadult older) {
        this.older = older;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneList(Olderadult o) {
        this.older = o;
        return "phonePage";
    }

    public List<Telephone> getPhones() {
        return phones;
    }

    public void setPhones(List<Telephone> phones) {
        this.phones = phones;
    }

    public Building[] getBuildings() {
        return Building.values();
    }
}
