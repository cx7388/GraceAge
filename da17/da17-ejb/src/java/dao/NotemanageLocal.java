/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Local;

/**
 *
 * @author cx738
 */
@Local
public interface NotemanageLocal {

    void draft(String s);

    String getNotecontent();
    
    void setNotecontent(String notecontent);
}
