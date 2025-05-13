<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상점 등록 페이지</title>
<link rel="stylesheet" href="${root}/css/login.css" />
<link rel="stylesheet" href="${root}/css/position.css" />
<script src="${root}/js/categoryFilter.js" defer></script>
</head>
<body>
    <div>
        <c:import url="/WEB-INF/views/menu.jsp"></c:import>
    </div>
    <div class="container">
        <form method="post" action="/${roleId}/add/${userId}" class="login-box">
            <div>
                <label for="category">1차 카테고리</label>
                <select id="category" name="resourceCategoryId">
                    <option value="">리소스 1차 카테고리</option>
                    <c:forEach var="category" items="${category}">
                        <option value="${category.resourceCategoryId}">${category.resourceCategoryName}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="secondCategory">2차 카테고리</label>
                <select id="secondCategory" name="resourceSubCategoryId">
                    <option value="">리소스 2차 카테고리</option>
                </select>
            </div>
            <div>
                <label for="itemName">상품명</label>
                <input type="text" id="itemName" name="itemName">
            </div>
            <div>
                <label for="itemWriter">판매자</label>
                <input type="text" id="itemWriter" name="itemWriter" value="${sessionScope.member.nickname}" readonly>
            </div>
            <div>
                <label for="itemPrice">가격</label>
                <input type="number" id="itemPrice" name="itemPrice">
            </div>
            <div>
                <button type="submit">등록</button>
                <a href="../../../list">취소</a>
            </div>
        </form>
	</div>
	
</body>
</html>