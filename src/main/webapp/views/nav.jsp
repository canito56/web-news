<nav class="nav">
    <div class="dropdown">
        <button class="dropnews">NEWS</button>
        <div class="dropdown-content">
            <% if (session.getAttribute("view") == "signup") { %>       
                <a href="NewsController?opt=signin">SIGN IN</a>
        	<% } %>   
            <% if (session.getAttribute("view") == "signin") { %>    
                <a href="NewsController?opt=signup">SIGN UP</a>
            <% } %>    
            <% if (session.getAttribute("view") != "signin" && session.getAttribute("view") != "signup") { %>
                <a href="NewsController?opt=list&orderby= ">All NEWS</a>
                <% if (session.getAttribute("view") != "add") { %> 
                    <a href="NewsController?opt=add">Add a NEWS</a>
                <% } %> 
                <% if (session.getAttribute("view") != "search") { %>
                    <a href="NewsController?opt=search">Search a NEWS</a>
                <% } %>
            <% } %>
        </div>
    </div>
    <div class="navuser">
        <div class="dropuser">
            <% if (session.getAttribute("nuser") != "") { %>
	            <p id="puser">User:</p>
	            <output id="output-user"><%= session.getAttribute("nuser") %></output>
	            <div class="dropdown-user">
	                <a href="NewsController?opt=chgpwd">Change Password</a>
	                <a href="NewsController?opt=signin">SIGN OFF</a>    
	            </div>
            <% } %>
        </div>
    </div>
</nav>