<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>데일리기프트</title>
    <link rel="stylesheet" type="text/css" href="/css/attendance.css">
</head>
<body>
<div class="attd-wrapper">
    <div class="titlebar">
        <div class="title">데일리 기프트</div>
        <div class="period">
            <c:out value="${month}"/>월 01일 부터 <c:out value="${month}"/>월 <c:out value="${lastDay}"/>일 까지
        </div>
    </div>
    <div class="grid">
    <c:forEach begin="1" end="${lastDay}" var="day">
        <c:set var="isToday" value="${isThisMonth and today == day}" />
        <c:set var="attended" value="${attendanceMap[day] eq true}" />
        <c:set var="reward" value="${not empty rewardList and rewardList.size() >= day ? rewardList[day-1] : (day + '일차 보상')}" />
        <div class="cell
            <c:if test='${attended}'>attended</c:if>
            <c:if test='${isToday}'> today</c:if>
        ">
            <div class="cell-header"><c:out value="${day}"/>일차</div>
            <div class="cell-reward"><c:out value="${reward}"/></div>
            <c:choose>
                <c:when test="${attended}">
                    <div class="cell-check">✔️</div>
                </c:when>
                <c:when test="${isToday}">
                    <div class="cell-today">오늘!</div>
                </c:when>
            </c:choose>
        </div>
    </c:forEach>
    </div>
    <form class="btn-area" method="post" action="/attendance/check-in">
        <input type="hidden" name="year" value="${year}"/>
        <input type="hidden" name="month" value="${month}"/>
        <button type="submit"
                class="big-btn"
                <c:if test='${isThisMonth and attendedToday}'>disabled</c:if>>
            선물받기
        </button>
    </form>
    <ul class="desc">
        <li>매일 첫 로그인 시 출석 가능합니다.</li>
        <li>출석 완료 시 각 칸의 선물 아이콘이 활성화됩니다.</li>
        <li>이번 달 <span style="color:#34db63;font-weight:bold;"><c:out value="${lastDay}"/></span>일 모두 출석시 추가 보상이 지급됩니다.</li>
    </ul>
</div>
</body>
</html>
