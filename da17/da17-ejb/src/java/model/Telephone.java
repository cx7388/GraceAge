/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cx738
 */
@Entity
@Table(name = "TELEPHONE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telephone.findAll", query = "SELECT t FROM Telephone t")
    , @NamedQuery(name = "Telephone.findByTelephoneid", query = "SELECT t FROM Telephone t WHERE t.telephoneid = :telephoneid")
//    , @NamedQuery(name = "Telephone.findByOlderid", query = "SELECT t FROM Telephone t WHERE t.olderid = :olderid")
    ,@NamedQuery(name = "Telephone.findLargestId", query = " SELECT t FROM Telephone t WHERE t.telephoneid = (SELECT MAX(tt.telephoneid) FROM Telephone tt WHERE tt.telephoneid = t.telephoneid)")
    , @NamedQuery(name = "Telephone.findByNumber", query = "SELECT t FROM Telephone t WHERE t.number = :number")})

public class Telephone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TELEPHONE")
    @SequenceGenerator(name="TELEPHONE", sequenceName="TELEPHONE", allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TELEPHONE")
//    @TableGenerator(name = "TELEPHONEID", table = "TELEPHONE" , allocationSize = 1,initialValue =1 )
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "TELEPHONEID")
    private Integer telephoneid;
//    @Column(name = "OLDERID")
//    private Integer olderid;
//    @Size(max = 50)
    @Column(name = "NUMBER")
    private String number;
    @JoinColumn(name = "OLDERID", referencedColumnName = "OLDERID")
    @ManyToOne
    private Olderadult olderadult;

//    private Integer olderid;

    public Telephone() {
    }

    public Telephone(Integer telephoneid) {
        this.telephoneid = telephoneid;
    }

    public Telephone(Integer telephoneid,Olderadult olderadult,String number) {
        this.telephoneid = telephoneid;
        this.olderadult = olderadult;
        this.number = number;
    }

    public Integer getTelephoneid() {
        return telephoneid;
    }

    public void setTelephoneid(Integer telephoneid) {
        this.telephoneid = telephoneid;
    }
//
//    public Integer getOlderid() {
//        return olderid;
//    }
//
//    public void setOlderid(Integer olderid) {
//        this.olderid = olderid;
//    }

    public Olderadult getOlderadult() {
        return olderadult;
    }

    public void setOlderadult(Olderadult olderadult) {
        this.olderadult = olderadult;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (telephoneid != null ? telephoneid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telephone)) {
            return false;
        }
        Telephone other = (Telephone) object;
        if ((this.telephoneid == null && other.telephoneid != null) || (this.telephoneid != null && !this.telephoneid.equals(other.telephoneid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Telephone[ telephoneid=" + telephoneid + " ]";
    }

}
