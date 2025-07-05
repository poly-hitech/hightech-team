package com.example.test.Controller;

import com.example.test.Model.Users;
import com.example.test.Service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    // 달력 페이지
    @GetMapping("/calendar")
    public String calendar(@RequestParam(required = false) Integer year,
                           @RequestParam(required = false) Integer month,
                           HttpSession session,
                           Model model) {

        Users user = (Users) session.getAttribute("member");
        Long userId = user.getUserId();
        LocalDate now = LocalDate.now();

        if (year == null) year = now.getYear();
        if (month == null) month = now.getMonthValue();

        Map<Integer, Boolean> attendanceMap = attendanceService.getMonthAttendance(userId, year, month);

        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("attendanceMap", attendanceMap);
        
        log.info("출석 {}", attendanceMap);

        return "attendance"; // /WEB-INF/views/attendance.jsp
    }

    // 오늘 출석체크
    @PostMapping("/check-in")
    public String checkIn(HttpSession session, @RequestParam Integer year, @RequestParam Integer month, Model model) {
        Users user = (Users) session.getAttribute("member");
        Long userId = user.getUserId();
        attendanceService.checkTodayAttendance(userId);
        // 바로 달력으로 리다이렉트
        return "redirect:/attendance/calendar?year=" + year + "&month=" + month;
    }
}
