<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 상점</title>
<link rel="stylesheet" href="${root}/css/shop.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div>
        <c:import url="/WEB-INF/views/menu.jsp"></c:import>
    </div>
    <main>
        <div class="container">
		   	<!-- 1차 카테고리 버튼 -->
		  	<div class="category-buttons" id="categoryButtons">
		        <button class="category-btn" data-id="all">전체</button>
		       	<c:forEach var="category" items="${list}">
		           	<button class="category-btn" data-id="${category.resourceCategoryId}">
		            	${category.resourceCategoryName}
		       		</button>
		    	</c:forEach>
		    </div>
            <!-- 2차 카테고리 영역 -->
            <div class="subcategory-area" id="subcategoryArea" style="display: none;">
                <h3>2차 카테고리</h3>
                <div class="subcategory-buttons" id="subcategoryButtons"></div>
            </div>

            <!-- 상점 정보 그리드 -->
            <div class="products-grid" id="resourceGrid"></div>

            <div class="loading" id="loading">Loading...</div>
        </div>
    </main>
        <!-- 서버에서 전달된 데이터를 JavaScript 변수로 저장 -->
    <script>
    	console.log(JSON.parse('${list2}'));
    	var categories = JSON.parse('${list2}');
    	console.log(categories);
    </script>
    <script src="${root}/js/shopList.js"></script>
</body>
</html>