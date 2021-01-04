package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Entity;
import entities.Notification;
import entities.User;
import exception.EntityException;
import sql.DBConnector;

public class NotificationTable implements Table {

	//--- Variables statiques décrivant la table Notification ---//
	public static final int NOT_SAVED = -1;
	public static final String TABLE_NAME 			= "Notification";
	public static final String COLUMN_ID 			= "id";
	public static final String COLUMN_TEXT 			= "text";
	public static final String COLUMN_RECEIVEDDATE 	= "receivedDate";
	public static final String COLUMN_USER 			= "user";
	
	protected NotificationTable() {}
	
	@Override
	public Notification getByID(int id) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?";
		ResultSet rs = DBConnector.getInstance().executeQuery(sql, new Object[] {id});
		
		Notification notification = null;
		UserTable userTable = new UserTable();
		if (rs != null) {
			rs.next();
			notification = new Notification();
			try {
				notification.setId(rs.getInt(1));
				notification.setText(rs.getString(2));
				notification.setReceivedDate(rs.getDate(3));
				notification.setUser(userTable.getByID(rs.getInt(4)));
			} catch (EntityException e) {
				e.printStackTrace();
			}
		}
		return notification;
	}

	@Override
	public List<Entity> getAll() throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME;
		ResultSet rs = DBConnector.getInstance().executeQuery(sql);
		
		ArrayList<Entity> notifications = new ArrayList<Entity>();
		Notification notification = null;
		UserTable userTable = new UserTable();
		if (rs != null) {
			while (rs.next()) {
				notification = new Notification();
				try {
					notification.setId(rs.getInt(1));
					notification.setText(rs.getString(2));
					notification.setReceivedDate(rs.getDate(3));
					notification.setUser(userTable.getByID(rs.getInt(4)));
					notifications.add(notification);
				} catch (EntityException e) {
					e.printStackTrace();
				}
			}
			rs.close();
		}
		return notifications;
	}

	@Override
	public boolean save(Entity e) throws SQLException {
		if (e.getClass().equals(Notification.class)) {
			Notification notification = (Notification)e;
			if (notification.getId() == NOT_SAVED) {
				return this.insert(notification);
			} else {
				return this.update(notification);
			}
		}
		return false;
	}
	
	/**
	 * Met à jour les données de la base.
	 * @param e : Notification à mettre à jour.
	 * @return true si l'opération réussit.
	 */
	private boolean update(Notification e) {
		String sql = "UPDATE " + TABLE_NAME + " SET "
				+ COLUMN_TEXT + "=? "
				+ COLUMN_RECEIVEDDATE + "=? "
				+ COLUMN_USER + "=? "
				+ "WHERE " + COLUMN_ID + "=?";
		Object[] params = {e.getText(), e.getReceivedDate(), e.getUser().getId(), e.getId()};
		int id = DBConnector.getInstance().insertQuery(sql, params);
		return id != -1;
	}
	
	/**
	 * Insère les données de la notification dans la base.
	 * @param e : la Notification à insérer
	 * @return true si l'opération réussit.
	 */
	private boolean insert(Notification e) {
		String sql = "INSERT " + TABLE_NAME + "( "
				+ COLUMN_TEXT + ", "
				+ COLUMN_USER + ") "
				+ "VALUES(?,?)";
		Object[] params = {e.getText(), e.getUser().getId()};
		int id = DBConnector.getInstance().insertQuery(sql, params);
		try {
			e.setId(id);
		} catch (EntityException e1) {
			e1.printStackTrace();
		}
		return id != -1;
	}
	
	/**
	 * Enregistre une notification dans la BDD pour l'utilisateur.
	 * @param user Utilisateur à qui envoyer la notification
	 * @param text Contenu de la notification
	 * @return true si l'opération reussi.
	 */
	public boolean sendNotificationTo(User user, String text) {
		Notification notif = new Notification();
		boolean done = true;
		try {
			notif.setText(text);
			notif.setUser(user);
			done = this.save(notif);
		} catch (Exception e) {
			e.printStackTrace();
			done = false;
		}
		
		return done;
	}
}