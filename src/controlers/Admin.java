package controlers;

import java.io.IOException;
import java.sql.Date;
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
import entities.Activity;
import entities.Location;
import entities.User;
import sql.Utils;
import tables.TableLocator;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTIVITY_PAGE = "admin/activity.jsp";
	private static final String LOCATION_PAGE = "admin/location.jsp";
	private static final String USER_PAGE = "admin/user.jsp";
	private static final String MENU_PAGE = "admin/menu.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("s");
		String page = null;
		
		List<Entity> entities = new ArrayList<Entity>();
		if (s != null) {
			switch (s) {
			case "activity":
				page = ACTIVITY_PAGE;
				request.setAttribute("title", "Admin(activity)");
				try {
					entities.addAll(TableLocator.getActivityTable().getAll());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "location":
				page = LOCATION_PAGE;
				request.setAttribute("title", "Admin(location)");
				try {
					entities.addAll(TableLocator.getLocationTable().getAll());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "user":
				page = USER_PAGE;
				request.setAttribute("title", "Admin(user)");
				try {
					entities.addAll(TableLocator.getUserTable().getAll());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		} else {
			page = MENU_PAGE;
			request.setAttribute("title", "Admin");
		}
		
		if (page != null) {
			request.setAttribute("list", entities);
			request.setAttribute("page", page);
			request.getRequestDispatcher("jsp/default.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("s");
		String id = request.getParameter("id");
		boolean done = false;
		
		if (s != null && id != null) {
			switch (s) {
			case "activity":
				done = TableLocator.getActivityTable().delById(Integer.parseInt(id));
				break;
			case "location":
				done = TableLocator.getLocationTable().delById(Integer.parseInt(id));
				break;
			case "user":
				done = TableLocator.getUserTable().delById(Integer.parseInt(id));
				break;
			default:
				break;
			}
		}
		
		if (done) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(String.format("{\"result\":%s}", done));
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("s");
		String json = "";
		boolean done = false;
		
		if (s != null) {
			switch (s) {
			case "activity":
				String title = request.getParameter("title");
				String sd = request.getParameter("sd");
				String ed = request.getParameter("ed");
				String loc = request.getParameter("loc");
				String user = request.getParameter("user");
				if (title != null && sd != null && ed != null && loc != null && user != null) {
					Activity act = new Activity();
					try {
						act.setTitle(title);
						act.setStartDate(Utils.getTimestampOf(sd));
						act.setEndDate(Utils.getTimestampOf(ed));
						act.setLocation(TableLocator.getLocationTable().getByID(Integer.parseInt(loc)));
						act.setUser(TableLocator.getUserTable().getByID(Integer.parseInt(user)));
						done = TableLocator.getActivityTable().save(act);
						
						if(done)
							json = String.format("{\"result\":true,\"id\":%d,\"title\":\"%s\",\"sd\":\"%s\",\"ed\":\"%s\",\"loc\":%d,\"user\":%d}", act.getId(), act.getTitle(), Utils.timestampToString(act.getStartDate()), Utils.timestampToString(act.getEndDate()), act.getLocation().getId(), act.getUser().getId());
						else
							json = "{\"result\":false}";
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case "location":
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String gps = request.getParameter("gps");
				if (name != null && address != null && gps != null) {
					Location location = new Location();
					try {
						location.setName(name);
						location.setAddress(address);
						location.setGPS(gps);
						done = TableLocator.getLocationTable().save(location);
						
						if(done)
							json = String.format("{\"result\":true,\"id\":%d,\"name\":\"%s\",\"address\":\"%s\",\"gps\":\"%s\"}", location.getId(), location.getName(), location.getAddress(), location.getGPS());
						else
							json = "{\"result\":false}";
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case "user":
				String login = request.getParameter("login");
				String password = request.getParameter("password");
				String first = request.getParameter("first");
				String last = request.getParameter("last");
				String birth = request.getParameter("birth");
				String infected = request.getParameter("infected");
				String contact = request.getParameter("contact");
				String admin = request.getParameter("admin");
				if (login != null && password != null && first != null && last != null && birth != null && infected != null && contact != null && admin != null) {
					User user2 = new User();
					try {
						System.out.println(infected + " || " + contact + " || " + admin);
						user2.setLogin(login);
						user2.setPassword(Utils.hashPassword(password));
						user2.setFirstName(first);
						user2.setLastName(last);
						user2.setBirthDate(Date.valueOf(birth));
						user2.setInfected(infected=="on");
						user2.setContact(contact=="on");
						user2.setAdmin(admin=="on");
						done = TableLocator.getUserTable().save(user2);
						
						if(done)
							json = String.format("{\"result\":true,\"id\":%d,\"login\":\"%s\",\"password\":\"%s\",\"first\":\"%s\",\"last\":%s,\"birth\":\"%s\",\"infected\":%s,\"contact\":%s,\"admin\":%s}", 
									user2.getId(), user2.getLogin(), "***", user2.getFirstName(), user2.getLastName(), Utils.dateToString(user2.getBirthDate()), user2.isInfected(), user2.isContact(), user2.isAdmin());
						else
							json = "{\"result\":false}";
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			default:
				break;
			}
		}
		
		if (done) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json);
		}
	}

}
