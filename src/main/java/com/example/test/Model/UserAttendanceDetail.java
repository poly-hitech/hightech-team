package com.example.test.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAttendanceDetail {
    private Long attendId;
    private Long userId;
    private java.sql.Date attendDate;
}
