<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="../../css/login.css" />
	<link rel="stylesheet" href="../../css/position.css">
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
				<label>아이디:</label>
				<input type="text" name="username" value="${item.username}" readonly="readonly">						
			</div>
					
			<div>
				<label>비밀번호:</label>
				<input type="password" name="password">				
			</div>
			
			<div>
				<label>닉네임:</label>
				<input type="text" name="nickname" value="${item.nickname}">				
			</div>
			
			<div>
				<label>생일:</label>
				<input type="text"  value="${item.birthday}">				
			</div>
			
			<div>
				<label>휴대폰:</label>
				<input type="text" name="phone" value="${item.phone}">				
			</div>
			
			<div>
				<label>성별:</label>
				<input type="text" name="gender" value="${item.gender}">
			</div>
			
			<div>
				<label>가입일: ${item.signupDay}</label>
			</div>
									
			<div>
				<button type="submit">회원정보변경</button>
				<a href="${root}/">취소</a>
			</div>					
		</form>	
	</div>

</body>
</html>