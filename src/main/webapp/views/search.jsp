<head>
	<link rel="stylesheet" href="views/news.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
</head>

<% session.setAttribute("view", "search"); %>
<%@ include file="nav.jsp" %>

<div id="card" class="animate__animated animate__zoomIn">
    <fieldset class="fset-search">
        <legend>Search a NEWS</legend>
        <form action="NewsController?opt=search" method="POST">
            <div class="form-group">
                <label for="search">Title</label>
                <input type="text" id="search" name="title" placeholder="Title" required autofocus>
            </div>
            <div class="form-group">
                <input type="submit" id="btn_search" name="btn_search" value="Search">
            </div>
            <div class="sign-message">
                <output><%= session.getAttribute("message") %></output>
            </div>
        </form>
    </fieldset>
</div>