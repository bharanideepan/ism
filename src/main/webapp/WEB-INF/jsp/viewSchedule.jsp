<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="/css/viewSchedule.css">
      <title>View schedule</title>
   </head>
   <body id="background">
   <%@ include file="header.jsp" %> 
   
      <div class ="box">
         <table class="table" id="scheduleForm">
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
            <form:form action="cancelSchedule" method="post" modelAttribute="schedule">
               <tr>
                  <td>
                     <form:input id="comment" style="display:none" path="cancellationComment" placeHolder="Reason" required="true"/>
                  </td>
                  </tr>
                  <tr>
                  <td><input id="submit" type="submit" value="Confirm" style="display:none"/></td>
                  <td><input id="reset" type="reset" onclick="getCommentBox(this.value)" value="Cancel" style="display:none"></td>
               </tr>
            </form:form>
            <c:choose>
               <c:when test="${schedule.status != 'Rescheduled' && schedule.status != 'Cancelled'
                  && schedule.status != 'Selected' && schedule.status != 'Rejected'}">
                  <!--<tr>
                     <td><input id="reshdl" type="button" onclick="getCommentBox(this.value)" value="Reschedule"></td>
                     <td><input id="cancel" type="button" onclick="getCommentBox(this.value)" value="Cancel Schedule"></td>
                  </tr>-->
               </c:when>
               <c:otherwise>
                  <tr>
                     <td>Feedback:</td>
                     <td>${schedule.interviewFeedback}${schedule.cancellationComment}${schedule.rescheduleComment}</td>
                  </tr>
               </c:otherwise>
            </c:choose>
         </table>
         
      </div>
   </body>
   <script>
      function assignInterviewer(scheduleId) {
    	  var id = document.getElementById("assigned").value;
    	  if (id != "0") {
    	      location.href="assignInterviewer?scheduleId="+scheduleId+"&interviewerId="+id;
    	  }
      }
      function getCommentBox(value) {
           if(value === "Reschedule") {
               document.getElementById("rescheduleForm").style.display="block";
               document.getElementById("scheduleForm").style.display="none";
               document.getElementById("assign").style.display="none";
               document.getElementById("interviewers").style.display="block";
               document.getElementById("reshdl").style.display="none";
               document.getElementById("cancel").style.display="none";
               document.getElementById("comment").style.display="none";
               document.getElementById("submit").style.display="none";
               document.getElementById("reset").style.display="none";
               
           } else if(value === "Cancel Schedule") {
               document.getElementById("rescheduleForm").style.display="none";
               document.getElementById("scheduleForm").style.display="block";
               document.getElementById("assign").style.display="none";
               document.getElementById("interviewers").style.display="none";
               document.getElementById("reshdl").style.display="none";
               document.getElementById("cancel").style.display="none";
               document.getElementById("comment").style.display="block";
               document.getElementById("submit").style.display="block";
               document.getElementById("reset").style.display="block";
               
           } else if(value === "Cancel") {
               document.getElementById("rescheduleForm").style.display="none";
               document.getElementById("scheduleForm").style.display="block";
               document.getElementById("assign").style.display="block";
               document.getElementById("interviewers").style.display="none";
               document.getElementById("reshdl").style.display="block";
               document.getElementById("cancel").style.display="block";
               document.getElementById("comment").style.display="none";
               document.getElementById("submit").style.display="none";
               document.getElementById("reset").style.display="none";
               
           }
      }
   </script>
</html>