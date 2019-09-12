<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="css/user.css"/>
</head>
<body class="bg">
    <ul>
        <li><a href = "index"><img src="image/index.png" width="60"></a><li>
        <li style="float:right"><div class="dropdown">
            <button class="dropbtn"><c:out value="Account"/></button>
            <div class="dropdown-content">
        	    <a href="<c:url value="j_spring_security_logout" />" > Logout</a>
            </div>
        </div> </li>
    </ul>
    <h2><%=request.getAttribute("message") %></h2>
    <h2>Login</h2>
    <form:form method="post" action="loginUser" modelAttribute="user">
        <table>
            <tr>
                <td>User Name :
                <input type = "text" name = "Name" required></td>
            </tr>
            <tr>
                <td>Password :
                <input type = "password" name = "Password" required></td>
            </tr>
        </table>
        <tr>
            <td>Role:</td>
            <td><select name="role">
            	<c:forEach var="role" items="${roles}">
            		<option>
                        <c:out value="${role.name}"/>
            		</option>
				</c:forEach>
            </select></td>
        </tr>
        
        <button class="button">Login</button>
    </form:form>
	<br>
	<form method="get" action="/createUser">
   		<button class="button">Register</button>
    </form>
</body>
</html>