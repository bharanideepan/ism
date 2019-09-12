<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="/css/createCandidate.css">
<title>View schedule</title>
</head>
<body>

</body>
<%@ include file="header.jsp" %> 
    <div class="flex">
      <div class ="box">
        <table class="table" id="scheduleForm">
            <tr>
                <td>Candidate Name:</td>
                <td>${schedule.candidate.name}</td>
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
                <td>Schedule Status:</td>
                <td>${schedule.status}</td>
            </tr>
	        <c:if test="${schedule.status == 'Declined'}">
	        	<c:forEach var="scheduleRejectionTrack" items="${schedule.scheduleRejectionTracks}">
	        		<c:set var="employeeName" value="${scheduleRejectionTrack.employee.name}"/>
	        		<c:set var="comment" value="${scheduleRejectionTrack.comment}"/>
	        	</c:forEach>
	        	<tr>
	        		<td>Declined By:</td>
	        		<td>${employeeName}</td>
	        	</tr>
	        	<tr>
	        		<td>Reason:</td>
	        		<td>${comment}</td>
	        	</tr>
	        </c:if>
	    	<form:form action="cancelSchedule" method="post" modelAttribute="schedule">
	            <tr id="comment" style="display:none">
	                <td>Reason for cancelling:</td>
	                <td><form:input path="cancellationComment" required="true"/></td>
	            </tr>
	            <tr>
	                <td><input id="confirm" type="submit" value="Confirm" style="display:none"/></td>
                	<td><input id="reset" type="reset" onclick="getCommentBox(this.value)" value="Cancel" style="display:none"></td>
         		  </tr>
       			</form:form>
	                		<c:choose>
				                <c:when test="${schedule.status != 'Rescheduled' && schedule.status != 'Cancelled'
				                && schedule.status != 'Selected' && schedule.status != 'Rejected'}">
	        <tr>
	        	<td><input type="button" id="rescheduleButton" onclick="getCommentBox(this.value)" value="Reschedule"></td>
                <td><input type="button" id="cancelButton" onclick="getCommentBox(this.value)" value="Cancel Schedule"></td>
        	</tr>
			                	</c:when>
			                	<c:otherwise>
             	<tr>
        			<td>Feedback:</td>
        			<td>${schedule.interviewFeedback}${schedule.cancellationComment}${schedule.rescheduleComment}</td>
        		</tr>
			                	</c:otherwise>
                			</c:choose>
        </table>
        
        <table class="table" id="rescheduleForm" style="display:none">
	    	<form:form action="reschedule" method="post" modelAttribute="newSchedule">
            <tr>
                <td>Candidate Name:</td>
                <td>${schedule.candidate.name}</td>
            </tr>
            <tr>
                <td>Interview Type</td>
                <td><form:input value="${schedule.interviewType}" path="interviewType" readonly="true"/></td>
            </tr>
            <tr>
                <td>Interview Level:</td>
                <td><form:input value="${schedule.round}" path="round" readonly="true"/></td>
            </tr>
            <tr>
                <td>Date:</td>
                <td><input type="date" name="shdate"/></td>
            </tr>
            <tr>
                <td>Time:</td>
                <td ><input type="time" name="shtime"/></td>
            </tr>
            <tr>
                <td>Comment:</td>
                <td><input name="comment" required/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="hidden" name="scheduleId" value="${schedule.id}"/>
                	<input value="${schedule.candidate.id}" name="candidateId" type="hidden"/>
                	<input type="submit" value="Confirm"/>
       				<input type="reset" onclick="getCommentBox(this.value)" value="Cancel">
                	</td>
            </tr>
	        </form:form>
        </table>
    </div>
    <c:if test="${schedule.interviewer == null}">
    <div class="box">
        <table  class="table">
              <form action="assignInterviewer" method="post">
               <input type="hidden" name="scheduleId" value="${schedule.id}"/>
               <tr>
                   <th>Name</th>
                   <th>Department</th>
                   <th>Select</th>
               </tr>
               <c:forEach var="interviewer" items="${interviewers}">
                 <tr>
                   <td align="center">${interviewer.name}</td>
	               <td align="center">${interviewer.department}</td>
	               <td align="center"><input type = "radio" 
                       name="interviewerId" value="${interviewer.id}"/>
                   </td>   	 	
                 </tr>
              </c:forEach>
              <tr><td colspan="3"><input type="submit" value="assign"/></td></tr>
              </form>
         </table>  
      </div>
      </c:if>
    </div>
   </body>
      <script>
         function getCommentBox(value) {
              if(value === "Reschedule") {
                  document.getElementById("rescheduleForm").style.display="block";
                  document.getElementById("scheduleForm").style.display="none";
                  document.getElementById("rescheduleButton").style.display="none";
                  document.getElementById("cancelButton").style.display="none";
                  document.getElementById("comment").style.display="none";
                  document.getElementById("comment").style.display="block";
                  document.getElementById("confirm").style.display="block";
                  document.getElementById("reset").style.display="block";
                  
              } else if(value === "Cancel Schedule") {
                  document.getElementById("rescheduleForm").style.display="none";
                  document.getElementById("scheduleForm").style.display="block";
                  document.getElementById("rescheduleButton").style.display="none";
                  document.getElementById("cancelButton").style.display="none";
                  document.getElementById("comment").style.display="block";
                  document.getElementById("confirm").style.display="block";
                  document.getElementById("reset").style.display="block";
                  
              } else if(value === "Cancel") {
                  document.getElementById("rescheduleForm").style.display="none";
                  document.getElementById("scheduleForm").style.display="block";
                  document.getElementById("rescheduleButton").style.display="block";
                  document.getElementById("cancelButton").style.display="block";
                  document.getElementById("comment").style.display="none";
                  document.getElementById("confirm").style.display="none";
                  document.getElementById("reset").style.display="none";
                  
              }
         }
      </script>
</html>