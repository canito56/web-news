<head>
	<link rel="stylesheet" href="views/news.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
</head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("view", "edit"); %>
<%@ include file="nav.jsp" %>

<div id="card" class="animate__animated animate__zoomIn">
    <fieldset class="fset-edit">
        <legend>EDIT NEWS</legend>
        <form action="NewsController?opt=edit&id=<c:out value="${news.news_id}"></c:out>" method="POST">
            <div class="form-group">
                <output><c:out value="${news.title}"></c:out></output>
                <input type="hidden" id="title" name="title" value="" required>
            </div>
            <div class="form-group">
                <label for="news">News</label>
                <input type="text" id="news" name="news" value="<c:out value="${news.news}"></c:out>" required autofocus>
            </div>
            <div class="form-group">
                <input type="submit" id="save" name="save" value="Save">
            </div>
        </form>
    </fieldset>
</div>