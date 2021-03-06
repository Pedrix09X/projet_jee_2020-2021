package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.Activity;
import entities.Entity;
import entities.Location;
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
		if (rs != null && rs.next()) {
			user = new User();
			try {
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
					user.setId(rs.getInt(COLUMN_ID));
					user.setLogin(rs.getString(COLUMN_LOGIN));
					user.setPassword(rs.getString(COLUMN_PASSWORD));
					user.setFirstName(rs.getString(COLUMN_FIRSTNAME));
					user.setLastName(rs.getString(COLUMN_LASTNAME));
					user.setBirthDate(rs.getDate(COLUMN_BIRTHDATE));
					user.setInfected(rs.getBoolean(COLUMN_ID));
					user.setContact(rs.getBoolean(COLUMN_CONTACT));
					user.setAdmin(rs.getBoolean(COLUMN_ADMIN));
					users.add(user);
				} catch (EntityException e) {
					e.printStackTrace();
				}
			}
			rs.close();
		}
		return users;
	}
	
	public User getByLogin(String login) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_LOGIN + "=?";
		ResultSet rs = DBConnector.getInstance().executeQuery(sql, new Object[] {login});
		
		User user = null;
		if (rs != null && rs.next()) {
			user = new User();
			try {
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
			}
		}
		return user;
	}
	
	/**
	 * Cherche dans la BDD les utilisateur qui ont un nom d'utilisateur qui comencent par le paramêtre.
	 * @param partialLogin Nom d'utilisateur partiel pour la recherche
	 * @return la liste des nom d'utilisateurs trouvés
	 */
	public List<String> findLogins(String partialLogin) {
		String sql = String.format("SELECT %s FROM %s WHERE %s LIKE ? ORDER BY %s",
				COLUMN_LOGIN, TABLE_NAME, COLUMN_LOGIN, COLUMN_LOGIN);
		ResultSet rs = DBConnector.getInstance().executeQuery(sql, new Object[] {partialLogin+"%"});
		
		List<String> logins = new ArrayList<String>();
		try {
			if (rs != null) {
				while (rs.next()) 
					logins.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logins;
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
				+ COLUMN_LOGIN + "=?, "
				+ COLUMN_PASSWORD + "=?, "
				+ COLUMN_FIRSTNAME + "=?, "
				+ COLUMN_LASTNAME + "=?, "
				+ COLUMN_BIRTHDATE + "=?, "
				+ COLUMN_INFECTED + "=?, "
				+ COLUMN_CONTACT + "=?, "
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
	
	/**
	 * Permet de verifier si un nom d'utilisateur est déjà utilisé par un autre utilisateur
	 * @param login Le nom d'utilisateur à tester
	 * @return true si le nom d'utilisateur est unique
	 */
	public boolean testLogin(String login) {
		String sql = "SELECT * FROM User";
		ResultSet res = DBConnector.getInstance().executeQuery(sql);
		boolean unique = true;
		try {
			if (res != null) {
				while (res.next() && unique) {
					if (login.equals(res.getString(COLUMN_LOGIN))) {
						unique = false;
					}
				}
			}
		} catch (SQLException e) {}
		return unique;
	}

	/**
	 * Marque l'utilisateur comme infecté et cherche tous les cas contacts.
	 * @param user Utilisateur à infecté
	 * @return true si l'opération à reussi
	 */
	public boolean markUserAsInfected(User user) {
		boolean done = false;
		
		// On récupère les activités que l'utilisateur a déclarées les dix jours précédents
		ArrayList<Activity> activities = new ArrayList<Activity>();
		String getUserActivities = "SELECT * FROM " + ActivityTable.TABLE_NAME
				+ " WHERE " + ActivityTable.TABLE_NAME + "." + ActivityTable.COLUMN_USER + " = ? "
				+ " AND " + ActivityTable.TABLE_NAME + "." + ActivityTable.COLUMN_STARTDATE + " > CURRENT_TIMESTAMP() - INTERVAL 10 DAY;";
		ResultSet rs = DBConnector.getInstance().executeQuery(getUserActivities, new Object[] {user.getId()});

		Activity activity;
		try {
			while(rs.next()) {
				activity = new Activity();
				try {
					activity.setId(rs.getInt(ActivityTable.COLUMN_ID));
					activity.setTitle(rs.getString(ActivityTable.COLUMN_TITLE));
					activity.setStartDate(rs.getTimestamp(ActivityTable.COLUMN_STARTDATE));
					activity.setEndDate(rs.getTimestamp(ActivityTable.COLUMN_ENDDATE));
					activity.setLocation(TableLocator.getLocationTable().getByID(rs.getInt(ActivityTable.COLUMN_LOCATION)));
					activity.setUser(user);
					activities.add(activity);
				} catch (EntityException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Pour chaque activité, on récupère les id des utilisateurs ayant effectué une activité dans le même lieu et sur les mêmes plages horaires
		Set<Integer> idContacts = new HashSet<Integer>();
		for (Activity a : activities) {
			String getCasContacts = "SELECT DISTINCT " + ActivityTable.TABLE_NAME + "." + ActivityTable.COLUMN_USER + " FROM " + ActivityTable.TABLE_NAME
					+ " WHERE " + ActivityTable.TABLE_NAME + "." + ActivityTable.COLUMN_USER + " != ?"
					+ " AND " + ActivityTable.TABLE_NAME + "." + ActivityTable.COLUMN_LOCATION + " = ? "
					+ " AND (" + ActivityTable.TABLE_NAME + "." + ActivityTable.COLUMN_STARTDATE + " BETWEEN ? " + " AND ? "
					+ " OR " + ActivityTable.TABLE_NAME + "." + ActivityTable.COLUMN_ENDDATE + " BETWEEN ? " + " AND ? )";
			ResultSet utils = DBConnector.getInstance().executeQuery(getCasContacts, new Object[] {a.getUser().getId(), a.getLocation().getId(), a.getStartDate(), a.getEndDate(), a.getStartDate(), a.getEndDate()});
			try {
				while (utils.next()) {
					idContacts.add(utils.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// On ajoute les amis de l'utilisateur à la liste des cas contacts
		TableLocator.getFriendTable().getFriendsOf(user);
		for (User u : user.getFriends()) {
			idContacts.add(u.getId());
		}

		// Parcours du résultat de la requête 
		try {
			User casContact;
			for (Integer i : idContacts) {
				// Récupération de l'utilisateur par l'ID
				casContact = this.getByID(i);
				if (casContact != null && casContact.getId() != user.getId()) {
					// Marquer comme cas contact et sauvegarder
					casContact.setContact(true);
					if (this.save(casContact)) {
						// Envoi de la notification à l'utilisateur
						TableLocator.getNotificationTable().sendNotificationTo(casContact, 
								"Une personne que vous avez rencontré ces 10 derniers a été infecté. Faites vous tester le plus vite possible !", 
								NotificationTable.CONTACT, "", null);
					}
				}
			}
			done = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			done = false;
		}
		
		// Si tout ce passe bien, on marque l'utilisateur infecté comme infecté et on sauvegarde
		if (done) {
			user.setInfected(done);
			try {
				this.save(user);
			} catch (SQLException e) {
				e.printStackTrace();
				done = false;
			}
		}
		return done;
	}

	public boolean delById(int id) {
		boolean done = false;
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?";
		done = -1 == DBConnector.getInstance().insertQuery(sql, new Object[] {id});
		return done;
	}
}