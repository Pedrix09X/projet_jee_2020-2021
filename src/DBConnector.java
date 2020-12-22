import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			// Chargement de la classe
			try {
			    Class.forName( "com.mysql.jdbc.Driver" );
			} catch ( ClassNotFoundException e ) {
				System.out.println("Erreur lors du chargement de la classe !");
			    e.printStackTrace();
			}
			
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
	
	public String test() {
		String str = "no result";
		try {
			PreparedStatement stmt = this.connection.prepareStatement("show tables");
			ResultSet rs = stmt.executeQuery();
			str = "Tables:";
			while (rs.next()) {
				str += "<br>" + rs.getString(1);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return str;
	}
}
