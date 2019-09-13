<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="/css/viewSchedule.css">
      <link rel="stylesheet" type="text/css" href="/css/recruiterMenu.css">
      <title>View schedule</title>
   </head>
   <body id="background">
   <%@ include file="header.jsp" %> 
      <c:if test="${role == 'Employee'}">
        <div class="navbar">
            <a href="newSchedules">New Schedules</a>
            <a href="pendingSchedules">Pending Schedules</a>
            <c:if test="${role == 'Manager'}">
            <a href="viewSchedulesByManager">View Schedules</a>
            </c:if>
            <a href="logout" style="float:right">Log Out</a>
        </div>
      </c:if>
      <c:if test="${role == 'Recruiter'}">
        <div class="navbar">
            <a href="addCandidate">Add Candidate</a>
            <a href="viewCandidates">View Candidates</a>
            <a href="viewSchedules">View Schedules</a>
            <a href="logout" style="float:right">Log Out</a>
        </div>
      </c:if>
   
      <div class ="box" id="viewDiv">
         <table class="table">
            <tr>
               <td>Candidate Name:</td>
               <td>${schedule.candidate.name}</td>
            </tr>
            <tr>
               <td>Candidate Experience:</td>
               <td>${schedule.candidate.experience}</td>
            </tr>
            <tr>
               <td>Technology:</td>
               <td>${schedule.candidate.technology}</td>
            </tr>
            <tr>
               <td>Interview Type:</td>
               <td>${schedule.interviewType}</td>
            </tr>
            <tr>
               <td>Interview Round:</td>
               <td>${schedule.round}</td>
            </tr>
            <tr>
               <td>Date:</td>
               <td>${schedule.date}</td>
            </tr>
            <tr>
               <td>Time:</td>
               <td>${schedule.time}</td>
            </tr>
            <tr>
               <td>Schedule Status:</td>
               <td>${schedule.status}</td>
            </tr>
            <tr>
               <c:if test="${schedule.interviewer != null}">
               <td>Interviewer:</td>
               <td>${schedule.interviewer.name}</td>
                 <c:if test="${schedule.status == 'New'}">
                  <td>change Interviewer:</td>
                  <td>
                  	 <select id="assigned" name="interviewerId" onclick="assignInterviewer(${schedule.id})">
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
                  <td>Assign Interviewer:</td>
                  <td>
                  	 <select id="assigned" name="interviewerId" onclick="assignInterviewer(${schedule.id})">
                  	     <option value="0">None</option>
                  	     <c:forEach var="interviewer" items="${interviewers}">
                             <option  
                             value="${interviewer.id}">${interviewer.name}</option>
                         </c:forEach>
                  	 </select>
                  </td>
               </c:if>
            </tr>
            <c:if test="${schedule.status == 'Declined'}">
               <tr>
                  <td>Declined By:</td><td>Reason:</td>
               </tr>
               <c:forEach var="scheduleRejectionTrack" items="${schedule.scheduleRejectionTracks}">
	              <tr>
	                 <td>${scheduleRejectionTrack.employee.name}</td>
	                 <td>${scheduleRejectionTrack.comment}</td>
	              </tr>
               </c:forEach>
            </c:if>
            <c:if test="${role == 'Recruiter' && !(schedule.status == 'Selected' || schedule.status == 'Rejected')}">
               <tr>
                  <td><input type="button" onclick="getRecruiter(this.value)" value="Reschedule"/></td>
                  <td><input type="button" onclick="getRecruiter(this.value)" value="Update"></td>
                  <td><input type="button" onclick="getRecruiter(this.value)" value="Cancel Schedule"></td>
               </tr>
            </c:if>
         </table>
      </div>
         
         <div class="box" id="cancelDiv" style="display:none">
            <form action="cancelSchedule" method="post">
            <table class="table">
               <tr>
                  <td><textarea name="comment" placeHolder="Reason" required></textarea></td>
               </tr>
               <tr>
                  <td><input type="hidden" name="scheduleId" value="${schedule.id}"/>
                  		<input type="submit" value="Confirm"/></td>
                  <td><input type="reset" onclick="getCommentBox(this.value)" value="Cancel"></td>
               </tr>
               </table>
            </form>
         </div>
         
      <div class ="box" id="rescheduleDiv" style="display:none">
            <form:form action="reschedule" method="post" modelAttribute="newSchedule">
         <table class="table">
            <tr>
               <td>Candidate Name:</td>
               <td>${schedule.candidate.name}</td>
            </tr>
            <tr>
               <td>Candidate Experience:</td>
               <td>${schedule.candidate.experience}</td>
            </tr>
            <tr>
               <td>Interview Type:</td>
               <td><form:select path="interviewType" items="${types}" /></td>
            </tr>
            <tr>
               <td>Interview Round:</td>
               <td><form:input path="round" value="${schedule.round}" readonly="true" /></td>
            </tr>
            <tr>
               <td>Date:</td>
               <td><input type="datetime-local" name="shdate" value="${schedule.dateTime}"/></td>
            </tr>
            <tr>
               <td>Reason:</td>
               <td><textarea name="comment" required></textarea></td>
            </tr>
            <tr>
               <c:if test="${schedule.interviewer != null}">
	               <td>Interviewer:</td>
	               <td>${schedule.interviewer.name}</td>
               </c:if>
	                 <td>Assign Interviewer</td>
	                 <td>
	                 	 <select name="interviewerId">
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
               			<input type="submit" value="Confirm">
          			</td>
               		<td>
               			<input type="reset" value="Cancel" onclick="getRecruiter(this.value)">
          			</td>
           		</tr>
         </table>
        </form:form>
      </div>
         
      <div class ="box" id="updateDiv" style="display:none">
            <form:form action="updateSchedule" method="post" modelAttribute="schedule">
         <table class="table">
            <tr>
               <td>Candidate Name:</td>
               <td>${schedule.candidate.name}</td>
            </tr>
            <tr>
               <td>Candidate Experience:</td>
               <td>${schedule.candidate.experience}</td>
            </tr>
            <tr>
               <td>Interview Type:</td>
               <td><form:select path="interviewType" items="${types}" /></td>
            </tr>
            <tr>
               <td>Interview Round:</td>
               <td><form:input path="round" value="${schedule.round}" readonly="true"/></td>
            </tr>
            <tr>
               <td>Date:</td>
               <td><input type="datetime-local" name="shdate" value="${schedule.dateTime}"/></td>
            </tr>
            <tr>
               <c:if test="${schedule.interviewer != null}">
	               <td>Interviewer:</td>
	               <td>${schedule.interviewer.name}</td>
               </c:if>
	                 <td>Assign Interviewer</td>
	                 <td>
	                 	 <select name="interviewerId">
	                 	     <option value="">None</option>
	                 	     <c:forEach var="interviewer" items="${interviewers}">
	                            <option value="${interviewer.id}">${interviewer.name}</option>
	                        </c:forEach>
	                 	 </select>
	                 </td>
                </tr>
                <tr>
               		<td><input type="reset" onclick="getRecruiter(this.value)" value="Cancel"/></td>
               		<td>
               			<input type="hidden" name="scheduleId" value="${schedule.id}"/>
               			<input type="submit" value="Confirm">
            		</td>
           		</tr>
         </table>
        </form:form>
      </div>
         
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