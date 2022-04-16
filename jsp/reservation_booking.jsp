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

<%@
	page 
	import="Provisio.ReservationService"
%>

<% 
	ReservationService reservation_service = new ReservationService(); 
%>

<t:header>
    <jsp:attribute name="title">
        Book Your Vacation
    </jsp:attribute>
    <jsp:attribute name="styles">
        <!-- Any styles, cdns, or scripts go here -->
        <link rel="stylesheet" type="text/css" href="/Provisio/css/styles.css">
        <link rel="stylesheet" type="text/css" href="/Provisio/css/index.css">
		<style>
			#divSummary {
				position: relative;
				top: -230px;
				left: -12%;
				width: 75%;
				margin-left: 8%;
				background-color: #5AB9EA;
				border-color: black;
				border-style: solid;
				text-decoration: none;
				border-radius: 5px;
				box-shadow: 2px 2px 2px;
			}
			
			#showSummaryButton {
				margin-left: 7%;
			}

			.error-message {
                color: red;
                margin-bottom: 30px;
            }
		</style>
    </jsp:attribute>
    <jsp:attribute name="header_title">
        Provisio Hotels
    </jsp:attribute>
    <jsp:attribute name="page_title">
        Book Your Provisio Vacation
    </jsp:attribute>
    <jsp:attribute name="body">
		<p id="message">To book, enter your information below and click the submit button</p>

		<c:if test="${requestScope.errorMessage != null}">
			<div class="error-message">${requestScope.errorMessage}</div>
		</c:if>
		
		<form action="/Provisio/reservation" method="POST" id="resBookingForm">
			<input type='hidden' name='reservation_id' value='0' />
			
			<div class="res-in-form-divs">
				<strong>Locations: </strong> 
				<input type="radio" name="location" value='1' id='location1' required> 
				<label for="radio_1">Lake Garda, Italy</label> 

				<input type="radio" name="location" value='2' id='location2'> 
				<label for="radio_2">Marrakesh, Morocco</label> 

				<input type="radio" name="location" value='3' id='location3'> 
				<label for="radio_3">Rio de Janeiro, Brazil</label>
			</div>
			<hr>
			<div class="res-in-form-divs">
				<strong>Amenities: </strong> 
				<input type="radio" name="amenities" value='1' id='amenities1' required> 
				<label for="radio_4">Wi-Fi $12.99</label> 

				<input type="radio" name="amenities" value='2' id='amenities2'> 
				<label for="radio_5">Breakfast $8.99</label> 

				<input type="radio" name="amenities" value='3' id='amenities3'> 
				<label for="cbox_3">Parking $19.99</label>
			</div>
			<hr>

			<div class="res-in-form-divs">
				<strong>Bedding and Rates: </strong> 
				<input type="radio" name="beddingRates" value='1' id='beddingRates1' required> 
				<label for="radio_7">1 Double $110</label> 

				<input type="radio" name="beddingRates" value='2' id='beddingRates2'> 
				<label for="radio_8">1 Queen $125</label> 

				<input type="radio" name="beddingRates" value='3' id='beddingRates3'> 
				<label for="radio_9">2 Queen $150</label> 

				<input type="radio" name="beddingRates" value='4' id='beddingRates4'> 
				<label for="radio_10">1 King $165</label>
			</div>
			<hr>
			<div class="res-in-form-divs">
				<strong>Check-In Date: </strong> 
				<input type="date" name="checkin" id="checkin" required/> 

				<strong>Check-Out Date: </strong> 
				<input type="date" name="checkout" id="checkout" required/>
			</div>
			<hr>
			<div class="res-in-form-divs">
				<strong>Number of Nights: </strong> 
				<select name='numberOfNights' id='numberOfNights' required>
					<option value='1'>1</option>
					<option value='2'>2</option>
					<option value='3'>3</option>
					<option value='4'>4</option>
					<option value='5'>5</option>
					<option value='6'>6</option>
					<option value='7'>7</option>
					<option value='8'>8</option>
					<option value='9'>9</option>
					<option value='10'>10</option>
				</select>
		</form>

		<input type='button' onClick='showSummary()' id="showSummaryButton" value='Summary' />

		<div id='divSummary'>
			<p>
				Provisio Location:<span id='spanLocation'></span>
			</p>
			<p>
				Amenities: <span id='spanAmenities'></span>
			</p>
			<p>
				Bedding: <span id='spanBedding'></span>
			</p>

			<p>
				Number of Nights:<span id='spanNumNights'></span>
			</p>
			<p>
				Total:<span id='spanTotal'></span>
			</p>

			<p>
				Check-In Date:<span id='spanCheckInDate'></span>
			</p>
			<p>
				Check-Out Date:<span id='spanCheckOutDate'></span>
			</p>
			<p>
				Provisio Points:<span id='spanProvisioPoints'></span>
			</p>
			<p>
				<button onClick='submitForm()'>Submit</button>
				<input type='button' onClick='cancelRes()' value='Cancel' />
			</p>
		</div>
		<hr>

		<script type="text/javascript">
			function showSummary() {
				//	location1, amenities1, beddingRates1, checkin, checkout,numberOfNights
		
				//	Lake Garda, Italy Marrakesh, Morocco Rio de Janeiro, Brazil
				let amount = 0;
				if ($('#location1').is(':checked')) {
					$("#spanLocation").html("Lake Garda, Italy");
				} else if ($('#location2').is(':checked')) {
					$("#spanLocation").html("Marrakesh, Morocco");
				} else if ($('#location3').is(':checked')) {
					$("#spanLocation").html("Rio de Janeiro, Brazil");
				}
		
				if ($('#amenities1').is(':checked')) {
					amount = 12.99;
					$("#spanAmenities").html("Wi-Fi $12.99");
				} else if ($('#amenities2').is(':checked')) {
					amount = 8.99;
					$("#spanAmenities").html("Breakfast $8.99");
				} else if ($('#amenities3').is(':checked')) {
					amount = 19.99;
					$("#spanAmenities").html("Parking $19.99");
				}
		
				if ($('#beddingRates1').is(':checked')) {
					amount += 110;
					$("#spanBedding").html("1 Double $110");
				} else if ($('#beddingRates2').is(':checked')) {
					amount += 125;
					$("#spanBedding").html("1 Queen $125");
				} else if ($('#beddingRates3').is(':checked')) {
					amount += 150;
					$("#spanBedding").html("2 Queen $150");
				} else if ($('#beddingRates4').is(':checked')) {
					amount += 165;
					$("#spanBedding").html("1 King $165");
				}
		

				const checkin = $("#checkin").val();
				const checkout = $("#checkout").val();
				$("#spanCheckInDate").html(checkin);
				$("#spanCheckOutDate").html(checkin);
				const numberOfNights = parseInt($("#numberOfNights").val());
				$("#spanNumNights").html(numberOfNights);
				const total = amount * numberOfNights;
				$("#spanTotal").html("$" + total);
				const points = 150 * numberOfNights;
				$("#spanProvisioPoints").html(points);
				$("#divSummary").show();
			}

			function submitForm() {
				$("#divSummary").hide();
				$("#resBookingForm").submit();
			}

			function cancelRes() {
				$("#divSummary").hide();
			}

			$("#divSummary").hide();
		</script>
    </jsp:attribute>
</t:header>
