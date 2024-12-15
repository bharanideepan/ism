     function pagenation(status, page, choice, lastpage) {
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
          //var status = document.getElementById('result').value;
          httpRequest.open('GET', 'viewAllCandidates?pageNo='+pageno+"&result="+status);
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
                      var view = name.link("viewCandidateForUpdate?candidateId=" + candidateId);
                      column[0].innerHTML = view;
                      column[1].innerHTML = array[j].position;
                      column[2].innerHTML = array[j].emailId;
                      column[3].innerHTML = array[j].phoneNumber;
                      column[4].innerHTML = array[j].department;
                      column[5].innerHTML = array[j].experience; 
                      column[6].innerHTML = array[j].status
                      column[7].style.display =''; 
                      column[7].innerHTML =''; 
                      if(('New' == array[j].status) || ('Cleared' == array[j].status)) { 
                          var scheduleBtn = document.createElement("BUTTON");
                          scheduleBtn.id ='dbtn';
                          scheduleBtn.innerHTML = "&#x1F4C5";
                          scheduleBtn.setAttribute("class", "schedule");
                          scheduleBtn.setAttribute("onclick", "scheduleCandidate("+candidateId+");");
                          column[7].appendChild(scheduleBtn);
                      }
                      var recordBtn = "&#9776;";
                      var recordBtnLink = recordBtn.link("viewProgress?id="+candidateId);
                      column[8].innerHTML = recordBtnLink;
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
                      column[8].innerHTML = "";
                   }   
              } else {
                   console.log('Something went wrong..!!');
              }
           }
       }
  }
  function scheduleCandidate(id) {
	  location.href="scheduleForm?candidateId="+id;
  }
  function onEdit(id) {
	  location.href="viewProgress?id="+id;
  }
  
  function getByStatus() {
	  var result = document.getElementById("candidateStatus").value;
	  location.href="searchByStatus?result="+result;
  }