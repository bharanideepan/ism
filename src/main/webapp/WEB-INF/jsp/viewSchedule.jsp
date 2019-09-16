<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      <link rel="stylesheet" type="text/css" href="/css/ism.css">
  
      <title>View schedule</title>
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
          <li><a href="addCandidate"><span class="glyphicon glyphicon-plus"></span> Add Candidate</a></li>
          <li><a href="viewCandidates">View Candidate</a></li>
        </ul>
      </li>
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
               
      <div class ="table-div-single" id="viewDiv">
         <table class="table">
            <tr>
               <th>Candidate Name:</th>
               <td>${schedule.candidate.name}</td>
            </tr>
            <tr>
               <th>Candidate Experience:</th>
               <td>${schedule.candidate.experience}</td>
            </tr>
            <tr>
               <th>Technology:</th>
               <td>${schedule.candidate.technology}</td>
            </tr>
            <tr>
               <th>Interview Type:</th>
               <td>${schedule.interviewType}</td>
            </tr>
            <tr>
               <th>Interview Round:</th>
               <td>${schedule.round}</td>
            </tr>
            <tr>
               <th>Date:</th>
               <td>${schedule.date}</td>
            </tr>
            <tr>
               <th>Time:</th>
               <td>${schedule.time}</td>
            </tr>
            <tr>
               <th>Schedule Status:</th>
               <td>${schedule.status}</td>
            </tr>
            <tr>
               <c:if test="${schedule.interviewer != null}">
               <th>Interviewer:</th>
               <td>${schedule.interviewer.name}</td>
                 <c:if test="${schedule.status == 'New'}">
                  <th>change Interviewer:</th>
                  <td>
                  	 <select id="assigned" class="select" name="interviewerId" onclick="assignInterviewer(${schedule.id})">
                  	     <option value="0">None</option>
                  	     <c:forEach var="interviewer" items="${interviewers}">
                             <option  
                             value="${interviewer.id}">${interviewer.name}</option>
                         </c:forEach>
                  	 </select>
                  </td>
                 </c:if>
               </c:if>
               <c:if test="${schedule.interviewer == null}">
                  <th>Assign Interviewer:</th>
                  <td>
                  	 <select class="select" id="assigned" name="interviewerId" onclick="assignInterviewer(${schedule.id})">
                  	    <option value="0">None</option>
                  	    <c:forEach var="interviewer" items="${interviewers}">
                             <option value="${interviewer.id}">${interviewer.name}</option>
                         </c:forEach>
                  	 </select>
                  </td>
               </c:if>
            </tr>
            <c:if test="${role == 'Recruiter' && !(schedule.status == 'Selected' || schedule.status == 'Rejected')}">
               <tr>
                  <td><input class = "btn btn-primary" type="button" onclick="getRecruiter(this.value)" class="form-control" value="Reschedule"/></td>
                  <td><input class = "btn btn-primary" type="button" onclick="getRecruiter(this.value)" class="form-control" value="Update"></td>
                  <td><input class = "btn btn-primary" style=" background-color:#B22222;" type="button" onclick="getRecruiter(this.value)" class="form-control" value="Cancel Schedule"></td>
               </tr>
            </c:if>
            </table></div>
            <div class="table-div-single-side">
            <c:if test="${schedule.status == 'Declined'}">
            <table class="table">
               <tr>
                  <th>Declined By:</th><th>Reason:</th>
               </tr>
               <c:forEach var="scheduleRejectionTrack" items="${schedule.scheduleRejectionTracks}">
	              <tr>
	                 <td>${scheduleRejectionTrack.employee.name}</td>
	                 <td>${scheduleRejectionTrack.comment}</td>
	              </tr>
               </c:forEach>
         </table>
            </c:if>
      </div>
         
         <div class="reschedule-div-single" id="cancelDiv" style="display:none">
            <form action="cancelSchedule" method="post">
            <table class="table">
               <tr>
                  <td><textarea class="form-control" name="comment" placeHolder="Reason" required></textarea></td>
               </tr>
               <tr>
                  <td><input type="hidden" name="scheduleId" value="${schedule.id}"/>
                  		<input class = "btn btn-primary" type="submit" value="Confirm"/>
                  		<input class = "btn btn-primary" style="align:center; background-color:#B22222;" type="reset" onclick="getRecruiter(this.value)" value="Cancel"></td>
               </tr>
               </table>
            </form>
         </div>
         
      <div class ="reschedule-div-single" id="rescheduleDiv" style="display:none">
            <form:form action="reschedule" method="post" modelAttribute="newSchedule">
         <table class="table">
            <tr>
               <th>Candidate Name:</th>
               <td>${schedule.candidate.name}</td>
            </tr>
            <tr>
               <th>Candidate Experience:</th>
               <td>${schedule.candidate.experience}</td>
            </tr>
            <tr>
               <th>Interview Type:</th>
               <td><form:select class="select" path="interviewType" items="${types}" /></td>
            </tr>
            <tr>
               <th>Interview Round:</th>
               <td><form:input class="form-control" path="round" value="${schedule.round}" readonly="true" /></td>
            </tr>
            <tr>
               <th>Date:</th>
               <td><input class="form-control" type="datetime-local" name="shdate" value="${schedule.dateTime}"/></td>
            </tr>
            <tr>
               <th>Reason:</th>
               <td><textarea name="comment" class="form-control" required></textarea></td>
            </tr>
            <tr>
               <c:if test="${schedule.interviewer != null}">
	               <th>Interviewer:</th>
	               <td>${schedule.interviewer.name}</td>
               </c:if>
               </tr>
               <tr>
               <c:if test="${schedule.interviewer == null}">
	              <th>Assign Interviewer</th>
               </c:if>
               <c:if test="${schedule.interviewer != null}">
	              <th>Change Interviewer</th>
               </c:if>
	                 
	                 <td>
	                 	 <select name="interviewerId" class="select">
	                 	     <option value="">None</option>
	                 	     <c:forEach var="interviewer" items="${interviewers}">
	                            <option value="${interviewer.id}">${interviewer.name}</option>
	                        </c:forEach>
	                 	 </select>
	                 </td>
                </tr>
                <tr>
               		<td>
              			<input type="hidden" name="candidateId" value="${candidate.id}"/>
               			<input type="hidden" name="scheduleId" value="${schedule.id}"/>
               			<input class = "btn btn-primary" type="submit" value="Confirm">
          			</td>
               		<td>
               			<input class = "btn btn-primary" style=" background-color:#B22222;" type="reset" value="Cancel" onclick="getRecruiter(this.value)">
          			</td>
           		</tr>
         </table>
        </form:form>
      </div>
         
      <div class ="reschedule-div-single" id="updateDiv" style="display:none">
            <form:form action="updateSchedule" method="post" modelAttribute="schedule">
         <table class="table">
            <tr>
               <th>Candidate Name:</th>
               <td>${schedule.candidate.name}</td>
            </tr>
            <tr>
               <th>Candidate Experience:</th>
               <td>${schedule.candidate.experience}</td>
            </tr>
            <tr>
               <th>Interview Type:</th>
               <td><form:select class="select" path="interviewType" items="${types}" /></td>
            </tr>
            <tr>
               <th>Interview Round:</th>
               <td><form:input class="form-control" path="round" value="${schedule.round}" readonly="true"/></td>
            </tr>
            <tr>
               <th>Date:</th>
               <td><input type="datetime-local" class="form-control" name="shdate" value="${schedule.dateTime}"/></td>
            </tr>
            <tr>
               <c:if test="${schedule.interviewer != null}">
	               <th>Interviewer:</th>
	               <td>${schedule.interviewer.name}</td>
               </c:if>
               </tr>
               <tr>
               <c:if test="${schedule.interviewer == null}">
	              <th>Assign Interviewer</th>
               </c:if>
               <c:if test="${schedule.interviewer != null}">
	              <th>Change Interviewer</th>
               </c:if>
	                 <td>
	                 	 <select name="interviewerId" class="select">
	                 	     <option value="">None</option>
	                 	     <c:forEach var="interviewer" items="${interviewers}">
	                            <option value="${interviewer.id}">${interviewer.name}</option>
	                        </c:forEach>
	                 	 </select>
	                 </td>
                </tr>
                <tr>
               		<td>
               			<input type="hidden" name="scheduleId" value="${schedule.id}"/>
               			<input class = "btn btn-primary" type="submit" value="Confirm">
            		</td>
               		<td><input class = "btn btn-primary" style=" background-color:#B22222;" type="reset" onclick="getRecruiter(this.value)" value="Cancel"/></td>
           		</tr>
         </table>
        </form:form>
      </div>
      </div></div>
         
   </body>
   <script>
      function assignInterviewer(scheduleId) {
    	  var id = document.getElementById("assigned").value;
    	  if (id != "0") {
    	      location.href="assignInterviewer?scheduleId="+scheduleId+"&interviewerId="+id;
    	  }
      }
      function getRecruiter(value) {
           if(value === "Reschedule") {
               document.getElementById("viewDiv").style.display="none";
               document.getElementById("cancelDiv").style.display="none";
               document.getElementById("updateDiv").style.display="none";
               document.getElementById("rescheduleDiv").style.display="block";
               
           } else if(value === "Cancel Schedule") {
               document.getElementById("viewDiv").style.display="none";
               document.getElementById("cancelDiv").style.display="block";
               document.getElementById("updateDiv").style.display="none";
               document.getElementById("rescheduleDiv").style.display="none";
               
           } else if(value === "Cancel") {
               document.getElementById("viewDiv").style.display="block";
               document.getElementById("cancelDiv").style.display="none";
               document.getElementById("updateDiv").style.display="none";
               document.getElementById("rescheduleDiv").style.display="none";   
           } else if(value === "Update") {
               document.getElementById("viewDiv").style.display="none";
               document.getElementById("cancelDiv").style.display="none";
               document.getElementById("updateDiv").style.display="block";
               document.getElementById("rescheduleDiv").style.display="none";
               
           }
      }
   </script>
</html>