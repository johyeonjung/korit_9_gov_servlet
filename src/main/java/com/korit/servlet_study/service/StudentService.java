package com.korit.servlet_study.service;

import com.korit.servlet_study.ch11.dao.StudentDao;
import com.korit.servlet_study.ch11.dto.StudentDto;
import com.korit.servlet_study.ch11.entity.Student;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentService {
    //dto를 엔티티로 넘겨야 dao에 넘길수있음
    private final StudentDao studentDao;


    public Student save(StudentDto studentDto) {
        //엔티티로 변환후에 insert함
        Student student = studentDto.toEntity();
        studentDao.insert(student);
        return student;

    }
}
