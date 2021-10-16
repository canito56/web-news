<head>
	<link rel="stylesheet" href="views/news.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
</head>

<% session.setAttribute("view", "add"); %>
<%@ include file="nav.jsp" %>

<div id="card" class="animate__animated animate__zoomIn">
    <fieldset class="fset-add">
        <legend>Add NEWS</legend>
        <form action="NewsController?opt=add" method="POST">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" id="title" name="title" placeholder="Title" required autofocus>
            </div>
            <div class="form-group">
                <label for="news">News</label>
                <input type="text" id="news" name="news" placeholder="News" required>
            </div>
            <div class="form-group">
                <input type="submit" id="add" name="add" value="Add">
            </div>
            <div class="sign-message">
                <output><%= session.getAttribute("message") %></output>
            </div>
        </form>
    </fieldset>
</div>