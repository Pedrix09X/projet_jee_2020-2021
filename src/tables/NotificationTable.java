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
	public static final String COLUMN_SEEN			= "seen";
	public static final String COLUMN_TYPE			= "type";
	public static final String COLUMN_ACTION		= "action";
	public static final String COLUMN_USER 			= "user";
	public static final String COLUMN_FRIEND		= "friend";
	
	//--- Variables statiques décrivant le type de notification ---//
	public static final int DEFAULT 	= 0;
	public static final int ASK_FRIEND 	= 1;
	public static final int CONTACT 	= 2;
	
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
				notification.setId(rs.getInt(COLUMN_ID));
				notification.setText(rs.getString(COLUMN_TEXT));
				notification.setReceivedDate(rs.getDate(COLUMN_RECEIVEDDATE));
				notification.setSeen(rs.getBoolean(COLUMN_SEEN));
				notification.setType(rs.getInt(COLUMN_TYPE));
				notification.setAction(rs.getString(COLUMN_ACTION));
				notification.setUser(userTable.getByID(rs.getInt(COLUMN_USER)));
				notification.setFriend(userTable.getByID(rs.getInt(COLUMN_FRIEND)));
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
					notification.setId(rs.getInt(COLUMN_ID));
					notification.setText(rs.getString(COLUMN_TEXT));
					notification.setReceivedDate(rs.getDate(COLUMN_RECEIVEDDATE));
					notification.setSeen(rs.getBoolean(COLUMN_SEEN));
					notification.setType(rs.getInt(COLUMN_TYPE));
					notification.setAction(rs.getString(COLUMN_ACTION));
					notification.setUser(userTable.getByID(rs.getInt(COLUMN_USER)));
					notification.setFriend(userTable.getByID(rs.getInt(COLUMN_FRIEND)));
					notifications.add(notification);
				} catch (EntityException e) {
					e.printStackTrace();
				}
			}
			rs.close();
		}
		return notifications;
	}

	public List<Notification> getByUser(User user) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USER + "=? ORDER BY " + COLUMN_SEEN + " ASC, " + COLUMN_RECEIVEDDATE + " DESC";
		ResultSet rs = DBConnector.getInstance().executeQuery(sql, new Object[] {user.getId()});
		
		ArrayList<Notification> notifications = new ArrayList<Notification>();
		Notification notification = null;
		UserTable userTable = new UserTable();
		if (rs != null) {
			while (rs.next()) {
				notification = new Notification();
				try {
					notification.setId(rs.getInt(COLUMN_ID));
					notification.setText(rs.getString(COLUMN_TEXT));
					notification.setReceivedDate(rs.getDate(COLUMN_RECEIVEDDATE));
					notification.setSeen(rs.getBoolean(COLUMN_SEEN));
					notification.setType(rs.getInt(COLUMN_TYPE));
					notification.setAction(rs.getString(COLUMN_ACTION));
					notification.setUser(userTable.getByID(rs.getInt(COLUMN_USER)));
					notification.setFriend(userTable.getByID(rs.getInt(COLUMN_FRIEND)));
					notifications.add(notification);
				} catch (EntityException e) {
					e.printStackTrace();
				}
			}
			rs.close();
		}
		return notifications;
	}
	
	/**
	 * Retourne le nombre de notifications non lu par l'utilisateur
	 * @param user utilisateur dont le notification sont à compter
	 * @return le nombre de notification non lu
	 */
	public int getCount(User user) {
		String sql = "SELECT count(id) FROM " + TABLE_NAME + " WHERE " + COLUMN_USER + "=? AND " + COLUMN_SEEN + "=false";
		ResultSet rs = DBConnector.getInstance().executeQuery(sql, new Object[] {user.getId()});
		
		int count = 0;
		try {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			count = 0;
		}
		
		return count;
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
				+ COLUMN_TEXT + "=?, "
				+ COLUMN_RECEIVEDDATE + "=?, "
				+ COLUMN_SEEN + "=?, "
				+ COLUMN_TYPE + "=?, "
				+ COLUMN_ACTION + "=?, "
				+ COLUMN_USER + "=?, "
				+ COLUMN_FRIEND + "=? "
				+ "WHERE " + COLUMN_ID + "=?";
		Object friendID = null;
		if (e.getFriend() != null)
			friendID = e.getFriend().getId();
		Object[] params = {e.getText(), e.getReceivedDate(), e.isSeen(), e.getType(), e.getAction(), e.getUser().getId(), friendID, e.getId()};
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
				+ COLUMN_RECEIVEDDATE + ", "
				+ COLUMN_SEEN + ", "
				+ COLUMN_TYPE + ", "
				+ COLUMN_ACTION + ", "
				+ COLUMN_USER + ", "
				+ COLUMN_FRIEND + ") "
				+ "VALUES(?,?,?,?,?,?,?)";
		Object friend = null;
		if (e.getFriend() != null) {
			friend = e.getFriend().getId();
		}
		Object[] params = {e.getText(), e.getReceivedDate(), e.isSeen(), e.getType(), e.getAction(), e.getUser().getId(), friend};
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
		return sendNotificationTo(user, text, DEFAULT, "", null);
	}
	
	/**
	 * Enregistre une notification dans la BDD pour l'utilisateur.
	 * @param user Utilisateur à qui envoyer la notification
	 * @param text Contenu de la notification
	 * @param type Spécifie le type de notification parmi Type.Default, Type.Contact, Type.Ask_Friend
	 * @param friend Indique la personne qui est a l'origine de la notification si c'est une demande d'ami. Doit être null dans le cas contraire.
	 * @return true si l'opération reussi.
	 */
	public boolean sendNotificationTo(User user, String text, int type, String act, User friend) {
		Notification notif = new Notification();
		boolean done = true;
		try {
			notif.setText(text);
			notif.setUser(user);
			notif.setType(type);
			notif.setAction(act);
			notif.setFriend(friend);
			done = this.save(notif);
		} catch (Exception e) {
			e.printStackTrace();
			done = false;
		}
		
		return done;
	}

	/**
	 * Marque la notification avec l'id id comme lu
	 * @param id ID de la notification à marquer
	 * @return true si tout se passe bien 
	 */
	public boolean markAsSeen(int id) {
		boolean done = false;
		try {
			Notification notif = this.getByID(id);
			if (notif != null) {
				notif.setSeen(true);
				done = this.save(notif);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}
}