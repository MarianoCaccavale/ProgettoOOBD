package DAO;

import java.sql.*;
import java.util.ArrayList;

import Classi.Aeroporto;
import Classi.Tratta;
import Eccezioni.TrattaException;

public class TrattaDao {

	Connection conn = null;
	String errore = new String("");
	
	public void Insert(String codAeroportoPartenza, String codAeroportoArrivo) throws TrattaException {
		
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
				
				throw new TrattaException("La tratta esiste gi�!");
				
			}
		}
		
		
	}

	public ArrayList<Tratta> getTratteByAeroportoDiPartenza(String codAeroportoPartenza) throws TrattaException {

		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		
		if(codAeroportoPartenza.isBlank()) {
			
			throw new TrattaException("Impossibile cercare una tratta senza aeroporto di partenza!");
			
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement st = conn.prepareStatement("Select * from tratta where aeroportopartenza = ?");
			st.setString(1, codAeroportoPartenza);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				Tratta tmp = new Tratta(rs.getString("codtratta"), rs.getString("aeroportopartenza"), rs.getString("aeroportoarrivo"));
				Tratte.add(tmp);
			}
			
			st.close();
			conn.close();
			
			return Tratte;
			
		} catch (SQLException e) {
			errore = e.getMessage();	
			
			//throw new TrattaException(errore);
			
		}
		
		return Tratte;
		
	}

	public void deleteTratta(String codAeroportoPartenza, String codAeroportoArrivo) throws TrattaException {
		
		if (codAeroportoPartenza.isBlank() || codAeroportoArrivo.isBlank()) {
			
			throw new TrattaException("Impossibile cancellare tratta con uno degli aeroporti vuoto!");
			
		}
		
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement st = conn.prepareStatement("Delete from tratta where aeroportopartenza = ? AND aeroportoarrivo = ?");
			st.setString(1, codAeroportoPartenza);
			st.setString(2, codAeroportoArrivo);
			st.executeUpdate();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			errore = e.getMessage();	
			
			throw new TrattaException(errore);
			
		}
	}

}
