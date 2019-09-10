<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="/css/viewCandidates.css">
</head>
<body>
        <div>
            <div class="cards">
                <a href="index.jsp" ><img src="/image/logo.png" width="200px" height="200px"/></a>
            </div>
            <div class="cards">
                <h2 align="center">Interview Schedule Management</h2>
            </div>
        </div>
        <div class="sidebar">
            <a href="addCandidate">Add Candidate</a>
            <a href="viewCandidates">View Candidates</a>
            <a href="schedulesByStatus">View Schedules</a>
        </div>
        <div id="container">
        <form action="searchByName" method="post">
          <table align="center"><tr>
              <td><input type="text" name="name"/></td>
              <td><input type="submit" value=&#128269;></td></tr>
          </table>
        </form>
      
        <div>
          <table id="contentTable" class = "table" align="right" cellpadding = "10">
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Department</th>
                <th>Experience</th>
                <th>Status</th>
                <th>Schedule</th>
                <th>Update</th>
            </tr>
            <c:forEach var="candidate" items="${pagenationInfo.candidates}">
                <tr>
                    <td class = "td"><a href="viewProgress?id=${candidate.id}">${candidate.name}</a></td>
                    <td class = "td">${candidate.position}</td>
                    <td class = "td">${candidate.department}</td>
                    <td class = "td">${candidate.experience}</td>    
                    <td class = "td">${candidate.status}</td>                 
                    <td class = "td" > 
                      <button class = "schedule" 
                          onclick="location.href='scheduleForm?candidateId=${candidate.id}';">&#x1F4C5;
                      </button></td>           
                    <td class = "td">
                      <button class = "editButton"
                       onclick="location.href='viewCandidateForUpdate?candidateId=${candidate.id}';">&#x1F58B;</button>      
                    </td>
                </tr>
            </c:forEach>
          </table>  
        </div>
        </div>
</body>
</html>