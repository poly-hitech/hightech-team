<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리소스 상점</title>
<link rel="stylesheet" href="${root}/css/productDetail.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div>
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div>
	
	<main>
        <div class="container">
			<div class="productDetail-grid" id="resourceFile"></div>
        </div>
    </main>
	    
   	<script>
   		const shop = JSON.parse('${shop2}');
    	console.log(JSON.parse('${shop2}'));
    </script>
    <script src="${root}/js/shopDetail.js"></script>
</body>
</html>