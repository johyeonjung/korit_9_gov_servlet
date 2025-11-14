package com.korit.servlet_study.ch08;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/*
* 최종 목적
1. 게시글을 관리하는 프로그램
    1) 게시글 -> title, contents, writer
        -> 정보들을 담을 수 있는 객체가 필요
            -> 객체를 생성하기 위해서 클래스 먼저 정의 (이름 : Board)
        -> 게시글이 여러개 존재 : 게시글을 묶을 수 있는 방법 => List (순서)
            -> List : Generic 타입 / List<Board>

    2) 웹 백엔드파트 만들기 -> 백엔드는 어떻게 관리를 할까? -> HTTP 프로토콜
    * 프로토콜 : 약속 / 인터페이스(맞추는 것) -> 통신을 하는 데 쓰이는 양식을 맞추는 것
        * HTTP 프로토콜 : 단방향 통신을 한다 -> 한쪽에서만 요청을 보낸다
        * 소켓 : 양방향통신

        1. 단방향
        2. Method   -> 추가, 조회 필요 : Post, Get 필요
        3. URL 필요 : 주소
            => servlet 만들기 :
             url 뭘로 지정할지 ?
             -> 서버가 정함. 왜 ? httpCode에 근거가 있음.
                * 400번대의 오류 : 클라이언트 잘못, 500번대 : 서버 잘못
                -> @WebServlet으로 URL 정해서 서버 돌려보기.
                    * URL 양식 : http://IP : PORT/project/서블릿 URL
                    -> Postman으로 보내면 405에러가 뜬다 (메서드 지원 x -> HTTP 프로토콜 문제)
                    -> Get,Post가 없어서 뜨는 에러
                    -> doPost, doGet 만들면 해결
                    -> 서버가 데이터형식을 정해서 주면 클라이언트가 잘못된 형식으로 줬을 때 400번 에러.(Bad Request)
        4. 요청데이터 형식 JAVA X JSON(문자열 데이터)
            => 전송된 데이터를 꺼내려면 Request가 필요
            -> 문자열 데이터를 전송하는 동안 클라이언트와 서버간의 통로(Stream)이 생김
            -> 필요한 것 ?
                -> Postman에서 문자열을 보내주고 있으면 Java에서는 Input으로 받아들임.
                -> InputStream통로를 열어서 데이터를 받아와야함.
                -> getInputStream을 하게 되면 지금 현재 백엔드서버에서 데이터가 들어올 수 있는 데이터통로를 가져옴
                -> InputStream을 가지고 BufferedReader을 만들어야함. (바로는 못 넣고, InputStreamReader랑 결합해서 넣기)
                    -> req.getReader에 이 과정이 다 포함
                    (req.getInputStream() -> new InputStreamReader read() -> mew BufferedReader() : 최종으로 BufferedReader생성)
                -> readline으로 다 가지고 오고싶으면 반복문 돌리기 : 총 몇 줄인지 모르기 때문에 (while(true)) 사용
                -> 반복문을 멈출 수 있는 조건 : str이 null인지 확인하기 (변수 하나 만들어서 json += str))

 */
@WebServlet("/ch08/servlet")
public class BoardServlet extends HttpServlet {
//    String json = "";
        List<Board> boardList = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(),boardList);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1인용 자전거로 사람을 한명씩 들고감 -> 비효율적 -> 바구니에 사람 모아두고 한번에 들고감 = buffer

//        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        while(true) {
//            String str = br.readLine();
//            if(str == null) break;
//            json += str;
//        }
//        System.out.println(json);

        ObjectMapper objectMapper= new ObjectMapper();
        Board board = objectMapper.readValue(req.getInputStream(),Board.class);
        System.out.println(board);


        boardList.add(board);
        System.out.println(boardList);

//        Response response = new Response();
//        response.builder().message("게시글 작성 완료").build();

        Response response = new Response();
        response.setMessage("게시글 작성 완료");
        System.out.println(response);

        resp.setContentType("application/Json;charset=UTF-8");
        resp.getWriter().println("getWriter()");

        objectMapper.writeValue(resp.getWriter(),response);




    }



}
