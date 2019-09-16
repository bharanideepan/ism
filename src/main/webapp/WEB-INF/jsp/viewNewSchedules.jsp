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
      
      <link rel="stylesheet" type="text/css" href="/css/ism.css">
</head>
<body>  
      <div class="col-md-12 col-md-offset-0">
         <div class="fresh-table full-color-orange">
                <div class="container-fluid">
                  <div class="navbar-header">
                     <font class="navbar-brand">Interview Schedule Management</font>
                  </div>
  <!-- <ul class="nav navbar-nav">
      <c:if test="${role == 'Manager'}">   
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Schedules <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="declinedSchedules">Declined <span class="badge">${noOfDeclinedSchedules}</span></a></li>
          <li><a href="viewSchedulesByManager">All</a></li>
        </ul>
      </li>
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
          <li><a href="addCandidate">Add</a></li>
          <li><a href="viewCandidates">View</a></li>
        </ul>
      </li>
      </c:if>
      <li><a href="logout">Log Out</a></li>
    </ul> -->  
  </div>
               
            <div class="menu-bar">
               <table class = "table">
                  <c:if test="${role == 'Manager'}">
                     <tr>
                        <th><a href="declinedSchedules">Declined <span class="badge">${noOfDeclinedSchedules}</span></a></th>
                     </tr>
                     <tr>
                        <th><a href="viewSchedulesByManager">All</a></th>
                     </tr>
                  </c:if>
                  <c:if test="${role != 'Recruiter'}">
                     <tr>
                        <th><a href="newSchedules">New <span class="badge">${noOfNewSchedules}</span></a></th>
                     </tr>
                     <tr>
                        <th><a href="pendingSchedules">Pending <span class="badge">${noOfPendingSchedules}</span></a></th>
                     </tr>
                  </c:if>
                  <c:if test="${role == 'Recruiter'}">
                     <tr>
                        <th><a href="viewSchedules">Schedules</a></th>
                     </tr>
                     <tr>
                        <th><a href="addCandidate"><span class="glyphicon glyphicon-plus"></span> Add Candidate</a></th>
                     </tr>
                     <tr>
                        <th><a href="viewCandidates">View Candidates</a></th>
                     </tr>
                  </c:if>
                  <tr>
                     <th><a href="logout">Log Out</a></th>
                  </tr>
               </table>
            </div>

        
      <div class="table-div">
         <c:if test="${schedules != null}">
            <c:if test="${!schedules.isEmpty()}">
               <table class = "table">
                  <tr>
                     <th>S.No.</th>
                     <th>Candidate Name</th>
                     <th>Position</th>
                     <th>Department</th>
                     <th>Experience</th>
                     <th>Technology</th>
                     <th>Date</th>
                     <th>Time</th>
                     <th>Accept</th>
                     <th>Decline</th>
                  </tr>
                  <c:set var="sNumber" value="1" scope="page"/>
                  <c:forEach var="schedule" items="${schedules}">
                     <tr>
                        <td>${sNumber}</td>
                        <td><a href="viewProgress?id=${schedule.candidate.id}">${schedule.candidate.name}</a></td>
                        <td>${schedule.candidate.position}</td>
                        <td>${schedule.candidate.department}</td>
                        <td>${schedule.candidate.experience}</td>
                        <td>${schedule.candidate.technology}</td>
                        <td>${schedule.date}</td>
                        <td>${schedule.time}</td>
                        <td > 
                           <button class = "form-control" 
                              onclick="location.href='acceptSchedule?scheduleId=${schedule.id}&candidateId=${schedule.candidate.id}&id=${schedule.interviewer.id}';">&#10004;
                           </button>
                        </td>
                        <td>
                           <button class = "form-control"
                              onclick="getComment('${schedule.interviewer.id}', '${schedule.id}', '${schedule.candidate.id}')">&times;
                           </button>
                        </td>
                     </tr>
                     <c:set var="sNumber" value="${sNumber+1}" scope="page"/>
                  </c:forEach>
               </table>
               <div id="pass">
                  <div class="modal-content">
                     <div>Comment</div>
                     <textarea id="feedback"/></textarea><br>
                     <input class="btn btn-primary" type="submit" id="saveComment" value="ok"/>
                  </div>
               </div>
            </c:if>
            <c:if test="${schedules.isEmpty()}">
               <table class = "table">
                  <tr>
                     <th>No new schedules</th>
                  </tr>
               </table>
            </c:if>
         </c:if>
         <c:if test="${schedules == null}">
            <table class = "table">
               <tr>
                  <th>No new schedules</th>
               </tr>
            </table>
         </c:if>
      </div></div></div>
      <script type="text/javascript">
         function getComment(employeeId , scheduleId, candidateId) {
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
                 location.href = "rejectSchedule?id="+employeeId+"&scheduleId="+scheduleId+"&candidateId="+candidateId+"&comment="+feedback;
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