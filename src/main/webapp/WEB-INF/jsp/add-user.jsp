<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      <link rel="stylesheet" href="/css/user.css"/>
</head>
<body style="background-color: #837872;">
   <%@ include file="header.jsp" %> 
   <nav class="navbar navbar-inverse">
     <div class="container-fluid">
       <div class="navbar-header">
         <a class="navbar-brand" href="home">ISM</a>
       </div>
       <ul class="nav navbar-nav">
         <li><a href="createUser">Add User</a></li>
         <li><a href="logout">Log Out</a></li>
       </ul>    
      </div>
    </nav>
   	<div class="signUp">
			<form:form method="post" action="saveUser" modelAttribute="user">
		      <div  align="center"><img  src="/image/user.png" height="50px" width="50px"/></div><br>
			  <div class="form-group">
			    <label for="email" >Username:</label><br>
			    <form:input type="text" placeholder="Username" path="name" class="form-control" required="required"/>
			  </div><br>
			  <div class="form-group">
			    <label for="pwd">Password:</label>
			    <form:input type="password" placeholder="Password" path="password" class="form-control" required="required"/>
			  </div><br>
			  <div class="form-group">
			    <label>Employee:</label>
			     <select class="form-control" name = "employeeId" required>
                   <c:forEach var="employee" items="${employees}">
                     <option value="${employee.id}">${employee.name}</option>  
                   </c:forEach>
                 </select>
			  </div><br>
			  <div class="form-group">
			    <label>Role:</label>
                  <select class="form-control" name = "userRoles" required>
                     <c:forEach var="role" items="${roles}">
                       <option value="${role.id}">${role.name}</option>  
                     </c:forEach>
                  </select>
			  </div><br>
			  <div align="center"><input type="submit" class="btn btn-primary" value="save">
              </div>
			</form:form>
    </div>
</body>
</html>