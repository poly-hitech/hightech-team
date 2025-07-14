<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 메뉴바는 어디에서든 동일하게 경로가 잡혀야 하기 때문에 절대 경로를 잡아줌 -->
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>메뉴바</title>
    <link rel="stylesheet" href="${root}/css/mainmenu.css" />
    <!-- Boxicons CSS -->
    <link flex href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
    <script src="${root}/js/mainmenu.js" defer></script>
</head>
  <body>
  <!-- 일반 회원, 판매 회원, 관리자 회원에 따라 UI/UX가 달라짐 -->
    <nav class="sidebar locked">
      <div class="logo_items flex">
        <span class="nav_image">
          <img src="images/logo.png" alt="logo_img" />
        </span>
        <span class="logo_name"><a href="${root}/" class="logo_link">코딩기딩</a></span>
        <i class="bx bx-lock-alt" id="lock-icon" title="Unlock Sidebar"></i>
        <i class="bx bx-x" id="sidebar-close"></i>
      </div>

      <div class="menu_container">
        <div class="menu_items">
          <ul class="menu_item">
            <div class="menu_title flex">
              <span class="title">리소스</span>
              <span class="line"></span>
            </div>
            <li class="item">
              <a href="${root}/shop/public/list" class="link flex">
                <i class="bx bx-home-alt"></i>
                <span>마켓</span>
              </a>
            </li>
            <li class="item">
            	<c:if test="${sessionScope.member!= null}">
            		<a href="${root}/shop/${sessionScope.member.roleId}/add/${sessionScope.member.userId}" class="link flex">
                		<i class="bx bx-home-alt"></i>
                		<span>상품 등록</span>
              		</a>
            	</c:if>
            	<c:if test="${sessionScope.member == null}">
            		<a href="${root}/login" class="link flex" id="pleaselogin">
                		<i class="bx bx-home-alt"></i>
                		<span>상품 등록</span>
              		</a>
            	</c:if>
            </li>
            <li class="item">
            	<c:if test="${sessionScope.member!= null}">
            		<a href="${root}/shop/myResources/${sessionScope.member.userId}" class="link flex">
                		<i class="bx bx-home-alt"></i>
                		<span>내 상품</span>
              		</a>
            	</c:if>
                <c:if test="${sessionScope.member == null}">
            		<a href="${root}/login" class="link flex" id="pleaselogin">
                		<i class="bx bx-home-alt"></i>
                		<span>내 상품</span>
              		</a>
            	</c:if>
            </li>
            <li class="item">
            	<c:if test="${sessionScope.member!= null}">
            		<a href="${root}/orders/purchasedResources/${sessionScope.member.userId}" class="link flex">
                		<i class="bx bx-grid-alt"></i>
                		<span>내 거래 내역</span>
              		</a>
            	</c:if>
                <c:if test="${sessionScope.member == null}">
            		<a href="${root}/login" class="link flex" id="pleaselogin">
                		<i class="bx bx-grid-alt"></i>
                		<span>내 거래 내역</span>
              		</a>
            	</c:if>
            </li>
            <li class="item">
              <a href="${root}/shop/mySalesList}${sessionScope.member.userId}" class="link flex">
                <i class="bx bx-grid-alt"></i>
                <span>내 판매 내역</span>
              </a>
            </li>
          </ul>

          <ul class="menu_item">
            <div class="menu_title flex">
              <span class="title">커뮤니티</span>
              <span class="line"></span>
            </div>
            <li class="item">
              <a href="#" class="link flex">
                <i class="bx bxs-magic-wand"></i>
                <span>공지사항</span>
              </a>
            </li>
            <li class="item">
              <a href="#" class="link flex">
                <i class="bx bx-folder"></i>
                <span>취업 정보공유</span>
              </a>
            </li>
            <li class="item">
              <a href="#" class="link flex">
                <i class="bx bx-cloud-upload"></i>
                <span>자유 게시판</span>
              </a>
            </li>
          </ul>

          <ul class="menu_item">
            <div class="menu_title flex">
              <span class="title">랭킹</span>
              <span class="line"></span>
            </div>
            <li class="item">
              <a href="#" class="link flex">
                <i class="bx bx-flag"></i>
                <span>판매 랭킹</span>
              </a>
            </li>
          </ul>
          
          
        </div>

        <div class="sidebar_profile flex">
          <span class="nav_image">
            <img src="${fn:replace(sessionScope.member.profileImage, 'C:/upload', '/upload')}" alt="logo_img" />
          </span>
          <div class="data_text">
          	<c:if test="${sessionScope.member != null}">
            	<span class="name">${sessionScope.member.nickname}</span>
            	<span class="email">${sessionScope.member.username}</span>
            	<br>
			
				<span class="userInfo"><a href="${root}/logout">로그아웃</a></span>
				<span class="userInfo"> / </span>
				<span class="userInfo"><a href="${root}/users/update/${sessionScope.member.userId}">마이페이지</a></span>
			</c:if>
			
			<c:if test="${sessionScope.member == null}">
				<span class="userInfo"><a href="${root}/login">로그인</a></span>
				<span class="userInfo"> / </span>
				<span class="userInfo"><a href="${root}/register">회원가입</a></span>
			</c:if>
          </div>
        </div>
      </div>
    </nav>
  </body>
</html>