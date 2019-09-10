<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
   <head>
      <link rel="stylesheet" href="/css/createSchedule.css">
   <body>
         <h1>Interview Schedule Management</h1>

      <div id="createScheduleId" class="makeSchedule" align="center">
         <form:form id="createScheduleFormId" action="createSchedule" method="post" modelAttribute="schedule">
            <table class="table">
               <input type="hidden" name="candidateId" value="${candidateId}"/>
               <tr>
                  <td>InterviewLevel</td>
                  <td>
                  	 <form:select path="interviewLevel" items="${levels}" />
                  </td>
               </tr>
               <tr>
                  <td>InterviewTypes</td>
                  <td>
                  	 <form:select path="interviewType" items="${types}" />
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
         </form:form>
      </div>
   </body>
</html>