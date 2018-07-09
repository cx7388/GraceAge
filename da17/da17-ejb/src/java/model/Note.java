/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
//import javax.persistence.Basic;
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
import javax.persistence.Temporal;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cx738
 */
@Entity
@Table(name = "NOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n")
    , @NamedQuery(name = "Note.findByNoteid", query = "SELECT n FROM Note n WHERE n.noteid = :noteid")
//    , @NamedQuery(name = "Note.findByCareid", query = "SELECT n FROM Note n WHERE n.careid = :careid")
    , @NamedQuery(name = "Note.findByContent", query = "SELECT n FROM Note n WHERE n.content = :content")})
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTE")
    @SequenceGenerator(name = "NOTE", sequenceName = "NOTE", allocationSize = 1)
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "NOTEID")
    private Integer noteid;
//    @Size(max = 255)
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "NOTETIME")
    private Date time;
    @JoinColumn(name = "CAREID", referencedColumnName = "CAREID")
    @ManyToOne
    private Caregiver caregiver;

    public Note() {
    }

    public Note(Integer noteid) {
        this.noteid = noteid;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noteid != null ? noteid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.noteid == null && other.noteid != null) || (this.noteid != null && !this.noteid.equals(other.noteid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Note[ noteid=" + noteid + " ]";
    }

}
