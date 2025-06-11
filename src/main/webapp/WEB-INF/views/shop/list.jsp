<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리소스 상점</title>
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
	    /* const categories = [
	        <c:forEach var="category" items="${list}" varStatus="loop">
	            {
	                resourceCategoryId: ${category.resourceCategoryId},
	                resourceCategoryName: "${category.resourceCategoryName}",
	                resourceSubCategory: [
	                    <c:forEach var="sub" items="${category.resourceSubCategory}" varStatus="subLoop">
	                    	<c:if test="${sub.resourceSubCategoryId} != null">
	                        {
	                            resourceSubCategoryId: ${sub.resourceSubCategoryId},
	                            resourceSubCategoryName: "${sub.resourceSubCategoryName}",
	                            resourceShop: [
	                                <c:forEach var="shop" items="${sub.resourceShop}" varStatus="shopLoop">
	                                    {
	                                        itemId: ${shop.itemId},
	                                        itemName: "${shop.itemName}",
	                                        itemWriter: "${shop.itemWriter}",
	                                        itemPrice: ${shop.itemPrice},
	                                        resourceFile: [
	                                            <c:forEach var="file" items="${shop.resourceFile}" varStatus="fileLoop">
	                                                {
	                                                	resourceFileName: "${file.resourceFileName}"
	                                                     resourceFileName: "C:\\upload\\images\\9c4b7953-d5c8-483f-b341-d186f1c9988b_contact.jpg"
	                                                }<c:if test="${!fileLoop.last}">,</c:if>
	                                            </c:forEach>
	                                        ]
	                                    }<c:if test="${!shopLoop.last}">,</c:if>
	                                </c:forEach>
	                            ]
	                        }<c:if test="${!subLoop.last}">,</c:if>
	                        </c:if>
	                    </c:forEach>
	                ]
	            }<c:if test="${!loop.last}">,</c:if>
	        </c:forEach>
	    ]; */
	    
	    console.log("Categories defined:", categories);
    </script>
    <script src="${root}/js/shopList.js"></script>
</body>
</html>