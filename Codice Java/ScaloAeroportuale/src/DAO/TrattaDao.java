package DAO;

import java.sql.*;

import Classi.Aeroporto;

public class TrattaDao {

	Connection conn = null;
	String errore = new String("");
	
	public void Insert(String codAeroportoPartenza, String codAeroportoArrivo) {
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement st = conn.prepareStatement("Insert into tratta values(nextval('sequenza_tratta'),? , ?)");
			st.setString(1, codAeroportoPartenza);
			st.setString(2, codAeroportoArrivo);
			st.execute();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			errore = e.getMessage();
			
			if (errore.contains("trattacorretta")) {
				
				//throw new TrattaException("La tratta esiste già!");
				
			}
		}
		
		
	}

}
