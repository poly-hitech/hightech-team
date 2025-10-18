<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정 페이지</title>

</head>
<body>
<%--     <div>
        <c:import url="/WEB-INF/views/menu.jsp"></c:import>
    </div> --%>
    <div class="container">
    	<form method="post">
    		<div>
    			<input name="title" type="text" placeholder="제목" value="${forumPost.title}"
  				/>
  			</div>
  			<div>
  				<textarea name="content" type="text">${forumPost.content}</textarea>
  			</div>
  			<div>
  				<button class="regist" type="submit">수정</button>
  			</div>  					
  		</form>
	</div>
</body>
</html>