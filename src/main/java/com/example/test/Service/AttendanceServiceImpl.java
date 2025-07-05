package com.example.test.Service;

import com.example.test.Dao.AttendanceDao;
import com.example.test.Model.UserAttendance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceDao attendanceDao;

    @Override
    public void checkIn(Long userId) {
        Date todayDate = java.sql.Date.valueOf(LocalDate.now()); // LocalDate → Date

        UserAttendance att = attendanceDao.selectByUserId(userId);

        if (att == null) {
            // 최초 출석
            att = new UserAttendance(userId, todayDate, 1);
            attendanceDao.insert(att);
        } else {
            // DB에서 가져온 Date → LocalDate로 변환
            LocalDate lastCheck = ((java.sql.Date) att.getLastCheckDate()).toLocalDate();
            LocalDate today = LocalDate.now();

            if (!lastCheck.isEqual(today)) {
                // 어제 출석했으면 연속 카운트 증가, 아니면 1로 초기화
                if (lastCheck.isEqual(today.minusDays(1))) {
                    att.setConsecutiveDays(att.getConsecutiveDays() + 1);
                } else {
                    att.setConsecutiveDays(1);
                }
                att.setLastCheckDate(todayDate);
                attendanceDao.update(att);
            }
        }
    }

    public void resetAttendance() {
        List<UserAttendance> list = attendanceDao.selectAll();
        for (UserAttendance att : list) {
            Date lastCheckDate = att.getLastCheckDate();
            LocalDate lastCheck;
            if (lastCheckDate instanceof java.sql.Date) {
                lastCheck = ((java.sql.Date) lastCheckDate).toLocalDate();
            } else {
                lastCheck = lastCheckDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            }
            LocalDate today = LocalDate.now();
            if (!lastCheck.isEqual(today)) {
                att.setConsecutiveDays(0);
                attendanceDao.update(att);
            }
        }
    }

    //    @Scheduled(cron = "0 0 0 * * *") // 매일 00시
//    @Scheduled(cron = "*/5 * * * * *")
    public void scheduledReset() {
        System.out.println("스케줄러 동작 테스트 : " + LocalDateTime.now());
        resetAttendance();
    }
}
