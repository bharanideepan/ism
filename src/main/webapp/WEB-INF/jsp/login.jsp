<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="/css/user.css"/>
    
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
</head>
<body class="bg" id="login" >
   <%@ include file="header.jsp" %> 
   
        <div>
            <form:form method="post" action="loginUser" modelAttribute="user">
			 <div class="form-group">
                  <label for="name">User Name :</label>
                  <input class="form-control" type="text" name="Name" id="name" required/>
              </div>
			 <div class="form-group">
                <label for="pwd">Password :</label>
                <input class="form-control" id="pwd" type="password" name = "Password" required/>
              </div>
               <button class="btn btn-default">Login</button>
            </form:form>
        </div>
</body>
</html>