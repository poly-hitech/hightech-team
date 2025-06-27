<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	${marketList}
	<main>
		<h1>구매한 리소스</h1>

		<div class="card-container">
			<c:forEach var="category" items="${marketList}">
				<c:forEach var="sub" items="${category.resourceSubCategory}">
					<c:forEach var="shop" items="${sub.resourceShop}">
						<div class="card"
							onclick="location.href='/shop/detail?itemId=${shop.itemId}'">
							<div class="card-image">
								<c:choose>
									<c:when test="${not empty shop.resourceFile}">
										<c:set var="imagePath"
											value="${fn:substringAfter(file.resourceFileName, '/upload')}" />
										<img src="/upload${imagePath}" />
									</c:when>
									<c:otherwise>
										<img src="/resources/images/no-image.png" alt="이미지 없음" />
									</c:otherwise>
								</c:choose>
							</div>
							<div class="card-content">
								<h3>${shop.itemName}</h3>
								<p>₩ ${shop.itemPrice}</p>
								<p class="writer">${shop.itemWriter}</p>
							</div>
						</div>
					</c:forEach>
				</c:forEach>
			</c:forEach>
		</div>
	</main>

	<script>
		
	</script>
	<script src="${root}/js/purchasedResources.js"></script>
</body>
</html>