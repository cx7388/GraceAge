/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author yangdaowei
 */
@Embeddable
public class Dorm {
    @Enumerated(EnumType.STRING)
    private Building building;
    private Integer floor;
    @Column(name = "ROOMNUMBER")
    private Integer number;

    public Dorm() {
        
    }
    
    public Dorm(Building building, Integer floor, Integer number) {
        this.building = building;
        this.floor = floor;
        this.number = number;
    }
    
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    } 
}
