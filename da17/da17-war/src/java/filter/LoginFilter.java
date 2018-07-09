/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import controller.Cglogin;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yangdaowei
 */
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {

        HttpServletRequest httpServletRequest
                = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse
                = (HttpServletResponse) servletResponse;

        Cglogin cglogin = (Cglogin) httpServletRequest
                .getSession().getAttribute("cglogin");
        
        Enumeration<String> e = httpServletRequest.getSession().getAttributeNames();
        while (e.hasMoreElements()) {
            String param = e.nextElement();
            System.out.println("attribute: " + param);
        }
        if (cglogin == null || !cglogin.isLoggedIn()) {
            httpServletResponse.sendRedirect(
                    httpServletRequest.getContextPath() + "/cglogin.xhtml?faces-redirect=true"
            );
        } else {
            System.out.println("Logged in");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
    
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {

    }	
}
