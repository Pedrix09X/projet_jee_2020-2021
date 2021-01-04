package controlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;
import tables.TableLocator;

/**
 * Servlet implementation class Covided
 */
@WebServlet("/Covided")
public class Covided extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Covided() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			session.setAttribute("error", "Vous devez être connecté pour accéder à cette ressource.");
			response.sendRedirect("login");
			return;
		}
		
		if (TableLocator.getUserTable().markUserAsInfected(user)) {
			session.setAttribute("success", "Vous avez été marqué comme infecté. Tous les utilisateurs concerné seront marqués comme \"cas contact\" et vont être notifiés.");
			response.sendRedirect(request.getContextPath());
		} else {
			session.setAttribute("error", "Une erreur s'est produite et vous n'avez pas été marqué comme infecté. Veuillez réessayer ultérieurement.");
			response.sendRedirect(request.getContextPath());
		}
		
	}

}
