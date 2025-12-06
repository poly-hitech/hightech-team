<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>판매 랭킹 - Dev Community Hub</title>
    <link rel="stylesheet" href="${root}/css/mainmenu.css" />
    <!-- Boxicons CSS -->
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
    <script src="${root}/js/mainmenu.js" defer></script>
    <link rel="stylesheet" href="${root}/css/salesRanks.css" />
</head>
<body>
    <!-- Header (menu.jsp 포함) -->
	<div>
		<c:import url="menu.jsp"></c:import>
	</div>
	
    <div class="main-content">
        <!-- Hero Section -->
        <div class="hero-section">
            <h1 class="hero-title">🏆 판매 랭킹</h1>
            <p class="hero-subtitle">인기 리소스를 확인하고 트렌드를 파악하세요</p>
        </div>

        <!-- 전체 랭킹 -->
        <div class="rank-section">
            <h2 class="section-title">전체 판매 랭킹</h2>
            <p class="section-subtitle">역대 가장 많이 판매된 리소스</p>
            <div class="rank-grid">
                <c:forEach var="item" items="${total}" varStatus="status">
                    <a href="${root}/shop/detail/${item.itemId}" class="rank-link">
                        <div class="rank-card">
                            <div class="rank-image-wrapper">
                                <span class="rank-badge ${status.index == 0 ? 'top1' : status.index == 1 ? 'top2' : status.index == 2 ? 'top3' : ''}">${status.index + 1}위</span>
                                <img src="${item.resourceImage}" alt="${item.itemName}" class="rank-image" />
                            </div>
                            <div class="rank-details">
                                <h3 class="rank-item-title">${item.itemName}</h3>
                                <p class="rank-price">${item.itemPrice}원</p>
                                <p class="rank-seller">판매자: ${item.userId}</p>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>

        <!-- 일간 랭킹 -->
        <div class="rank-section">
            <h2 class="section-title">일간 판매 랭킹</h2>
            <p class="section-subtitle">오늘 가장 핫한 리소스</p>
            <div class="rank-grid">
                <c:forEach var="item" items="${daily}" varStatus="status">
                    <a href="${root}/shop/detail/${item.itemId}" class="rank-link">
                        <div class="rank-card">
                            <div class="rank-image-wrapper">
                                <span class="rank-badge ${status.index == 0 ? 'top1' : status.index == 1 ? 'top2' : status.index == 2 ? 'top3' : ''}">${status.index + 1}위</span>
                                <img src="${item.resourceImage}" alt="${item.itemName}" class="rank-image" />
                            </div>
                            <div class="rank-details">
                                <h3 class="rank-item-title">${item.itemName}</h3>
                                <p class="rank-price">${item.itemPrice}원</p>
                                <p class="rank-seller">판매자: ${item.userId}</p>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>

        <!-- 주간 랭킹 -->
        <div class="rank-section">
            <h2 class="section-title">주간 판매 랭킹</h2>
            <p class="section-subtitle">이번 주 인기 리소스</p>
            <div class="rank-grid">
                <c:forEach var="item" items="${weekly}" varStatus="status">
                    <a href="${root}/shop/detail/${item.itemId}" class="rank-link">
                        <div class="rank-card">
                            <div class="rank-image-wrapper">
                                <span class="rank-badge ${status.index == 0 ? 'top1' : status.index == 1 ? 'top2' : status.index == 2 ? 'top3' : ''}">${status.index + 1}위</span>
                                <img src="${item.resourceImage}" alt="${item.itemName}" class="rank-image" />
                            </div>
                            <div class="rank-details">
                                <h3 class="rank-item-title">${item.itemName}</h3>
                                <p class="rank-price">${item.itemPrice}원</p>
                                <p class="rank-seller">판매자: ${item.userId}</p>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>

        <!-- 월간 랭킹 -->
        <div class="rank-section">
            <h2 class="section-title">월간 판매 랭킹</h2>
            <p class="section-subtitle">이번 달 베스트셀러</p>
            <div class="rank-grid">
                <c:forEach var="item" items="${monthly}" varStatus="status">
                    <a href="${root}/shop/detail/${item.itemId}" class="rank-link">
                        <div class="rank-card">
                            <div class="rank-image-wrapper">
                                <span class="rank-badge ${status.index == 0 ? 'top1' : status.index == 1 ? 'top2' : status.index == 2 ? 'top3' : ''}">${status.index + 1}위</span>
                                <img src="${item.resourceImage}" alt="${item.itemName}" class="rank-image" />
                            </div>
                            <div class="rank-details">
                                <h3 class="rank-item-title">${item.itemName}</h3>
                                <p class="rank-price">${item.itemPrice}원</p>
                                <p class="rank-seller">판매자: ${item.userId}</p>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>    <!-- Footer -->
    <div class="footer">
        <p class="footer-text">&copy; 2025 Dev Community Hub 코딩기딩. All rights reserved.</p>
        <div class="footer-links">
            <a href="https://wookportfolio.duckdns.org" class="footer-link">정지욱</a>
            <a href="portfolio-nu-rosy-28.vercel.app" class="footer-link">장민규</a>
            <a href="" class="footer-link">김택수</a>
        </div>
    </div>
</body>
</html>