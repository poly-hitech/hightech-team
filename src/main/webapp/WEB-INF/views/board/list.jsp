<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>게시판</title>
</head>
<body>
<!-- Header (menu.jsp 포함) -->
<%-- 	<div>
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div> --%>
	<table border="1">
		<thead>
               <tr>
                    <th>제목</th>
                    <th>날짜</th>
                    <th>조회수</th>
                    <th>작성자</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
        </thead>
		<tbody>
      
                <c:if test="${boardList.size()<1}">
                    <tr>
                        <td colspan="5">빈공간</td>
                    </tr>
                </c:if>

                <c:forEach var="boardList" items="${boardList}">
                    <tr>
                        <td>
            				<a href="${root}/board/detail/${boardList.postId}">${boardList.title}</a>
        				</td>
                        <td>${boardList.writeDate}</td>
                        <td>${boardList.count}</td>
                        <td>${boardList.postWriter}</td>
                        <td>
                        	<a href="${root}/board/modify/${boardList.postId}" class="link flex">수정하기</a>
                        </td>
                        <td>
                        	<a href="${root}/board/list/${boardList.boardId}/${boardList.postId}">삭제하기</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
		<tfoot>
            </tfoot>
	</table>
<a href="${root}/board/add/${boardId}" class="link flex">글쓰기</a>


</body>
</html>
