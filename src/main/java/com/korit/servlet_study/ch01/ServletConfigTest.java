package com.korit.servlet_study.ch01;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class ServletConfigTest extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        Object age = req.getServletContext().getAttribute("age");
        System.out.println(age);
    }
}
