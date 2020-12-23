package entities;

import exception.EntityException;

public class Location implements Entity {

	private int id;
	private String name;
	private String address;
	private String gps;

	
	public Location() {
		this.id = -1;
		this.name = "";
		this.address = "";
		this.gps = "";
	}


	public int getId() {
		return id;
	}

	/**
	 * Change l'id du lieu.
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

	public String getName() {
		return name;
	}

	/**
	 * Change le nom du lieu.
	 * @param name : le nouveau nom. Le nom doit contenir au maximum 100 caractères.
	 * @throws EntityException si le nom fait plus de 100 caractères.
	 */
	public void setName(String name) throws EntityException {
		if (name.length() > 100) {
			throw new EntityException("Length of 'name' must be lower than 100");
		} else {
			this.name = name;
		}
	}

	public String getAddress() {
		return address;
	}

	/**
	 * Change l'adresse du lieu.
	 * @param name : la nouvelle adresse. L'adresse doit contenir au maximum 100 caractères.
	 * @throws EntityException si l'adresse fait plus de 100 caractères.
	 */
	public void setAddress(String address) throws EntityException {
		if (address.length() > 100) {
			throw new EntityException("Length of 'address' must be lower than 100");
		} else {
			this.address = address;
		}
	}

	public String getGPS() {
		return gps;
	}

	/**
	 * Change les coordonnées GPS du lieu.
	 * @param name : les nouvelles coordonnées GPS. Les coordonnées GPS doivent contenir au maximum 100 caractères.
	 * @throws EntityException si les coordonnées GPS font plus de 100 caractères.
	 */
	public void setGPS(String gps) throws EntityException {
		if (gps.length() > 100) {
			throw new EntityException("Length of 'gps' must be lower than 100");
		} else {
			this.gps = gps;
		}
	}

	@Override
	public String toString() {
		return "ID : " + this.id + ", TITLE : " + this.name;
	}
}