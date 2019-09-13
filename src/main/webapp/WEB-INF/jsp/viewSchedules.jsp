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
         <link rel="stylesheet" type="text/css" href="/css/recruiterMenu.css">
      
   </head>
   <body>
      <%@ include file="header.jsp" %> 
      <c:if test="${role == 'Manager'}">
        <div class="navbar">
            <a href="newSchedules">New Schedules</a>
            <a href="pendingSchedules">Pending Schedules</a>
            <a href="viewSchedulesByManager">View Schedules</a>
            <a href="logout" style="float:right">Log Out</a>
        </div>
      </c:if>
      <c:if test="${role == 'Recruiter'}">
        <div class="navbar">
            <a href="addCandidate">Add Candidate</a>
            <a href="viewCandidates">View Candidates</a>
            <a href="viewSchedules">View Schedules</a>
            <a href="logout" style="float:right">Log Out</a>
        </div>
      </c:if>
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
                     <th>Records</th>
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
                        <td>${schedule.status}</td>
                        <c:choose>
                           <c:when test="${schedule.interviewer != null}">
                              <td>${schedule.interviewer.name}</td>
                           </c:when>
                           <c:otherwise>
                              <td>Not assigned</td>
                           </c:otherwise>
                        </c:choose>
                        <td><a href="getScheduleWithInterviewers?scheduleId=${schedule.id}">More</a></td>
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
   </body>
</html>