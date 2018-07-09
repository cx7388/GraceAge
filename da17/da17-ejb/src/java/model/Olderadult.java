/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
//import java.util.Collection;
import java.util.List;
//import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
//import javax.validation.constraints.NotNull;

/**
 *
 * @author cx738
 */
@Entity
@Table(name = "OLDERADULT")
@NamedQueries({
    @NamedQuery(name = "Olderadult.findAll", query = "SELECT o FROM Olderadult o")
    , @NamedQuery(name = "Olderadult.findByOlderid", query = "SELECT o FROM Olderadult o WHERE o.olderid = :olderid")
    , @NamedQuery(name = "Olderadult.findByFirstname", query = "SELECT o FROM Olderadult o WHERE o.firstname = :firstname")
    , @NamedQuery(name = "Olderadult.findByLastname", query = "SELECT o FROM Olderadult o WHERE o.lastname = :lastname")
    , @NamedQuery(name = "Olderadult.findByEmail", query = "SELECT o FROM Olderadult o WHERE o.email = :email")})
@XmlRootElement
public class Olderadult implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OLDERADULT")
    @SequenceGenerator(name="OLDERADULT", sequenceName="OLDERADULT", allocationSize=1)
    @Column(name = "OLDERID")
    private Integer olderid;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ZIP")
    private String zip;
    @Embedded
    private Dorm dorm;
    @OneToMany(mappedBy = "olderadult")
    private List<Telephone> telephoneCollection;
        @JoinTable(name = "OLDER_SERVICE", joinColumns = {
        @JoinColumn(name = "OLDERID", referencedColumnName = "OLDERID")}, inverseJoinColumns = {
        @JoinColumn(name = "SERVICEID", referencedColumnName = "SERVICEID")})
    @ManyToMany
    private List<Service> serviceCollection;
    public Olderadult() {
    }


    public Olderadult(Integer id, String firstname,String lastname, 
            String email,String address, String city, String zip, Dorm dorm ) {
        this.olderid = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.dorm = dorm;
    }

    public Dorm getDorm() {
        return dorm;
    }

    public void setDorm(Dorm dorm) {
        this.dorm = dorm;
    }

    public Integer getOlderid() {
        return olderid;
    }

    public void setOlderid(Integer olderid) {
        this.olderid = olderid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (olderid != null ? olderid.hashCode() : 0);
        return hash;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @XmlTransient
    public List<Telephone> getTelephoneCollection() {
        return telephoneCollection;
    }

    public void setTelephoneCollection(List<Telephone> telephoneCollection) {
        this.telephoneCollection = telephoneCollection;
    }

    @XmlTransient
    public List<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(List<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }
    
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Olderadult)) {
            return false;
        }
        Olderadult other = (Olderadult) object;
        if ((this.olderid == null && other.olderid != null) || (this.olderid != null && !this.olderid.equals(other.olderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Olderadult[olderid: " + olderid + ", name: " + firstname + " " + lastname + "]";
    }

}
