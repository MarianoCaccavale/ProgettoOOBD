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

	public ArrayList<Tratta> getTratteByAeroportoDiPartenza(Aeroporto aeroportoPartenza) throws TrattaException {

		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement st = conn.prepareStatement("Select * from tratta as t join aeroporto as a on t.aeroportoarrivo = a.codaeroporto where aeroportopartenza = ?");
			st.setString(1, aeroportoPartenza.getCodAeroporto());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				Aeroporto aeroportoArrivo = new Aeroporto(rs.getString("codaeroporto"), rs.getString("nomeaeroporto"), rs.getString("città"));
				Tratta tmp = new Tratta(aeroportoPartenza, aeroportoArrivo);
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

	public Tratta getTrattaByCod(String trattaAssociata) throws TrattaException {
		
		Tratta risultato = new Tratta();
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Select t.aeroportopartenza, a1.nomeaeroporto, a1.città, t.aeroportoarrivo, a2.nomeaeroporto, a2.città from (tratta as t join aeroporto as a1 on t.aeroportoarrivo = a1.codaeroporto) join aeroporto as a2 on t.aeroportopartenza = a2.codaeroporto where codtratta = ?");

			ps.setString(1, trattaAssociata);
			ResultSet rs = ps.executeQuery();
		
			
			if (rs.next()) {
				
				Aeroporto aeroportoPartenza = new Aeroporto(rs.getString("aeroportopartenza"), rs.getString("nomea1"), rs.getString("cittàa1"));
				Aeroporto aeroportoArrivo = new Aeroporto(rs.getString("aeroportoarrivo"), rs.getString("nomea2"), rs.getString("cittàa2"));
				
				risultato = new Tratta(aeroportoPartenza, aeroportoArrivo);
				
			}			
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new TrattaException(errore);
			
		}
		
		return risultato;
	}
	
	public Tratta getTratte(String AeroportoPartenza, String AeroportoArrivo) throws TrattaException {
			
		Tratta risultato = new Tratta();
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Select t.aeroportopartenza, a1.nomeaeroporto as nomea1, a1.città as cittàa1, t.aeroportoarrivo, a2.nomeaeroporto as nomea2, a2.città as cittàa2 from (tratta as t join aeroporto as a1 on t.aeroportopartenza = a1.codaeroporto) join aeroporto as a2 on t.aeroportoarrivo = a2.codaeroporto where a1.nomeaeroporto = ? and a2.nomeaeroporto = ?");

			ps.setString(1, AeroportoPartenza);
			ps.setString(2, AeroportoArrivo);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
				
			Aeroporto aeroportoPartenza = new Aeroporto(rs.getString("aeroportopartenza"), rs.getString("nomea1"), rs.getString("cittàa1"));
			Aeroporto aeroportoArrivo = new Aeroporto(rs.getString("aeroportoarrivo"), rs.getString("nomea2"), rs.getString("cittàa2"));
				
			risultato = new Tratta(aeroportoPartenza, aeroportoArrivo);
				
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new TrattaException(errore);
			
		}
		
		return risultato;
	}

}
