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
import tables.NotificationTable;
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
		
		// Si un petit malin essaie d'accéder à l'une des pages sans être connecté, il sera redirigé vers la page de connexion
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String s = request.getParameter("s");
		String r = request.getParameter("r");
		
		if (user != null) {
			if (r != null && r.equals("json")) {
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
			}
			
			if (s != null && s.equals("seen")) {
				String sId = request.getParameter("id");
				if (sId != null) {
					boolean done = false;
					int id = Integer.parseInt(sId);
					done = TableLocator.getNotificationTable().markAsSeen(id);
					response.getWriter().append(String.format("{\"result\":%s, \"id\":%d}", done, id));
				}
			} else if(s != null && s.equals("ask")) {
				boolean done = false;
				String login = request.getParameter("user");
				if (login != null) {
					User friend;
					try {
						friend = TableLocator.getUserTable().getByLogin(login);
						if (friend != null) {
							done = TableLocator.getNotificationTable().sendNotificationTo(
									friend, 
									user.getLogin() + " veut vous ajouter à sa liste d'amis. Voulez-vous accepter ?", 
									NotificationTable.ASK_FRIEND, "", user);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						done = false;
					}
					
					String result = String.format("{\"result\":%s}", done);
					response.getWriter().append(result);
	
				}
			} else {
				doGet(request, response);
			}
		}
	}

}
