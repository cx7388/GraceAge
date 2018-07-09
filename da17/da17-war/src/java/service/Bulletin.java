/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cx738
 */
@Entity
@Table(name = "BULLETIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bulletin.findAll", query = "SELECT b FROM Bulletin b")
    , @NamedQuery(name = "Bulletin.findByBulletinid", query = "SELECT b FROM Bulletin b WHERE b.bulletinid = :bulletinid")
    , @NamedQuery(name = "Bulletin.findByContent", query = "SELECT b FROM Bulletin b WHERE b.content = :content")})
public class Bulletin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BULLETIN")
    @SequenceGenerator(name = "BULLETIN", sequenceName = "BULLETIN", allocationSize = 1)
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "BULLETINID")
    private Integer bulletinid;
//    @Size(max = 255)
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "BULLETINTIME")
    private Date time;
    @Column(name = "PUBLISHER")
    private String publisher;

    public Bulletin() {
    }

    public Bulletin(Integer bulletinid) {
        this.bulletinid = bulletinid;
    }

    public Integer getBulletinid() {
        return bulletinid;
    }

    public void setBulletinid(Integer bulletinid) {
        this.bulletinid = bulletinid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bulletinid != null ? bulletinid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bulletin)) {
            return false;
        }
        Bulletin other = (Bulletin) object;
        if ((this.bulletinid == null && other.bulletinid != null) || (this.bulletinid != null && !this.bulletinid.equals(other.bulletinid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.Bulletin[ bulletinid=" + bulletinid + " ]";
    }

}
