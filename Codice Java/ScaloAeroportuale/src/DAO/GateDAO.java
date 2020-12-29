package DAO;

import java.sql.*;
import java.util.ArrayList;

import Classi.Gate;
import Eccezioni.GateException;


public class GateDAO {

	Connection conn = null;
	String errore = new String("");
	
	public void insertGate(String nomeGate, String codAeroporto) throws GateException{
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement pr = conn.prepareStatement("Insert into gate values(nextval('sequenza_gate'), ?, ?)");
			
			pr.setString(1, codAeroporto);
			pr.setString(2, nomeGate);
			pr.executeQuery();
			pr.close();
			conn.close();
			
		} catch (SQLException e) {
			
			errore = e.getMessage();
			
			if(errore.contains("nomeunique")) {
				
				throw new GateException("Inserimento fallito! Esiste già un gate con questo nome");
				
			}else if (errore.contains("nomegate")) {
				
				throw new GateException("Nome gate errato! Il nome del gate deve essere una lettera maiuscola seguita da un numero");
				
			}
			
		}
		
	}
	
	public void updateGate(String vecchioNome, String nuovoNome, String codAeroporto) throws GateException {
		
		try {
			
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement ps = conn.prepareStatement("Update gate set nomegate = ? where nomegate = ? AND codaeroporto = ?");
			
			ps.setString(1, nuovoNome);
			ps.setString(2, vecchioNome);
			ps.setString(3, codAeroporto);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		}catch (SQLException e) {
			
			errore = e.getMessage();
			
			if (errore.contains("nomeunique")) {
				
				throw new GateException("Impossibile aggiorare! Esiste già un Gate con questo nome");
				
			}else if (errore.contains("nomegate")) {
				
				throw new GateException("Nome gate errato! Il nome del gate deve essere una lettera maiuscola seguita da un numero");
				
			}else {
				
				throw new GateException(errore);
				
			}
			
		}
		
	}
	
	public void delete(String nomeGate, String codAeroporto) throws GateException {
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement pr = conn.prepareStatement("delete from gate where nomegate = ? AND codaeroporto = ?");
			
			pr.setString(1, nomeGate);
			pr.setString(2, codAeroporto);
			
			pr.executeUpdate();
			pr.close();
			conn.close();
			
		} catch (SQLException e) {
			
			errore = e.getMessage();
			throw new GateException(errore);
			
		}
		
	}

	public ArrayList<Gate> getAllGate(String codAeroporto) {
		
		ArrayList<Gate> AllGate = new ArrayList<Gate>();
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement pr = conn.prepareStatement("Select * from gate where codaeroporto = ?");
			
			pr.setString(1, codAeroporto);
			
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				
				Gate tmp = new Gate(rs.getString("codgate"), rs.getString("nomegate"));
				AllGate.add(tmp);
			}
			
			rs.close();
			pr.close();
			conn.close();
			return AllGate;
			
		} catch (SQLException e) {
			
		}
		
		return AllGate;
	}

	

	

}
