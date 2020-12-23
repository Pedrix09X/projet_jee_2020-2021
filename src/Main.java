import java.sql.SQLException;
import java.util.List;

import entities.Entity;
import tables.UserTable;

public class Main {

	public static void main(String[] args) {
		UserTable userTable = new UserTable();
		try {
			List<Entity> users = userTable.getAll();
			for (Entity user : users) {
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
