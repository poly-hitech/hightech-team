<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>${shop.itemName}</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/shopDetail.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div>
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div>
	<main>
		<div class="shop-detail-container">
			<div class="left-section">
				<div class="file-preview-box">
					<div class="section-title">리소스 프리뷰</div>
					<ul>
						<c:forEach var="file" items="${shop.resourceFile}">
							<li><i class="file-icon"></i> ${file}</li>
						</c:forEach>
					</ul>
				</div>

				<div class="section-box">
					<div class="section-title">리소스 설명</div>
					<div>${shop.resourceContent}</div>
				</div>

				<div class="section-box">
					<div class="section-title">판매자 연락처</div>
					<div>${shop.users.phone}</div>
				</div>

				<div class="section-review-box">
					<div class="section-title">리소스 평가하기</div>
					<div class="rating-options">
						<input type="radio" name="rating" value="1" id="rating-1">최신순</input>
						<input type="radio" name="rating" value="2" id="rating-2">별점
						높은 순</input> <input type="radio" name="rating" value="3" id="rating-3">별점
						낮은 순</input>
					</div>
				</div>
			</div>

			<div class="right-section">
				<div class="preview-image-box">
					<img
						src="${pageContext.request.contextPath}${fn:replace(shop.resourceImage, 'C:/upload', '/upload')}"
						alt="리소스 이미지">
				</div>

				<div class="info-box">
					<div class="category">${shop.resourceCategory.resourceCategoryName}
						&gt; ${shop.resourceSubCategory.resourceSubCategoryName}</div>

					<div class="title">${shop.itemName}</div>

					<div class="meta-group">
						<div class="meta-left">
							<div>
								제작자 <a href="#">${shop.users.nickname}</a>
							</div>
							<div>
								구매 <strong>${shop.counting.totalcount}</strong>
							</div>
						</div>
						<div class="meta-right">
							<div>
								파일 사이즈 <strong> MB</strong>
							</div>
							<div>
								업로드일
								<fmt:formatDate value="${shop.resourceDate}" pattern="yyyy.MM.dd" />
							</div>
						</div>
					</div>

					<button class="download-btn">다운로드</button>
					<button class="other-btn">제작자의 다른 리소스 보기</button>
				</div>
			</div>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/js/shopDetail.js"></script>
</body>
</html>
