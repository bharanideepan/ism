<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>View Candidate</title>
 <link rel="stylesheet" type="text/css" href="/css/viewCandidate.css">
  <link rel="stylesheet" type="text/css" href="/css/popUp.css">
</head>
<body onload="currentStatus('${status}');">
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
            <a href="schedulesByStatus?status=New">View Schedules</a>
        </div>
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
    <div id="pass">
      <div class="modal-content">
          <div id="created">Created Successfully</div><br>
          <div id="updated">Updated Successfully</div><br>
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