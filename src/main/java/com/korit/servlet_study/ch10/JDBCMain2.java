package com.korit.servlet_study.ch10;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class JDBCMain2 {
    public static void main(String[] args) {

    final String URL = "jdbc:mysql://localhost:3309/student_db";
    final String USERNAME = "root";
    final String PASSWORD = "1q2w3e4r";
    String searchData = "";



        try {
            // 1. DB Connection
            Connection connection  = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            // 2. SQL 작성
            String sql = """
                    select
                        ct.course_id,
                        ct.course_code,
                        ct.course_name,
                        pt.professor_id,
                        pt.professor_name,
                        ct.credit,
                        ct.enrollment_capacity,
                        ct.classroom
                    from course_tb ct
                    join professor_tb pt on ct.professor_id = pt.professor_id
                    where ct.course_name like concat('%',?,'%');
                    """;

            // 3. SQL문 실행을 위한 PreparedStatement  생성
            PreparedStatement ps = connection.prepareStatement(sql);

            // 4. ? 와일드카드 위치에 값 맵핑(1 = 몇 번째 물음표인지)
            ps.setString(1,searchData);
//
            ResultSet rs = ps.executeQuery();
//
//            rs.next();
            while (rs.next()) {
//                Map<String,Object> resultMap = Map.of (
//                      "course_id",rs.getString("course_id"),
//                      "course_code" , rs.getString("course_code"),
//                      "professor_name" , rs.getString("professor_name"),
//                      "course_name" , rs.getString("course_name"),
//                      "credit" , rs.getString("credit"),
//                      "enrollment_capacity" , rs.getString("enrollment_capacity")
//                );
                Map<String,Object> resultMap = new LinkedHashMap<>();
                resultMap.put("course_id",rs.getInt("course_id"));
                resultMap.put("course_code",rs.getString("course_code"));
                resultMap.put("professor_name",rs.getString("professor_name"));
                resultMap.put("course_name",rs.getString("course_name"));
                resultMap.put("credit",rs.getString("credit"));
                resultMap.put("enrollment_capacity",rs.getString("enrollment_capacity"));
                System.out.println(resultMap);

                @Data
                @AllArgsConstructor
                class Professor {
                    private int professorId;
                    private String professorName;
                }
                @Data
                @AllArgsConstructor
                class Course {
                    private int courseId;
                    private String courseCode;
                    private String courseName;
                    private Professor professor;
                    private int credit;
                    private int enrollmentCapacity;
                    private String classroom;
                }

               Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        new Professor(rs.getInt("professor_id"),rs.getString("professor_name")),
                        rs.getInt("credit"),
                        rs.getInt("enrollment_capacity"),
                        rs.getString("classroom")
                );
                System.out.println(course);
            }

//            String courseId = rs.getString("course_id");
//            String courseCode = rs.getString("course_code");
//            String pName = rs.getString("professor_name");
//            String cName = rs.getString("course_name");
//            String cr = rs.getString("credit");
//            String enCapacity = rs.getString("enrollment_capacity");
//
//            System.out.println("과목ID : " + courseId);
//            System.out.println("과목CODE : " +courseCode);
//            System.out.println("과목명 : " +cName);
//            System.out.println("교수명 : "+ pName);
//            System.out.println("학점 : " + cr);
//            System.out.println("수용인원 : " + enCapacity);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
