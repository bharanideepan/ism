<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com -->
  <title>Bootstrap Theme Company Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      <link rel="stylesheet" href="/css/user.css"/>
  <style>
  body {
    font: 400 15px Lato, sans-serif;
    line-height: 1.8;
    color: #818181;
  }
  h2 {
    font-size: 24px;
    text-transform: uppercase;
    color: #303030;
    font-weight: 600;
    margin-bottom: 30px;
  }
  h4 {
    font-size: 19px;
    line-height: 1.375em;
    font-weight: 400;
    margin-bottom: 30px;
  }  
  .jumbotron {
    background-color: #333;
    color: #fff;
    padding: 100px 25px;
    font-family: Montserrat, sans-serif;
  }
  .container-fluid {
    padding: 60px 50px;
  }
  .bg-grey {
    background-color: #f6f6f6;
  }
  .logo-small {
    color: #f4511e;
    font-size: 50px;
  }
  .logo {
    color: #f4511e;
    font-size: 200px;
  }
  .thumbnail {
    padding: 0 0 15px 0;
    border: none;
    border-radius: 0;
  }
  .thumbnail img {
    width: 100%;
    height: 100%;
    margin-bottom: 10px;
  }
  .carousel-control.right, .carousel-control.left {
    background-image: none;
    color: #333;
  }
  .carousel-indicators li {
    border-color: #f4511e;
  }
  .carousel-indicators li.active {
    background-color: #f4511e;
  }
  .item h4 {
    font-size: 19px;
    line-height: 1.375em;
    font-weight: 400;
    font-style: italic;
    margin: 70px 0;
  }
  .item span {
    font-style: normal;
  }
  .panel {
    border: 1px solid #f4511e; 
    border-radius:0 !important;
    transition: box-shadow 0.5s;
  }
  .panel:hover {
    box-shadow: 5px 0px 40px rgba(0,0,0, .2);
  }
  .panel-footer .btn:hover {
    border: 1px solid #f4511e;
    background-color: #fff !important;
    color: #f4511e;
  }
  .panel-heading {
    color: #fff !important;
    background-color: #f4511e !important;
    padding: 25px;
    border-bottom: 1px solid transparent;
    border-top-left-radius: 0px;
    border-top-right-radius: 0px;
    border-bottom-left-radius: 0px;
    border-bottom-right-radius: 0px;
  }
  .panel-footer {
    background-color: white !important;
  }
  .panel-footer h3 {
    font-size: 32px;
  }
  .panel-footer h4 {
    color: #aaa;
    font-size: 14px;
  }
  .panel-footer .btn {
    margin: 15px 0;
    background-color: #f4511e;
    color: #fff;
  }
  .navbar {
    margin-bottom: 0;
    background-color: #333;
    z-index: 9999;
    border: 0;
    font-size: 12px !important;
    line-height: 1.42857143 !important;
    letter-spacing: 4px;
    border-radius: 0;
    font-family: Montserrat, sans-serif;
  }
  .navbar li a, .navbar .navbar-brand {
    color: #fff !important;
  }
  .navbar-nav li a:hover, .navbar-nav li.active a {
    color: #333 !important;
    background-color: #fff !important;
  }
  .navbar-default .navbar-toggle {
    border-color: transparent;
    color: #fff !important;
  }
  footer .glyphicon {
    font-size: 20px;
    margin-bottom: 20px;
    color: #333;
  }
  .slideanim {visibility:hidden;}
  .slide {
    animation-name: slide;
    -webkit-animation-name: slide;
    animation-duration: 1s;
    -webkit-animation-duration: 1s;
    visibility: visible;
  }
  @keyframes slide {
    0% {
      opacity: 0;
      transform: translateY(70%);
    } 
    100% {
      opacity: 1;
      transform: translateY(0%);
    }
  }
  @-webkit-keyframes slide {
    0% {
      opacity: 0;
      -webkit-transform: translateY(70%);
    } 
    100% {
      opacity: 1;
      -webkit-transform: translateY(0%);
    }
  }
  @media screen and (max-width: 768px) {
    .col-sm-4 {
      text-align: center;
      margin: 25px 0;
    }
    .btn-lg {
      width: 100%;
      margin-bottom: 35px;
    }
  }
  @media screen and (max-width: 480px) {
    .logo {
      font-size: 150px;
    }
  }
  .glyphicon-plus-sign {
     color: #f4511e;
  }
  .glyphicon-eye-open {
     color: #f4511e;
  }
  .glyphicon-ok-sign {
     color: #f4511e;
  }
  </style>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#about">ABOUT</a></li>
        <li><a href="#services">HELP</a></li>
        <li><a href="#features">FEATURE</a></li>
        <li><a href="#contact">CONTACT</a></li>
        <li><a onclick="loginForm()">LOGIN</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="jumbotron text-center">
  <h1>Interview Schedule Management</h1> 
  <h4>Choose a job you love, and you will never have to work a day in your life. —Confucius</h4><br>
