package com.korit.servlet_study.ch09;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//@WebFilter("/study/students")
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String encoding = StandardCharsets.UTF_8.name();

        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("application/json");

        // 다음 필터 또는 서블릿 실행
        chain.doFilter(request, response);
    }
}
