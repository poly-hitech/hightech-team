<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>

</head>
<body>
<%--     <div>
        <c:import url="/WEB-INF/views/menu.jsp"></c:import>
    </div> --%>
    <div class="container">
    	<form method="post" enctype="multipart/form-data">
    		<div>
    			<input name="title" type="text" placeholder="제목"/>
  			</div>
  			<div>
  				<textarea name="content" type="text" placeholder="내용"></textarea>
  			</div>
            <!-- ✅ 2. 파일 선택 필드 추가 -->
            <div>
                <label for="file-upload">첨부 파일</label>
                <input type="file" id="file-upload" name="file" multiple/>
            </div>
  			<div>
  				<button class="regist" type="submit">등록</button>
  			</div>  					
  		</form>
	</div>
</body>
</html>