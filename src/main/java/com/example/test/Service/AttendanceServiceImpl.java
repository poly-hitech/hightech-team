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
import java.util.stream.IntStream;

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

        List<UserAttendanceDetail> list = attendanceDao.selectMonthAttendance(userId, Date.valueOf(start),
                Date.valueOf(end));

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
        log.debug("지급 포인트: {}", point);
        Date today = new Date(System.currentTimeMillis());

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("today", today);

        int count = attendanceDao.countTodayAttendance(params);
        if (count > 0) return false; // 이미 출석함
        attendanceDao.insertAttendance(userId, today);
        BuyPoint myPoint = usersDao.getPointByUserId(userId);
        myPoint.setPointMoney(myPoint.getPointMoney() + point);
        usersDao.earnPointByUserId(myPoint); // 출석시 포인트 지급

        log.info("현재 포인트: {}", myPoint);
        return true;
    }

    @Override
    public void giveBonusIfEligible(Long userId, int year, int month) {
        BuyPoint point = new BuyPoint();
        if (isFullAttendance(userId, year, month) && !hasReceivedBonus(userId, year, month)) {
            log.info("출석 보너스 지급: userId={}, year={}, month={}", userId, year, month);

            Map<String, Object> params = new HashMap<>();
            params.put("userId", userId);
            params.put("year", year);
            params.put("month", month);

            point.setUserId(userId);
            point.setPointMoney(1000L); // 보너스 포인트 설정
            // 포인트 추가
            usersDao.addPoint(point);
            // 지급 이력 기록
            attendanceDao.insertBonusRecord(params);
        }
    }

    @Override
    public boolean hasReceivedBonus(Long userId, int year, int month) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("year", year);
        params.put("month", month);
        return attendanceDao.hasReceivedBonus(params);
    }

    /**
     * 해당 월의 출석이 모두 완료되었는지 확인
     * @param userId 유저 ID
     * @param year   연도
     * @param month  월
     * @return true: 출석 완료, false: 미완료
     */
    private boolean isFullAttendance(Long userId, int year, int month) {
        int lastDay = LocalDate.of(year, month, 1).lengthOfMonth();
        Map<Integer, Boolean> attendanceMap = getMonthAttendance(userId, year, month);
        return IntStream.rangeClosed(1, lastDay)
                .allMatch(day -> attendanceMap.getOrDefault(day, false));
    }
}
