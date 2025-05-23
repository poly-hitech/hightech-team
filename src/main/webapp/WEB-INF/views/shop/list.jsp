<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리소스 상점</title>
<link rel="stylesheet" href="${root}/css/shop.css" />
</head>
<body>
	<div>
		<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	</div>
	
	<div style="padding-left: 300px;">
		<c:forEach var="list" items="${list}">
            <h2>${list.resourceCategoryName}</h2>
        </c:forEach>
	</div>
    
	<main>
		<nav class="category-nav">
	    	<ul>
	        	<li><a data-primary="All">전체</a></li>
	            <li>
	            	<a data-primary="Images">이미지 소스</a>
	           		<ul class="dropdown">
	                	<c:forEach var="secondary" items="${secondaryCategories['Images']}">
	                    	<li><a data-secondary="${secondary}">${secondary}</a></li>
	                    </c:forEach>
	                </ul>
	            </li>
	            <li>
	            	<a data-primary="Music">음원</a>
	               	<ul class="dropdown">
	                	<c:forEach var="secondary" items="${secondaryCategories['Music']}">
	                    	<li><a data-secondary="${secondary}">${secondary}</a></li>
	                    </c:forEach>
	                </ul>
	            </li>
	            <li>
	            	<a data-primary="Development">개발</a>
	              	<ul class="dropdown">
	                	<c:forEach var="secondary" items="${secondaryCategories['Development']}">
	                    	<li><a data-secondary="${secondary}">${secondary}</a></li>
	                    </c:forEach>
	                </ul>
	            </li>
	        </ul>
	    </nav>
	    
		<div class="secondary-categories" id="secondary-categories">
    		<ul id="secondary-list"></ul>
    	</div>
	    
        <div>
            <c:forEach var="category" items="${list}">
            	<div>
            		${category.resourceCategoryName}
            	</div>
            	<c:forEach var="sub" items="${category.resourceSubCategory}">
	            	<div>
	            		${sub.resourceSubCategoryName}
	            	</div>
	            	<c:forEach var="shop" items="${sub.resourceShop}">
	            		<c:forEach var="file" items="${shop.resourceFile}">
			                <div>
			                    <img src="${file.resourceFileName}">
			                    <h3>${shop.itemName}</h3>
			                    <div class="author">${shop.itemWriter}</div>
			                    <div class="price">${shop.itemPrice}</div>
			                </div>
		                </c:forEach>
	             	</c:forEach>
                </c:forEach>
            </c:forEach>
        </div>
        <div class="loading" id="loading">Loading...</div>
    </main>
</body>
</html>