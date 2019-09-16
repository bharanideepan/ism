<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/user.css"/>
</head>
<body class="bg">
	<div class="background">
	  <div class="content"> 
		  <h1>Manage the interview schedule</h1>
		  <p>
		      A tool designed to automate and manage your organization's recruiting and staffing operations.
		  </p>
	  </div>
	    <div class="loginform">
			<form:form method="post" action="loginUser" modelAttribute="user">
		      <div align="center"><img  src="/image/user.png" height="50px" width="50px"/></div><br>
			  <div class="form-group">
			    <label for="email">Username:</label><br>
			    <form:input type="text" placeholder="Username" path="name" class="form-control"/>
			  </div><br>
			  <div class="form-group">
			    <label color="black" for="pwd">Password:</label>
			    <form:input type="password" placeholder="Password" path="password" class="form-control"/>
			  </div><br>
			  <button type="submit" class="btn btn-primary">Login</button>
			</form:form>
		</div>
	</div>
</body>
</html>