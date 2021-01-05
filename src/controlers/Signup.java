package controlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;
import exception.EntityException;
import sql.DBConnector;
import sql.Utils;
import tables.TableLocator;
import tables.UserTable;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String TITLE = "Inscription";
    private static final String PAGE_NAME = "signup.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			request.setAttribute("title", TITLE);
			request.setAttribute("page", PAGE_NAME);
			request.getRequestDispatcher("jsp/default.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserTable userTable = TableLocator.getUserTable();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			try {
				user = new User();
				String login = request.getParameter("login");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String password = request.getParameter("pass");
				String confirmPassword = request.getParameter("passConfirmed");
	
				// Vérification du format de la date de naissance
				Date dob;
				try {
					dob = Date.valueOf(request.getParameter("birthDate"));
				} catch (Exception e){
					dob = null;
				}
	
				// Vérification du login
				if (login.isBlank()) {
					session.setAttribute("error", "Le login ne doit pas être vide.");
					doGet(request, response);
	
				} else {
					if (!userTable.testLogin(login)) {
						session.setAttribute("error", "Cet identifiant est déjà utilisé.");
						doGet(request, response);
	
					// Verification du nom et du prénom
					} else if (lastName.isBlank()) {
						session.setAttribute("error", "Le nom ne doit pas être vide.");
						doGet(request, response);
	
					} else if (firstName.isBlank()) {
						session.setAttribute("error", "Le prénom ne doit pas être vide.");
						doGet(request, response);
	
	
					// Vérification du mot de passe
					} else if (password.length() < 6) {
						session.setAttribute("error", "Le mot de passe doit faire au moins 6 caractères.");
						doGet(request, response);
	
					} else if (password.isBlank()) {
						session.setAttribute("error", "Le mot de passe ne doit pas être vide.");
						doGet(request, response);
	
					} else if (!password.equals(confirmPassword)) {
						session.setAttribute("error", "Veuillez confirmer votre mot de passe.");
						doGet(request, response);	
	
					// Si tous les paramètres sont corrects, on crée l'utilisateur
					} else if (dob != null) {
						user.setLogin(login);
						user.setFirstName(request.getParameter("firstName"));
						user.setLastName(request.getParameter("lastName"));
						user.setPassword(Utils.hashPassword(password));
						user.setBirthDate(dob);
	
						userTable.save(user);
						TableLocator.getNotificationTable().sendNotificationTo(user, "Bravo ! Votre inscription est terminé. Vous faite maintenant partie de la famille !");
						session.setAttribute("user", user);
						session.setAttribute("success", "Bonjour, " + user.getLogin() + ". Vous êtes désormais connecté.");
						response.sendRedirect(request.getContextPath());
					} else {
						session.setAttribute("error", "La date doit être au format YYYY-MM-DD.");
						doGet(request, response);
					}	
				}
	
			} catch (Exception e) {
				session.setAttribute("error", "Une erreur est survenu lors de l'inscription. Réessayez ultérieurement.");
				doGet(request, response);
			}
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

}