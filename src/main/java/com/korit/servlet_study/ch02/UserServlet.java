package com.korit.servlet_study.ch02;

import org.jboss.weld.context.http.Http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/ch02/users")
public class UserServlet extends HttpServlet {
    private List<User> users;

    public void init() throws ServletException {
        users = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //username == "test"
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        List<User> foundUsers = users.stream()
                .filter(user -> user.getUsername().equals(req.getParameter("username")))
                .toList();
        User foundUser = foundUsers.isEmpty() ? null : foundUsers.get(0);

        resp.setContentType(StandardCharsets.UTF_8.name());

        if(Objects.isNull(foundUser)) {
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("해당 username은 존재하지 않습니다");
            return;
        }
        resp.getWriter().println(foundUser);
//        if ("test".equals(req.getParameter("username"))) {
//            resp.getWriter().println(users);
//        } else {
//            resp.setStatus(200);
//            resp.getWriter().println("200");
//        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        User u = new User(username,password,name,email,phone);
        users.add(u);

        System.out.println(users);










    }





}