</div>

<!-- Container (About Section) -->
<div id="about" class="container-fluid text-center">
      <h2>About</h2>
      <h4>Employees are the valuable assert of a company.</h4>
      <h4>Here we help to manage an entire interview process to select our valuable assert.
      </br>You can schedule an interview and mangae it with an ease.
      </h4><br>
</div>

<!-- Container (Services Section) -->
<div id="services" class="container-fluid text-center">
  <h2>Help</h2>
  <h2>Status &nbsp Descriptions</h2>
  <br>
  <div class="row">
  </div>
  <div class="col-sm-6">
      <h2 align="left">Candidate &nbsp Status &nbsp Description</h2><br>
      <h4 align="left" >New - When a candidate is newly added. <br><br>
          Pending - When a candidate is assigned for an interview.<br><br>
          Cleared - When a candidate clears the interview assinged.<br><br>
          Cancelled - When a Schedule assigned for a candidate is cancelled.<br><br>
          Selected - When a candidate is selected for the company.<br><br>
          Rejected - When a candidate is rejected in the interview.</h4>
      <p></p>
  </div>
   <div class="col-sm-6">
      <h2 align="left">Schedule &nbsp Status &nbsp Description</h2><br>
      <h4 align="left">New - When a new schedule is assinged for a candidate. <br><br>
          Selected - When a candidate is selected for the company.<br><br>
          Rejected - When a candidate is rejected in the interview. <br><br>
          Declined - When a employee declines the request. <br><br>
          Canceled - When a schedule is canceled.<br><br>
          Rescheduled - When a created schedule is rescheduled.
      </h4>
          
      <p></p>
  </div>
  </div>
</div>
<div id="features" class="container-fluid text-center">
  <h2>FEATURES</h2>
  <h4>ADMIN</h4>
  <div class="row slideanim">
    <div class="col-sm-12">
      <span class="btn-lg glyphicon glyphicon-plus-sign"></span>
      <h4>ADD &nbsp USER</h4>
      <p>Add new users</p>
    </div>
  </div>
  <h4>Recruiter</h4>
  <br>
  <div class="row slideanim">
    <div class="col-sm-4">
      <span class="btn-lg glyphicon glyphicon-plus-sign"></span>
      <h4>ADD &nbsp CANDIDATE</h4>
      <p>Recruiter can add new candidates</p>
    </div>
    <div class="col-sm-4">
      <span class="btn-lg glyphicon glyphicon-eye-open"></span>
      <h4>VIEW &nbsp CANDIDATES</h4>
      <p>View added candidates</p>
    </div>
    <div class="col-sm-4">
      <span class="btn-lg glyphicon glyphicon-plus-sign"></span>
      <h4>ADD &nbsp SCHEDULE</h4>
      <p>Add an interview for candidate</p>
    </div>
  </div>
  <br><br>
  <h4>Manager</h4>
  <div class="row slideanim">
    <div class="col-sm-6">
      <span class="btn-lg glyphicon glyphicon-ok-sign"></span>
      <h4>ASSIGN &nbsp SCHEDULE</h4>
      <p>Assign schedule for a candidate</p>
    </div>
    <div class="col-sm-6">
      <span class="btn-lg glyphicon glyphicon-eye-open"></span>
      <h4>VIEW &nbsp SCHEDULES</h4>
      <p>View all schedules</p>
    </div>
  </div>
    <h4>Employee</h4>
  <div class="row slideanim">
    <div class="col-sm-6">
      <span class="btn-lg glyphicon glyphicon-eye-open"></span>
      <h4>VIEW &nbsp NEW &nbsp SCHEDULE</h4>
      <p>Newly assigned schedules</p>
    </div>
    <div class="col-sm-6">
      <span class="btn-lg glyphicon glyphicon-eye-open"></span>
      <h4>VIEW &nbsp PENDING &nbsp SCHEDULES</h4>
      <p>Accepted Schedules</p>
    </div>
  </div>
