<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="/css/user.css"/>
      <link rel="stylesheet" type="text/css" href="/css/popUp.css">
</head>
<body class="bg" id="login" onload="currentStatus('${status}');">
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
       <div id="pass">
         <div class="modal-content">
            <div id="created">Signed in Successfully</div>
            <br>
            <div id="updated">Bad Credentials</div>
            <br>
            <span class="close">&times;</span>
         </div>
      </div>
      <script type="text/javascript">
         function currentStatus(status) {
             if (status === "created") {
                 var modal = document.getElementById("pass");
                 var created = document.getElementById("created");
                 var span = document.getElementsByClassName("close")[0];
                 modal.style.display = "block";
                 created.style.display = "block"
                 span.onclick = function() {
                     modal.style.display = "none";
                     created.style.display = "none"; 
                  }
             } else if (status === "badCredential") {
                 var modal = document.getElementById("pass");
                 var updated = document.getElementById("updated");
                 var span = document.getElementsByClassName("close")[0];
                 modal.style.display = "block";
                 updated.style.display = "block"; 
                 span.onclick = function() {
                     modal.style.display = "none";
                     updated.style.display = "none"; 
                 }
             }
         }
      </script>
</body>
</html>