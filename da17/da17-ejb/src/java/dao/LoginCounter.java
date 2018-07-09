/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Singleton;

/**
 *
 * @author yangdaowei
 */

@Singleton
public class LoginCounter {
    private Integer count = 0;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    
    public void incrementCount(){
        this.count++;
    }
    
    public void decrementCount(){
        this.count--;
    }
}
