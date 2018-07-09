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
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Olderadult;
import model.Telephone;

/**
 *
 * @author cx738
 */
@Named(value = "phoneList")
@SessionScoped
public class PhoneList implements Serializable {

    @EJB
    private OlderadultFacadeLocal olderadultDao;
    private List<Olderadult> olders;
    @EJB//每个都要注入
    private TelephoneFacadeLocal phoneDao;
    private List<Telephone> phones;
    private Olderadult older;
    private Telephone phone;
    private String number;

    public PhoneList() {
    }

    public String getPhoneList(Olderadult o) {
        this.older = o;
        return "phonePage";
    }

    public OlderadultFacadeLocal getOlderadultDao() {
        return olderadultDao;
    }

    public void setOlderadultDao(OlderadultFacadeLocal olderadultDao) {
        this.olderadultDao = olderadultDao;
    }

    public List<Olderadult> getOlders() {
        return olders;
    }

    public void setOlders(List<Olderadult> olders) {
        this.olders = olders;
    }

    public TelephoneFacadeLocal getPhoneDao() {
        return phoneDao;
    }

    public void setPhoneDao(TelephoneFacadeLocal phoneDao) {
        this.phoneDao = phoneDao;
    }

    public List<Telephone> getPhones() {
        return phones;
    }

    public void setPhones(List<Telephone> phones) {
        this.phones = phones;
    }

    public Olderadult getOlder() {
        return older;
    }

    public void setOlder(Olderadult older) {
        this.older = older;
    }

    public Telephone getPhone() {
        return phone;
    }

    public void setPhone(Telephone phone) {
        this.phone = phone;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public List<Telephone> getPhoneList() {
        this.phones = this.older.getTelephoneCollection();
        return phones;
    }
    public void deleteNumber(Integer id) {
        Telephone dphone = this.phoneDao.find(id);
        this.phones.remove(dphone);
        this.older.setTelephoneCollection(this.phones);
        this.phoneDao.remove(dphone);
        this.olderadultDao.edit(this.older);

    }

    public void addNewNumber() {
        Telephone newPhone = new Telephone();
        newPhone.setOlderadult(this.older);
        newPhone.setNumber(this.number);
        this.phones.add(newPhone);
        this.older.setTelephoneCollection(this.phones);     
        this.phoneDao.create(newPhone);
        this.olderadultDao.edit(this.older);
    }
}
