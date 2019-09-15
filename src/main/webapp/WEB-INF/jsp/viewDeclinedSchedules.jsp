<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html> 
<html>
   <head>
      <meta charset="UTF-8">
      <title>View schedules</title>
        <link rel="stylesheet" type="text/css" href="/css/popUp.css">      
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      
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
       <c:if test="${role == 'Recruiter'}">
      <li class="active"><a href="viewSchedules">View Schedules</a></li>
      <li><a href="addCandidate">Add Candidate</a></li>
      <li><a href="viewCandidates">View New Candidates</a></li>
      </c:if>
      <li><a href="logout">Log Out</a></li>
    </ul> -->
  </div>

               
                  <div class="menu-bar">
                     <table class = "table">
                     <c:if test="${role == 'Manager'}">
                              <tr><th><a href="declinedSchedules">Declined <span class="badge">${noOfDeclinedSchedules}</span></a></th></tr>
                              <tr><th><a href="viewSchedulesByManager">All</a></th></tr>
                              <tr><th><a href="newSchedules">New <span class="badge">${noOfNewSchedules}</span></a></th></tr>
                              <tr><th><a href="pendingSchedules">Pending <span class="badge">${noOfPendingSchedules}</span></a></th></tr>
                     </c:if>
                     <c:if test="${role == 'Recruiter'}">
                        <tr><th><a href="viewSchedules">Schedules</a></th></tr>
                              <tr><th><a href="addCandidate"><span class="glyphicon glyphicon-plus"></span> Add Candidate</a></th></tr>
                              <tr><th><a href="viewCandidates">View Candidates</a></th></tr>
                     </c:if>
                     <tr><th><a href="logout">Log Out</a></th></tr>
                     </table>
                  </div>
		<div class="table-div">
         <c:if test="${schedules != null}">
            <c:if test="${!schedules.isEmpty()}">
               <table class = "table">
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
                        <td>
        <a href="getScheduleWithInterviewers?scheduleId=${schedule.id}"><span class="glyphicon glyphicon-list-alt"></span></a></td>
                     </tr>
                     <c:set var="sNumber" value="${sNumber+1}" scope="page"/>
                  </c:forEach>
               </table>
            </c:if>
            <c:if test="${schedules.isEmpty()}">
               <table class = "table">
                  <tr>
                     <th>No results available for your search</th>
                  </tr>
               </table>
            </c:if>
         </c:if>
         <c:if test="${schedules == null}">
            <table class = "table">
               <tr>
                  <th>No results available for your search</th>
               </tr>
            </table>
         </c:if>
      </div>
       <div id="pass">
         <div class="modal-content">
            <div id="created">Created Successfully</div>
            <br>
            <div id="updated">Updated Successfully</div>
            <br>
            <span class="close">&times;</span>
         </div>
      </div></div></div>
 
   </body>
</html>