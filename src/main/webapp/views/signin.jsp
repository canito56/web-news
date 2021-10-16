<head>
	<link rel="stylesheet" href="views/news.css">
</head>

<% session.setAttribute("view", "signin"); %>
<%@ include file="nav.jsp" %>

<div class="card-signin">
    <fieldset class="fset-signin">
        <legend>SIGN IN</legend>
        <form action="NewsController?opt=signin" method="POST">
            <div class="signin">
                <div class="form-group">
                    <label for="user" class="label-in">User</label>
                    <input type="text" id="user" name="user" placeholder="User" required autofocus>
                </div>
                <div class="form-group">
                    <label for="password" class="label-in">Password</label>
                    <input type="password" id="password" name="password" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <input type="submit" id="signin" name="signin" value="Sign In">
                </div>
            </div>
            <div class="sign-message">
                <output><%= session.getAttribute("message") %></output>
            </div>
        </form>
    </fieldset>
</div>