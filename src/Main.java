import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import entities.Activity;
import entities.Entity;
import entities.User;
import exception.EntityException;
import tables.ActivityTable;
import tables.UserTable;

public class Main {

	public static void main(String[] args) {
		testUser();
		testActivity();
	}
	
	private static void testUser() {
		UserTable userTable = new UserTable();

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
		ActivityTable activityTable = new ActivityTable();
		UserTable userTable = new UserTable();

		// test save Activity
		Activity activity = new Activity();
		try {
			activity.setTitle("Chose intéressante");
			activity.setStartDate(new Date(0));
			activity.setEndDate(new Date(0));
			activity.setLocation(1);
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
}