package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Activity;
import entities.Entity;
import entities.User;
import exception.EntityException;
import sql.DBConnector;

public class ActivityTable implements Table {

	//--- Variables statiques décrivant la table Activity ---//
	public static final int NOT_SAVED = -1;
	public static final String TABLE_NAME 			= "Activity";
	public static final String COLUMN_ID 			= "id";
	public static final String COLUMN_TITLE 		= "title";
	public static final String COLUMN_STARTDATE 	= "startDate";
	public static final String COLUMN_ENDDATE 		= "endDate";
	public static final String COLUMN_LOCATION 		= "location";
	public static final String COLUMN_USER			= "user";
	
	protected ActivityTable() {}
	
	@Override
	public Activity getByID(int id) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?";
		ResultSet rs = DBConnector.getInstance().executeQuery(sql, new Object[] {id});
		
		Activity activity = null;
		UserTable userTable = new UserTable();
		LocationTable locationTable = new LocationTable();
		if (rs != null) {
			rs.next();
			activity = new Activity();
			try {
				activity.setId(rs.getInt(COLUMN_ID));
				activity.setTitle(rs.getString(COLUMN_TITLE));
				activity.setStartDate(rs.getTimestamp(COLUMN_STARTDATE));
				activity.setEndDate(rs.getTimestamp(COLUMN_ENDDATE));
				activity.setLocation(locationTable.getByID(rs.getInt(COLUMN_LOCATION)));
				activity.setUser(userTable.getByID(rs.getInt(COLUMN_USER)));
			} catch (EntityException e) {
				e.printStackTrace();
			}
		}
		return activity;
	}

	@Override
	public List<Entity> getAll() throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME;
		ResultSet rs = DBConnector.getInstance().executeQuery(sql);
		
		ArrayList<Entity> activities = new ArrayList<Entity>();
		Activity activity = null;
		UserTable userTable = new UserTable();
		LocationTable locationTable = new LocationTable();
		if (rs != null) {
			while (rs.next()) {
				activity = new Activity();
				try {
					activity.setId(rs.getInt(COLUMN_ID));
					activity.setTitle(rs.getString(COLUMN_TITLE));
					activity.setStartDate(rs.getTimestamp(COLUMN_STARTDATE));
					activity.setEndDate(rs.getTimestamp(COLUMN_ENDDATE));
					activity.setLocation(locationTable.getByID(rs.getInt(COLUMN_LOCATION)));
					activity.setUser(userTable.getByID(rs.getInt(COLUMN_USER)));
					activities.add(activity);
				} catch (EntityException e) {
					e.printStackTrace();
				}
			}
			rs.close();
		}
		return activities;
	}
	
	/**
	 * Retourne toutes les activités d'un utilisateur.
	 * @param user Utilisateur dont les activités sont à retourner.
	 * @return liste d'activités. La liste peut être vide. Null si user est null.
	 * @throws SQLException Si une erreur SQL se produit
	 */
	public List<Activity> getFromUser(User user) throws SQLException {
		ArrayList<Activity> activities = null;
		if (user != null) {
			activities = new ArrayList<Activity>();
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USER + "=? ORDER BY " + COLUMN_ENDDATE + " DESC";
			ResultSet rs = DBConnector.getInstance().executeQuery(sql, new Object[] {user.getId()});
			
			Activity activity = null;
			LocationTable locationTable = TableLocator.getLocationTable();
			
			while (rs.next()) {
				activity = new Activity();
				try {
					activity.setId(rs.getInt(COLUMN_ID));
					activity.setTitle(rs.getString(COLUMN_TITLE));
					activity.setStartDate(rs.getTimestamp(COLUMN_STARTDATE));
					activity.setEndDate(rs.getTimestamp(COLUMN_ENDDATE));
					activity.setLocation(locationTable.getByID(rs.getInt(COLUMN_LOCATION)));
					activity.setUser(user);
					activities.add(activity);
				} catch (EntityException e) {
					e.printStackTrace();
				}
			}
		}
		return activities;
	}

	public boolean delById(int id) {
		boolean done = false;
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?";
		done = -1 == DBConnector.getInstance().insertQuery(sql, new Object[] {id});
		return done;
	}
	
	@Override
	public boolean save(Entity e) throws SQLException {
		if (e.getClass().equals(Activity.class)) {
			Activity activity = (Activity)e;
			if (activity.getId() == NOT_SAVED) {
				return this.insert(activity);
			} else {
				return this.update(activity);
			}
		}
		return false;
	}
	
	/**
	 * Met à jour les données de la base.
	 * @param e : Activity à mettre à jour.
	 * @return true si l'opération réussit.
	 */
	private boolean update(Activity e) {
		String sql = "UPDATE " + TABLE_NAME + " SET "
				+ COLUMN_TITLE + "=? "
				+ COLUMN_STARTDATE + "=? "
				+ COLUMN_ENDDATE + "=? "
				+ COLUMN_LOCATION + "=? "
				+ COLUMN_USER + "=? "
				+ "WHERE " + COLUMN_ID + "=?";
		Object[] params = {e.getTitle(), e.getStartDate(), e.getEndDate(), e.getLocation(), e.getUser().getId(), e.getId()};
		int id = DBConnector.getInstance().insertQuery(sql, params);
		return id != -1;
	}
	
	/**
	 * Insère les données de l'activité dans la base.
	 * @param e : l'Activity à insérer
	 * @return true si l'opération réussit.
	 */
	private boolean insert(Activity e) {
		String sql = "INSERT " + TABLE_NAME + "( "
				+ COLUMN_TITLE + ", "
				+ COLUMN_STARTDATE + ", "
				+ COLUMN_ENDDATE + ", "
				+ COLUMN_LOCATION + ", "
				+ COLUMN_USER + ") "
				+ "VALUES(?,?,?,?,?)";
		Object[] params = {e.getTitle(), e.getStartDate(), e.getEndDate(), e.getLocation().getId(), e.getUser().getId()};
		int id = DBConnector.getInstance().insertQuery(sql, params);
		try {
			e.setId(id);
		} catch (EntityException e1) {
			e1.printStackTrace();
		}
		return id != -1;
	}
}