</div>

<!-- Container (Contact Section) -->
<div id="contact" class="container-fluid bg-grey">
  <h2 class="text-center">CONTACT</h2>
  <div class="row">
    <div class="col-sm-5">
      <p>Contact us and we'll get back to you within 24 hours.</p>
      <p><span class="glyphicon glyphicon-map-marker"></span> Guindy, Chennai</p>
      <p><span class="glyphicon glyphicon-envelope"></span> mani@ideas2it.com</p>
      <p><span class="glyphicon glyphicon-envelope"></span> bharani@ideas2it.com</p>
      <p><span class="glyphicon glyphicon-envelope"></span> arun@ideas2it.com</p>
    </div>
    </div>
  </div>
</div>

<!-- Image of location/map -->
<img src="/w3images/map.jpg" class="w3-image w3-greyscale-min" style="width:100%">

<footer class="container-fluid text-center">
  <a href="#myPage" title="To Top">
    <span class="glyphicon glyphicon-chevron-up"></span>
  </a>
  <p>Visit our page <a href="https://www.ideas2it.com" title="Visit w3schools">Copyrights &copy; Ideas2It</a></p>
</footer>
    <div class="blur">
	    <div id="loginForm" class="loginform">
	       <div class="bg">
			<form:form  id="loginUser" method="post" action="loginUser" modelAttribute="user">
		      <div class="bg" align="center"><img  src="/image/user.png" height="50px" width="50px"/></div><br>
			  <div class="form-group">
			    <label for="email" >Username:</label><br>
			    <form:input type="text" placeholder="Username" path="name" class="form-control"/>
			  </div><br>
			  <div class="form-group">
			    <label for="pwd">Password:</label>
			    <form:input type="password" placeholder="Password" path="password" class="form-control"/>
			  </div><br>
			  <div align="center;"><input type="submit" onclick="login()" class="btn btn-primary" value="Login">
                <button id ="close" style="float:right; background-color:#B22222;" class="btn btn-primary">Close</button>
              </div>
			</form:form>
		</div>
		</div>
    </div>
<script>
function loginForm() {
	var form = document.getElementById('loginForm');
	form.style.display="block";
}
function login() {
	var form = document.getElementById('loginUser');
	form.action = "loginUser";
}
$(document).ready(function(){
  // Add smooth scrolling to all links in navbar + footer link
  $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
    // Make sure this.hash has a value before overriding default behavior
    if (this.hash !== "") {
      // Prevent default anchor click behavior
      event.preventDefault();

      // Store hash
      var hash = this.hash;

      // Using jQuery's animate() method to add smooth page scroll
      // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 900, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
    } // End if
  });
  
  $(window).scroll(function() {
    $(".slideanim").each(function(){
      var pos = $(this).offset().top;

      var winTop = $(window).scrollTop();
        if (pos < winTop + 600) {
          $(this).addClass("slide");
        }
    });
  });
})
</script>

</body>
</html>
