/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intercepter;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author yangdaowei
 */

@Interceptor
@Loggable
public class LoggingInterceptor implements Serializable{
    @Inject
    private Logger logger;
    
    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
            logger.info("Entering " + ic.getTarget().getClass().getName() + " with method " + ic.getMethod().getName());
        try {
            return ic.proceed();
        } finally {
            logger.info("Leaving " + ic.getTarget().getClass().getName() + " with method " + ic.getMethod().getName());
        }
    }
}