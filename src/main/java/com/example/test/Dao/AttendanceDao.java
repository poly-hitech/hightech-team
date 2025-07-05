package com.example.test.Dao;

import com.example.test.Model.UserAttendanceDetail;

import java.sql.Date;
import java.util.List;

public interface AttendanceDao {
    List<UserAttendanceDetail> selectMonthAttendance(Long userId, Date startDate, Date endDate);
    int countTodayAttendance(Long userId, Date today);
    void insertAttendance(Long userId, Date attendDate);
}
