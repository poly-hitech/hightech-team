package com.example.test.Controller;

import com.example.test.Model.Users;
import com.example.test.Service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @PostMapping("/attendance/check-in")
    @ResponseBody
    public String checkIn(HttpSession session) {
        Users user = (Users) session.getAttribute("member");
        Long userId = user.getUserId();
        attendanceService.checkIn(userId);
        return "success";
    }
}
