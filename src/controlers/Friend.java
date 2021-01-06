package controlers;

import java.io.IOException;
import java.sql.SQLException;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Friend() {
        super();
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
