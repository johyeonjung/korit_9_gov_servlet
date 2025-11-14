package com.korit.servlet_study.ch11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch11.dao.StudentDao;
import com.korit.servlet_study.ch11.dto.StudentDto;
import com.korit.servlet_study.ch11.entity.Student;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import com.korit.servlet_study.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentService studentService;
    //할때마다 생성안되려고
    private ObjectMapper objectMapper;


    //딱 한번만 실행됨
    @Override
    public void init() throws ServletException {
        //서블릿은 dao와 dto에 직접 접근할 수 없기 때문에 받아온 것을 서비스만 해줌
        studentService = new StudentService(new StudentDao((DBConnectionMgr.getInstance()) ));
        objectMapper = new ObjectMapper();
    }

    //클라이언트에서 json을 보낼 거니까 그걸 받아서 객체로 변환해야 함
    //클라이언트가 보낸 요청 정보가 req에 들어있음
    //서블릿은 json을 dto로, 또는 반대로 변환
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //json의 변수와 dto의 변수명이 같아서 객체 변환됨
        StudentDto studentDto = objectMapper.readValue(req.getReader(), StudentDto.class);
        //서비스는 dto를 entity 또는 반대로 함
        //dao는 엔티티를 데이터베이스 테이블로 또는 행으로 받아온 받아온 데이터를 엔티티로 변환
        Student savedStudent = studentService.save(studentDto);
        objectMapper.writeValue(resp.getWriter(),savedStudent);
    }


}
