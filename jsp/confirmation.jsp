<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Provisio Home</title> 
<link rel="stylesheet" type="text/css" href="./styles.css"/>
</head>
<%
	String confirmationNumber =(String) request.getAttribute("confirmationNumber");
%>
<body>

    <div class='container'>    
        <div id="mySidebar" class="sidebar">
            <div class="closebtn" onclick="closeNav()"><strong>×</strong></div>
            <h2 id="nav-bar-top">Login/Register</h2>
            <div class="nav-item"><a href="index.jsp">Home</a></div>
            <div class="nav-item"><a href="#">About Us</a></div>
            <div class="nav-item"><a href="#">Locations</a></div>
            <div class="nav-item"><a href="ReservationBooking.jsp">Book Your Vacation</a></div>
            <div class="nav-item"><a href="#">Lookup Your Vacation</a></div>
            <div class="nav-item"><a href="#">Provisio Points</a></div>
        </div>

        <div id="main">
            <button class="openbtn" onclick="openNav()">
                <strong>&gt</strong>
            </button>
            <div class='top-bar'>
                <div class="header-content_2" id="brand"><h1>Provisio Hotels</h1></div>
            </div>
            <div class="page-heading"><h3>Provisio Reservation Confirmation</h3></div>
            <p id="reg-message"><strong>Your reservation was successfully booked.
            Your reservation # is <%=confirmationNumber %></strong></p> 
            <p>What would you like to do next?</p>

            <div class="prompt-item"><a href="ReservationBooking.jsp">Book a Vacation</a></div>
            <div class="prompt-item"><a href="#">Lookup a Vacation</a></div>
            <div class="prompt-item"><a href="#">Track Provisio Points</a></div>
            <div class="prompt-item"><a href="index.jsp">Home</a></div>
            
                        
    </div>
  

    <!-- Functions for collapsing side nav bar -->
    <script>
        function openNav() {
            document.getElementById("mySidebar").style.width = "250px";
            document.getElementById("main").style.marginLeft = "250px";
        }
        
        function closeNav() {
            document.getElementById("mySidebar").style.width = "0";
            document.getElementById("main").style.marginLeft= "0";
        }
        openNav();
    </script>
</body>
</html>