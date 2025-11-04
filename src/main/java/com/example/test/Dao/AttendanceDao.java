package com.example.test.Dao;

import com.example.test.Model.UserAttendanceDetail;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface AttendanceDao {
    List<UserAttendanceDetail> selectMonthAttendance(Long userId, Date startDate, Date endDate);
    int countTodayAttendance(Map<String, Object> params);
    void insertAttendance(Long userId, Date attendDate);
    void insertBonusRecord(Map<String, Object> params);
    boolean hasReceivedBonus(Map<String, Object> params);
}
