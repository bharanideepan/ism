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
                <td>Time:</td>
                <td>${schedule.time}</td>
            </tr>
            <tr>
                <td>Status:</td>
                <td>${schedule.status}</td>
            </tr>
        </table>
    </div>
    <div id="interviewersId">
        <table>
        <c:foreach var="interviewer" items="${interviewers}">
            <tr>
                <td>Interviewer Name:</td>
                <form action="assignInterviewer" methos="post">
                <td>
                	<input type="hidden" name="interviewerId" value="${interviewer.id}">
                	<input type="hidden" name="scheduleId" value="${schedule.id}">
                	<input type="submit" value="${interviewer.name}">
               	</td>
               	</form>
            </tr>
        </c:foreach>
        </table>
    </div>

</body>
</html>