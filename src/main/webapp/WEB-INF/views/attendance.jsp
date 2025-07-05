<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%
    int year = (Integer) request.getAttribute("year");
    int month = (Integer) request.getAttribute("month");
    Map<Integer, Boolean> attendanceMap = (Map<Integer, Boolean>) request.getAttribute("attendanceMap");
    List<String> rewardList = (List<String>) request.getAttribute("rewardList"); // 각 칸의 보상 텍스트(아이콘 url 등으로 대체 가능)

    LocalDate date = LocalDate.of(year, month, 1);
    int lastDay = date.lengthOfMonth();
    LocalDate todayDate = LocalDate.now();
    boolean isThisMonth = (todayDate.getYear() == year && todayDate.getMonthValue() == month);
    int today = todayDate.getDayOfMonth();
    boolean attendedToday = attendanceMap != null && attendanceMap.getOrDefault(today, false);
%>
<!DOCTYPE html>
<html>
<head>
    <title>데일리기프트</title>
    <link rel="stylesheet" type="text/css" href="/css/attendance.css">
</head>
<body>
<div class="maple-attd-wrapper">
    <div class="maple-titlebar">
        <div class="maple-title">데일리 기프트</div>
        <div class="maple-period">
            <%= String.format("%02d월 %02d일 부터 %02d월 %02d일 까지", month, 1, month, lastDay) %>
        </div>
    </div>
    <div class="maple-grid">
    <%
        for (int day = 1; day <= lastDay; day++) {
            boolean isToday = isThisMonth && (todayDate.getDayOfMonth() == day);
            boolean attended = attendanceMap != null && attendanceMap.getOrDefault(day, false);
            String reward = (rewardList != null && rewardList.size() >= day) ? rewardList.get(day-1) : (day + "일차 보상");
    %>
        <div class="maple-cell <%= attended ? "attended" : "" %> <%= isToday ? "today" : "" %>">
            <div class="cell-header"><%= day %>일차</div>
            <div class="cell-reward"><%= reward %></div>
            <% if (attended) { %>
                <div class="cell-check">✔️</div>
            <% } else if (isToday) { %>
                <div class="cell-today">오늘!</div>
            <% } %>
        </div>
    <% } %>
    </div>
    <form class="maple-btn-area" method="post" action="/attendance/check-in">
        <input type="hidden" name="year" value="<%= year %>"/>
        <input type="hidden" name="month" value="<%= month %>"/>
        <button type="submit"
                class="maple-big-btn"
                <%= (isThisMonth && attendedToday) ? "disabled" : "" %>>
            선물받기
        </button>
    </form>
    <ul class="maple-desc">
        <li>매일 첫 로그인 시 출석 가능합니다.</li>
        <li>출석 완료 시 각 칸의 선물 아이콘이 활성화됩니다.</li>
        <li>이번 달 <span style="color:#34db63;font-weight:bold;"><%= lastDay %></span>일 모두 출석시 추가 보상이 지급됩니다.</li>
    </ul>
</div>
</body>
</html>
