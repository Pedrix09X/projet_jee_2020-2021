package entities;

import java.sql.Date;

import exception.EntityException;

public class Notification implements Entity {

	private int id;
	private String text;
	private Date receivedDate;
	private User user;

	
	public Notification() {
		this.id = -1;
		this.text = "";
		this.receivedDate = new Date(0);
		this.user = null;
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

	@Override
	public String toString() {
		return "ID : " + this.id + ", TEXT : " + this.text;
	}
}