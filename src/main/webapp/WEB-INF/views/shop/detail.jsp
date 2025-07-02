<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>${shop.itemName}</title>
<link rel="stylesheet" href="${root}/css/shopDetail.css" />
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
                <c:if test="${result == true}">
                    <div class="review-form-box">
                        <div class="user-header">
                            <img class="user-avatar" src="${root}/images/default-profile.png" alt="사용자" />
                            <span class="username"> ${sessionScope.loginUser.nickname} </span>
                            <span class="stars">★★★★★</span> <!-- 별점 선택도 JS로 구현 가능 -->
                        </div>
                        <form id="reviewForm" method="post" action="${root}/shop/review">
                            <input type="hidden" name="itemId" value="${shop.itemId}" />
                            <textarea name="reviewContent" maxlength="128"
                                      placeholder="리소스를 구입한 경우에만 평가 등록이 가능합니다.&#10;비방 또는 욕설 등의 유해한 댓글은 고지 없이 삭제될 수 있습니다."></textarea>
                            <div class="form-footer">
                                <span class="char-count">0/128</span>
                                <button type="submit">등록</button>
                            </div>
                        </form>
                    </div>
                </c:if>

                <!-- 리뷰 목록 -->
                <div class="review-list">
                    <c:forEach var="review" items="${reviewList}">
                        <div class="review-item">
                            <div class="review-header">
                                <img class="user-avatar" src="${root}/images/default-profile.png" alt="프로필"/>
                                <div>
                                    <span class="username">${review.user.nickname}</span>
                                    <span class="stars">★★★★★</span>
                                    <div class="review-date">
                                        <fmt:formatDate value="${review.createdAt}" pattern="yyyy.MM.dd hh:mm a" />
                                    </div>
                                </div>
                            </div>
                            <div class="review-content">${review.content}</div>
                        </div>
                    </c:forEach>
                </div>
			</div>

			<div class="right-section">
				<div class="preview-image-box">
					<img
						src="${root}${fn:replace(shop.resourceImage, 'C:/upload', '/upload')}"
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
					<c:if test="${result == true}">
						<button id="download" class="download-btn">다운로드</button>
					</c:if>
					<c:if test="${result != true}">
                        <c:set var="userId" value="${sessionScope.member.userId != null ? sessionScope.member.userId : 0}" />
                        <c:set var="itemId" value="${shop.itemId}" />
						<button id="buy" class="download-btn" data-user-id="${userId}" data-item-id="${itemId}">구매하기</button>
					</c:if>
					<button class="other-btn">제작자의 다른 리소스 보기</button>
				</div>
			</div>
		</div>
	</main>
	<script>
		const shop = ${shop2};
	</script>
	<script src="${root}/js/shopDetail.js"></script>
</body>
</html>
