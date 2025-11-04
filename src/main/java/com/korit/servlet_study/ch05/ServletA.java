package com.korit.servlet_study.ch05;

public class ServletA extends Servlet {

    @Override
     public void doGet(Request req, Response resp) {
        System.out.println("서블릿 A 에서 doGet 호출");
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setData("응답 데이터");
    }
    @Override
    public void doPost(Request req, Response resp) {
        System.out.println("서블릿 B 에서 doPost 호출");
    }
}
