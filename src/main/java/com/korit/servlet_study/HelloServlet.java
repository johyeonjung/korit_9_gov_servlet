package com.korit.servlet_study;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //content type -> 서버의 요청 또는 응답 데이터 형식을 선택
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        out.println("""
                {
                    "name" : "김준일",
                    "age" : "32"
                }
                """);
    }

    public void destroy() {
    }
}