package controlers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import sql.Utils;
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		entities.Activity activity = new entities.Activity();

		// Si un petit malin essai d'accéder à l'une des pages sans être connecté, il sera redirigé vers la page de connexion
		if (user == null) {
			session.setAttribute("error", "Vous devez être connecté pour accéder à cette ressource.");
			response.sendRedirect("login");
			return;
		}
		
		try {
			String name = request.getParameter("activityName");
			Timestamp start, end;
			try {
				start = Utils.getTimestampOf(request.getParameter("start"));	// On récupère le timestamp correspondant au parametre recu
				end = Utils.getTimestampOf(request.getParameter("end"));
			} catch (Exception e){
				e.printStackTrace();
				start = null;
				end = null;
			}
			
			int locID;
			try {
				locID = Integer.parseInt(request.getParameter("selectLoc"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				locID = -1;
			}
			
			// Test si le nom est vide
			if (name == null || name.isBlank()) {
				session.setAttribute("error", "Le titre ne doit pas être vide.");
				response.sendRedirect("activity?s=add");
				return;
			}
			
			// Test si l'une des dates est null
			if (start == null || end == null) {
				session.setAttribute("error", "La date de début ou la date de fin n'est pas correctement formée.");
				response.sendRedirect("activity?s=add");
				return;
			}
			
			// Test si la localisation est valide
			if (locID == -1) {
				session.setAttribute("error", "Vous devez choisir un lieu pour ajouter une activité");
				response.sendRedirect("activity?s=add");
				return;
			}
			
			// Si tous ces testes ont échoué, alors on continue et on crée l'activité
			activity.setTitle(name);
			activity.setStartDate(start);
			activity.setEndDate(end);
			activity.setLocation(TableLocator.getLocationTable().getByID(locID));
			activity.setUser(user);
			if (!TableLocator.getActivityTable().save(activity)) {
				session.setAttribute("error", "Impossible d'ajouter l'activité. Réessayez ultérieurement.");
				response.sendRedirect("activity?s=add");
				return;
			}
			
			session.setAttribute("success", "Activité ajouté avec succès.");
			response.sendRedirect("activity?s=list");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Une erreur est survenu lors de l'ajout. Réessayez ultérieurement.");
			response.sendRedirect("activity?s=add");
		}
		
	}

}
