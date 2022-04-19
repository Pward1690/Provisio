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
        Look Up Provisio Reservation
    </jsp:attribute>
    <jsp:attribute name="styles">
        <!-- Any styles, cdns, or scripts go here -->
        <style>
            .error-message {
                color: red;
                margin-bottom: 30px;
            }
            #entry-field {
                display: flex;
                align-items: center;
                flex-direction: column;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="header_title">
        Provisio Hotels
    </jsp:attribute>
    <jsp:attribute name="page_title">
        Look Up Provisio Reservation
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="entry-field">
            <p>To look up a previous reservation, enter the reservation number in the field <br> 
            below and then click the submit button.</p><br><br>

            <c:if test="${requestScope.error_message != null}">
                <div class="error-message">${requestScope.error_message}</div>
            </c:if>

            <form action="/Provisio/lookup/reservation" method="POST">
                <label for="resNumber">Reservation Number:</label>
                <input type="text" id="resNumber" name="resNumber">
                <br><br>

                <button type="submit" value="submit">Submit</button>
                <br><br>

            </form>
        </div>
    </jsp:attribute>
</t:header>