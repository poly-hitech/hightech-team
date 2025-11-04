<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 리소스 수정</title>
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
     	<div class="productDetail-card">
			<form>
				<div>
					<img src="${shop.resourceFile.resourceFilename}">
				</div>
				<div>
					<h3>리소스 명: ${shop.itemName}</h3>
				</div>
                <div class="content">내용 : ${shop.resourceContent}</div>
                <div class="author">작성자 : ${shop.itemWriter}</div>
                <div class="price">가격: ${shop.itemPrice}</div>
                <div>
                	<button class="btn btn-secondary" Id="buyButton">변경</button>
                	<a href="${root}/shop/myResources/${sessionScope.member.userId}" class="btn btn-secondary" Id="buyButton">취소</a>
                </div>
        	</form>
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