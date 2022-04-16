/**
 * Capstone Provisio Project
 * Green Team
 * 04/14/2022
 */

package Provisio;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet(
	name = "ReservationServlet",
	urlPatterns = "/reservation"
)
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReservationService service;
	private String home_project_context_redirect_url;
	private String reservation_booking_project_context_redirect_url;
	private String confirmation_project_context_redirect_url;

	public ReservationServlet() {
		super();
		service = new ReservationService();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(
		ServletConfig config
	) throws ServletException {
		super.init(config);

		// Get config values:
		ServletContext context = getServletContext();
		this.home_project_context_redirect_url = context.getInitParameter("UserHomeRedirectProjectContextURL");
		this.reservation_booking_project_context_redirect_url = context.getInitParameter("UserReservationRedirectProjectContextURL");
		this.confirmation_project_context_redirect_url = context.getInitParameter("UserConfirmationRedirectProjectContextURL");
	}

	protected void doGet(
		HttpServletRequest request, 
		HttpServletResponse response
	)
		throws ServletException, 
		IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher(this.home_project_context_redirect_url);
		rd.forward(request, response);
	}

	protected void doPost(
		HttpServletRequest request, 
		HttpServletResponse response
	)
		throws ServletException, 
		IOException 
	{
		// Validate the form:
		String error_message = this.validateForm(request);
		if (
			!this.isEmpty(error_message)
		){
			request.setAttribute("errorMessage", error_message);
			RequestDispatcher rd = request.getRequestDispatcher(this.reservation_booking_project_context_redirect_url);
			rd.forward(request, response);
			return;
		}

		ReservationBean rb = createReservation(request);
		rb.setUser_id(1);
		rb.setGuest_cost_id(1);
		Long id = service.save(rb);
		if (id > 0)// success
		{
			request.setAttribute("confirmationNumber", id + "");
			RequestDispatcher rd = request.getRequestDispatcher(this.confirmation_project_context_redirect_url);
			rd.forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Reservation failed");
			RequestDispatcher rd = request.getRequestDispatcher(this.reservation_booking_project_context_redirect_url);
			rd.forward(request, response);
		}
	}

	private ReservationBean createReservation(
		HttpServletRequest request
	){
		try {
			ReservationBean rb = new ReservationBean();
			String temp = request.getParameter("location");
			rb.setProvisio_location_id(parseInt(temp, -1));
			temp = request.getParameter("amenities");
			rb.setAmenity_id(parseInt(temp, -1));
			rb.setBedding_id(parseInt(request.getParameter("beddingRates"), -1));
			rb.setCheck_in_date(request.getParameter("checkin"));
			rb.setCheck_out_date(request.getParameter("checkout"));
			rb.setNumber_of_nights(parseInt(request.getParameter("numberOfNights"), -1));

			try {
				rb.setReservation_date(DateHelper.getDate());
			} catch (ParseException __){
				rb.setReservation_date(null);
			}

			try {
				rb.setIp(IPHelper.getIP());
			} catch (UnknownHostException __){
				rb.setIp("Unknown");
			}
			
		
			return rb;
		} catch (Exception ex) {
			return null;
		}
	}

	private int parseInt(String val, int def) {
		try {
			return Integer.parseInt(val);
		} catch (Exception ex) {
			return def;
		}
	}

	/**
	 * Validate the form values
	 * @return String
	 */
	private String validateForm(
		HttpServletRequest request
	){
		// Make sure none of the required fields are empty:
		if (
			this.anyAreEmpty(
				new String[] {
					request.getParameter("location").trim(),
					request.getParameter("amenities").trim(),
					request.getParameter("beddingRates").trim(),
					request.getParameter("checkin").trim(),
					request.getParameter("checkout").trim(),
					request.getParameter("numberOfNights").trim()
				}
			)
		)
			return "Invalid form submission. Please try again";

		// Validate fields specifically:
		if (
			this.isInvalidLocation(
				request.getParameter("location").trim()
			)
		)
			return "Invalid location. Please try again.";

		if (
			this.isInvalidAmenity(
				request.getParameter("amenities").trim()
			)
		)
			return "Invalid amenity. Please try again.";

		if (
			this.isInvalidBeddingRate(
				request.getParameter("beddingRates").trim()
			)
		)
			return "Invalid bedding rate.";

		if (
			this.isInvalidDates(
				request.getParameter("checkin").trim(),
				request.getParameter("checkout").trim()
			)
		)
			return "Invalid dates. Please try again.";

		return null;
	}

	/**
	 * Validate number of nights
	 * @return Boolean
	 */
	private Boolean isInvalidNumberOfNights(
		String number_of_nights
	){
		try {
			Integer num_nights = Integer.parseInt(number_of_nights);

			if (num_nights < 1 || num_nights > 10)
				return true;
		} catch (Exception __){
			return false;
		}

		return false;
	}

	/**
	 * Validate check in and check out dates
	 * @return Boolean
	 */
	private Boolean isInvalidDates(
		String check_in_date,
		String check_out_date
	){
		if (
			!DateHelper.isValidDate(check_in_date) ||
			!DateHelper.isValidDate(check_out_date)
		)
			return false;

		return true;
	}


	/**
	 * Validate bedding rates
	 * @return Boolean
	 */
	private Boolean isInvalidBeddingRate(
		String bedding_rate
	){
		return (
			!bedding_rate.equals("1") &&
			!bedding_rate.equals("2") &&
			!bedding_rate.equals("3") &&
			!bedding_rate.equals("4")
		);
	}

	/**
	 * Validate location
	 * @return Boolean
	 */
	private Boolean isInvalidLocation(
		String location
	){
		System.out.println("LOCATION: " + location);
		return (
			!location.equals("1") && 
			!location.equals("2") && 
			!location.equals("3")
		);
	}

	/**
	 * Validate amenities
	 * @return Boolean
	 */
	private Boolean isInvalidAmenity(
		String amenity
	){
		return this.isInvalidLocation(amenity);
	}

	/**
	 * Check if empty
	 * @return Boolean
	 */
	private Boolean isEmpty(
		String param
	){
		return param == null || param.equals("");
	}

	/**
	 * Check if empty (with supplied array)
	 * @return Boolean
	 */
	private Boolean anyAreEmpty(
		String[] params
	){
		for (String param: params){
			if (
				this.isEmpty(param)
			)
				return true;
		}

		return false;
	}
}
