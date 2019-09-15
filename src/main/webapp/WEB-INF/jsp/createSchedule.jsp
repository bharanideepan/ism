<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
   <head>
      
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="/css/ism.css"/>
  
   <body id="background">
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
         <div class= "table-div-create">
            <form:form name="form" id="createScheduleFormId" action="createSchedule" method="post" modelAttribute="schedule">
               <div class="box">
                  <table class="table">
                  <tr><th>Interview Schedule Form</th></tr>
                     <tr>
                        <td>InterviewTypes</td>
                        <td>
                           <form:select class="select" path="interviewType" items="${types}" />
                        </td>
                     </tr>
                     <tr>
                        <td>Round</td>
                        <td>
                           <c:forEach var="schedule" items="${candidate.schedules}">
                              <c:set var="round" value="${schedule.round}" scope="page"/>
                           </c:forEach>
                           <form:input class="form-control" path="round" value="${round+1}" />
                        </td>
                     </tr>
                     <tr>
                        <td>Date</td>
                        <td>
                           <input class="form-control" type="datetime-local" name="shdate" required="required"/>
                        </td>
                     </tr>
               <tr>
                  <td>Assign Interviewer</td>
                  <td>
                  	 <select class="select" name="interviewerId">
                  	     <option value="">None</option>
                  	     <c:forEach var="interviewer" items="${interviewers}">
                             <option value="${interviewer.id}">${interviewer.name}</option>
                         </c:forEach>
                  	 </select>
                  </td>
               </tr>
                     <tr>
                        <td><input class="form-control" type="reset" value="Clear"></td>
                        <td>
                           <input type="hidden" name="candidateId" value="${candidate.id}"/>
                           <input class="form-control" type="submit" value="Submit">
                        </td>
                     </tr>
                  </table>
               </div>
            </form:form>
         </div>
      </div></div>
   </body>
</html>