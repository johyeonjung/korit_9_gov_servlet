package com.korit.servlet_study.service;

import com.korit.servlet_study.ch11.dao.ProfessorDao;
import com.korit.servlet_study.ch11.entity.Professor;
import lombok.RequiredArgsConstructor;

import java.util.List;
//servlet과 dao를 연결해주는 service
/*
service : 데이터 처리하는 곳
데이터베이스에 넣을 데이터가 있으면 dao*까지 접근, 아니면 return만 해도 됨
*dao에 간 데이터를 객체로 바꾸고 결과값을 다시 서비스로 보낼수 있음
 */
@RequiredArgsConstructor
public class ProfessorService {
    //final을 왜 쓰지?
    private final ProfessorDao professorDao;

    public List<Professor> getProfessors(String name) {
        return professorDao.findAllLikeName(name);
    }
}
