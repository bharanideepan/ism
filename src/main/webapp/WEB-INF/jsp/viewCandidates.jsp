<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="/css/viewCandidates.css">
</head>
<body>
<%@ include file="header.jsp" %>  
<%@ include file="recruiterMenu.jsp" %>
        <div>
          <table align="center">
          <tr>
            <form action="searchByName" method="post">
              <td><input type="text" name="name" placeholder="Search By Name" required/></td>
              <td><input type="submit" value=&#128269;></td>
            </form>
            <form action="searchByStatus" method="post">
              <td><select name="result">
                    <c:forEach var="result" items="${pagenationInfo.results}" >
                      <option  value="${result}">${result}</option>                  
                    </c:forEach>
                  </select>
              </td>
              <td><input type="submit"  value=&#128269;></td>
            </form>
          </tr>
          </table>
        </div>
      <c:if test="${pagenationInfo.candidates != null}">
      	<c:if test="${!pagenationInfo.candidates.isEmpty()}">
      <div>
        <table id="contentTable" class = "table" align="center" cellpadding = "10">
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Department</th>
                <th>Experience</th>
                <th>Candidate Status</th>
                <th>Schedule</th>
                <th>Update</th>
            </tr>
            <c:forEach var="candidate" items="${pagenationInfo.candidates}">
                <tr>
                    <td class = "td"><a href="viewProgress?id=${candidate.id}">${candidate.name}</a></td>
                    <td class = "td">${candidate.position}</td>
                    <td class = "td">${candidate.department}</td>
                    <td class = "td">${candidate.experience}</td>    
                    <td class = "td">${candidate.status}</td>
                    <c:if test="${candidate.status == 'Cleared'}">                 
                      <td class = "td" > 
                        <button class = "schedule" 
                            onclick="location.href='scheduleForm?candidateId=${candidate.id}';">&#x1F4C5;
                        </button></td>
                    </c:if>  
                    <c:if test="${candidate.status == 'New'}">                 
                      <td class = "td" > 
                        <button class = "schedule" 
                            onclick="location.href='scheduleForm?candidateId=${candidate.id}';">&#x1F4C5;
                        </button></td>
                    </c:if>      
                    <c:if test="${candidate.status != 'Cleared'}">     
                      <c:if test="${candidate.status != 'New'}">                 
                        <td class = "td" > 
                        </td>
                      </c:if>              
                    </c:if>            
                    <td class = "td">
                      <button class = "editButton"
                       onclick="location.href='viewCandidateForUpdate?candidateId=${candidate.id}';">&#x1F58B;</button>      
                    </td>
                </tr>
            </c:forEach>
          </table>  
        </div>
        <div align="center">
          <div class = "cards">
            <button value =1  id = "back" 
               onclick = "pagenation('${pagenationInfo.status}', this.value, '-1', ${pagenationInfo.lastPageNo});"
               class = "btn">&#10096;</button>
          </div>
            <c:forEach var="page" items="${pagenationInfo.pages}">
            <div class = "cards">
             <button class = "btn" onclick = "pagenation('${pagenationInfo.status}', ${page}, 'page', ${pagenationInfo.lastPageNo});">
                 ${page}</button>          
            </div> 
           </c:forEach>
         <div class = "cards">
           <button value =1 id = "next" class = "btn"  
               onclick = "pagenation('${pagenationInfo.status}', this.value, '1', ${pagenationInfo.lastPageNo});">
           &#10097;</button>
        </div>
      </div> 
	      </c:if>
	      <c:if test="${pagenationInfo.candidates.isEmpty()}">
	        <table id="contentTable" class = "table" align="center" cellpadding = "10">
	        	<tr><th>No results available for your search</th></tr>
	        </table> 
	      </c:if>
	      </c:if>
	      <c:if test="${pagenationInfo.candidates == null}">
	        <table id="contentTable" class = "table" align="center" cellpadding = "10">
	        	<tr><th>No results available for your search</th></tr>
	        </table> 
	      </c:if>
      <script src="/js/candidatePagenation.js"></script>
</body>
</html>