<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="/css/createCandidate.css"/>
</head>
<body class="bg">
        <div id="header">
           	<img src="/image/logo.png" width="100px" height="50px"/>
           	<h2 id="heading">Interview Schedule Management</h2>
        </div>
        <div>
          <table class="table" >
            <caption>Login</caption>
            <form:form method="post" action="loginUser" modelAttribute="user">
              <tr>
                  <td>User Name :
                  <input type = "text" name = "Name" required></td>
              </tr>
              <tr>
                <td>Login As:
                <select name="role">
            	<c:forEach var="role" items="${roles}">
            		<option>
                        <c:out value="${role.name}"/>
            		</option>
				</c:forEach>
                </select></td>
              </tr>
              <tr>
                <td>Password :
                <input type = "password" name = "Password" required></td>
              </tr>
              <tr>
               <td><button class="button">Login</button></td>
              </tr>
            </form:form>
          </table>
        </div><br>
    	<div align="center">
	      <form method="get" action="/createUser">
   		    <button class="button">Register</button>
          </form>
        </div>
</body>
</html>