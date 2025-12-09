<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>게시판</title>
    <link rel="stylesheet" href="${root}/css/boardList.css">
</head>
<body>
<!-- Header (menu.jsp 포함) -->
	<div class="sidebar-wrapper">
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div>
	<div class="scrollable-content">
	<div class="boardlist-container">
			<div class="board-page-header">
		        <c:if test="${boardId==1}"><h2>공지사항</h2></c:if>
		        <c:if test="${boardId==2}"><h2>취업 정보 공유</h2></c:if>
		        <c:if test="${boardId==3}"><h2>자유게시판</h2></c:if>
		        <div class="list-action-bar">
		             <a href="${root}/board/add/${boardId}" class="write-btn">글쓰기</a>
		        </div>
    		</div>
            <c:if test="${boardList.size() < 1}">
                <div class="empty-message">등록된 게시글이 없습니다.</div>
            </c:if>
            <c:forEach var="boardList" items="${boardList}">
                <div class="boardlist-item">
    				<div class="boardlist-header">
        				<a href="${root}/board/detail/${boardList.postId}">${boardList.title}</a><span class="item-date"><fmt:formatDate value="${boardList.writeDate}" pattern="yyyy/MM/dd HH:mm:ss"/></span>
    				</div>
    				<div class="item-meta">
        				<div class="meta-info">
            				<span class="item-writer">${boardList.postWriter}</span>
            				<span class="item-count">조회 ${boardList.count}</span>
        				</div>
        				<div class="meta-actions">
            				<a href="${root}/board/modify/${boardList.postId}">수정</a>
            				<span class="divider">|</span>
            				<a href="${root}/board/list/${boardList.boardId}/${boardList.postId}">삭제</a>
        				</div>
    				</div>
				</div>
            </c:forEach>
            </div>
    </div>
</body>
</html>
