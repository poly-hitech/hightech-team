package com.example.test.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAttendance {
    private Long userId;
    private Date lastCheckDate;
    private Integer consecutiveDays;
}
