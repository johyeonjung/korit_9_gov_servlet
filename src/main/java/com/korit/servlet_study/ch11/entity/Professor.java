package com.korit.servlet_study.ch11.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//builder은 All이 디폴트라서 NoArg 쓸수없음
//그래서 쓰고 싶으면 둘 다 명시해야 함
//@NoArgsConstructor
//@AllArgsConstructor

@Data
@Builder
public class Professor {
    private int professorId;
    private String professorName;
}
