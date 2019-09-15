     function pagenation(technology, date, page, choice, lastpage) {
          httpRequest = new XMLHttpRequest();
          if (!httpRequest) {
             console.log('Unable to create XMLHTTP instance');
             return false;
          }
          var pageno = page;
          document.getElementById('back').value = pageno;
          document.getElementById('next').value = pageno; 
          if (pageno === 1) {
              document.getElementById('back').style.display = 'none';
          } else {
              document.getElementById('back').style.display = '';
          }
          if (pageno === lastpage) {
              document.getElementById('next').style.display = 'none';
          } else {
              document.getElementById('next').style.display = '';
          }
          if (choice === '1' && pageno < lastpage) {
              pageno = page * 1 + 1;
              document.getElementById('next').value = pageno;
          } else if (choice === '-1' && pageno > 1) {
              pageno = page - 1;
              document.getElementById('back').value = pageno;
          }
          httpRequest.open('GET', 'viewAllManagerSchedules?technology='+technology+'&pageNo='+pageno+"&date="+date);
          httpRequest.responseType = 'json';
          httpRequest.send();
          httpRequest.onreadystatechange = function() {
          if (httpRequest.readyState === XMLHttpRequest.DONE) {
              if (httpRequest.status === 200) {
                  var j =0;
                  var array = httpRequest.response;                     
                  for (var i=1; i<= array.length; i++) {
                      var row = document.getElementById('contentTable').rows; 
                      var column = row[i].cells;
                      var name = array[j].candidateName;
                      var candidateId = array[j].candidateId;
                      var scheduleId = array[j].scheduleId;
                      var view = name.link("viewProgress?id="+candidateId);
                      column[0].innerHTML = view;
                      column[1].innerHTML = array[j].round;
                      column[2].innerHTML = array[j].interviewType;
                      column[3].innerHTML = array[j].date;
                      column[4].innerHTML = array[j].time;
                      column[5].innerHTML = array[j].status;
                      if(array[j].interviewerName != "null") {
                    	  column[6].innerHTML = array[j].interviewerName;
                      } else {
                    	  column[6].innerHTML = "Not Assigned";
                      }
                      column[7].style.display ='';  
                      column[7].innerHTML =''; 
                      var recordBtn = document.createElement("BUTTON");
                      recordBtn.id ='recBtn';
                      recordBtn.innerHTML = "&#9776;";
                      recordBtn.setAttribute("onclick", "onRecord("+scheduleId+");");
                      column[7].appendChild(recordBtn); 
                      j = j + 1;
                   }
                   for (var i=array.length+1 ; i<=10; i++) {
                      var row = document.getElementById('contentTable').rows; 
                      var column = row[i].cells;
                      column[0].innerHTML = "";
                      column[1].innerHTML = "";
                      column[2].innerHTML = "";
                      column[3].innerHTML = "";
                      column[4].innerHTML = "";
                      column[5].innerHTML = "";
                      column[6].innerHTML = "";
                      column[7].style.display ='none'; 
                   }   
              } else {
                   console.log('Something went wrong..!!');
              }
           }
       }
  }
  
  function getByDate(technology) {
	  var date = document.getElementById("enteredDate").value;
	  location.href="managerSchedulesByDate?shdate="+date+"&technology="+technology;
  }
 
  function onRecord(scheduleId) {
      location.href="getScheduleWithInterviewers?scheduleId="+scheduleId;
  }