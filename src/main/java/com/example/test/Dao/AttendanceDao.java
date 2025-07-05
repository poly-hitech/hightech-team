package com.example.test.Dao;

import com.example.test.Model.UserAttendance;

import java.util.List;

public interface AttendanceDao {
    UserAttendance selectByUserId(Long userId);
    void insert(UserAttendance attendance);
    void update(UserAttendance attendance);
    List<UserAttendance> selectAll();
}
