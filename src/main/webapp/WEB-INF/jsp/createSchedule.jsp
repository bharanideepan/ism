<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
   <head>
      
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
   <body id="background">
<%@ include file="header.jsp" %>    

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">ISM</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="viewSchedules">View Schedules</a></li>
      <li><a href="addCandidate">Add Candidate</a></li>
      <li><a href="viewCandidates">View New Candidates</a></li>
      <li><a href="logout">Log Out</a></li>
    </ul>
  </div>
</nav>
  
      <div id="createScheduleId" class="makeSchedule" align="center">
         <div class= "flex">
            <form:form name="form" id="createScheduleFormId" action="createSchedule" method="post" modelAttribute="schedule">
               <div class="box">
                  <table class="table">
                  <caption>Interview Schedule Form</caption>
                     <tr>
                        <td>InterviewTypes</td>
                        <td>
                           <form:select path="interviewType" items="${types}" />
                        </td>
                     </tr>
                     <tr>
                        <td>Round</td>
                        <td>
                           <c:forEach var="schedule" items="${candidate.schedules}">
                              <c:set var="round" value="${schedule.round}" scope="page"/>
                           </c:forEach>
                           <form:input path="round" value="${round+1}" />
                        </td>
                     </tr>
                     <tr>
                        <td>Date</td>
                        <td>
                           <input type="datetime-local" name="shdate" required="required"/>
                        </td>
                     </tr>
               <tr>
                  <td>Assign Interviewer</td>
                  <td>
                  	 <select name="interviewerId">
                  	     <option value="">None</option>
                  	     <c:forEach var="interviewer" items="${interviewers}">
                             <option value="${interviewer.id}">${interviewer.name}</option>
                         </c:forEach>
                  	 </select>
                  </td>
               </tr>
                     <tr>
                        <td><input type="reset" value="Clear"></td>
                        <td>
                           <input type="hidden" name="candidateId" value="${candidate.id}"/><input type="submit" value="Submit">
                        </td>
                     </tr>
                  </table>
               </div>
            </form:form>
         </div>
      </div>
   </body>
</html>