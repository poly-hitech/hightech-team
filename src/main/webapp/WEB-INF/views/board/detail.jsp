<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>게시글</title>
    <link rel="stylesheet" href="${root}/css/forumPostDetail.css">
</head>
<body>
    <div class="sidebar-wrapper">
        <c:import url="/WEB-INF/views/menu.jsp"></c:import>
    </div>
    
    <div class="scrollable-content">
        <div class="content-border">
            <div class="post-header">
                <h1 class="post-title">${forumPost.title}</h1>
                <span class="post-date">
                    <fmt:formatDate value="${forumPost.writeDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
                </span>
            </div>
            
            <div class="post-sub-info">
                <span class="post-writer">${forumPost.postWriter}</span>
                <span class="post-count">조회수: ${forumPost.count}</span>
            </div>

            <div class="post-attachment-area">
            <c:choose>
                <c:when test="${not empty postFiles}">
                    <div class="file-preview-box">
                        <div class="section-title">첨부 파일 미리보기</div>
                        <ul class="file-list">
                            <c:forEach var="f" items="${postFiles}">
                                <c:set var="abs" value="${f.fileName}" />  
                                <c:set var="rel" value="${fn:substringAfter(abs, 'upload/')}" />
                                <c:set var="folder" value="${fn:substringBefore(rel, '/')}" />
                                <c:set var="fullFileName" value="${fn:substringAfter(rel, folder)}"/>
                                <c:set var="name" value="${fn:substringAfter(fullFileName, '_')}" />

                                <li class="file-item"> 
                                    <c:choose>
                                        <c:when test="${folder == 'images'}">
                                            <div class="file-type-label">이미지</div>
                                            <img src="${root}/upload/${rel}" alt="${name}">   
                                        </c:when>
                                        <c:when test="${folder == 'musics'}">
                                            <audio controls src="${root}/upload/${rel}"></audio>
                                        </c:when>
                                        <c:when test="${folder == 'video'}">
                                            <video controls width="100%" src="${root}/upload/${rel}"></video>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${root}/upload/${rel}" target="_blank">${name}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                    </c:otherwise>
            </c:choose>
            </div>
            
            <div class="post-main-content">${forumPost.content}</div>

            <div class="comment-section">
                <div class="comment-form">
                    <form action="${root}/board/detail/${forumPost.postId}" method="post">
                        <input type="hidden" name="postId" value="${forumPost.postId}"> 
                        <label>새 댓글</label>
                        <input type="text" name="commentContent" required>
                        <button class="regist" type="submit">등록</button>
                    </form>
                </div>
                
                <br>
                
                <label>댓글 (<c:out value="${comments.size()}" default="0"/>개)</label>
                
                <table border="1" class="comment-list-table">
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
                                        <td>
                                            <fmt:formatDate value="${comment.commentWriteDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
                                        </td>
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
        </div>
    </div>
</body>
</html>