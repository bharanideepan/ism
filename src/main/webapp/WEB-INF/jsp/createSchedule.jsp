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
            <form:form name="form" id="createScheduleFormId" action="createSchedule" method="post" modelAttribute="schedule">
               <div class="box">
                  <table class="table">
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
                           <input type="datetime-local" name="shdate" required="required"/>
                        </td>
                     </tr>
               <tr>
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
                        <td><input type="reset" value="Clear"></td>
                        <td>
                           <input type="hidden" name="candidateId" value="${candidate.id}"/><input type="submit" value="Submit">
                        </td>
                     </tr>
                  </table>
               </div>
            </form:form>
         </div>
      </div>
   </body>
</html>