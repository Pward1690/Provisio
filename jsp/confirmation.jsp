<!--
    Capstone Provisio Project
    Green Team
    04/14/2022
-->

<%@ 
    page 
    language="java" 
    contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
%>

<%@
    taglib 
    prefix="t" 
    tagdir="/WEB-INF/tags" 
%>

<t:header>
    <jsp:attribute name="title">
        Provisio Reservation Confirmation
    </jsp:attribute>
    <jsp:attribute name="styles">
        <!-- Any styles, cdns, or scripts go here -->
    </jsp:attribute>
    <jsp:attribute name="header_title">
        Provisio Hotels
    </jsp:attribute>
    <jsp:attribute name="page_title">
        Provisio Reservation Confirmation
    </jsp:attribute>
    <jsp:attribute name="body">
        <p id="reg-message"><strong>Your reservation was successfully booked. Your reservation # is ${requestScope.confirmationNumber}.</strong></p> 

        <p>What would you like to do next?</p>

        <div class="prompt-item">
            <a href="${initParam.UserReservationRedirectHostContextURL}">Book a Vacation</a>
        </div>

        <div class="prompt-item">
            <a href="${initParam.UserReservationLookupRedirectHostContextURL}">Lookup a Vacation</a>
        </div>

        <div class="prompt-item">
            <a href="#">Track Provisio Points</a>
        </div>

        <div class="prompt-item">
            <a href="${initParam.UserHomeRedirectHostContextURL}">Home</a>
        </div>
    </jsp:attribute>
</t:header>
