package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.Location;
import exception.EntityException;
import sql.DBConnector;

public class LocationTable implements Table {

	//--- Variables statiques décrivant la table Location ---//
	public static final int NOT_SAVED = -1;
	public static final String TABLE_NAME 		= "Location";
	public static final String COLUMN_ID 		= "id";
	public static final String COLUMN_NAME 		= "name";
	public static final String COLUMN_ADDRESS 	= "address";
	public static final String COLUMN_GPS 		= "gps";
	
	protected LocationTable() {}
	
	@Override
	public Location getByID(int id) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?";
		ResultSet rs = DBConnector.getInstance().executeQuery(sql, new Object[] {id});
		
		Location location = null;
		if (rs != null) {
			rs.next();
			location = new Location();
			try {
				location.setId(rs.getInt(1));
				location.setName(rs.getString(2));
				location.setAddress(rs.getString(3));
				location.setGPS(rs.getString(4));
			} catch (EntityException e) {
				e.printStackTrace();
			}
		}
		return location;
	}

	@Override
	public List<Entity> getAll() throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_NAME;
		ResultSet rs = DBConnector.getInstance().executeQuery(sql);
		
		ArrayList<Entity> locations = new ArrayList<Entity>();
		Location location = null;
		if (rs != null) {
			while (rs.next()) {
				location = new Location();
				try {
					location.setId(rs.getInt(1));
					location.setName(rs.getString(2));
					location.setAddress(rs.getString(3));
					location.setGPS(rs.getString(4));
					locations.add(location);
				} catch (EntityException e) {
					e.printStackTrace();
				}
			}
			rs.close();
		}
		return locations;
	}

	@Override
	public boolean save(Entity e) throws SQLException {
		if (e.getClass().equals(Location.class)) {
			Location location = (Location)e;
			if (location.getId() == NOT_SAVED) {
				return this.insert(location);
			} else {
				return this.update(location);
			}
		}
		return false;
	}
	
	/**
	 * Met à jour les données de la base.
	 * @param e : Location à mettre à jour.
	 * @return true si l'opération réussit.
	 */
	private boolean update(Location e) {
		String sql = "UPDATE " + TABLE_NAME + " SET "
				+ COLUMN_NAME + "=? "
				+ COLUMN_ADDRESS + "=? "
				+ COLUMN_GPS + "=? "
				+ "WHERE " + COLUMN_ID + "=?";
		Object[] params = {e.getName(), e.getAddress(), e.getGPS(), e.getId()};
		int id = DBConnector.getInstance().insertQuery(sql, params);
		return id != -1;
	}
	
	/**
	 * Insère les données du lieu dans la base.
	 * @param e : la Location à insérer
	 * @return true si l'opération réussit.
	 */
	private boolean insert(Location e) {
		String sql = "INSERT " + TABLE_NAME + "( "
				+ COLUMN_NAME + ", "
				+ COLUMN_ADDRESS + ", "
				+ COLUMN_GPS + ") "
				+ "VALUES(?,?,?)";
		Object[] params = {e.getName(), e.getAddress(), e.getGPS()};
		int id = DBConnector.getInstance().insertQuery(sql, params);
		try {
			e.setId(id);
		} catch (EntityException e1) {
			e1.printStackTrace();
		}
		return id != -1;
	}
}
