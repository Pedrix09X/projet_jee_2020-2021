package controlers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import tables.TableLocator;
import tables.UserTable;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String TITLE = "Connexion";
    private static final String PAGE_NAME = "login.jsp";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", TITLE);
		request.setAttribute("page", PAGE_NAME);
		request.getRequestDispatcher("jsp/default.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserTable userTable = TableLocator.getUserTable();
		try {
			User user = userTable.login(request.getParameter("login"), request.getParameter("pass"));
			if (user == null) {
				request.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrecte");
				doGet(request, response);
			} else {
				request.getSession().setAttribute("user", user);
				request.setAttribute("success", "Bonjour, " + user.getLogin() + ". Vous désormais connecté.");
				response.sendRedirect(request.getContextPath());
			}
		} catch (SQLException e) {
			request.setAttribute("error", "Une erreur est survenu lors de la connexion à votre compte. Réessayez ultérieurement.");
			doGet(request, response);
		}
	}

}
