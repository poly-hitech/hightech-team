<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/login.css" />
	<link rel="stylesheet" href="css/position.css">
</head>
<body>
	<div>
		<c:import url="menu.jsp"></c:import>
	</div>

	<div class="container">
		<form class="login-box" method="post">
			<div>
	        	<h2>회원가입</h2>
	        </div>
			<div>
				<label>아이디:</label>
				<input type="text" name="username">						
			</div>
					
			<div>
				<label>비밀번호:</label>
				<input type="password" name="password">				
			</div>
			
			<div>
				<label>성명:</label>
				<input type="text" name="name">				
			</div>
			
			<div>
				<label>닉네임:</label>
				<input type="text" name="nickname" placeholder="미입력 시 아이디로 닉네임이 설정됩니다.">				
			</div>
			
			<div>
				<label>생일:</label>
				<input type="date" name="birthday">				
			</div>
			
			<div>
				<label>휴대폰:</label>
				<input type="text" name="phone">				
			</div>
			
			<div>
				<label>성별:</label>
				<input type="text" name="gender">				
			</div>
									
			<div>
				<button type="submit">회원가입</button>
				<a href="/">취소</a>
			</div>					
		</form>	
	</div> 

</body>
</html>