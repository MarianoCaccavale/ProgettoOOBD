package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import Eccezioni.TrattaException;
import Eccezioni.VoloException;

public class VoloDAO {
	
	Connection conn = null;
	String errore = new String("");
	
	
	public void Insert (Date data, int numPosti, String Compagnia, String Tratta) throws VoloException {
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement st = conn.prepareStatement("Insert into volo values(nextval('sequenza_volo'), ?, ?, ?, ?, ?)");
			st.setDate(1, data);
			st.setInt(2, numPosti);
			st.setInt(3, 0);
			st.setString(4, Compagnia);
			st.setString(5, Tratta);
			st.execute();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			errore = e.getMessage();	
			
			throw new VoloException(errore);
		}
	}

}
