package sql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	
	private static DBConnector instance;
	
	// Données de connection
	private final String user = "root";
	private final String pass = "root";
	private final String host = "localhost";
	private final String base = "antiCovid";
	private final String url = "jdbc:mysql://" + host + ":3306/" + base;
	private Connection connection = null;
	
	private DBConnector () {
		try {
			// Création de la connection
		    this.connection = DriverManager.getConnection(url, user, pass);
		} catch ( SQLException e ) {
			System.out.println("Erreur lors de la connection à la BDD");
			e.printStackTrace();
		}
	}
	
	/**
	 * Récupération d'une instance unique à DBConnector (singleton)
	 * @return DBConnector
	 */
	public static synchronized DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
		}
		
		return instance;
	}
	
	/**
	 * Récupération de la connection
	 * @return Connection vers la BDD
	 */
	public Connection getConnection() {
		return this.connection;
	}
	
	public ResultSet executeQuery(String sql) {
		return executeQuery(sql, null);
	}
	
	public ResultSet executeQuery(String sql, Object[] params) {
		ResultSet rs = null;
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql, 1);
			if (params != null) {
				Object param;
				for (int i = 0; i < params.length; i++) {
					param = params[i];

					if (param.getClass().equals(String.class)) {
						stmt.setString(i+1, (String) param);
					} else if (param.getClass().equals(Integer.class)) {
						stmt.setInt(i+1, (int) param);
					} else if (param.getClass().equals(Boolean.class)) {
						stmt.setBoolean(i+1, (boolean) param);
					} else if (param.getClass().equals(Date.class)) {
						stmt.setDate(i+1, (Date) param);
					}
				}
			}
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int insertQuery(String sql, Object[] params) {
		int res = -1;
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (params != null) {
				Object param;
				for (int i = 0; i < params.length; i++) {
					param = params[i];

					if (param.getClass().equals(String.class)) {
						stmt.setString(i+1, (String) param);
					} else if (param.getClass().equals(Integer.class)) {
						stmt.setInt(i+1, (int) param);
					} else if (param.getClass().equals(Boolean.class)) {
						stmt.setBoolean(i+1, (boolean) param);
					} else if (param.getClass().equals(Date.class)) {
						stmt.setDate(i+1, (Date) param);
					}
				}
			}
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			res = rs.getInt(1);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Methode qui permet de tester la connection a la BDD
	 * @return String de la liste des tables
	 */
	public String test() {
		String str = "no result";
		try {
			PreparedStatement stmt = this.connection.prepareStatement("show tables");
			ResultSet rs = stmt.executeQuery();
			str = "<p>Tables:";
			while (rs.next()) {
				str += "<br>" + rs.getString(1);
			}
			str += "</p>";
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return str;
	}
}
