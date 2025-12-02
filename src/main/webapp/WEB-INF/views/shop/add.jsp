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
<link rel="stylesheet" href="${root}/css/position.css" />
<link rel="stylesheet" href="${root}/css/addResource.css" />
<link rel="stylesheet" href="${root}/css/shop.css" />
</head>
<body>
    <div>
        <c:import url="/WEB-INF/views/menu.jsp"></c:import>
    </div>
	<main>
	    <div class=" add-resource-container">
	        <div class="shop-form-card">
	            <div class="shop-form-header">
	                <h1 class="shop-title">리소스 상품 등록</h1>
	                <p class="shop-subtitle">
	                    마켓에 등록할 리소스 정보를 입력하세요. 카테고리, 설명, 이미지, 자료 파일을 꼼꼼히 등록할수록 노출과 판매에 도움이 됩니다.
	                </p>
	            </div>
	
	            <form method="post" class="shop-form" enctype="multipart/form-data">
	                <section class="form-section">
	                    <h2 class="form-section-title">기본 정보</h2>
	                    <p class="form-section-desc">상품 카테고리와 이름, 설명을 입력해주세요.</p>
	
	                    <div class="form-grid">
	                        <div class="form-field">
	                            <label for="category">1차 카테고리</label>
	                            <select id="category" name="resourceCategoryId">
	                                <option value="">리소스 1차 카테고리 선택</option>
	                                <c:forEach var="category" items="${category}">
	                                    <option value="${category.resourceCategoryId}">
	                                        ${category.resourceCategoryName}
	                                    </option>
	                                </c:forEach>
	                            </select>
	                            <span class="field-help">리소스의 최상위 분류를 선택하세요.</span>
	                        </div>
	
	                        <div class="form-field">
	                            <label for="secondCategory">2차 카테고리</label>
	                            <select id="secondCategory" name="resourceShop.resourceSubCategoryId">
	                                <option value="">리소스 2차 카테고리</option>
	                            </select>
	                            <span class="field-help">1차 카테고리를 선택하면 자동으로 하위 카테고리가 나타납니다.</span>
	                        </div>
	
	                        <div class="form-field">
	                            <label for="itemName">상품명</label>
	                            <input type="text" id="itemName" name="resourceShop.itemName" placeholder="예: SF 배경 일러스트 패키지 (10종)">
	                            <span class="field-help">한눈에 어떤 리소스인지 알 수 있게 작성하세요.</span>
	                        </div>
	
	                        <div class="form-field form-field-full">
	                            <label for="resourceContent">상품 설명 (256자 내외)</label>
	                            <textarea id="resourceContent"
	                                      name="resourceShop.resourceContent"
	                                      maxlength="256"
	                                      rows="4"
	                                      placeholder="구성, 사용처, 해상도, 파일 형식 등 구매자가 알고 싶어할 정보를 적어주세요. (최대 256자)"></textarea>
	                            <span class="field-help">너무 짧기보다는 핵심 정보를 간단히 요약해서 적는 것이 좋습니다.</span>
	                        </div>
	
	                        <div class="form-field">
	                            <label for="itemWriter">판매자</label>
	                            <input type="text" id="itemWriter" name="resourceShop.itemWriter" value="${sessionScope.member.nickname}" readonly>
	                        </div>
	
	                        <div class="form-field">
	                            <label for="itemPrice">가격 (원)</label>
	                            <input type="number" id="itemPrice" name="resourceShop.itemPrice" placeholder="예: 5000">
	                            <span class="field-help">무료로 제공하려면 0원을 입력하세요.</span>
	                        </div>
	                    </div>
	                </section>
	
	                <section class="form-section">
	                    <h2 class="form-section-title">파일 및 이미지</h2>
	                    <p class="form-section-desc">대표 이미지와 실제 리소스 파일을 업로드해주세요.</p>
	
	                    <div class="upload-grid">
	                        <div class="form-field">
	                            <label for="fileInput">대표 이미지</label>
	                            <div id="dropzone" class="dropzone-wrapper"
	                                 ondragover="handleDragOver(event)"
	                                 ondragleave="handleDragLeave(event)"
	                                 ondrop="handleDrop(event)"
	                                 onclick="document.getElementById('fileInput').click()">
	
	                                <span class="dropzone-cancel" onclick="event.stopPropagation(); clearFile();">×</span>
	
	                                <span class="dropzone-icon">📁</span>
	                                <span class="dropzone-main-text">파일을 끌어다 놓거나 클릭해서 선택하세요</span>
	                                <span class="dropzone-sub-text">권장: 1:1 또는 16:9 비율의 썸네일 이미지</span>
	                                
                                    <!-- 이미지 미리보기 -->
									<div class="dropzone-preview">
									    <img id="imagePreview" alt="미리보기" style="display:none; max-width:100%; max-height:180px; border-radius:8px;" />
									</div>

								
								    <!-- 파일 이름 표시 -->
								    <span class="dropzone-file-name" id="imageFileName">선택된 파일 없음</span>
	
	                                <input type="file" name="resourceImage" id="fileInput" style="display: none;" onchange="handleFileChange(event)" />
	                            </div>
	                        </div>
	
	                        <div class="form-field">
	                            <label for="resourceFile">자료 파일</label>
	                            <div id="fileContainer">
	                                <div class="file-input-wrapper">
	                                    <input type="file" id="resourceFile" name="resourceFile" multiple="multiple" class="file-input">
	                                </div>
	                            </div>
	                            <div class="file-buttons">
	                                <button type="button" id="addFileBtn">파일 추가</button>
	                                <button type="button" id="removeLastFileBtn">파일 삭제</button>
	                            </div>
	                            
	                            <ul id="resourceFileList" class="file-list"></ul>
	                            
	                            <span class="field-help">
	                                이미지, 음원, 문서, 코드 등 리소스 본문 파일을 업로드하세요. 여러 개의 파일을 한 번에 등록할 수 있습니다.
	                            </span>
	                        </div>
	                    </div>
	                </section>
	
	                <div class="form-actions">
	                    <a href="../../../list" class="btn-secondary">취소</a>
	                    <button type="submit" class="btn-primary">상품 등록</button>
	                </div>
	            </form>
	        </div>
	    </div>
    </main>

    <script>
        const subCategories = [
            <c:set var="totalCount" value="0" />
            <c:forEach var="category" items="${category}">
                <c:forEach var="sub" items="${category.resourceSubCategory}">
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
    <script src="${root}/js/addFileBtn.js"></script>
</body>
</html>
