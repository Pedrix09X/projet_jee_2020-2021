package entities;

import java.sql.Timestamp;

import exception.EntityException;

public class Activity implements Entity {
	
	private int id;
	private String title;
	private Timestamp startDate;
	private Timestamp endDate;
	private Location location;
	private User user;

	
	public Activity() {
		this.id = -1;
		this.title = "";
		this.startDate = new Timestamp(0);
		this.endDate = new Timestamp(0);
		this.location = null;
		this.user = null;
	}


	public int getId() {
		return id;
	}

	/**
	 * Change l'id de l'activité.
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

	public String getTitle() {
		return title;
	}

	/**
	 * Change le titre de l'activité.
	 * @param title : le nouveau titre. Le titre doit contenir maximum 100 caractères.
	 * @throws EntityException si le titre fait plus de 100 caractères.
	 */
	public void setTitle(String title) throws EntityException {
		if (title.length() > 100) {
			throw new EntityException("Length of 'title' must be lower than 100");
		} else {
			this.title = title;
		}
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * Change la date de début de l'activité.
	 * @param timestamp : la nouvelle date  de début.
	 */
	public void setStartDate(Timestamp timestamp) {
		this.startDate = timestamp;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * Change la date de fin de l'activité
	 * @param timestamp : la nouvelle date. Elle doit être postérieure à la date de début de l'activité.
	 * @throws EntityException si la date n'est pas postérieure à la date de début de l'activité.
	 */
	public void setEndDate(Timestamp timestamp) throws EntityException {
		if (timestamp.before(startDate)) {
			throw new EntityException("'endDate' must come after 'startDate'");
		} else {
			this.endDate = timestamp;
		}
	}

	public Location getLocation() {
		return location;
	}

	/**
	 * Change le lieu de l'activité.
	 * @param location : le nouveau lieu. Doit être supérieur à zéro.
	 * @throws EntityException si le lieu est inférieur ou égal à 0.
	 */
	public void setLocation(Location location) throws EntityException {
		this.location = location;
	}
	
	public User getUser() {
		return user;
	}

	/**
	 * Change l'utilisateur auquel appartient l'activité.
	 * @param user  : le nouvel utilisateur.
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "ID : " + this.id + ", TITLE : " + this.title;
	}
}