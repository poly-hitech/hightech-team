<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/login.css" />
	<link rel="stylesheet" href="css/position.css" />
	<script src="js/login.js"></script>
</head>
<body>
	<div>
		<c:import url="menu.jsp"></c:import>
	</div>

	<div class="container">
		<form class="login-box" method="post">
			<div>
	        	<h2>로그인</h2>
	        </div>
			<div>
				<label>아이디:</label>
				<input type="text" name="username" id="username">				
			</div>
					
			<div>
				<label>비밀번호:</label>
				<input type="password" name="password" id="password">				
			</div>
									
			<div>
				<button type="submit">로그인</button>
				<a href="/">취소</a>
			</div>					
		</form>	
	</div>
	<c:if test="${not empty message}">
        <script>
            alert("${message}"); // Model에서 받은 메시지 표시
            <c:if test="${redirect}">
                window.location.href = "/"; // 성공 시 메인 페이지로 이동
            </c:if>
        </script>
    </c:if>

</body>
</html>