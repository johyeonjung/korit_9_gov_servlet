package com.korit.servlet_study.ch11.dao;


import com.korit.servlet_study.ch11.entity.Course;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.*;

@RequiredArgsConstructor
public class CourseDao {
    private final DBConnectionMgr mgr;



    public void insert(Course course) {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = mgr.getConnection();
            String sql = """
            insert into course_tb
            values (default,?,?,?,?,?,?) 
            """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            ps.setString(1,rs.getString("course_code"));
            ps.setString(2,rs.getString("course_name"));
            ps.setInt(3,rs.getInt("professor_id"));
            ps.setInt(4,rs.getInt("credit"));
            ps.setInt(5,rs.getInt("enrollment_capacity"));
            ps.setString(6,rs.getString("classroom"));

            if(ps.executeUpdate() < 1) {
                throw new SQLException();
            }
            rs = ps.getGeneratedKeys();

            while(rs.next()){
                int courseId = rs.getInt(1);
                course.setCourseId(courseId);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            mgr.freeConnection(con,ps,rs);
        }


    }

}
