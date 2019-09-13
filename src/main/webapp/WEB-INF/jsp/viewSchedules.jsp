<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>View schedules</title>
      <link rel="stylesheet" type="text/css" href="/css/viewSchedules.css">
        <link rel="stylesheet" type="text/css" href="/css/popUp.css">
   </head>
   <body onload="currentStatus('${status}');">
      <%@ include file="header.jsp" %> 
      <div id="dateSearch"  align="center">
      <form method="post" action="schedulesByDate">
      	<input type="date" name="shdate" required/>
      	<button type="submit">Search</button>
      </form>
      </div>
      <div>
         <c:if test="${schedules != null}">
            <c:if test="${!schedules.isEmpty()}">
               <table id="contentTable" class = "table" align="center" cellpadding = "10">
                  <tr>
                     <th>S.No.</th>
                     <th>Candidate Name</th>
                     <th>Round</th>
                     <th>Interview Type</th>
                     <th>Date</th>
                     <th>Time</th>
                     <th>Schedule Status</th>
                     <th>Interviewer</th>
                  </tr>
                  <c:set var="sNumber" value="1" scope="page"/>
                  <c:forEach var="schedule" items="${schedules}">
                     <tr>
                        <td>${sNumber}</td>
                        <td><a href="viewProgress?id=${schedule.candidate.id}">${schedule.candidate.name}</a></td>
                        <td>${schedule.round}</td>
                        <td>${schedule.interviewType}</td>
                        <td>${schedule.date}</td>
                        <td>${schedule.time}</td>
                        <td><a href="getScheduleWithInterviewers?scheduleId=${schedule.id}">${schedule.status}</a></td>
                        <c:choose>
                           <c:when test="${schedule.interviewer != null}">
                              <td>${schedule.interviewer.name}</td>
                           </c:when>
                           <c:otherwise>
                              <td>Not assigned</td>
                           </c:otherwise>
                        </c:choose>
                     </tr>
                     <c:set var="sNumber" value="${sNumber+1}" scope="page"/>
                  </c:forEach>
               </table>
            </c:if>
            <c:if test="${schedules.isEmpty()}">
               <table id="contentTable" class = "table" align="center" cellpadding = "10">
                  <tr>
                     <td>No results available for your search</td>
                  </tr>
               </table>
            </c:if>
         </c:if>
         <c:if test="${schedules == null}">
            <table id="contentTable" class = "table" align="center" cellpadding = "10">
               <tr>
                  <td>No results available for your search</td>
               </tr>
            </table>
         </c:if>
      </div>
       <div id="pass">
         <div class="modal-content">
            <div id="created">Created Successfully</div>
            <br>
            <div id="updated">Updated Successfully</div>
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
             } else if (status === "updated") {
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