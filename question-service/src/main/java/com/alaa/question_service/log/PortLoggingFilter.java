package com.alaa.question_service.log;

import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PortLoggingFilter implements Filter {

    @Autowired
    private Environment env;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        String port = env.getProperty("server.port");
        System.out.println("Request handled by instance on port: " + port);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
