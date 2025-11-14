package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Student;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//final 잇으면 무조건 생성해야해서 required~
//student 관리하는 클래스, dto 사용 X 관계 x
@RequiredArgsConstructor
public class StudentDao {
    //final이 없으면 allargs 어노테이션을 써야 함
    //final은 지동초기화 안됨
    private final DBConnectionMgr mgr;
    ResultSet rs = null;
    public void insert(Student student) {
        //지역변수를 null로 초기화시켜놓고 빼놓음으로써 finally에서도 쓸수있게 함
        //지역 변수가 밑에서 초기화되지 않는 경우를 생각해서 미리 null값으로 초기화함
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = mgr.getConnection();
            String sql = """
                    insert into student_tb
                    values (default, ? , ? , ? , ? , ?, ? , ?)
                    """;
            //jDBC에서 자동 증가된 PK를 돌려받기 위한 문법 (DB에서 insert하고 자동 증가된 id를 사용함)
            //이 SQL를 실행하고 난 뒤에 DB가 자동으로 생성한  key를 나한테 줘라는 뜻
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // 이게 세팅되어있지 않으면 밑에서 generatedKey를 사용하지 못한다
            //자료형 맞추기
            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getPhone());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getDepartmentId());
            ps.setInt(5, student.getGrade());
            ps.setString(6, student.getMajorType());
            ps.setString(7, student.getAddmissionYear());

            //실행해라, resert?가 없어서 query안함
            if(ps.executeUpdate() <1) {
                throw new SQLException();
            }

           rs = ps.getGeneratedKeys();

            while(rs.next()) {
                int studentId = rs.getInt(1);
                student.setStudentId(studentId);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            mgr.freeConnection(con, ps,rs);
        }
    }
}
