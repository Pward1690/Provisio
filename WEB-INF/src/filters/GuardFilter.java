/**
 * Capstone Provisio Project
 * Green Team
 * 04/14/2022
 */

/**
 * Intercepts every request and handles authentication for us
 */
package Provisio;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@WebFilter(
	urlPatterns = {
		"/jsp/*",
		"/.gitignore",
		"*.md",
		"*.txt",
		"*.docx",
		"/reservation"
	}
)
public class GuardFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private ServletContext context;

	/**
	 sp/prompt.jsp* Contains all file names that should be redirected (on fail)
	 * to user lobby area.
	 * 
	 * NOTE: if file name is not in here, then it will get redirected to
	 * login upon failing.
	 */
	private final static String[] guest_user_areas = {
		"login",
		"register"
	};

	/**
	 * Contains all file names that shouldn't be messed with at all
	 *////////
	private final static String[] do_nothing_areas = {
		"home",
		"index",
		"error-404",
		"about"
	};

	/**
	 * Contains file names that are banned
	 */
	private final static String[] banned_areas = {
		".md",
		".txt",
		".docx",
		".gitignore"
	};

	private String session_user_email;
	private String login_redirect_url;
	private String lobby_redirect_url;
	private String error_404_redirect_url;
       
    public GuardFilter() {
        super();
    }

	public void init(
		FilterConfig config
	) throws ServletException {
		this.context = config.getServletContext();

		this.session_user_email = context.getInitParameter("SessionUserEmail");
		this.login_redirect_url = context.getInitParameter("LoginRedirectHostContextURL");
		this.lobby_redirect_url = context.getInitParameter("UserLobbyRedirectHostContextURL");
		this.error_404_redirect_url = context.getInitParameter("UserError404RedirectHostContextURL");
	}

	@Override
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain
	) throws IOException, ServletException {
		HttpServletRequest http_request = (HttpServletRequest) request;
		HttpServletResponse http_response = (HttpServletResponse) response;

		System.out.println("hit filter");

		Boolean didBlock = this.guardRequest(
			http_request,
			http_response
		);

		if (didBlock)
			return;

		chain.doFilter(request, response);
	}

	/**
	 * Guard requests
	 */
	public Boolean guardRequest(
		HttpServletRequest request,
		HttpServletResponse response
	) throws IOException {
		StringBuffer requestURL = request.getRequestURL();
		HttpSession session = request.getSession();

		// If is banned:
		if (this.isBanned(requestURL)){
			System.out.println("GUARD: Redirected to " + this.error_404_redirect_url);
			response.sendRedirect(this.error_404_redirect_url);
			return true;
		}

		// If unrestricted, pass through:
		if (this.isUnrestricted(requestURL))
			return false;

		// Check if this is a guest allowed area:
		if (this.isGuestArea(requestURL)){
			if (session.getAttribute(session_user_email) != null){
				System.out.println("GUARD: Redirected to " + this.lobby_redirect_url);
				response.sendRedirect(this.lobby_redirect_url);
				return true;
			}

			return false;
		}

		// Is not guest area, redirect to login if fail:
		if (session.getAttribute(session_user_email) == null){
			System.out.println("GUARD: Redirected to " + this.login_redirect_url);
			response.sendRedirect(this.login_redirect_url);
			return true;
		}

		return false;
	}

	/**
	 * Check if requestURL page is a valid guest user area
	 */
	private Boolean isGuestArea(
		StringBuffer requestURL
	){
		for (String guest_area: this.guest_user_areas){
			if (
				requestURL.toString().contains(guest_area)
			)
				return true;
		}

		return false;
	}

	/**
	 * Check if requestURL page is banned
	 */
	private Boolean isBanned(
		StringBuffer requestURL
	){
		for (String banned_area: this.banned_areas){
			if (
				requestURL.toString().contains(banned_area)
			)
				return true;
		}

		return false;
	}

	/**
	 * Check if requestURL page should not be messed with
	 */
	private Boolean isUnrestricted(
		StringBuffer requestURL
	){
		for (String unrestricted_area: this.do_nothing_areas){
			if (
				requestURL.toString().contains(unrestricted_area)
			){
				return true;
			}
		}

		return false;
	}
}