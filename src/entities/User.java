package entities;

import java.sql.Date;
import java.util.ArrayList;

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
	private ArrayList<User> friends;

	public User() {
		this.id = -1;
		this.login = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.birthDate = new Date(0);
		this.infected = false;
		this.contact = false;
		this.admin = false;
		this.friends = new ArrayList<User>();
	}
	
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
			throw new EntityException("Id must be greater than 0.");
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
			throw new EntityException("Length of 'login' must be lower than 20");
		} else {
			this.login = login;
		}
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Change le mot de passe de l'utilisateur
	 * @param password nouveau mot de passe
	 */
	public void setPassword(String password){
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	/**
	 * Change le prénom de l'utilisateur
	 * @param firstName nouveau prénom. Le prénom doit contenir maximum 20 caractères
	 * @throws EntityException si le prénom fait plus de 20 caractères
	 */
	public void setFirstName(String firstName) throws EntityException {
		if (firstName.length() > 20) {
			throw new EntityException("Length of 'firstname' must be lower than 20");
		} else {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return lastName;
	}

	/**
	 * Change le nom de l'utilisateur
	 * @param lastName nouveau nom. Le nom doit contenir maximum 20 caractères
	 * @throws EntityException si le nom fait plus de 20 caractères
	 */
	public void setLastName(String lastName) throws EntityException {
		if (lastName.length() > 20) {
			throw new EntityException("Length of 'lastname' must be lower than 20");
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
	
	public ArrayList<User> getFriends() {
		return friends;
	}

	/**
	 * Définit la liste d'amis de l'utilisateur.
	 * @param friends liste d'amis. Si null, ne fait rien.
	 */
	public void setFriends(ArrayList<User> friends) {
		if (friends != null) {
			this.friends = friends;
		}
	}

	@Override
	public String toString() {
		return "ID: " + this.id + ", LOGIN: " + this.login;
	}
}