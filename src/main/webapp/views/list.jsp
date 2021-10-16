<head>
	<link rel="stylesheet" href="views/news.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
</head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("view", "list"); %>
<%@ include file="nav.jsp" %>

<table id="table" class="animate__animated animate__zoomIn">    
    <tr>
        <th><a href="NewsController?opt=list&orderby=title" id="th-title">TITLE</a></th>
        <th id="th-news">NEWS</th>
        <th><a href="NewsController?opt=list&orderby=date_created" id="th-date">DATE</a></th>
        <th id="th-del"></th>
    </tr>
    <c:forEach var="listNews" items="${listNews}">
    <tr>
        <td><a id="td-title" href="NewsController?opt=edit&id=<c:out value="${listNews.news_id}"></c:out>"><c:out value="${listNews.title}"></c:out></a></td>
        <td id="td-news"><c:out value="${listNews.news}"></c:out></td>
        <td id="td-date"><c:out value="${listNews.date_created}"></c:out></td>
        <td id="td-del"><a href="NewsController?opt=delete&id=<c:out value="${listNews.news_id}"></c:out>" class="del"><img src="images/trash.png" align="middle" width=16 height=19></a></td>
    </tr>
    </c:forEach>
</table>