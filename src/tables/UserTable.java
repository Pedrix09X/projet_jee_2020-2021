package tables;

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
	public static final int NOT_SAVED = -1;
	public static final String TABLE_NAME 		= "User";
	public static final String COLUMN_ID 		= "id";
	public static final String COLUMN_LOGIN 	= "login";
	public static final String COLUMN_PASSWORD 	= "password";
	public static final String COLUMN_FIRSTNAME = "firstName";
	public static final String COLUMN_LASTNAME 	= "lastName";
	public static final String COLUMN_BIRTHDATE = "birthDate";
	public static final String COLUMN_INFECTED 	= "isInfected";
	public static final String COLUMN_CONTACT 	= "isContact";
	public static final String COLUMN_ADMIN 	= "isAdmin";
	
	protected UserTable() {}
	
	@Override
	public User getByID(int id) throws SQLException {
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
				user.setBirthDate(rs.getDate(6));
				user.setInfected(rs.getBoolean(7));
				user.setContact(rs.getBoolean(8));
				user.setAdmin(rs.getBoolean(9));
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
					user.setBirthDate(rs.getDate(6));
					user.setInfected(rs.getBoolean(7));
					user.setContact(rs.getBoolean(8));
					user.setAdmin(rs.getBoolean(9));
					users.add(user);
				} catch (EntityException e) {
					e.printStackTrace();
				}
			}
			rs.close();
		}
		return users;
	}

	@Override
	public boolean save(Entity e) throws SQLException {
		if (e.getClass().equals(User.class)) {
			User user = (User)e;
			if (user.getId() == NOT_SAVED) {
				return this.insert(user);
			} else {
				return this.update(user);
			}
		}
		return false;
	}
	
	/**
	 * Va mettre à jour les données de la base
	 * @param e User a mettre à jour
	 * @return true si tout se passe bien
	 */
	private boolean update(User e) {
		String sql = "UPDATE " + TABLE_NAME + " SET "
				+ COLUMN_LOGIN + "=? "
				+ COLUMN_PASSWORD + "=? "
				+ COLUMN_FIRSTNAME + "=? "
				+ COLUMN_LASTNAME + "=? "
				+ COLUMN_BIRTHDATE + "=? "
				+ COLUMN_INFECTED + "=? "
				+ COLUMN_CONTACT + "=? "
				+ COLUMN_ADMIN + "=? "
				+ "WHERE " + COLUMN_ID + "=?";
		Object[] params = {e.getLogin(), e.getPassword(), e.getFirstName(), e.getLastName(), e.getBirthDate(), e.isInfected(), e.isContact(), e.isAdmin(), e.getId()};
		int id = DBConnector.getInstance().insertQuery(sql, params);
		return id != -1;
	}
	
	/**
	 * Va insérer les données du user dans la base
	 * @param e User à insérer
	 * @return true si tout ce passe bien 
	 */
	private boolean insert(User e) {
		String sql = "INSERT INTO " + TABLE_NAME + "( "
				+ COLUMN_LOGIN + ", "
				+ COLUMN_PASSWORD + ", "
				+ COLUMN_FIRSTNAME + ", "
				+ COLUMN_LASTNAME + ", "
				+ COLUMN_BIRTHDATE + ", "
				+ COLUMN_INFECTED + ", "
				+ COLUMN_CONTACT + ", "
				+ COLUMN_ADMIN + ") "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		Object[] params = {e.getLogin(), e.getPassword(), e.getFirstName(), e.getLastName(), e.getBirthDate(), e.isInfected(), e.isContact(), e.isAdmin()};
		int id = DBConnector.getInstance().insertQuery(sql, params);
		try {
			e.setId(id);
		} catch (EntityException e1) {
			e1.printStackTrace();
		}
		return id != -1;
	}
	
	/**
	 * Permet de récupérer un utilisateur avec son login et son mot de passe. Ne doit être utilisé que dans le cadre de la connexion à un compte.
	 * @param login de l'utilisateur
	 * @param password de l'utilisateur
	 * @return Retourne l'utilisateur qui correspond à ces données ou null si aucun utilisateur n'est trouvé
	 * @throws SQLException Si une erreur SQL se produit
	 */
	public User login(String login, String password) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_LOGIN + "=? AND " + COLUMN_PASSWORD + "=?";
		Object[] params = {login, password};
		ResultSet rs = DBConnector.getInstance().executeQuery(sql, params);
		User user = null;
		while (rs.next()) {
			try {
				user = new User();
				user.setId(rs.getInt(COLUMN_ID));
				user.setLogin(rs.getString(COLUMN_LOGIN));
				user.setPassword(rs.getString(COLUMN_PASSWORD));
				user.setFirstName(rs.getString(COLUMN_FIRSTNAME));
				user.setLastName(rs.getString(COLUMN_LASTNAME));
				user.setBirthDate(rs.getDate(COLUMN_BIRTHDATE));
				user.setInfected(rs.getBoolean(COLUMN_INFECTED));
				user.setContact(rs.getBoolean(COLUMN_CONTACT));
				user.setAdmin(rs.getBoolean(COLUMN_ADMIN));
			} catch (EntityException e) {
				e.printStackTrace();
				user = null;
			}
		}
		return user;
	}
}
