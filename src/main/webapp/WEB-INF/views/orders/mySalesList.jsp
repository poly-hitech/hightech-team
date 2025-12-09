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
<title>최근 판매 내역</title>
<link rel="stylesheet" href="${root}/css/purchasedResources.css" />
</head>
<body>
	<div>
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div>
	<main>
		<div class="page-header">
			<div>
				<h2 class="page-title">💰 최근 판매 내역</h2>
				<p class="page-subtitle">회원님의 리소스 판매 내역을 최신순으로 확인하세요</p>
			</div>
			<c:if test="${not empty purchasedList}">
				<div class="page-summary">
					총 <span>${fn:length(purchasedList)}</span>건의 판매
				</div>
			</c:if>
		</div>
		
		<c:if test="${not empty purchasedList}">
        <div class="card-container">
            <c:forEach var="order" items="${purchasedList}">
                <div class="order-card">
                    <div class="order-header">
                    	<div>
	                        <div class="order-number">주문번호: ${order.ordersId}</div>
	                        <div class="order-meta">
	                        	<span>📅 판매일: <strong><fmt:formatDate value='${order.ordersDate}' pattern='yyyy-MM-dd HH:mm' /></strong></span>
	                        	<span>👤 구매자 ID: <strong>${order.ordersUser}</strong></span>
	                        </div>
                        </div>
                        <div class="price-badge">
                        	<span class="price-label">판매 금액</span>
                        	<div class="price-value">${order.itemPrice}원</div>
                        </div>
                    </div>
                    
                    <div class="order-body">
	                    <div class="order-thumb">
	                        <img src="${order.resourceImage}" alt="${order.itemName}" class="resource-image">
	                    </div>
	                    <div class="order-info">
	                        <h3 class="item-name">${order.itemName}</h3>
	                        <div class="item-meta">
	                        	<span class="category-badge">${order.resourceCategoryName}</span>
	                        	<span class="category-badge category-badge--sub">${order.resourceSubCategoryName}</span>
	                        </div>
	                        <p class="item-description">${fn:substring(order.resourceContent, 0, 100)}<c:if test="${fn:length(order.resourceContent) > 100}">...</c:if></p>
	
	                        <c:if test="${not empty order.resourceFile}">
	                            <div class="file-list">
	                                <div class="file-list__header">📎 첨부파일 (${fn:length(order.resourceFile)}개)</div>
	                                <ul class="file-list__items">
	                                    <c:forEach var="file" items="${order.resourceFile}">
	                                        <li class="file-item">${file.resourceFileName}</li>
	                                    </c:forEach>
	                                </ul>
	                            </div>
	                        </c:if>
	                    </div>
                    </div>
                </div>
            </c:forEach>
        </div>
	    </c:if>
	    
	    <c:if test="${empty purchasedList}">
	    	<div class="empty-state">
	    		<div class="empty-state__icon">📦</div>
	    		<h3 class="empty-state__title">판매 내역이 없습니다</h3>
	    		<p class="empty-state__description">아직 판매된 리소스가 없습니다. 리소스를 등록하고 판매를 시작해보세요!</p>
	    		<a href="${root}/shop/${sessionScope.member.roleId}/add/${sessionScope.member.userId}" class="empty-state__button">리소스 등록하기</a>
	    	</div>
	    </c:if>
	</main>

	<script src="${root}/js/purchasedResources.js"></script>
</body>
</html>