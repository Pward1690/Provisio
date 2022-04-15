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
        Not Found
    </jsp:attribute>
    <jsp:attribute name="styles">
        <!-- Any styles, cdns, or scripts go here -->
        <style>
        	.error {
        		font-size: 22px;
        		font-weight: bold;
        		text-align: center;
        	}
        </style>
    </jsp:attribute>
    <jsp:attribute name="header_title">
        Provisio Hotels
    </jsp:attribute>
    <jsp:attribute name="page_title">
        404 - Not Found
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="error">Uh Oh! Resource not found ):</div>
    </jsp:attribute>
</t:header>