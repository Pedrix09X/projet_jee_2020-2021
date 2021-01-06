package entities;

import java.sql.Date;

import exception.EntityException;
import tables.NotificationTable;

public class Notification implements Entity {

	private int id;
	private String text;
	private Date receivedDate;
	private boolean seen;
	private int type;
	private String action;
	private User user;
	private User friend;

	
	public Notification() {
		this.id = -1;
		this.text = "";
		this.receivedDate = new Date(0);
		this.seen = false;
		this.type = 0;
		this.action = "";
		this.user = null;
		this.friend = null;
	}


	public int getId() {
		return id;
	}

	/**
	 * Change l'id de la notification.
	 * @param id : le nouvel id. Doit être supérieur à 0.
	 * @throws EntityException si l'id est inférieur ou égal à 0.
	 */
	public void setId(int id) throws EntityException {
		if (id < 1) {
			throw new EntityException("Id must be greater than 0.");
		} else {
			this.id = id;
		}
	}

	public String getText() {
		return text;
	}

	/**
	 * Change le texte de la notification.
	 * @param name : le nouveau texte. Le texte doit contenir au maximum 255 caractères.
	 * @throws EntityException si le texte fait plus de 255 caractères.
	 */
	public void setText(String text) throws EntityException {
		if (text.length() > 255) {
			throw new EntityException("Length of 'text' must be lower than 100");
		} else {
			this.text = text;
		}
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 * Change la date de réception de la notification.
	 * @param name : la nouvelle date de réception.
	 */
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public boolean isSeen() {
		return seen;
	}


	public void setSeen(boolean seen) {
		this.seen = seen;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public User getUser() {
		return user;
	}

	/**
	 * Change l'utilisateur qui a reçu la notification.
	 * @param user : le nouvel utilisateur.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}


	/**
	 * Joint à la notification, l'utilisateur qui fait la demande d'ami. Si l'utilisateur n'est pas null,
	 * cela changera automatiquement le type de notification à NotificationTable.ASK_FRIEND
	 * @param friend 
	 */
	public void setFriend(User friend) {
		this.friend = friend;
		if (friend != null) {
			this.setType(NotificationTable.ASK_FRIEND);
		}
	}


	@Override
	public String toString() {
		return "ID : " + this.id + ", TEXT : " + this.text;
	}
}