<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
   <head>
      <link rel="stylesheet" href="/css/createSchedule.css">
   <body>
<%@ include file="header.jsp" %>   
<%@ include file="recruiterMenu.jsp" %>

      <div id="createScheduleId" class="makeSchedule" align="center">
      <div class= "flex">
        <div class="box">
         <form:form name="form" id="createScheduleFormId" action="createSchedule" method="post" modelAttribute="schedule">
            <table class="table">
               <input type="hidden" name="candidateId" value="${candidate.id}"/>
               <tr>
                  <td>InterviewTypes</td>
                  <td>
                  	 <form:select path="interviewType" items="${types}" />
                  </td>
               </tr>
               <tr>
                  <td>Round</td>
                  <td>
                  	<c:forEach var="schedule" items="${candidate.schedules}">
           				 <c:set var="round" value="${schedule.round}" scope="page"/>
                  	</c:forEach>
                  	 <form:input path="round" value="${round+1}" />
                  </td>
               </tr>
              <tr>
                  <td>Date</td>
                  <td>
                  	 <input type="date" name="shdate" />
                  </td>
               </tr>
               <tr>
                  <td>Time</td>
                  <td>
                  	 <input type="time" name="shtime" />
                  </td>
               </tr>
               <tr>
                  <td><input type="reset" value="Clear"></td>
                  <td><input type="submit" value="Submit"></td>
               </tr>
            </table>
           </div>
           <div class="box"> 
            <table  class="table">
               <tr>
                   <th>Name</th>
                   <th>Department</th>
                   <th>Assign</th>
               </tr>
               <c:forEach var="interviewer" items="${interviewers}">
                 <tr>
                   <td align="center">${interviewer.name}</td>
	               <td align="center">${interviewer.department}</td>
	               <td align="center"><input type = "radio" 
                       onclick = 'assignInterviewer();' name="interviewerId" value="${interviewer.id}"/>
                   </td>   	 	
                 </tr>
              </c:forEach>
             </table>
          </div>
         </form:form>
       </div>
      </div>
      <script type="text/javascript">
      function assignInterviewer() {
    	   var checkBoxGroup = document.forms['form']['interviewerId'];			
    	   var limit = 1;
    	   for (var i = 0; i < checkBoxGroup.length; i++) {
    	       checkBoxGroup[i].onclick = function() {
    	           var checkedcount = 0;
    	           for (var i = 0; i < checkBoxGroup.length; i++) {
    	               checkedcount += (checkBoxGroup[i].checked) ? 1 : 0;
    	           }
    	       }
               if (checkedcount > limit) {			
                   this.checked = false;
               }
    	   }
    	}  
      </script>
   </body>
</html>