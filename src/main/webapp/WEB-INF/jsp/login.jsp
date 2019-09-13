<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="/css/user.css"/>
</head>
<body class="bg" id="login" >
   <%@ include file="header.jsp" %> 
        <div >
          <table class="table">
            <form:form method="post" action="loginUser" modelAttribute="user">
              <tr>
                  <td class="info">User Name :</td></tr>
              <tr>
                  <td class="data">
                  <input class="ans" type = "text" name = "Name" required></td>
              </tr>
              <tr >
                <td class="info">Password :</td></tr>
              <tr>  
                <td class="data"><input class="ans" type = "password" name = "Password" required></td>
              </tr>
              <tr>
               <td align="center"><button class="button">Login</button></td>
              </tr>
            </form:form>
              <tr><td><a href="createUser" color="green">create new user ?</a></td></tr>
          </table>
        </div><br>
</body>
</html>