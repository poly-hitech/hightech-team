<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="review-list">
	<c:forEach var="review" items="${reviewList}">
		<div class="review-item">
			<div class="review-header">
				<img class="user-avatar"
					src="${root}/images/default-profile.png"
					alt="프로필" />
				<div class="review-meta">
					<div class="nickname">
						${review.user.nickname} <span class="star-display"> <c:forEach
								var="i" begin="1" end="5">
								<c:choose>
									<c:when test="${i <= review.reviewCount}">
										<span class="star filled">★</span>
									</c:when>
									<c:otherwise>
										<span class="star">★</span>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</span>
					</div>
					<div class="review-date">
						<fmt:formatDate value="${review.reviewDate}"
							pattern="yyyy.MM.dd hh:mm a" />
					</div>
				</div>
			</div>
			<div class="review-content">${review.reviewContent}</div>
		</div>
	</c:forEach>
</div>