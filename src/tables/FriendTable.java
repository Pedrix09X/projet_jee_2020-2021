package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.User;
import sql.DBConnector;

public class FriendTable {

	public static final String TABLE_NAME		= "Friends";
	public static final String COLUMN_USER		= "user";
	public static final String COLUMN_FRIEND	= "friend";
	
	protected FriendTable() {}
	
	/**
	 * Récupère depuis la base de donnée, tous les amis de l'utilisateur et l'ajoute a sa liste d'amis.
	 * Cet utilisateur doit être enregistré dans la base, donc avoir un ID différent de -1.
	 * @param user Utilisateur dont les amis sont à récupérer.
	 */
	public void getFriendsOf(User user) {
		ArrayList<User> friends = new ArrayList<User>();
		if (user.getId() != UserTable.NOT_SAVED) {
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USER + "=?";
			ResultSet rs = DBConnector.getInstance().executeQuery(sql, new Object[] {user.getId()});
			try {
				UserTable table = new UserTable();
				while (rs.next()) {
					int friendID = rs.getInt(2);
					friends.add(table.getByID(friendID));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				friends = null;
			}
		}
		user.setFriends(friends);
	}
	
	/**
	 * Ajoute un amis a la liste d'amis de l'utilisateur et enregistre la donnée dans la base.
	 * @param user Utilisateur a qui l'ami doit être enregistré
	 * @param friend Ami à ajouter
	 */
	public boolean addFriend(User user, User friend) {
		boolean done = false;
		if (user != null && friend != null) {
			String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?,?)";
			DBConnector.getInstance().insertQuery(sql, new Object[] {user.getId(), friend.getId()});
			user.getFriends().add(friend);
			done = true;
		}
		
		return done;
	}

}
