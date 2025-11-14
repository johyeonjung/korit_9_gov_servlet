package com.korit.servlet_study.ch09;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch08.Response;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet("/ch09/servlet")
public class StudentServlet extends HttpServlet {
    private List<Student> studentList = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private StudentRepository studentRepository = new StudentRepository();

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = new StudentRepository();
        config.getServletContext().setAttribute("studentRepository", studentRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        ObjectMapper objectMapper = new ObjectMapper();

        //객체로 넣어주고 고유 아이디 각 학생마다 넣어줌(학생 정보가 들어오면)
        Student student = objectMapper.readValue(req.getInputStream(), Student.class);
//        student.setId(autoId++);
//        studentList.add(student);
//        Response response = new Response("메시지 전달");

        studentRepository.insert(student);
        objectMapper.writeValue(resp.getWriter(), Map.of("message", "학생 추가 완료"));


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setContentType("application/json");
        String searchNameValue = req.getParameter("searchName");


        objectMapper.writeValue(resp.getWriter(), studentList);

  }
}

