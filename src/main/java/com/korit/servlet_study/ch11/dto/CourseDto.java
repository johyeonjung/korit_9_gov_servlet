package com.korit.servlet_study.ch11.dto;

import com.korit.servlet_study.ch11.entity.Course;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CourseDto {
    private int id;
    private String code;
    private String name;
    private int professorId;
    private int credit;
    private int enrollmentCapacity;
    private String classroom;

    public Course toEntity() {
        return Course.builder()
                .courseId(id)
                .courseCode(code)
                .courseName(name)
                .build();
    }

}
