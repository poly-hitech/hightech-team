<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="../../css/login.css" />
	<link rel="stylesheet" href="../../css/position.css" />
</head>
<body>
	<div>
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div>

	<div class="container">
		<form class="login-box" method="post">
			<div>
	        	<h2>마이페이지</h2>
	        </div>
			<div>
				<label for="username">아이디:</label>
				<input id="username" name="username" value="${item.username}" readonly>						
			</div>

			<div>
				<label for="password">비밀번호:</label>
				<input type="password" id="password" name="password">	
			</div>
			
			<div>
				<label for="nickname">닉네임:</label>
				<input id="nickname" name="nickname" value="${item.nickname}">				
			</div>
			
			<div>
				<label for="birthday">생일:</label>
    			<input type="date" id="birthday" name="birthday" 
           			value="<fmt:formatDate value='${item.birthday}' pattern='yyyy-MM-dd' />" />			
			</div>
			
			<div>
				<label for="phone">휴대폰:</label>
				<input id="phone" name="phone" value="${item.phone}">				
			</div>
			
			<div>
				<label for="gender">성별:</label>
				<input id="gender" name="gender" value="${item.gender}">
			</div>
			
			<div>
				<label>가입일: <fmt:formatDate value='${item.signupDay}' pattern='yyyy-MM-dd' /></label>
			</div>
									
			<div>
				<button type="submit">회원정보변경</button>
				<a href="${root}/">취소</a>
			</div>					
		</form>	
	</div>

</body>
</html>