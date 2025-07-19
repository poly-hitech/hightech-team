package com.example.test.Dao;

import com.example.test.Model.UserAttendanceDetail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public List<UserAttendanceDetail> selectMonthAttendance(Long userId, Date startDate, Date endDate) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        return sqlSession.selectList("userAttendance.selectMonthAttendance", param);
    }

    @Override
    public int countTodayAttendance(Map<String, Object> params) {
        return sqlSession.selectOne("userAttendance.countTodayAttendance", params);
    }

    @Override
    public void insertAttendance(Long userId, Date attendDate) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("attendDate", attendDate);
        sqlSession.insert("userAttendance.insertAttendance", param);
    }

    @Override
    public void insertBonusRecord(Map<String, Object> params) {
        sqlSession.insert("userAttendance.insertBonusRecord", params);
    }

    @Override
    public boolean hasReceivedBonus(Map<String, Object> params) {
        Integer count = sqlSession.selectOne("userAttendance.hasReceivedBonus", params);
        return count != null && count > 0;
    }
}
