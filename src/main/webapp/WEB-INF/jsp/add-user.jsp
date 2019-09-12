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
        <li><a href = "index"><img src="img/index.png" width="60"></a><li>
        <li style="float:right"><div class="dropdown">
            <button class="dropbtn"><c:out value="Account"/></button>
            <div class="dropdown-content">
        	    <a href="<c:url value="logout" />" > Logout</a>
            </div>
        </div> </li>
    </ul>
    <h1>Register</h1>
    <form:form method="post" action="saveUser" modelAttribute="user">
        <table>
            <tr>
                <td>User Name :
                <input type = "text" name = "Name" required></td>
            </tr>
            <tr>
                <td>Password :
                <input type = "password" name = "Password" required
                    pattern ="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}"
                    title="Must contain at least one number,one uppercase,lowercase letter, length 8"></td>
            </tr>
            <tr>
                <td>Employee Id :
                <input type = "number" name = "Employee" required></td>
            </tr>
            <tr>
                <td>Select Role : 
                    <c:forEach var="role" items="${roles}">
                        <input type="checkbox" name="UserRoles" value="${role.id}">
                        <c:out value="${role.name}"/>
                    </c:forEach>
                </td>
            </tr>
        </table>
        <button class="button">Create</button>
    </form:form>
</body>
</html>