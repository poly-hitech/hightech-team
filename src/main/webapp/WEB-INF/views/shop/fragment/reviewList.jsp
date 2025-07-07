<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="review-list">
    <c:forEach var="review" items="${reviewList}">
        <c:set var="sortType" value="${param.sortType != null ? param.sortType : 'latest'}" />
        <c:set var="itemId" value="${review.itemId}" />
        <c:set var="userId" value="${review.user.userId}" />
        <div class="review-item">
            <div class="review-header">
                <img class="user-avatar" src="${fn:replace(review.user.profileImage, 'C:/upload', '/upload')}" alt="프로필" />
                <div class="review-meta">
                    <div class="nickname">
                        ${review.user.nickname}
                        <span class="star-display">
                            <c:forEach var="i" begin="1" end="5">
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
                        <fmt:formatDate value="${review.reviewDate}" pattern="yyyy.MM.dd hh:mm a" />
                    </div>
                </div>
                <c:if test="${review.user.userId == sessionScope.member.userId}">
                    <!-- 더보기 버튼 -->
                    <button class="more-menu-btn" data-review-id="${review.reviewId}" aria-label="더보기"></button>
                </c:if>
                <!-- 더보기 메뉴 -->
                <div class="more-menu" id="moreMenu-${review.reviewId}">
                    <ul>
                        <li><a href="#" class="review-edit" data-review-id="${review.reviewId}">수정</a></li>
                        <li><a href="#" class="review-delete" data-review-id="${review.reviewId}">삭제</a></li>
                    </ul>
                </div>
            </div>
            <div class="review-content">
                <!-- 기존 내용 표시 -->
                <p id="content-${review.reviewId}" class="review-text">${review.reviewContent}</p>

                <!-- 수정 폼 (처음엔 숨김) -->
                <div class="edit-form" id="edit-form-${review.reviewId}" style="display: none;">
	                <div class="edit-stars">
					        <div class="star-select">
                                <input type="radio" id="5" name="newRating" value="5"><label for="5">★</label>
                                <input type="radio" id="4" name="newRating" value="4"><label for="4">★</label>
                                <input type="radio" id="3" name="newRating" value="3"><label for="3">★</label>
                                <input type="radio" id="2" name="newRating" value="2"><label for="2">★</label>
                                <input type="radio" id="1" name="newRating" value="1"><label for="1">★</label>
                            </div>
					</div>
                    <textarea class="edit-textarea" id="edit-text-${review.reviewId}" maxlength="128">${review.reviewContent}</textarea>
                    <span class="char-count">${fn:length(review.reviewContent)}/128</span>
                    <div class="edit-buttons">
                        <button class="save-edit" data-review-id="${review.reviewId}" data-user-id="${userId}">저장</button>
                        <button class="cancel-edit" data-review-id="${review.reviewId}">취소</button>
                    </div>
                </div>
            </div>

        </div>
    </c:forEach>
    <div class="pagination">
        <c:if test="${hasPrev}">
            <button class="page-btn" data-page="${startPage - 1}" data-sort="${sortType}">이전</button>
        </c:if>
        <c:forEach var="i" begin="1" end="${totalPages}">
            <button class="page-btn ${i == currentPage ? 'active' : ''}" data-page="${i}" data-sort="${sortType}">
                ${i}
            </button>
        </c:forEach>
        <c:if test="${hasNext}">
        	<button class="page-btn" data-page="${endPage + 1}" data-sort="${sortType}">다음</button>
        </c:if>
    </div>
</div>
	<script>
	    $(document).ready(function () {
	        $('.edit-textarea').on('input', function () {
	            const len = $(this).val().length;
	            $('.char-count').text(len + "/128");
	        });
	    });
	</script>
