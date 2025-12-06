<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dev Community Hub</title>
    <link rel="stylesheet" href="${root}/css/mainmenu.css" />
    <!-- Boxicons CSS -->
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
    <script src="${root}/js/mainmenu.js" defer></script>
    <style>
        .main-content {
            margin-left: 270px;
            padding: 20px;
            transition: margin-left 0.4s ease;
            font-family: "Poppins", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Noto Sans KR", sans-serif;
        }
        .sidebar.close + .main-content {
            margin-left: calc(55px + 20px);
        }
        @media (max-width: 800px) {
            .main-content {
                margin-left: 0;
                padding: 10px;
            }
        }

        .hero-section {
            background: linear-gradient(to right, #6B46C1, #D53F8C);
            color: white;
            text-align: center;
            padding: 50px 20px;
            margin-bottom: 40px;
        }
        .hero-title {
            font-size: 2.5rem;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .hero-subtitle {
            font-size: 1.2rem;
            margin-bottom: 20px;
        }
        .hero-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #fff;
            color: #6B46C1;
            text-decoration: none;
            border-radius: 5px;
            font-weight: 600;
        }
        .hero-button:hover {
            background-color: #e0e0e0;
        }

        .resource-section, .community-section {
            margin-bottom: 40px;
        }
        .section-title {
            font-size: 2rem;
            font-weight: bold;
            color: #D275D9;
            text-align: center;
        }
        .section-subtitle {
        	color: rgba(220, 140, 230, 0.4);
            text-align: center;
            margin-bottom: 20px;
        }
        .resource-grid {
            display: grid;
            grid-template-columns: 1fr;
            gap: 20px;
        }
        @media (min-width: 640px) {
            .resource-grid {
                grid-template-columns: repeat(2, 1fr);
            }
        }
        @media (min-width: 1024px) {
        	.resource-slider {
                width: calc(250px * 5);
            }
            .resource-grid {
                grid-template-columns: repeat(5, 1fr);
            }
        }

        .community-grid {
            display: grid;
            grid-template-columns: 1fr;
            gap: 20px;
        }
        @media (min-width: 768px) {
            .community-grid {
                grid-template-columns: repeat(3, 1fr);
            }
        }
        
        .board-column {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        .board-column-title {
            font-size: 1.3rem;
            font-weight: 700;
            color: #6B46C1;
            text-align: center;
            margin-bottom: 10px;
            padding-bottom: 10px;
            border-bottom: 2px solid #D275D9;
        }

        .resource-card {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            overflow: hidden;
            margin: 0 auto;
            width: 250px;
        }
        .resource-image {
            width: 100%;
            height: 150px;
            object-fit: cover;
        }
        .resource-details {
            padding: 15px;
        }
        .resource-title {
            font-size: 1.2rem;
            font-weight: 600;
            color: #333;
            margin-bottom: 10px;
        }
        .resource-price {
            font-size: 1rem;
            color: #666;
            margin-bottom: 10px;
        }
        .resource-button {
            display: inline-block;
            padding: 8px 15px;
            background-color: #4070f4;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
        .resource-button:hover {
            background-color: #3560c4;
        }

        .community-card {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 15px;
        }
        .community-card-with-image {
            display: flex;
            gap: 15px;
        }
        .community-image {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 5px;
            flex-shrink: 0;
        }
        .community-content {
            flex: 1;
        }
        .community-title {
            font-size: 1.2rem;
            font-weight: 600;
            color: #333;
            margin-bottom: 10px;
        }
        .community-summary {
            font-size: 1rem;
            color: #666;
            margin-bottom: 10px;
        }
        .community-link {
            color: #4070f4;
            text-decoration: none;
        }
        .community-link:hover {
            text-decoration: underline;
        }

        .footer {
            background: #212121;
            color: white;
            text-align: center;
            padding: 20px;
            margin-top: auto;
        }
        .footer-text {
            margin-bottom: 10px;
        }
        .footer-links {
            display: flex;
            justify-content: center;
            gap: 15px;
        }
        .footer-link {
            color: #fff;
            text-decoration: none;
        }
        .footer-link:hover {
            color: #bbb;
        }
    </style>
</head>
<body>
    <!-- Header (menu.jsp 포함) -->
	<div>
		<c:import url="menu.jsp"></c:import>
	</div>
	
    <div class="main-content">
        <!-- Hero Section -->
        <div class="hero-section">
            <h1 class="hero-title">개발자 커뮤니티 코딩기딩에 오신 것을 환영합니다</h1>
            <p class="hero-subtitle">자유롭게 나를 표현하고 창작물을 공유 및 판매하시면 됩니다.</p>
            <a href="${root}/shop/public/list" class="hero-button">리소스 마켓</a>
        </div>

        <!-- Resource Market Section -->
        <div class="resource-section">
            <h2 class="section-title">오늘의 인기 리소스</h2>
            <p class="section-subtitle">당신도 지금 이 순간 오늘의 판매왕이 될 수 있습니다.</p>
            <div class="resource-grid">
                <c:forEach var="resource" items="${popularResources}">
                    <div class="resource-card">
                        <img src="${resource.resourceImage}" alt="${resource.itemName}" class="resource-image" />
                        <div class="resource-details">
                            <h3 class="resource-title">${resource.itemName}</h3>
                            <p class="resource-price">${resource.itemPrice}</p>
                            <a href="${root}/shop/detail/${resource.itemId}" class="resource-button">리소스 정보</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Community Section -->
        <div class="community-section">
            <h2 class="section-title">최근 게시물</h2>
            <p class="section-subtitle">공지사항부터 최근 게시물까지 확인해보세요.</p>
            
            <div class="community-grid">
                <!-- 공지사항 -->
                <div class="board-column">
                    <h3 class="board-column-title">공지사항</h3>
                    <c:forEach var="post" items="${notice}">
                        <div class="community-card">
                            <h4 class="community-title">${post.title}</h4>
                            <p class="community-summary">${fn:substring(post.content, 0, 50)}...</p>
                            <a href="${root}/board/list/1" class="community-link">Read More</a>
                        </div>
                    </c:forEach>
                </div>
                
                <!-- 취업 정보 공유 -->
                <div class="board-column">
                    <h3 class="board-column-title">취업 정보 공유</h3>
                    <c:forEach var="post" items="${job}">
                        <div class="community-card">
                            <h4 class="community-title">${post.title}</h4>
                            <p class="community-summary">${fn:substring(post.content, 0, 50)}...</p>
                            <a href="${root}/board/list/2" class="community-link">Read More</a>
                        </div>
                    </c:forEach>
                </div>
                
                <!-- 자유 게시판 -->
                <div class="board-column">
                    <h3 class="board-column-title">자유 게시판</h3>
                    <c:forEach var="post" items="${board}">
                        <div class="community-card <c:if test="${not empty post.postImage}">community-card-with-image</c:if>">
                            <c:if test="${not empty post.postImage}">
                                <img src="${post.postImage}" alt="${post.title}" class="community-image" />
                            </c:if>
                            <div class="community-content">
                                <h4 class="community-title">${post.title}</h4>
                                <p class="community-summary">${fn:substring(post.content, 0, 50)}...</p>
                                <a href="${root}/board/list/3" class="community-link">Read More</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p class="footer-text">&copy; 2025 Dev Community Hub 코딩기딩. All rights reserved.</p>
            <div class="footer-links">
                <a href="https://wookportfolio.duckdns.org" class="footer-link">정지욱</a>
                <a href="portfolio-nu-rosy-28.vercel.app" class="footer-link">장민규</a>
                <a href="" class="footer-link">김택수</a>
            </div>
        </div>
    </div>
</body>
</html>