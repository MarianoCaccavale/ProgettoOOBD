package Connessione;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDB {

	private static ConnessioneDB istanza;
	private Connection connessione = null; 
	private final String USERNAME = "postgres";
	private final String PASSWORD = "progettooobd";
	private final String IP = "localhost";
	private final String PORT = "5432";
	private String url = "jdbc:postgresql://" + IP + ":" + PORT + "/prova";
	
	private ConnessioneDB() throws SQLException {
		
		try {
			
			connessione = DriverManager.getConnection(url, USERNAME, PASSWORD);
			
			
		}catch(SQLException e) {
			
			throw new SQLException(e.getMessage());
			
		}
	}
	
	public Connection getConnection() {
		
		return connessione;
		
	}
	
	public static ConnessioneDB getIstanza() throws SQLException{
		
		if (istanza == null) {
			
			istanza = new ConnessioneDB();
			
		}else if (istanza.getConnection().isClosed()) {
			
			istanza= new ConnessioneDB();
			
		}
		
		return istanza;

	}
	
}
