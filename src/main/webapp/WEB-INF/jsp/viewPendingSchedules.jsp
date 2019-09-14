<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
   <head>
      <meta charset="UTF-8">
      
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

      <link rel="stylesheet" type="text/css" href="/css/popUp.css">
   </head>
   <body>
      <%@ include file="header.jsp" %>
      
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">ISM</a>
    </div>
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
      <li><a href="logout">Log Out</a></li>
    </ul>
  </div>
</nav>

      <div>
         <c:if test="${schedules != null}">
            <c:if test="${!schedules.isEmpty()}">
               <table id="contentTable" class = "table" align="center" cellpadding = "10">
                  <tr>
                     <th>S.No.</th>
                     <th>Candidate Name</th>
                     <th>Position</th>
                     <th>Department</th>
                     <th>Experience</th>
                     <th>Status</th>
                     <th>Date</th>
                     <th>Time</th>
                     <th>Selected</th>
                     <th>Reject</th>
                  </tr>
                  <c:set var="sNumber" value="1" scope="page"/>
                  <c:forEach var="schedule" items="${schedules}">
                     <tr>
                        <td>${sNumber}</td>
                        <td><a href="viewProgress?id=${schedule.candidate.id}">${schedule.candidate.name}</a></td>
                        <td>${schedule.candidate.position}</td>
                        <td>${schedule.candidate.department}</td>
                        <td>${schedule.candidate.experience}</td>
                        <td>${schedule.candidate.status}</td>
                        <td>${schedule.date}</td>
                        <td>${schedule.time}</td>
                        <td > 
                           <button class = "select" 
                              onclick="getComment('selected','${schedule.id}');">&#10004;
                           </button>
                        </td>
                        <td>
                           <button class = "reject"
                              onclick="getComment('rejected', '${schedule.id}');">&times;
                           </button>
                        </td>
                     </tr>
                     <div id="pass">
                        <div class="modal-content">
                           <div>Comment</div>
                           <textarea id="feedback" name="feedback"></textarea><br>
                           <input type="submit" id="saveComment" value="ok"/>
                        </div>
                     </div>
                     <c:set var="sNumber" value="${sNumber+1}" scope="page"/>
                  </c:forEach>
               </table>
            </c:if>
            <c:if test="${schedules.isEmpty()}">
               <table id="contentTable" class = "table" align="center" cellpadding = "10">
                  <tr>
                     <th>No pending schedules</th>
                  </tr>
               </table>
            </c:if>
         </c:if>
         <c:if test="${schedules == null}">
            <table id="contentTable" class = "table" align="center" cellpadding = "10">
               <tr>
                  <th>No pending schedules</th>
               </tr>
            </table>
         </c:if>
      </div>
      <script type="text/javascript">
         function getComment(result , id) {
         	if (result === "selected") {
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
                     location.href = "interviewResult?id="+id+"&result=selected&feedback="+feedback;
                  }
         	} else if (result === "rejected") {
                 var modal = document.getElementById("pass");
                 var feedback = document.getElementById("feedback");
                 var submit = document.getElementById("saveComment");
                 modal.style.display = "block";
                 feedback.style.display = "block"; 
                 submit.style.display = "block"
                 submit.onclick = function() {
                     modal.style.display = "none";
                     submit.style.display = "none"; 
                     var feedback = document.getElementById("feedback").value;
                     location.href = "interviewResult?id="+id+"&result=rejected&feedback="+feedback;
                 }
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