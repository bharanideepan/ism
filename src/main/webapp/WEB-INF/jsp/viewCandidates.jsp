<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
      
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
                                         
	    <form class="navbar-form navbar-right" action="schedulesByDate" method="post">
	      <div class="dropdown">
	        <select class="status" id="candidateStatus" name="result"  onclick="getByStatus();">
                  <option value="${candidateStatus}">${candidateStatus}</option>
               <c:forEach var="result" items="${pagenationInfo.results}" >
                  <option value="${result}">${result}</option>
               </c:forEach>
            </select>
	      </div>
	    </form>
  </div>
</nav>
  
      <div>
         <!--<table align="center">
            <tr>
<<<<<<< HEAD
                  <td class="info">Search By Status</td>
                  <td>
                     <select value="{pagenationInfo.status}" class="status" id="candidateStatus" name="result"  onclick="getByStatus();">
                        <c:forEach var="result" items="${pagenationInfo.results}" >
                           <option value="${result}">${result}</option>
                        </c:forEach>
                     </select>
=======
               <form action="searchByName" method="post">
                  <td><input type="text" name="name" placeholder="Search By Name" required/></td>
                  <td><input type="submit" value=&#128269;></td>
               </form>
               <form action="searchByStatus" method="post">
                  <td class="info">Search By Status</td>
                  <td>
                     
>>>>>>> 5389730e9c14ba1c5628da8c621fe51a7d993cb0
                  </td>
                <!--<td><input type="submit"  value=&#128269;></td>-->
               <!--</form>
            </tr>
         </table>-->
      </div>
      
      <c:if test="${pagenationInfo.candidates != null}">
         <c:if test="${!pagenationInfo.candidates.isEmpty()}">
            <div>
               <table  class = "table" id="contentTable">
                <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Email ID</th>
                <th>Phone No</th>
                <th>Department</th>
                <th>Experience</th>
                <th>Candidate Status</th>
                <th>Schedule</th>
                <th>History</th>
            </tr>
            <c:forEach var="candidate" items="${pagenationInfo.candidates}">
                <tr>
                    <td class = "td"><a href="viewCandidateForUpdate?candidateId=${candidate.id}">${candidate.name}</a></td>
                    <td class = "td">${candidate.position}</td>
                    <td class = "td">${candidate.emailId}</td>
                    <td class = "td">${candidate.phoneNumber}</td>
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
                       onclick="location.href='viewProgress?id=${candidate.id}';">&#128065;</button>      
                    </td>
                </tr>
            </c:forEach>
          </table>  
        </div>
        
	      <c:if test="${pagenationInfo.pages.size() != 1}">
        
         	 <ul class="pagination">
			  <li>
			  	<button class = "btn" value =1  id = "back" onclick = "pagenation('${pagenationInfo.status}', this.value, '-1', ${pagenationInfo.lastPageNo});">
            		Previous
           		</button>
       		  </li>
       		  
            <c:forEach var="page" items="${pagenationInfo.pages}">
 				 <li><button class = "btn" onclick = "pagenation('${pagenationInfo.status}', ${page}, 'page', ${pagenationInfo.lastPageNo});">
                 ${page}</button></li>
           </c:forEach>
           
              <li>
			  	<button class = "btn" value =1 id = "next" onclick = "pagenation('${pagenationInfo.status}', this.value, '1', ${pagenationInfo.lastPageNo});">
           			Next
       			</button>
   			  </li>
			</ul>       
			
			</c:if>
			
      
	      </c:if>
	      <c:if test="${pagenationInfo.candidates.isEmpty()}">
	        <table class = "table">
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