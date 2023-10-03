package org.workspace;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;

public class MySecurityFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Before my security filter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("After my security filter");
    }
}
