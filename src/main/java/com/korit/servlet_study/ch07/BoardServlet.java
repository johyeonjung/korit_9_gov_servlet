package com.korit.servlet_study.ch07;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ch07/servlet")
public class BoardServlet extends HttpServlet {
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
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        Board board = objectMapper.readValue(req.getInputStream(),Board.class);
        boardList.add(board);
        Response response = new Response();
        response.setMessage("게시글 작성 완료");

        objectMapper.writeValue(resp.getWriter(),response);




        System.out.println(board);




    }
}
