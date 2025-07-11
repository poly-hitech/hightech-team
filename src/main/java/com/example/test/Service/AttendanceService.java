package com.example.test.Service;

import java.util.Map;

public interface AttendanceService {
    Map<Integer, Boolean> getMonthAttendance(Long userId, int year, int month);
    boolean checkTodayAttendance(Long userId);
}
