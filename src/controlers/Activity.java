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

import entities.Entity;
import entities.User;
import tables.TableLocator;

/**
 * Servlet implementation class Activity
 */
@WebServlet("/Activity")
public class Activity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String TITLE_LIST = "Liste des activités";
	public static final String TITLE_ADD = "Ajouter une activité";
	public static final String PAGE_LIST = "activity/list.jsp";
	public static final String PAGE_ADD = "activity/add.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Activity() {
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
		
		// Si aucun paramêtre n'est donné, on redirige l'utilisateur vers la liste des activités.
		if (request.getParameter("s") == null) {
			response.sendRedirect("activity?s=list");
			return;
		}
		
		if (request.getParameter("s").equals("add")) {
			// Affiche le formulaire qui permet de créer une activité
			request.setAttribute("title", TITLE_ADD);
			request.setAttribute("page", PAGE_ADD);
			try {
				List<Entity> list = TableLocator.getLocationTable().getAll();
				request.setAttribute("list", list);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// Affiche la page avec la liste des activités
			List<entities.Activity> activities = null;
			try {
				activities = TableLocator.getActivityTable().getFromUser(user);
			} catch (SQLException e) {
				session.setAttribute("error", "Une erreur s'est produite lors de la récupération des activités.");
				response.sendRedirect(request.getContextPath());
			}
			request.setAttribute("title", TITLE_LIST);
			request.setAttribute("page", PAGE_LIST);
			request.setAttribute("list", activities);
		}
		request.getRequestDispatcher("jsp/default.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
