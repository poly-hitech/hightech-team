package com.example.test.Service;

import com.example.test.Dao.AttendanceDao;
import com.example.test.Dao.UsersDao;
import com.example.test.Model.BuyPoint;
import com.example.test.Model.UserAttendanceDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceDao attendanceDao;

    @Autowired
    UsersDao usersDao;

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
    public boolean checkTodayAttendance(Long userId, int point) {
        Date today = new Date(System.currentTimeMillis());

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("today", today);

        int count = attendanceDao.countTodayAttendance(params);
        if (count > 0) return false; // 이미 출석함
        attendanceDao.insertAttendance(userId, today);
        BuyPoint myPoint = usersDao.getPointByUserId(userId);
        myPoint.setPointMoney(myPoint.getPointMoney() + point);
        usersDao.earnPointByUserId(myPoint); // 출석시 10포인트 지급
        return true;
    }
}
