package com.korit.servlet_study.ch03;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@WebServlet("/ch03/users")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository;
    private ObjectMapper objectMapper;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
//
//        while(true) {
//            String line = bufferedReader.readLine();
//            if(Objects.isNull(line)) {
//                break;
//            }
//            System.out.println(line);
//        }
        //stream에서 넘어온 데이터 자료형을 userclass 객체 자료형으로 바꿔줌
        UserDto userDto = objectMapper.readValue(req.getInputStream(),UserDto.class);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        System.out.println(userDto);

        User foundUser = userRepository.findByUsername(userDto.getUsername());
        if(Objects.isNull(foundUser)) {
            ErrorResponse errorResponse = ErrorResponse.builder().
                    status(400)
                    .message("이미 존재하는 username입니다")
                    .build();
            //바로 json으로 변환하자마자 응답하겠다
            resp.setStatus(400);

            //이녀석이 json으로 보기 위해 꼭 필요한 코드
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getWriter(), errorResponse);
            return;
        }
        User userEntity = userDto.toUser();
        userRepository.insert(userDto.toUser());
//        userRepository.insert();

        SuccessResponse successResponse = SuccessResponse.<User>builder()
                .status(200)
                .message("사용자 등록을 완료하였습니다")
                //현재 가입된 사용자 정보를 body에 넣음
                .body(userEntity)
                .build();
        //동일하게 응답 하겠다 response를 json문자열로 바꿔서 응답하겠다
        objectMapper.writeValue(resp.getWriter(),successResponse);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        List<User> users = userRepository.findAll();
        objectMapper.writeValue(resp.getWriter(),users);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userRepository=userRepository.getInstance();
    }
}
