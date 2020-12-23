package entities;

import java.sql.Date;
import java.sql.SQLException;

import exception.EntityException;

public class User implements Entity {

	private int id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private boolean infected;
	private boolean contact;
	private boolean admin;

	public int getId() {
		return id;
	}

	/**
	 * Change l'id de l'utilisateur
	 * @param id nouvel id. Doit être supérieur à 0
	 * @throws EntityException si l'id est inférieur ou égal à 0
	 */
	public void setId(int id) throws EntityException {
		if (id < 1) {
			throw new EntityException("Id must be greater then 0.");
		} else {
			this.id = id;
		}
	}

	public String getLogin() {
		return login;
	}

	/**
	 * Change le login de l'utilisateur
	 * @param login nouveau login. Le login doit contenir maximum 20 caractères
	 * @throws EntityException si le login fait plus de 20 caractères
	 */
	public void setLogin(String login) throws EntityException {
		if (login.length() > 20) {
			throw new EntityException("Length of 'login' must be lower then 20");
		} else {
			this.login = login;
		}
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Change le mot de passe de l'utilisateur
	 * @param login nouveau mot de passe. Le mot de passe doit contenir maximum 50 caractères
	 * @throws EntityException si le mot de passe fait plus de 50 caractères
	 */
	public void setPassword(String password) throws EntityException {
		if (login.length() > 20) {
			throw new EntityException("Length of 'password' must be lower then 50");
		} else {
			this.password = password;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	/**
	 * Change le prénom de l'utilisateur
	 * @param login nouveau prénom. Le prénom doit contenir maximum 20 caractères
	 * @throws EntityException si le prénom fait plus de 20 caractères
	 */
	public void setFirstName(String firstName) throws EntityException {
		if (login.length() > 20) {
			throw new EntityException("Length of 'firstname' must be lower then 20");
		} else {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return lastName;
	}

	/**
	 * Change le nom de l'utilisateur
	 * @param login nouveau nom. Le nom doit contenir maximum 20 caractères
	 * @throws EntityException si le nom fait plus de 20 caractères
	 */
	public void setLastName(String lastName) throws EntityException {
		if (login.length() > 20) {
			throw new EntityException("Length of 'lastname' must be lower then 20");
		} else {
			this.lastName = lastName;
		}
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isInfected() {
		return infected;
	}

	public void setInfected(boolean infected) {
		this.infected = infected;
	}

	public boolean isContact() {
		return contact;
	}

	public void setContact(boolean contact) {
		this.contact = contact;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + ", LOGIN: " + this.login;
	}
}
