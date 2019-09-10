<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="/css/viewNewSchedules.css">
</head>
<body>
        <div>
            <h2 align="center">Interview Schedule Management</h2>
        </div>
        <div class="sidebar">
            <a href="newSchedules?id=${employee.id}">New Schedules</a>
            <a href="pendingSchedules?id=${employee.id}">Pending Schedules</a>
        </div>
      <form action="searchByName" method="post">
          <table align="center"><tr>
              <td><input type="text" name="name"/></td>
              <td><input type="submit" value=&#128269;></td></tr>
          </table>
      </form>
      
      <div>
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
            <c:forEach var="schedule" items="${employee.schedules}">
                <tr>
                    <td class = "td"><a href="viewProgress?id=${schedule.candidate.id}">${schedule.candidate.name}</a></td>
                    <td class = "td">${schedule.candidate.position}</td>
                    <td class = "td">${schedule.candidate.department}</td>
                    <td class = "td">${schedule.candidate.experience}</td>    
                    <td class = "td">${schedule.candidate.status}</td>                 
                    <td class = "td" > 
                      <button class = "accept" 
                          onclick="location.href='acceptSchedule?scheduleId=${schedule.id}&candidateId=${schedule.candidate.id}&id=${employee.id}';">&#10004;
                      </button></td>           
                    <td class = "td">
                      <button class = "reject"
                          onclick="location.href='rejectSchedule?id=${schedule.id}';">&times;
                      </button></td>
                </tr>
            </c:forEach>
        </table>  
      </div>
</body>
</html>