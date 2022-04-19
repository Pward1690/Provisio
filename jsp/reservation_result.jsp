<!--
    Capstone Provisio Project
    Green Team
    04/18/2022
-->

<%@ 
	page 
	language="java" 
	contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "
        java.util.ArrayList,
        java.util.Hashtable
    "
%>

<%@ 
	taglib 
	uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c"
%>

<%@ 
    taglib 
    prefix="fmt" 
    uri="http://java.sun.com/jsp/jstl/fmt"
%>

<%@
	taglib 
	prefix="t" 
	tagdir="/WEB-INF/tags" 
%>

<t:header>
    <jsp:attribute name="title">
        Provisio Reservation Result
    </jsp:attribute>
    <jsp:attribute name="styles">
        <!-- Any styles, cdns, or scripts go here -->
        <link rel="stylesheet" href="/Provisio/css/bootstrap-table-only.css" />
        <style>
            .error-message {
                color: red;
                margin-bottom: 30px;
            }

            .table-div {
                padding-right: max(10px, 10%, 50px);
                padding-left: max(10px, 10%, 50px);
            }

            #reg-message {
                margin-bottom: 20px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="header_title">
        Provisio Hotels
    </jsp:attribute>
    <jsp:attribute name="page_title">
        Provisio Reservation Lookup Result
    </jsp:attribute>
    <jsp:attribute name="body">
        <c:set var="reservation_data" scope="session" value="${sessionScope.reservation_data}" />

        <p id="reg-message">Reservation result for reservation id <b>${reservation_data.id}</b>.</p>

        <c:if test="${requestScope.error_message != null}">
            <div class="error-message">${requestScope.error_message}</div>
        </c:if>

        <div class="row">
            <div class="col-sm-12 table-div">
                <table class="table table-dark">
                    <thead>
                        <tr>
                            <th scope="col">Location</th>
                            <th scope="col">Amenity</th>
                            <th scope="col">Bedding</th>
                            <th scope="col">Guests</th>
                            <th scope="col">Nights</th>
                            <th scope="col">Check In</th>
                            <th scope="col">Check Out</th>
                            <th scope="col">Points</th>
                            <th scope="col">Total Cost</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="td-center">${reservation_data.location_name}</td>
                            <td class="td-center">${reservation_data.amenity_name}</td>
                            <td class="td-center">${reservation_data.bedding_name}</td>
                            <td class="td-center">${reservation_data.number_of_guests}</td>
                            <td class="td-center">${reservation_data.number_of_nights}</td>
                            <td class="td-center">${reservation_data.check_in_date}</td>
                            <td class="td-center">${reservation_data.check_out_date}</td>
                            <td class="td-center">${reservation_data.points}</td>

                            <fmt:formatNumber 
                                var="total_cost" 
                                type="number" 
                                minFractionDigits="2" 
                                maxFractionDigits="2" 
                                value="${(reservation_data.amenity_cost + reservation_data.bedding_cost) * reservation_data.number_of_nights}"
                            />
                            <td class="td-center">${total_cost}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:attribute>
</t:header>
