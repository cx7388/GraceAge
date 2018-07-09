/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CaregiverFacadeLocal;
import dao.NoteFacadeLocal;
import dao.NotemanageLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import model.Caregiver;
import model.Note;
import org.primefaces.context.RequestContext;

/**
 *
 * @author cx738
 */
@Named(value = "writeNote")
@SessionScoped
public class WriteNote implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/TimeIndicate/TimeIndicate.wsdl")
    private TimeIndicate_Service service;

   
    private List<Note> notebook;
    private Caregiver caregiver;
    private String content = "";
    private String draft = "";
    @EJB
    private NotemanageLocal notemanage;
    @EJB
    private CaregiverFacadeLocal caregiverDao;
    @EJB
    private NoteFacadeLocal noteDao;

    /**
     * Creates a new instance of WriteNote
     */
    public WriteNote() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public void saveAsdraft() {
        this.setDraft(content);
        this.notemanage.draft(this.draft);
    }

    public void recover() {
        if (this.draft.equals("")) {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Attention", "No Draft!!"));
        } else {
            this.content = this.notemanage.getNotecontent();
        }
    }

    public void discardDraft() {
        this.notemanage.draft("");
        this.draft = "";
    }

    public String saveTonote(Caregiver c) {
        if (!(this.content.isEmpty() | this.content.equals(""))) {
            List<Note> notes = c.getNoteCollection();
            Note newNote = new Note();
            XMLGregorianCalendar xgc=generateTime();
            Date timestamp=xgc.toGregorianCalendar().getTime();
            newNote.setContent(this.content);
            newNote.setCaregiver(c);
            newNote.setTime(timestamp);
            notes.add(newNote);
            c.setNoteCollection(notes);
            this.noteDao.create(newNote);
            this.caregiverDao.edit(c);
            this.content="";
            this.draft="";
             return "notebook";

        } else {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cannot save empty note!!"));
            return "";
        }
       
    }
    public String goToNotebook(Caregiver c){
        this.caregiver = c;
        return "notebook";
    }
    public List<Note> getNotebook() {
        notebook = this.caregiver.getNoteCollection();
        return notebook;
    }

    public void setNotebook(List<Note> notebook) {
        this.notebook = notebook;
    }
    public void deleteNote(Integer id){
        Note dNote = this.noteDao.find(id);
        this.notebook.remove(dNote);
        this.caregiver.setNoteCollection(notebook);
        this.noteDao.remove(dNote);
        this.caregiverDao.edit(caregiver);
    }

    private XMLGregorianCalendar generateTime() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        controller.TimeIndicate port = service.getTimeIndicatePort();
        return port.generateTime();
    }

    
    
}
