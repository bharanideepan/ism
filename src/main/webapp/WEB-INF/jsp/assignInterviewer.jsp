<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assign interviewer</title>
</head>
<body>
      <div class="navbar">
         <a href="addCandidate">Add candidate</a>
         <a href="viewCandidates">View Candidates</a>
         <a href="viewSchedules">View Schedules</a>
      </div>
      
    <div id="scheduleId">
        <table>
            <tr>
                <td>Candidate Name:</td>
                <td>${schedule.candidate.name}</td>
            </tr>
            <tr>
                <td>Interview Type</td>
                <td>${schedule.interviewType}</td>
            </tr>
            <tr>
                <td>Interview Level:</td>
                <td>${schedule.interviewLevel}</td>
            </tr>
            <tr>
                <td>Date:</td>
                <td>${schedule.date}</td>
            </tr>
            <tr>
                <td>Status:</td>
                <td>${schedule.status}</td>
            </tr>
        </table>
    </div>
    <div id="interviewersId">
        <table>
        <c:forEach var="interviewer" items="${interviewers}">
            <tr>
                <td>Interviewer Name:</td>
                <form action="assignInterviewer" method="post">
                <td>
                	<input type="hidden" name="interviewerId" value="${interviewer.id}">
                	<input type="hidden" name="scheduleId" value="${schedule.id}">
                	<input type="submit" value="${interviewer.name}">
               	</td>
               	</form>
            </tr>
        </c:forEach>
        </table>
    </div>

</body>
</html>