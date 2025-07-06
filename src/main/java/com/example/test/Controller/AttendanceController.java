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
                           HttpSession session,
                           Model model) {

        Users user = (Users) session.getAttribute("member");
        Long userId = user.getUserId();
        LocalDate now = LocalDate.now();

        if (year == null) year = now.getYear();
        if (month == null) month = now.getMonthValue();

        int lastDay = LocalDate.of(year, month, 1).lengthOfMonth();
        int today = now.getDayOfMonth();
        boolean isThisMonth = (now.getYear() == year && now.getMonthValue() == month);

        Map<Integer, Boolean> attendanceMap = attendanceService.getMonthAttendance(userId, year, month);

        // 오늘 출석 여부
        boolean attendedToday = isThisMonth && attendanceMap.getOrDefault(today, false);

        // 보상 리스트 샘플(직접 만들어서 넘기면 됨)
        List<String> rewardList = new ArrayList<>();
        for (int i = 1; i <= lastDay; i++) {
            rewardList.add(i + "일차 보상");
        }

        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("lastDay", lastDay);
        model.addAttribute("today", today);
        model.addAttribute("isThisMonth", isThisMonth);
        model.addAttribute("attendedToday", attendedToday);
        model.addAttribute("attendanceMap", attendanceMap);
        model.addAttribute("rewardList", rewardList);

        log.info("출석 {}", attendanceMap);

        return "attendance";
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
