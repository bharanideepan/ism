<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Candidate</title>
      
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="/css/user.css"/>
        <link rel="stylesheet" href="/css/ism.css"/>
</head>
<body>
      <div class="col-md-12 col-md-offset-0">
         <div class="fresh-table full-color-orange">
            <div class="container-fluid">
               <div class="navbar-header">
                  <font class="navbar-brand">Interview Schedule Management</font>
               </div>
    <!-- <ul class="nav navbar-nav">
      <c:if test="${role == 'Recruiter'}">
      <li><a href="viewSchedules">Schedules</a></li>      
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Candidates <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="addCandidate"><span class="glyphicon glyphicon-plus"></span> Add Candidate</a></li>
          <li><a href="viewCandidates">View Candidates</a></li>
        </ul>
      </li>
      </c:if>
      <li><a href="logout">Log Out</a></li>
    </ul> -->
  </div>

            <div class="menu-bar">
               <table class = "table">
                  <c:if test="${role == 'Recruiter'}">
                     <tr>
                        <th><a href="viewSchedules">Schedules</a></th>
                     </tr>
                     <tr>
                        <th><a href="addCandidate"><span class="glyphicon glyphicon-plus"></span> Add Candidate</a></th>
                     </tr>
                     <tr>
                        <th><a href="viewCandidates">View Candidates</a></th>
                     </tr>
                  </c:if>
                  <tr>
                     <th><a href="logout">Log Out</a></th>
                  </tr>
               </table>
            </div>  
  <div class="table-div-create">
 <form:form name ="form" action="saveCandidate" method="post" modelAttribute="candidate" enctype = "multipart/form-data"> 
      <table class="table"> 
        <form:input type="hidden" path="id" value="${candidate.id}"/>
        <form:input type="hidden" path="resumeFilePath" value="${candidate.resumeFilePath}"/>
        <tr> <td colspan = "3">Candidate Information </td> 
        <tr><td >Name * :</td>
          <td>
            <form:input class="form-control" type="text" path="name" required="required"></form:input>
          </td>
        </tr>  
        <tr><td>Phone Number:</td>
          <td>
          <form:input class="form-control" type ="tel"  maxlength="10" value="${candidate.phoneNumber}" path="phoneNumber"/>
          </td>
        </tr>
        <tr><td>Email Id * :</td>
          <td>
          <form:input class="form-control" type ="email" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
          		value="${candidate.emailId}" path="emailId" required="required"/>
          </td>
        </tr>
        <tr><td>Position * :</td>
          <td>
          <form:input class="form-control" type ="text" value="${candidate.position}" path="position" required="required"/>
          </td>
        </tr>
        <tr><td>Department:</td>
          <td>
          <form:select class="select" required="required" path="department" value="${candidate.department}"
          		items="${candidateFormInfo.departments}"/>
          </td>
        </tr>
        <tr><td>Technology:</td>
          <td>
          <form:select class="select" path="technology" value="${candidate.technology}" items="${candidateFormInfo.technologies}"/>
          </td>
        </tr>
        <tr><td>Experience:</td>
          <td>
          <form:input class="form-control" type ="text" value="${candidate.experience}" path="experience"/>
          </td>
        </tr>
        <tr><td>Resume * :</td>
          <td> <input class="form-control" name="resume" type="file" required="required"/>
        </td></tr>  
        <tr>
          <c:if test="${update == action}">
              <td>
                  <input class="btn btn-primary" id="save" type ="submit" value="Save"/>
              </td>
          </c:if>
          <c:if test="${update != action}">
              <td>
                  <input id="save" class="btn btn-primary" type ="submit" onclick="changeAction()" value="Update"/>
              </td>
          </c:if>
        </tr>
       </table>
  </form:form>
  </div></div></div>
  
  <script type="text/javascript">
      function changeAction() {
          document.form.action = "updateCandidate";
      }
  </script>
</body>
</html>