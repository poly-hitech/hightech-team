<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>게시글</title>
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
                    <th>내용</th>
                    <th>날짜</th>
                    <th>조회수</th>
                    <th>작성자</th>
                </tr>
        </thead>
		<tbody>
                    <tr>
                        <td>${forumPost.title}</td>
                        <td>${forumPost.content}</td>
                        <td>${forumPost.writeDate}</td>
                        <td>${forumPost.count}</td>
                        <td>${forumPost.postWriter}</td>
                    </tr>
            </tbody>
		<tfoot>
            </tfoot>
	</table>
<div>
				<form action="${root}/board/detail/${forumPost.postId}" method="post">
        
        <input type="hidden" name="postId" value="${forumPost.postId}"> 
        
        <label>새 댓글</label>
        <input type="text" name="commentContent" required>
        
        <button class="regist" type="submit">등록</button>
    </form>
        
        <br>
        
        <label>댓글 목록 (<c:out value="${comments.size()}" default="0"/>개)</label>
        
        <table border="1">
            <thead>
                <tr>
                    <th>작성자</th>
                    <th>내용</th>
                    <th>날짜</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${comments != null and not empty comments}">
                        <c:forEach var="comment" items="${comments}">
                            <tr>
                                <td>${comment.commentWriter}</td>
                                <td>${comment.commentContent}</td>
                                <td>${comment.commentWriteDate}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="3" style="text-align: center;">등록된 댓글이 없습니다.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
			</div>

</body>
</html>
