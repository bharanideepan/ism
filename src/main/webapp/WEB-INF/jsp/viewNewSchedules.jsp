<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="/css/viewNewSchedules.css">
    <link rel="stylesheet" type="text/css" href="/css/popUp.css">
   <link rel="stylesheet" type="text/css" href="/css/recruiterMenu.css">
</head>
<body id="background">
<%@ include file="header.jsp" %> 
        <div class="navbar">
            <a href="newSchedules">New Schedules</a>
            <a href="pendingSchedules">Pending Schedules</a>
            <a href="logout" style="float:right">Log Out</a>
        </div>
      <div>
         <c:if test="${schedules != null}">
            <c:if test="${!schedules.isEmpty()}">
               <table id="contentTable" class = "table" align="center" cellpadding = "10">
                  <tr>
                     <th>Name</th>
                     <th>Position</th>
                     <th>Department</th>
                     <th>Experience</th>
                     <th>Status</th>
                     <th>Accept</th>
                     <th>Reject</th>
                  </tr>
                  <c:forEach var="schedule" items="${schedules}">
                     <tr>
                        <td><a href="viewProgress?id=${schedule.candidate.id}">${schedule.candidate.name}</a></td>
                        <td>${schedule.candidate.position}</td>
                        <td>${schedule.candidate.department}</td>
                        <td>${schedule.candidate.experience}</td>
                        <c:if test="${schedule.candidate.status != 'New'}">
                        <td>Cleared ${schedule.round-1}st round</td>
                        </c:if>
                        <c:if test="${schedule.candidate.status == 'New'}">
                        <td>${schedule.candidate.status}</td>
                        </c:if>
                        <td>${schedule.date}</td>
                        <td>${schedule.time}</td>
                        <td > 
                           <button class = "accept" 
                              onclick="location.href='acceptSchedule?scheduleId=${schedule.id}&candidateId=${schedule.candidate.id}&id=${schedule.interviewer.id}';">&#10004;
                           </button>
                        </td>
                        <td>
                           <button class = "reject"
                              onclick="getComment('${schedule.interviewer.id}', '${schedule.id}', '${schedule.candidate.id}')">&times;
                           </button>
                        </td>
                     </tr>
                  </c:forEach>
               </table>
               <div id="pass">
                  <div class="modal-content">
                     <div>Comment</div>
                     <input type="text" id="feedback" name="feedback" /><br>
                     <input type="submit" id="saveComment" value="submit"/>
                  </div>
               </div>
            </c:if>
            <c:if test="${schedules.isEmpty()}">
               <table id="contentTable" class = "table" align="center" cellpadding = "10">
                  <tr>
                     <td>No new schedules</td>
                  </tr>
               </table>
            </c:if>
         </c:if>
         <c:if test="${schedules == null}">
            <table id="contentTable" class = "table" align="center" cellpadding = "10">
               <tr>
                  <td>No new schedules</td>
               </tr>
            </table>
         </c:if>
      </div>
      <script type="text/javascript">
         function getComment(employeeId , scheduleId, candidateId) {
             var modal = document.getElementById("pass");
             var feedback = document.getElementById("feedback");
             var submit = document.getElementById("saveComment");
             modal.style.display = "block";
             feedback.style.display = "block"
             submit.style.display = "block"
             submit.onclick = function() {
                 modal.style.display = "none";
                 submit.style.display = "none";
                 var feedback = document.getElementById("feedback").value;
                 location.href = "rejectSchedule?id="+employeeId+"&scheduleId="+scheduleId+"&candidateId="+candidateId+"&comment="+feedback;
             }
             window.onclick = function(event) {
                  if (event.target == modal) {
                      modal.style.display = "none";
                      feedback.style.display = "none";
                      submit.style.display = "none";
                  }
              }
         }
      </script>
   </body>
</html>