package controlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class Notification
 */
@WebServlet("/Notification")
public class Notification extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String TITLE = "Notifications";
    private static final String PAGE_NAME = "notification/list.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notification() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		// Si un petit malin essai d'accéder à l'une des pages sans être connecté, il sera redirigé vers la page de connexion
		if (user == null) {
			session.setAttribute("error", "Vous devez être connecté pour accéder à cette ressource.");
			response.sendRedirect("login");
			return;
		}
		
		List<entities.Notification> notifs = new ArrayList<entities.Notification>();
		try {
			notifs = TableLocator.getNotificationTable().getByUser(user);
		} catch (SQLException e) {
			session.setAttribute("error", "Une erreur s'est produite lors de la récupération de vos notifications.");
			notifs = null;
		}
		
		request.setAttribute("list", notifs);
		request.setAttribute("title", TITLE);
		request.setAttribute("page", PAGE_NAME);
		request.getRequestDispatcher("jsp/default.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
