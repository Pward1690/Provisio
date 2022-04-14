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
		String action = request.getParameter("action");
		if (action == null || action.equals("cancel")) {
			RequestDispatcher rd = request.getRequestDispatcher(this.home_project_context_redirect_url);
			rd.forward(request, response);
			return;
		}
		switch (action) {
		case "reservation":
			ReservationBean rb = createReservation(request);
			rb.setUser_id(1);
			rb.setGuest_cost_id(1);
			int id = service.save(rb);
			if (id > 0)// success
			{
				request.setAttribute("confirmationNumber", id + "");
				RequestDispatcher rd = request.getRequestDispatcher(this.confirmation_project_context_redirect_url);
				rd.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "reservation failed");
				RequestDispatcher rd = request.getRequestDispatcher(this.reservation_booking_project_context_redirect_url);
				rd.forward(request, response);
			}
			break;
		}
	}

	private ReservationBean createReservation(HttpServletRequest request) {
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
}
