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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cx738
 */
@Entity
@Table(name = "SERVICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
    , @NamedQuery(name = "Service.findByServiceid", query = "SELECT s FROM Service s WHERE s.serviceid = :serviceid")
    , @NamedQuery(name = "Service.findByServicename", query = "SELECT s FROM Service s WHERE s.servicename = :servicename")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVICE")
    @SequenceGenerator(name="SERVICE", sequenceName="SERVICE", allocationSize=1)
    @Column(name = "SERVICEID")
    private Integer serviceid;
    @Column(name = "SERVICENAME")
    private String servicename;
    @ManyToMany(mappedBy="serviceCollection")
     private List<Olderadult> olderadultCollection;

    public Service() {
    }

    public Service(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public List<Olderadult> getOlderadultCollection() {
        return olderadultCollection;
    }

    public void setOlderadultCollection(List<Olderadult> olderadultCollection) {
        this.olderadultCollection = olderadultCollection;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceid != null ? serviceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.serviceid == null && other.serviceid != null) || (this.serviceid != null && !this.serviceid.equals(other.serviceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Service[ serviceid=" + serviceid + " ]";
    }
    
}
