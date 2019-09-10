<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>View Candidate</title>
 <link rel="stylesheet" type="text/css" href="/css/viewCandidate.css">
</head>
<body>
    <div>
        <h2 align="center">Interview Schedule Management </h2>
    </div>
    <div>
        <table class="table">
            <tr>
                <td>Name:</td>
                <td>${candidate.name}</td>
            </tr>
            <tr>
                <td>Position:</td>
                <td>${candidate.position}</td>
            </tr>
            <tr>
                <td>Department:</td>
                <td>${candidate.department}</td>
            </tr>
            <tr>
                <td>Status:</td>
                <td>${candidate.status}</td>
            </tr>
            <tr>
                <th colspan="4">Interview Details</th>
            </tr>
            <tr> 
                <th>SI.No</th>
                <th>Level</th>
                <th>Type</th>
                <th>Result</th>
            </tr>
            <c:set var="siNo" value="1" scope="page"/>
            <c:forEach var="schedule" items="${candidate.schedules}">
                <tr>
                    <td>${siNo}</td>
                    <td>${schedule.interviewLevel}</td>
                    <td>${schedule.interviewType}</td>
                    <td>${schedule.status}</td>
                </tr>
                <c:set var="siNo" value="${siNo + 1}" scope="page"/>
            </c:forEach>
        </table>
    </div>
</body>
</html>