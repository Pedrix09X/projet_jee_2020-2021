package controlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.EntityException;
import tables.TableLocator;

/**
 * Servlet implementation class Location
 */
@WebServlet("/Location")
public class Location extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Location() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if (request.getParameter("r") != null && request.getParameter("r").equals("json")) {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
			String locName = request.getParameter("name");
			String locAddress = request.getParameter("address");
			String locGPS = request.getParameter("gps");
			if (!locName.isBlank() && !locAddress.isBlank()) {
				entities.Location loc = new entities.Location();
				try {
					loc.setName(locName);
					loc.setAddress(locAddress);
					loc.setGPS(locGPS);
					TableLocator.getLocationTable().save(loc);
					out.append("{\"id\": " + loc.getId() + ", \"name\": \"" + loc.getName() + "\"}");
				} catch (Exception e) {
					e.printStackTrace();
					response.sendError(500);
				}
			}
		}
	}
}
