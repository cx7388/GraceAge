/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author cx738
 */
@WebService(serviceName = "TimeIndicate")
@Stateless()
public class TimeIndicate {

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "showTime")
    public String showTime() {
         Date date = new Date();
         SimpleDateFormat format = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss zzz");
        return format.format(date);
    }
    
    @WebMethod(operationName = "generateTime")
    public Date generateTime(){
        Date date=new Date();
        return date;
    }
}
