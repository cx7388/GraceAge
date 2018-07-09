/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cx738
 */
@Entity
@Table(name = "CAREGIVER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caregiver.findAll", query = "SELECT c FROM Caregiver c")
    ,@NamedQuery(name = "Caregiver.control", query = "SELECT c FROM Caregiver c WHERE c.username= :username AND c.password = :password")
    , @NamedQuery(name = "Caregiver.findByCareid", query = "SELECT c FROM Caregiver c WHERE c.careid = :careid")
    , @NamedQuery(name = "Caregiver.findByUsername", query = "SELECT c FROM Caregiver c WHERE c.username = :username")
    , @NamedQuery(name = "Caregiver.findByPassword", query = "SELECT c FROM Caregiver c WHERE c.password = :password")})
public class Caregiver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "CAREID")
    private Integer careid;
//    @Size(max = 50)
    @Column(name = "USERNAME")
    private String username;
//    @Size(max = 50)
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "caregiver")
    private List<Note> noteCollection;
    public Caregiver() {
    }

    public Caregiver(Integer careid) {
        this.careid = careid;
    }

    public Integer getCareid() {
        return careid;
    }

    public void setCareid(Integer careid) {
        this.careid = careid;
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

    public List<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(List<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (careid != null ? careid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caregiver)) {
            return false;
        }
        Caregiver other = (Caregiver) object;
        if ((this.careid == null && other.careid != null) || (this.careid != null && !this.careid.equals(other.careid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Caregiver[ careid=" + careid + " ]";
    }

}
