<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:import url="../menu.jsp"></c:import>
	</div>
	
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
	<main>
        <div>
            <c:forEach var="resourceCategory" items="${list}">
            	<div>
            		${resourceCategory.resourceCategoryName}
            	</div>
            	<div>
            		${resourceCategory.resourceSubCategory}
            	</div>
                <div>
                    <img src="${resourceCategory.resourceSubCategory.resourceShop.resource}">
                    <h3>${resourceCategory.resourceSubCategory.resourceShop.itemName}</h3>
                    <div class="author">${resourceCategory.resourceSubCategory.resourceShop.itemWriter}</div>
                    <div class="price">$${resourceCategory.resourceSubCategory.resourceShop.itemPrice}</div>
                </div>
            </c:forEach>
        </div>
        <div class="loading" id="loading">Loading...</div>
    </main>
</body>
</html>