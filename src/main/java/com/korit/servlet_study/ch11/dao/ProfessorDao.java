package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Professor;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProfessorDao {
    private final DBConnectionMgr mgr;
    //findall이면 list 다건
    //find만 있으면 객체, professor 사용
    public List<Professor> findAllLikeName(String name) {
        DBConnectionMgr mgr = DBConnectionMgr.getInstance();
        List<Professor> professors = new ArrayList<>();

        //데이터베이스 연결
        //Connection가 전역변수인 이유 : finally에서 연결을 끊으려 하니 전역변수가 아니라 들고올 수가 없어짐!
        //null인 이유 : 전역변수로 빼고보니 초기화를 해줘야 해서 null로 됐음
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = mgr.getConnection();
            String sql = """
                    select 
                        professor_id,
                        professor_name
                    from
                        professor_tb
                    where
                        professor_name like concat('%',?,'%')
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, name);

//            //select에 있는 값들을 모두 객체로 만들어서 준다
//            //이 두개는 주소가 다르기 때문에 위에서 변경해도 아래 쿼리는 변하지 않음
//            그래서 rs에 넣어야 같은 주소로 되기 때문에 변수를 생성함
//            ps.executeQuery();
//            ps.executeQuery().getInt("professor_id");

            rs = ps.executeQuery();
            //쿼리 결과값의 길이를 알수 없기 때문에 while문 사용
            while (rs.next()) {
                //rs.getInt 다음 컬럼명으로 해야 함
                Professor professor = Professor.builder()
                        .professorId(rs.getInt("professor_id"))
                        .professorName(rs.getString("professor_name"))
                        .build();
                professors.add(professor);
            }
//            //생성은 소멸의 역순
//            rs.close();
//            ps.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //try문 끝에 넣으면 중간에 예외터지면 실행이 안되기 때문에 finally에 넣음
            //풀에서 노는데 구명조끼 반납을 안함
//            mgr.freeConnection(con);
            mgr.freeConnection(con, ps, rs);
        }
        return professors;
    }
}
//        try {
//            con = mgr.getConnection();
//            String sql = """
//                    select professor_id,
//
//                           professor_name
//                    from professor_tb
//
//                    order by professor_id
//                    """;
//
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while(rs.next()) {
//                Professor professor =  Professor.builder()
//                        .professorId(rs.getInt("professor_id"))
//                        .professorName(rs.getString("professor_name"))
//                        .build();
//                professors.add(professor);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            mgr.freeConnection(con,ps,rs);
//        }
//
//        return professors;
//    }
//
//
//}
