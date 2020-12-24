package DAO;

import java.sql.*;
import java.util.ArrayList;

import Classi.Aeroporto;
import Classi.Tratta;

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

	public ArrayList<Tratta> getTratteByAeroportoDiPartenza(String codAeroportoPartenza) {

		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement st = conn.prepareStatement("Select * from tratta where codaeroportopartenza = ?)");
			st.setString(1, codAeroportoPartenza);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				Tratta tmp = new Tratta(rs.getString(0), rs.getString(1), rs.getString(2));
				Tratte.add(tmp);
			}
			
			st.close();
			conn.close();
			
			return Tratte;
			
		} catch (SQLException e) {
			errore = e.getMessage();
			
			if (errore.contains("trattacorretta")) {
				
				//throw new TrattaException("La tratta esiste già!");
				
			}
		}

		return Tratte;
		
	}

}
