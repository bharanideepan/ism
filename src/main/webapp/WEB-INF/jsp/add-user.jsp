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
  
      <link rel="stylesheet" href="/css/ism.css"/>
</head>
<body style="background-color: #837872;"> 
      <div class="col-md-12 col-md-offset-0">
         <div class="fresh-table full-color-orange">
                <div class="container-fluid">
                  <div class="navbar-header">
                     <font class="navbar-brand">Interview Schedule Management</font>
                  </div>
</div>
                  <div class="menu-bar">
                     <table class = "table">
                     <tr><th><a href="logout">Log Out</a></th></tr>
                     </table>
                  </div>
   	<div class="table-div-signup">
			<form:form method="post" action="saveUser" modelAttribute="user">
		      <div  align="center"><img  src="/image/user.png" height="50px" width="50px"/></div><br>
               <div class="box">
                  <table class="table">
                  <tr><th>Create User</th></tr>
                     <tr>
                        <td>Username:</td>
                        <td>
                           <form:input type="text" placeholder="Username" path="name" class="form-control" required="required"/>
                        </td>
                     </tr>
                     <tr>
                        <td>Password:</td>
                        <td>
                           <form:input type="password" placeholder="Password" path="password" class="form-control" required="required"/>
                        </td>
                     </tr>
                     <tr>
                        <td>Employee:</td>
                        <td>
                           <select class="select" name = "employeeId" required>
                   <c:forEach var="employee" items="${employees}">
                     <option value="${employee.id}">${employee.name}</option>  
                   </c:forEach>
                 </select>
                        </td>
                     </tr>
               <tr>
                  <td>Role:</td>
                  <td>
                  <select class="select" name = "userRoles" required>
                     <c:forEach var="role" items="${roles}">
                       <option value="${role.id}">${role.name}</option>  
                     </c:forEach>
                  </select>
                  </td>
               </tr>
                     <tr>
                        <td><input class="form-control" type="reset" value="Clear"></td>
                        <td>
                           <input type="hidden" name="candidateId" value="${candidate.id}"/><input type="submit" class="btn btn-primary" value="save">
                        </td>
                     </tr>
                  </table>
               </div>
			</form:form>
    </div></div></div>
</body>
</html>