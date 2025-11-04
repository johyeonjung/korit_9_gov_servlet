package com.korit.servlet_study.ch01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP 프로토콜 Method
 * 1. Get
 * - 용도 : 리소스 조회
 *     - 특징 :
 *         서버로부터 데이터를 요청만 하고 수정하지 않음
 *         요청 데이터(파라미터) 가 URL에 포함됨 ex : http://test.com/users?username=test1234
 *         브라우저 히스토리에 남음
 *         북마크 가능
 *         캐싱 가능
 * 2. Post
 *      - 용도 : 새로운 리소스 생성
 *      - 특징 :
 *          서버에 데이터를 전송하여 새로운 리소스 생성
 *          요청 데이터가 HTTP Body에 포함됨
 *          브라우저 히스토리에 남지 않음
 *          캐싱되지 않음
 * 3. Put
 *      - 용도 : 리소스 전체 수정/생성
 *      - 특징 :
 *          리소스가 있으면 전체를 교체, 없으면 생성
 *          전체 데이터를 전송해야함
 * 4. Patch
 *      - 용도 : 리소스 부분 수정
 *      - 특징 :
 *          리소스의 일부만 수정
 *          Put보다 효율적(변경할 필드만 전송)
 */
@WebServlet("/ch02/method")
public class HttpMethodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET 요청 들어옴");
        Map<String,String> datas = Map.of(
                "name","김준일",
                "age","32",
                "address","동래구"
        );

        //요청
        System.out.println(req.getMethod());

        // 요청 데이터(파라미터)
        System.out.println(req.getParameter("datasKey"));

        String datasKey = req.getParameter("datasKey");

        System.out.println(datas.get(datas.get(datasKey)));

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = resp.getWriter();
        out.println(datas.get(datasKey));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post 요청 들어옴");

        Map<String, String> datas = new HashMap<>();
        datas.put("name", "ㅈㅎㅈ");
        datas.put("age", "25");
        datas.put("address", "연제구");
        System.out.println(req.getMethod());
        System.out.println(req.getParameter("textData"));
        datas.put("keyName",req.getParameter("value"));
        resp.setStatus(200);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.getWriter().println("데이터 추가 성공");


    }

}
