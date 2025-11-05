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
import java.util.ArrayList;
import java.util.List;
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
                           @RequestParam(required = false, defaultValue = "0") int modal,
                           HttpSession session,
                           Model model) {

        Users user = (Users) session.getAttribute("member");
        if(user == null) {
            return "redirect:/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
        }
        Long userId = user.getUserId();
        LocalDate now = LocalDate.now();

        if (year == null) year = now.getYear();
        if (month == null) month = now.getMonthValue();

        int lastDay = LocalDate.of(year, month, 1).lengthOfMonth();
        int today = now.getDayOfMonth();
        boolean isThisMonth = (now.getYear() == year && now.getMonthValue() == month);

        Map<Integer, Boolean> attendanceMap = attendanceService.getMonthAttendance(userId, year, month);

        boolean attendedToday = isThisMonth && attendanceMap.getOrDefault(today, false);

        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("lastDay", lastDay);
        model.addAttribute("today", today);
        model.addAttribute("isThisMonth", isThisMonth);
        model.addAttribute("attendedToday", attendedToday);
        model.addAttribute("attendanceMap", attendanceMap);

        log.info("출석 {}", attendanceMap);

        // **modal=1로 들어오면 헤더/바디 없이 attendance.jsp만 반환**
        if (modal == 1) {
            return "attendance";
        } else {
            return "menu";
        }
    }

    // 오늘 출석체크
    @PostMapping("/check-in")
    @ResponseBody
    public String checkIn(HttpSession session, @RequestParam int year, @RequestParam int month, @RequestParam String point, Model model) {
        int pointValue = Integer.parseInt(point);
        Users user = (Users) session.getAttribute("member");
        Long userId = user.getUserId();
        attendanceService.checkTodayAttendance(userId, pointValue);
        attendanceService.giveBonusIfEligible(userId, year, month);
        return "success";
    }
}
