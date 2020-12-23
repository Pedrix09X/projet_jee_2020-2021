package tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.User;
import exception.EntityException;
import sql.DBConnector;

public class UserTable implements Table {
	
	//--- Variables statiques décrivant la table User ---//
	public static final String TABLE_NAME 		= "User";
	public static final String COLUMN_ID 		= "id";
	public static final String COLUMN_LOGIN 	= "login";
	public static final String COLUMN_PASSWORD 	= "password";
	public static final String COLUMN_FIRSTNAME = "firstName";
	public static final String COLUMN_LASTNAME 	= "lastName";
	public static final String COLUMN_INFECTED 	= "isInfected";
	public static final String COLUMN_CONTACT 	= "isContact";
	public static final String COLUMN_ADMIN 	= "isAdmin";
	
	@Override
	public Entity getByID(int id) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?";
		ResultSet rs = DBConnector.getInstance().executeQuery(sql, new Object[] {id});
		
		User user = null;
		if (rs != null) {
			rs.next();
			user = new User();
			try {
				user.setId(rs.getInt(1));
				user.setLogin(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setInfected(rs.getBoolean(6));
				user.setContact(rs.getBoolean(7));
				user.setAdmin(rs.getBoolean(8));
			} catch (EntityException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public List<Entity> getAll() throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME;
		ResultSet rs = DBConnector.getInstance().executeQuery(sql);
		
		ArrayList<Entity> users = new ArrayList<Entity>();
		User user = null;
		if (rs != null) {
			while (rs.next()) {
				user = new User();
				try {
					user.setId(rs.getInt(1));
					user.setLogin(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setFirstName(rs.getString(4));
					user.setLastName(rs.getString(5));
					user.setInfected(rs.getBoolean(6));
					user.setContact(rs.getBoolean(7));
					user.setAdmin(rs.getBoolean(8));
					users.add(user);
				} catch (EntityException e) {
					e.printStackTrace();
				}
			}
		}
		return users;
	}
}
