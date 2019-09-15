<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>View Candidate</title>
      
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
  <link rel="stylesheet" type="text/css" href="/css/popUp.css">
  <link rel="stylesheet" type="text/css" href="/css/ism.css">
</head>
<body onload="currentStatus('${status}');">
  
            <div class="col-md-10 col-md-offset-1">
                <div class="fresh-table full-color-orange">
                
                
      <%@ include file="header.jsp" %> 
      
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
      <c:if test="${role == 'Manager'}">   
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Schedules <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="declinedSchedules">Declined <span class="badge">${noOfDeclinedSchedules}</span></a></li>
          <li><a href="viewSchedulesByManager">All</a></li>
        </ul>
      </li>
      </c:if>
      
      <c:if test="${role != 'Recruiter'}">    
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Your Schedules <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="newSchedules">New <span class="badge">${noOfNewSchedules}</span></a></li>
          <li><a href="pendingSchedules">Pending <span class="badge">${noOfPendingSchedules}</span></a></li>
        </ul>
      </li>
      </c:if>
      
      <c:if test="${role == 'Recruiter'}">
      <li><a href="viewSchedules">Schedules</a></li>      
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Candidates <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="addCandidate"><span class="glyphicon glyphicon-plus"></span> Add Candidate</a></li>
          <li><a href="viewCandidates">View Candidates</a></li>
        </ul>
      </li>
      </c:if>
      
      <li><a href="logout">Log Out</a></li>
    </ul>
  </div>
</nav>

        <table class="table">
            <tr>
               <th colspan="4" align="center">Candidate Details</th>
            </tr>
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
               <td>Experience:</td>
               <td>${candidate.experience}</td>
            </tr>
            <tr>
               <td>Technology:</td>
               <td>${candidate.technology}</td>
            </tr>
            <tr>
               <td>Resume:</td>
               <td><a href="${candidate.resumeFilePath}" target="_blank"><span class="glyphicon glyphicon-download-alt"></span></a></td>
            </tr>
            </table>
            <c:if test="${schedules != null}">
               <c:if test="${!schedules.isEmpty()}">
               
            <table class="table">
            <tr>
               <th colspan="4" align="center">Interview Details</th>
            </tr>
                  <tr>
                     <th>SI.No</th>
                     <th>Round</th>
                     <th>Type</th>
                     <th>Schedule status</th>
                     <th>Comment</th>
                  </tr>
                  <c:set var="siNo" value="1" scope="page"/>
                  <c:forEach var="schedule" items="${schedules}">
                     <tr>
                        <td>${siNo}</td>
                        <td>${schedule.round}</td>
                        <td>${schedule.interviewType}</td>
                        <td>${schedule.status}</td>
                        <td>${schedule.rescheduleComment}${schedule.cancellationComment}${schedule.interviewFeedback}</td>
                        <c:if test="${schedule.interviewType != 'Final' && schedule.status == 'Selected'}">
                           <c:set var="check" value="1"/>
                        </c:if>
                     </tr>
                     <c:set var="siNo" value="${siNo + 1}" scope="page"/>
                  </c:forEach>
         </table>
               </c:if>
            </c:if>
            </div></div>
            
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