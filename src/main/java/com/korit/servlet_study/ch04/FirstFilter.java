package com.korit.servlet_study.ch04;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/ch04/filter")
public class FirstFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터호출(전처리)");
        //전처리
        filterChain.doFilter(servletRequest,servletResponse);
        //후처리
        System.out.println("필터 (후처리)");
    }

}
