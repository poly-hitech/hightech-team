<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 판매 목록</title>
<link rel="stylesheet" href="${root}/css/productDetail.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<!-- 내 판매 목록 페이지 -->
	<!-- 고려할 사항: 내가 등록한 판매 목록, 해당 판매 리소스의 총 판매 가격, 판매 수량, 랭킹 -->
	<div>
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div>
	
	<main>
        <div class="container">
			<div class="productDetail-grid" id="resourceFile"></div>
        </div>
    </main>
	    
   	<script>
    	const userId = '${sessionScope.member.userId != null ? sessionScope.member.userId : ""}';
   		console.log("", userId);
   		const shop = ${shop2};
    	console.log("", shop);
    </script>
    <script src="${root}/js/shopDetail.js"></script>
</body>
</html>