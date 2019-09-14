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
    </ul>
	    <div class="navbar-form navbar-right" >
	      <div class="form-group">
	        <input id="enteredDate" value="" type="date" class="form-control" name="shdate" required>
	      </div>
	      <button onclick="getByDate()" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
	    </div>
  </div>
</nav>

		<div>
         <c:if test="${pagenationInfo.scheduleInfos != null}">
            <c:if test="${!pagenationInfo.scheduleInfos.isEmpty()}">
               <table class = "table" id="contentTable">
                  <tr>
                     <th>Candidate Name</th>
                     <th>Round</th>
                     <th>Interview Type</th>
                     <th>Date</th>
                     <th>Time</th>
                     <th>Schedule Status</th>
                     <th>Interviewer</th>
                     <th>Records</th>
                  </tr>
                  <c:forEach var="schedule" items="${pagenationInfo.scheduleInfos}">
                     <tr>
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
        <a href="getScheduleWithInterviewers?scheduleId=${schedule.id}">&#9776;</a></td>
                     </tr>
                  </c:forEach>
               </table>
            </c:if>
         <div>
          <div>
            <button value =1  id = "back" 
               onclick = "pagenation('${pagenationInfo.searchedDate}', this.value, '-1', ${pagenationInfo.lastPageNo});"
               class = "btn">&#10096;</button>
          </div>
            <c:forEach var="page" items="${pagenationInfo.pages}">
            <div>
            <button class = "btn" onclick = "pagenation('${pagenationInfo.searchedDate}', ${page}, 'page', ${pagenationInfo.lastPageNo});">${page}</button>          
            </div> 
           </c:forEach>
         <div>
           <button value =1 id = "next" 
            onclick = "pagenation('${pagenationInfo.searchedDate}', this.value, '1', ${pagenationInfo.lastPageNo});"
           class = "btn">&#10097;</button>
        </div>
      </div> 
            <c:if test="${pagenationInfo.scheduleInfos.isEmpty()}">
               <table class = "table">
                  <tr>
                     <th>No results available for your search</th>
                  </tr>
               </table>
            </c:if>
         </c:if>
         <c:if test="${pagenationInfo.scheduleInfos == null}">
            <table class = "table">
               <tr>
                  <th>No results available for your search</th>
               </tr>
            </table>
         </c:if>
      </div>
      <script src="/js/schedulePagenation.js"></script>
   </body>
</html>