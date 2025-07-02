<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상점 등록 페이지</title>
<link rel="stylesheet" href="${root}/css/login.css" />
<link rel="stylesheet" href="${root}/css/position.css" />
<link rel="stylesheet" href="${root}/css/addResource.css" />
</head>
<body>
    <div>
        <c:import url="/WEB-INF/views/menu.jsp"></c:import>
    </div>
    <div class="container">
        <form method="post" class="login-box" enctype="multipart/form-data">
            <div>
                <label for="category">1차 카테고리: </label>
                <select id="category" name="resourceCategoryId">
                    <option value="">리소스 1차 카테고리</option>
                    <c:forEach var="category" items="${category}">
                        <option value="${category.resourceCategoryId}">${category.resourceCategoryName}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
		        <label for="secondCategory">2차 카테고리: </label>
		        <select id="secondCategory" name="resourceShop.resourceSubCategoryId">
		            <option value="">리소스 2차 카테고리</option>
		        </select>
            </div>
            <div>
                <label for="itemName">상품명: </label>
                <input type="text" id="itemName" name="resourceShop.itemName">
            </div>
            <div>
            	<label for="itemContent">상품 설명(256자 내외): </label>
            	<input type="text" id="resourceShop.itemContent">
            </div>
            <div>
                <label for="itemWriter">판매자: </label>
                <input type="text" id="itemWriter" name="resourceShop.itemWriter" value="${sessionScope.member.nickname}" readonly>
            </div>
            <div>
                <label for="itemPrice">가격: </label>
                <input type="number" id="itemPrice" name="resourceShop.itemPrice">
            </div>
            <div class="form-group">
                <label for="file">자료 파일</label>
                <div id="dropzone" class="dropzone-wrapper"
                     ondragover="handleDragOver(event)"
                     ondragleave="handleDragLeave(event)"
                     ondrop="handleDrop(event)"
                     onclick="document.getElementById('fileInput').click()">

                     <!-- 취소(X) 버튼 -->
                     <span class="dropzone-cancel" onclick="event.stopPropagation(); clearFile();">×</span>

                    <span class="dropzone-text" id="dropzone-text">파일을 선택하거나 여기로 끌어놓으세요</span>
                    <input type="file" name="resourceImage" id="fileInput" style="display: none;" onchange="handleFileChange(event)" />
                </div>
            </div>
            <div>
                <button type="submit">등록</button>
                <a href="../../../list">취소</a>
            </div>
        </form>
	</div>
	<script>
		const subCategories = [
			<c:set var="totalCount" value="0" />
			<c:forEach var="category" items="${category}">
				<c:forEach var="sub" items="${category.resourceSubCategory}" varStatus="status">
					<c:set var="totalCount" value="${totalCount + 1}" />
				</c:forEach>
			</c:forEach>

			<c:set var="currentIndex" value="0" />
			<c:forEach var="category" items="${category}">
				<c:forEach var="sub" items="${category.resourceSubCategory}">
					{
						id: '${sub.resourceSubCategoryId}',
						name: '${sub.resourceSubCategoryName}',
						categoryId: '${sub.resourceCategoryId}'
					}
					<c:set var="currentIndex" value="${currentIndex + 1}" />
					<c:if test="${currentIndex < totalCount}">,</c:if>
				</c:forEach>
			</c:forEach>
		];

		document.getElementById("category").addEventListener("change", function () {
            const selectedCategoryId = this.value;
            const secondSelect = document.getElementById("secondCategory");

            // 초기화
            secondSelect.innerHTML = "";

            const defaultOption = document.createElement("option");
            defaultOption.value = "";
            defaultOption.text = "리소스 2차 카테고리";
            secondSelect.add(defaultOption);

            // 필터링된 값만 추가
            subCategories
                .filter(sub => String(sub.categoryId) === selectedCategoryId)
                .forEach(sub => {
                    const option = document.createElement("option");
                    option.value = sub.id;
                    option.text = sub.name;
                    secondSelect.add(option);
                });
        });
	</script>
	<script src="${root}/js/addResource.js"></script>
</body>
</html>