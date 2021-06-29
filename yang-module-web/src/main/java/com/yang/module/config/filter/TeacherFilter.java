package com.yang.module.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * urlPatterns不需要加上context-path
 */
@WebFilter(urlPatterns = "/teacher/*" ,filterName = "TeacherFilter" )
@Order(1)
@Slf4j
public class TeacherFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("TeacherFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
