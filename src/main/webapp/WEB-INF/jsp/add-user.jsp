<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="/css/signup.css"/>
</head>
<body class="bg" id="login">
        <div id="header">
           	<img src="/image/logo.png" width="100px" height="50px"/>
           	<h2 id="heading">Interview Schedule Management</h2>
        </div>
    <form:form method="post" action="saveUser" modelAttribute="user">
        <table class="table">
            <tr><td align="center" colspan="2"><h2 style="color:green">Sign Up</h2></td></tr>
            <tr>
                <td>User Name * </td>
                <td>
                <input type = "text" name = "Name" required></td>
            </tr>
            <tr>
                <td>Password * </td>
                <td>
                <input type = "password" name = "Password" required
                    pattern ="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}"
                    title="Must contain at least one number,one uppercase,lowercase letter, length 8"></td>
            </tr>
            <tr>
                <td>Employee Id * </td>
                <td>
                <input type = "number" name = "Employee" required></td>
            </tr>
            <tr>
                <td>Select Role                
               <select name = "userRoles" required>
                 <c:forEach var="role" items="${roles}">
                   <option value="${role.id}">${role.name}</option>  
                 </c:forEach>
               </select>
               </td>
            </tr>
             <tr><td><button class="button">Create</button></td></tr>
        </table>
    </form:form>
</body>
</html>