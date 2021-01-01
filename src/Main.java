import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import entities.Activity;
import entities.Entity;
import entities.Location;
import entities.Notification;
import entities.User;
import exception.EntityException;
import tables.ActivityTable;
import tables.LocationTable;
import tables.NotificationTable;
import tables.TableLocator;
import tables.UserTable;

public class Main {

	public static void main(String[] args) {
		testUser();
		testLocation();
		testActivity();
		testNotification();
	}
	
	private static void testUser() {
		UserTable userTable = TableLocator.getUserTable();

		// test save User
		User user = new User();
		try {
			user.setLogin("MEmeMEmeMEme");
			user.setPassword("JeSaisPlus");
			user.setFirstName("Moi");
			user.setLastName("PasMoi");
			user.setAdmin(true);
			userTable.save(user);
		} catch (EntityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		// test print Users
		try {
			List<Entity> users = userTable.getAll();
			for (Entity u : users) {
				System.out.println(u.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private static void testActivity() {
		ActivityTable activityTable = TableLocator.getActivityTable();
		UserTable userTable = TableLocator.getUserTable();
		LocationTable locationTable = TableLocator.getLocationTable();

		// test save Activity
		Activity activity = new Activity();
		try {
			activity.setTitle("Chose intéressante");
			activity.setStartDate(new Date(0));
			activity.setEndDate(new Date(0));
			activity.setLocation(locationTable.getByID(1));
			activity.setUser(userTable.getByID(1));
			activityTable.save(activity);
		} catch (EntityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		// test print Activities
		try {
			List<Entity> activities = activityTable.getAll();
			for (Entity a : activities) {
				System.out.println(a.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private static void testLocation() {
		LocationTable locationTable = TableLocator.getLocationTable();

		// test save Location
		Location location = new Location();
		try {
			location.setName("Lieu intéressant");
			location.setAddress("Rue des feux follets");
			location.setGPS("48°41'37\"N6°11'05\"E");
			locationTable.save(location);
		} catch (EntityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		// test print Locations
		try {
			List<Entity> locations = locationTable.getAll();
			for (Entity l : locations) {
				System.out.println(l.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private static void testNotification() {
		NotificationTable notificationTable = TableLocator.getNotificationTable();
		UserTable userTable = TableLocator.getUserTable();

		// test save Notification
		Notification notification = new Notification();
		try {
			notification.setText("Ceci est une notification.");
			notification.setReceivedDate(new Date(0));
			notification.setUser(userTable.getByID(1));
			notificationTable.save(notification);
		} catch (EntityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		// test print Notifications
		try {
			List<Entity> notifications = notificationTable.getAll();
			for (Entity n : notifications) {
				System.out.println(n.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}