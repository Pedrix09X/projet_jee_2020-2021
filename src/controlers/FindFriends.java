package controlers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;
import tables.TableLocator;

/**
 * Servlet implementation class FindFriends
 */
@WebServlet("/FindFriends")
public class FindFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindFriends() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String partialLogin = request.getParameter("partialLogin");
		
		if (user != null && partialLogin != null && !partialLogin.isBlank()) {
			List<String> logins = TableLocator.getUserTable().findLogins(partialLogin);
			String res = "[";
			for (String login : logins) {
				res += String.format("\"%s\"", login);
			}
			res = res.replace("\"\"", "\",\"");
			res += "]";
			System.out.println(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(res);
		}
	}

}
