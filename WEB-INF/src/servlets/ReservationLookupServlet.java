/**
 * Capstone Provisio Project
 * Green Team
 * 04/18/2022
 */

package Provisio;

import java.io.IOException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 
import jakarta.servlet.ServletContext;
import jakarta.servlet.RequestDispatcher;


@WebServlet(
	name = "ReservationLookupServlet",
	urlPatterns = "/lookup/reservation"
)
public class ReservationLookupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String reservation_lookup_project_context_redirect_url;
	private String reservation_lookup_host_context_redirect_url;
	private String session_user_id_key;
	private String reservation_lookup_result_host_context_redirect_url;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationLookupServlet() {
        super();
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
		this.reservation_lookup_project_context_redirect_url = context.getInitParameter("UserReservationLookupRedirectProjectContextURL");
		this.reservation_lookup_host_context_redirect_url = context.getInitParameter("UserReservationLookupRedirectHostContextURL");
		this.session_user_id_key = context.getInitParameter("SessionUserId");
		this.reservation_lookup_result_host_context_redirect_url = context.getInitParameter("UserReservationLookupResultRedirectHostContextURL");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {}

	/**
	 * Just because I don't like navigating to .jsp (:
	 */
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) 
		throws ServletException, 
		IOException 
	{
		response.sendRedirect(this.reservation_lookup_host_context_redirect_url);
	}

	/**
	 * Handle login form here
	 */
	public void doPost (
		HttpServletRequest request,
		HttpServletResponse response
	) 
		throws ServletException, 
		IOException 
	{
		// Make sure required params exist (and gather them):
		String reservation_id = request.getParameter("resNumber");
		HttpSession session = request.getSession();
		String session_user_id = Integer.toString((Integer)session.getAttribute(this.session_user_id_key));

		RequestDispatcher request_dispatcher = request.getRequestDispatcher(this.reservation_lookup_project_context_redirect_url);

		if (
			reservation_id == null ||
			session_user_id == null
		){
			System.out.println("Failed to gather either reservation id or session user id during reservation lookup request.");
			request.setAttribute("error_message", "Something went wrong. Please try again.");
			request_dispatcher.forward(request, response);
			return;
		}

		// Try to get reservation:
		DBReservationLookupResult reservation_lookup_result = DBReservationLookupHandler.getReservation(
			reservation_id,
			session_user_id
		);

		if (
			reservation_lookup_result.hasResults() == false
		){
			request.setAttribute("error_message", "Could not find any reservations for you with that id. Please verify the id and try again.");
			request_dispatcher.forward(request, response);
			return;
		}

		session.setAttribute("reservation_data", reservation_lookup_result.getReservationLookupResult());
		response.sendRedirect(this.reservation_lookup_result_host_context_redirect_url);
	}
} 