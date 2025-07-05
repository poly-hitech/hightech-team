package com.example.test.Service;

import com.example.test.Dao.AttendanceDao;
import com.example.test.Model.UserAttendanceDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceDao attendanceDao;

    @Override
    public Map<Integer, Boolean> getMonthAttendance(Long userId, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<UserAttendanceDetail> list = attendanceDao.selectMonthAttendance(userId, Date.valueOf(start), Date.valueOf(end));

        Map<Integer, Boolean> result = new HashMap<>();
        for (UserAttendanceDetail att : list) {
            LocalDate attendDate = att.getAttendDate().toLocalDate();
            result.put(attendDate.getDayOfMonth(), true);
        }
        return result;
    }

    // 오늘 출석 체크 (중복방지)
    @Override
    public boolean checkTodayAttendance(Long userId) {
        Date today = new Date(System.currentTimeMillis());
        today = Date.valueOf(today.toLocalDate().plusDays(1));
        int count = attendanceDao.countTodayAttendance(userId, today);
        if (count > 0) return false; // 이미 출석함
        attendanceDao.insertAttendance(userId, today);
        return true;
    }
}
