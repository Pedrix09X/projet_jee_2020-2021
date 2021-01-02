package controlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if (request.getParameter("s").equals("add")) {
			request.setAttribute("title", TITLE_ADD);
			request.setAttribute("page", PAGE_ADD);
		} else {
			request.setAttribute("title", TITLE_LIST);
			request.setAttribute("page", PAGE_LIST);
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
