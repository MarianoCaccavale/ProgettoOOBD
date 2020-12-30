package DAO;

import java.sql.*;
import java.util.ArrayList;

import Classi.Aeroporto;
import Classi.Tratta;
import Connessione.ConnessioneDB;
import Eccezioni.TrattaException;

public class TrattaDAO {

	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	
	public void Insert(String codAeroportoPartenza, String codAeroportoArrivo) throws TrattaException {
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement st = conn.prepareStatement("Insert into tratta values(nextval('sequenza_tratta'),? , ?)");
			st.setString(1, codAeroportoPartenza);
			st.setString(2, codAeroportoArrivo);
			st.execute();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			errore = e.getMessage();
			
			if (errore.contains("trattacorretta")) {
				
				throw new TrattaException("La tratta esiste già!");
				
			}
		}
		
		
	}

	public ArrayList<Tratta> getTratteByAeroportoDiPartenza(String codAeroportoPartenza) throws TrattaException {

		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		
		if(codAeroportoPartenza.isBlank()) {
			
			throw new TrattaException("Impossibile cercare una tratta senza aeroporto di partenza!");
			
		}
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
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
			
			throw new TrattaException(errore);
			
		}
		
	}

	public void deleteTratta(String codAeroportoPartenza, String codAeroportoArrivo) throws TrattaException {
		
		if (codAeroportoPartenza.isBlank() || codAeroportoArrivo.isBlank()) {
			
			throw new TrattaException("Impossibile cancellare tratta con uno degli aeroporti vuoto!");
			
		}
		
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
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

	public void update(String vecchioCodAeroporto, String nuovoCodAeroporto, String codAeroporto) throws TrattaException {
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement ps = conn.prepareStatement("update tratta set aeroportoarrivo = ? where aeroportoarrivo = ? AND aeroportopartenza = ?");
			
			ps.setString(1, nuovoCodAeroporto);
			ps.setString(2, vecchioCodAeroporto);
			ps.setString(3, codAeroporto);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			if(errore.contains("trattacorretta")) {
				
				throw new TrattaException("Impossibile aggiornare la tratta! Esiste già una tratta tra questi due aeroporto.");
				
			}else {
				
				throw new TrattaException(errore);
				
			}
			
		}
		
		
	}

	public Tratta getTratteByCod(String trattaAssociata) throws TrattaException {
		
		Tratta risultato = new Tratta();
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Select * from tratta where codtratta = ?");

			ps.setString(1, trattaAssociata);
			ResultSet rs = ps.executeQuery();
		
			
			rs.next();			
			risultato = new Tratta(rs.getString(1), rs.getString(2), rs.getString(3));
				
			
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new TrattaException(errore);
			
		}
		
		return risultato;
	}

}
