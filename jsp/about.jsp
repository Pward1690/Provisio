<!--
    Capstone Provisio Project
    Green Team
    04/15/2022
-->

<%@ 
    page 
    language="java" 
    contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
%>
<%@ 
    taglib 
    uri="http://java.sun.com/jsp/jstl/core" 
    prefix="c"
%>

<%@
    taglib 
    prefix="t" 
    tagdir="/WEB-INF/tags" 
%>

<t:header>
    <jsp:attribute name="title">
        Provisio About
    </jsp:attribute>
    <jsp:attribute name="styles">
        <!-- Any styles, cdns, or scripts go here -->
        <link rel="stylesheet" type="text/css" href="/Provisio/css/styles.css">
    </jsp:attribute>
    <jsp:attribute name="header_title">
        Provisio Hotels
    </jsp:attribute>
    <jsp:attribute name="page_title">
        "About Provisio Hotels"
    </jsp:attribute>
    <jsp:attribute name="body">

        <div class="flex-div">
            <div class="flex-item-lg">
                <div class="slideshow">
                    
                    <img src="/Provisio/images/img4.png" style="width:100%">
                    <div class="text">Paris, France</div>
                      
                </div>
            </div>

            <div id="quote" class="flex-item-sm">
                <p>
                    Provisio Hotels started in 1995 when six friends decided to shake up the hotel industry 
                    by providing exceptional service in amazing locations for an unbelievable price. The 
                    first Provisio location was in Lake Garda, Italy, but eventually expanded to include locations on three continents. 
                    Provisio’s Lake Garda location’s first staff members were none other than the founders, 
                    Jacob Breault, Cameron Frison, Parrish Ward, Mitchell Kwon, Caleb Mastromonaco, and Andrew Schaefer, 
                    and together they built a brand recognized for excellence the whole world over. Here at Provisio, 
                    we are excited that you would consider us as your home away from home. We know you will not be disappointed. 
                    So check out all we have to offer because here at Provisio, we want you to know that “your grand getaway awaits!”
                </p>
            </div>
        </div>

        <jsp:attribute name="page_title">
        Contact Us
        </jsp:attribute>

        <div class="flex-div">
            <div class="flex-item-lg">
                <div class="slideshow">
                    
                    <img src="/Provisio/images/img5.png" style="width:100%">
                      
                </div>
            </div>

            <div id="quote" class="flex-item-sm">
                <p>Whatever your need, and whatever your preferred method of communication, 
                    Provisio Hotels is ready to help.
                </p>
                <br>
                <br>

                <ul>
                    <li>Phone: 1-77-Provisio (1-777-768-4746)</li>
                    <li>Email: grandgetaway@provisiohotels.com</li>
                    <li>Headquarters: 612 Highbrooke Rd.
                        London
                        W79 3NK
                    </li>
                </ul>
            </div>
        </div>

        
    </jsp:attribute>
</t:header>