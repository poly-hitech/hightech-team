<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/css/addCategory.css" />
</head>
<body>
	<div>
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div>

	<main>
		<h2>카테고리 등록</h2>
		<form action="/admin/shop/add" method="post">
			<table>
				<tr>
					<td><label for="resourceCategoryName">카테고리 명:<span
							class="required">*</span></label></td>
					<td><input type="text" id="resourceCategoryName"
						name="resourceCategoryName" required /></td>
				</tr>
				<tr>
					<td><label for="resourceSubCategoryName">서브 카테고리 명:<span
							class="required">*</span></label></td>
					<td><input type="text" id="resourceSubCategoryName"
						name="resourceSubCategoryName" required /></td>
				</tr>
			</table>
			<div class="btn-wrap">
				<input type="submit" value="등록" /> <a href="/admin/shop/list"
					class="cancel">취소</a>
			</div>
		</form>
	</main>
	<%-- <script src="${root}/js/addSubCateButton.js"></script> --%>
</body>
</html>