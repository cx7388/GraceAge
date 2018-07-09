/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author cx738
 */
@Stateful
public class Notemanage implements NotemanageLocal {
    private String notecontent;
    @Override
    public void draft(String s) {
        this.setNotecontent(s);
    }
    @Override
    public String getNotecontent() {
        return notecontent;
    }
    @Override
    public void setNotecontent(String notecontent) {
        this.notecontent = notecontent;
    }
    
    
}
