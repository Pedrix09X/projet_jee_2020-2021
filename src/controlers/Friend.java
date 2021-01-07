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
import entities.Notification;
import tables.TableLocator;

/**
 * Servlet implementation class Friend
 */
@WebServlet("/Friend")
public class Friend extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String TITLE = "Mes amis";
    private static final String PAGE_NAME = "friend/list.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Friend() {
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
		
		List<User> friends = null;
		TableLocator.getFriendTable().getFriendsOf(user);
		friends = user.getFriends();
		request.setAttribute("title", TITLE);
		request.setAttribute("page", PAGE_NAME);
		request.setAttribute("list", friends);
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
		String sId = request.getParameter("id");
		
		boolean done = false;
		if (user != null) {
			if (s != null && s.equals("add")) {
				if (sId != null) {
					int notifId = Integer.parseInt(sId);
					User friend = null;
					try {
						Notification notif = TableLocator.getNotificationTable().getByID(notifId);
						friend = notif.getFriend();
						if (friend != null) {
							done = TableLocator.getFriendTable().addFriend(user, friend);
							done = done && TableLocator.getFriendTable().addFriend(friend, user);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						done = false;
					}

					if (r != null && r.equals("json")) {
						if (done) {
							TableLocator.getNotificationTable().sendNotificationTo(friend, user.getLogin() + " a accepté votre demande d'ami.");
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().append(String.format("{\"result\":%s, \"id\":%d}", done, notifId));
							return;
						}
					}
				}
			}
		}
		
		response.sendRedirect(request.getContextPath());
	}

}
