package com.korit.servlet_study.ch11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch11.dao.ProfessorDao;
import com.korit.servlet_study.ch11.entity.Professor;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import com.korit.servlet_study.service.ProfessorService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.List;

/*
서블릿 : 요청과 응답을 처리하는 곳
요청과 응답 : 톰캣이 하고 서블릿으로 넘겨줌
필터 : 요청과 응답을 할 때 중간 필터를 거칠 수 있음
 */
//톰캣이 이 주소를 찾아갈 수 있게 매핑함
@WebServlet("/professors")
public class ProfessorServlet extends HttpServlet {
    ProfessorService professorService;
    ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DBConnectionMgr dbConnectionMgr =  DBConnectionMgr.getInstance();
        ProfessorDao professorDao = new ProfessorDao(dbConnectionMgr);
        professorService = new ProfessorService(professorDao);
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //파라미터 값을 먼저 받아와야 함
        String q = req.getParameter("q");
        List<Professor> professors = professorService.getProfessors(q);
        objectMapper.writeValue(resp.getWriter(),professors);

//        List <Professor> professorsList = professorService.getProfessors(req.getParameter("professor_name"));
//        objectMapper.writeValue(resp.getWriter(),professorsList);
    }
}
