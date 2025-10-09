<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매한 상품 목록</title>
<link rel="stylesheet" href="${root}/css/purchasedResources.css" />
</head>
<body>
	<div>
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div>
	<main>
		<h2>구매한 리소스</h2>
		<c:if test="${not empty purchasedList}">
        <div class="card-container">
            <c:forEach var="order" items="${purchasedList}">
                <div class="order-card">
                    <div class="order-header">주문번호: ${order.ordersId}</div>
                    <div class="order-info">
                        <img src="${order.resourceImage}" alt="Resource Image" class="resource-image">
                        <p>주문일: <fmt:formatDate value='${order.ordersDate}' pattern='yyyy-MM-dd' /></p>
                        <p>총 금액: ${order.ordersPrice}원</p>
                        <p>카테고리: ${order.resourceCategoryName} > ${order.resourceSubCategoryName}</p>
                        <p>자료명: ${order.itemName}</p>
                        <p>가격: ${order.itemPrice}원</p>
                        <p>작성자: ${order.itemWriter}</p>
                    </div>

                    <c:if test="${not empty order.resourceFile}">
                        <div class="file-list">
                            <p>첨부파일:</p>
                            <ul>
                                <c:forEach var="file" items="${order.resourceFile}">
                                    <li>${file.resourceFileName}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </c:if>
	</main>

	<script>
		
	</script>
	<script src="${root}/js/purchasedResources.js"></script>
</body>
</html>