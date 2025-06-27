<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/addSubCateButton.js"></script>

</head>
<body>
<!-- 	<div>
        <c:import url="/WEB-INF/views/menu.jsp"></c:import>
    </div> -->

	<form method="post" class="d-grid gap-3">
		<div class="row">
			<label for="resourceCategoryName" class="col-1">카테고리 명:<span style="color:blue;"> * </span></label>
 			<div class="col-2">
 				<input type="text" name="resourceCategory.resourceCategoryName" class="form-control" />
 			</div>
 		</div>
 		
	    <div id="subcategory-container">
	        <div class="row subcategory-row">
	            <label for="resourceSubCategory[0].resourceSubCategoryName" class="col-1">서브 카테고리 명:<span style="color:blue;"> * </span></label>
	            <div class="col-2">
	                <input type="text" name="resourceSubCategory[0].resourceSubCategoryName" class="form-control" />
	            </div>
	        </div>
	    </div>
	    
	     <button type="button" id="addSubCategory">서브 카테고리 추가</button>
 		
 		<div class="form-group mt-2" style="clear:left; text-align: center;">
        	<button type="submit">등록</button>
        	<a href="list" class="btn btn-sm btn-secondary">취소</a>
        </div>
	</form>
</body>
</html>