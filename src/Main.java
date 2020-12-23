import java.sql.SQLException;
import java.util.List;

import entities.Entity;
import entities.User;
import exception.EntityException;
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
	}

}
