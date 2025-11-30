<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매한 상품 목록</title>
<link rel="stylesheet" href="${root}/css/purchasedResources.css" />
</head>
<body>
	<div>
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div>
	<main>
	    <div class="page-header">
	        <div>
	            <h2 class="page-title">구매한 리소스</h2>
	            <p class="page-subtitle">
	                내가 지금까지 구매한 디지털 리소스 목록입니다.
	            </p>
	        </div>
	        <c:if test="${not empty purchasedList}">
	            <div class="page-summary">
	                총 <span>${fn:length(purchasedList)}</span>건
	            </div>
	        </c:if>
	    </div>

	    <c:if test="${not empty purchasedList}">
	        <div class="card-container">
	            <c:forEach var="order" items="${purchasedList}">
	                <article class="order-card">
	                    <div class="order-card__ribbon">구매 완료</div>
	
	                    <!-- 상단 주문 정보 -->
	                    <header class="order-header">
	                        <div>
	                            <div class="order-number">주문번호 #${order.ordersId}</div>
	                            <div class="order-meta">
	                                <span>
	                                    주문일
	                                    <strong>
	                                        <fmt:formatDate value='${order.ordersDate}' pattern='yyyy-MM-dd' />
	                                    </strong>
	                                </span>
	                                <span>카테고리 ${order.resourceCategoryName} &gt; ${order.resourceSubCategoryName}</span>
	                            </div>
	                        </div>
	                        <div class="price-badge">
	                            <span class="price-label">결제 금액</span>
	                            <span class="price-value">
	                                <fmt:formatNumber value='${order.ordersPrice}' pattern='#,###'/>원
	                            </span>
	                        </div>
	                    </header>
	
	                    <!-- 본문 영역 -->
	                    <div class="order-body">
	                        <div class="order-thumb">
	                            <img src="${order.resourceImage}" alt="Resource Image" class="resource-image">
	                        </div>
	
	                        <div class="order-info">
	                            <div class="meta-row">
	                                <span class="meta-label">자료명</span>
	                                <span class="meta-value">${order.itemName}</span>
	                            </div>
	                            <div class="meta-row">
	                                <span class="meta-label">가격</span>
	                                <span class="meta-value">
	                                    <fmt:formatNumber value='${order.itemPrice}' pattern='#,###'/>원
	                                </span>
	                            </div>
	                            <div class="meta-row">
	                                <span class="meta-label">작성자</span>
	                                <span class="meta-value">${order.itemWriter}</span>
	                            </div>
	                        </div>
	                    </div>
	
	                    <!-- 첨부 파일 영역 -->
	                    <c:if test="${not empty order.resourceFile}">
						    <footer class="file-list">
						        <p class="file-list__title">첨부파일</p>
						        <ul class="file-list__items">
						            <c:forEach var="file" items="${order.resourceFile}">
										<c:set var="parts" value="${fn:split(file.resourceFileName, '.')}" />
										<c:set var="ext" value="${fn:toLowerCase(parts[fn:length(parts) - 1])}" />
						
						                <c:set var="fileType" value="etc" />
						                <c:set var="fileTypeLabel" value="기타" />
						
						                <c:choose>
						                    <c:when test="${ext == 'jpg' or ext == 'jpeg' or ext == 'png' or ext == 'svg'}">
						                        <c:set var="fileType" value="image" />
						                        <c:set var="fileTypeLabel" value="이미지" />
						                    </c:when>
						
						                    <c:when test="${ext == 'ogg' or ext == 'mp3'}">
						                        <c:set var="fileType" value="music" />
						                        <c:set var="fileTypeLabel" value="음원" />
						                    </c:when>
						
						                    <c:when test="${ext == 'pdf' or ext == 'txt' or ext == 'hwp'
						                                   or ext == 'ppt' or ext == 'pptx' or ext == 'zip'}">
						                        <c:set var="fileType" value="document" />
						                        <c:set var="fileTypeLabel" value="문서" />
						                    </c:when>
						
						                    <c:when test="${ext == 'mp4' or ext == 'webm'
						                                   or ext == 'wmv' or ext == 'avi'}">
						                        <c:set var="fileType" value="video" />
						                        <c:set var="fileTypeLabel" value="영상" />
						                    </c:when>
						
						                    <c:when test="${ext == 'js' or ext == 'lua' or ext == 'cs'
						                                   or ext == 'css' or ext == 'sln' or ext == 'html'
						                                   or ext == 'java' or ext == 'c' or ext == 'cpp'
						                                   or ext == 'h' or ext == 'hh'}">
						                        <c:set var="fileType" value="programming" />
						                        <c:set var="fileTypeLabel" value="코드" />
						                    </c:when>
						                </c:choose>
						
						                <li>
						                    <span class="file-chip file-chip--${fileType}">
						                        <span class="file-chip__category">${fileTypeLabel}</span>
						                        <span class="file-chip__name">${file.resourceFileName}</span>
						                    </span>
						                </li>
						            </c:forEach>
						        </ul>
						    </footer>
	                    </c:if>
	                </article>
	            </c:forEach>
	        </div>
	    </c:if>

	    <c:if test="${empty purchasedList}">
	        <div class="empty-state">
	            <h3>아직 구매한 리소스가 없습니다.</h3>
	            <p>마켓에서 마음에 드는 리소스를 찾아보세요.</p>
	            <a href="${root}/market/list" class="empty-state__btn">마켓 둘러보기</a>
	        </div>
	    </c:if>
	</main>
	<script src="${root}/js/purchasedResources.js"></script>
</body>
</html>