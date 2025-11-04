package com.korit.servlet_study.ch05;

import java.util.List;
import java.util.Map;

public class Tomcat {
    public static void main(String[] args) {
        System.out.println("요청");
        Request request = new Request();
        Response response = new Response();
// 잠이나 푹 자고 말이야
        // 그래가지고 무슨 공부를 하겠다고
        // 그런 정신력으로 정처기 시험은 어떻게 치겠다는 거야
        // 열심히 수업 들어도 시험 합격할수 있을지도 모를판에말이야

        request.setUrl("/servlet/a");
        request.setMethod("GET");
        request.setData("요청 테스트 데이터");

        // Key - Value => Servlet객체
        Map<String, Servlet> servletMap = Map.of(
                "/servlet/a", new ServletA(),
                "/servlet/b", new ServletB()
        );
//
//        switch (request.getMethod()) {
//            case "GET":
//                servletMap.get(request.getUrl()).doGet(request,response);
//                break;
//            case "POST":
//                servletMap.get(request.getUrl()).doPost(request,response);
//                break;
//        }

        List<Filter> filters = List.of(
                new Filter1(),
                new Filter2(),
                new Filter3()
        );

        FilterChain filterChain = new FilterChain(filters, servletMap.get(request.getUrl()), 0);
        filterChain.doFilter(request, response);

        System.out.println(response);
        System.out.println("응답");
    }
}
