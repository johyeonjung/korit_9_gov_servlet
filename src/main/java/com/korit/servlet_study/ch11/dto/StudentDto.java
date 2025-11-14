package com.korit.servlet_study.ch11.dto;

import com.korit.servlet_study.ch11.entity.Student;
import lombok.Builder;
import lombok.Data;

//setter가 있어야 오브첵터가 일할수있음!!
@Builder
@Data
public class StudentDto {
    private String name;
    private String phone;
    private String email;
    private int department;
    private int grade;
    private String major;
    private int admissionYear;

    public Student toEntity() {
        return Student.builder()
                .studentName(name)
                .phone(phone)
                .email(email)
                .departmentId(department)
                .grade(grade)
                .majorType(major)
                .addmissionYear(Integer.toString(admissionYear))
                .build();
    }
}
