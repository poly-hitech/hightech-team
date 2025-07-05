package com.example.test.Dao;

import com.example.test.Model.UserAttendance;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public UserAttendance selectByUserId(Long userId) {
        return sqlSession.selectOne("userAttendance.selectByUserId", userId);
    }

    @Override
    public void insert(UserAttendance attendance) {
        sqlSession.insert("userAttendance.insert", attendance);
    }

    @Override
    public void update(UserAttendance attendance) {
        sqlSession.update("userAttendance.update", attendance);
    }

    @Override
    public List<UserAttendance> selectAll() {
        return sqlSession.selectList("userAttendance.selectAll");
    }
}
