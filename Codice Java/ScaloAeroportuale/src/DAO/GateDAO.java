package DAO;

import java.sql.*;
import java.util.ArrayList;

import Classi.Gate;
import Connessione.ConnessioneDB;
import Eccezioni.GateException;


public class GateDAO {

	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	
	public void insertGate(String nomeGate, String codAeroporto) throws GateException{
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
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
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
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
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
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
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
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

	public ArrayList<Gate> getGateLiberi(String codAeroporto, Timestamp dataVolo) throws GateException {
		
		ArrayList<Gate> GateLiberi = new ArrayList<Gate>();
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			String query = new String("(select DISTINCT(g.codgate), g.nomegate from gate as g where g.codaeroporto = ?) EXCEPT (select DISTINCT(gv.codgate), gv.nomegate from gate_view as gv where (gv.codaeroporto = ? AND ( gv.datainizio <= ? AND ? <= gv.tempomax) OR ( gv.datainizio <= ? AND ? <= gv.tempomax)))");
			
			PreparedStatement pt = conn.prepareStatement(query);
			
			pt.setString(1, codAeroporto);
			pt.setString(2, codAeroporto);
			pt.setTimestamp(3, dataVolo);
			pt.setTimestamp(4, dataVolo);
			
			Timestamp dataFine = dataVolo;
			dataFine.setHours(dataFine.getHours()+1);
			
			pt.setTimestamp(5, dataFine);
			pt.setTimestamp(6, dataFine);
			
			ResultSet rs = pt.executeQuery();
			
			while (rs.next()) {
				
				Gate tmp = new Gate(rs.getString(1), rs.getString(2));
				GateLiberi.add(tmp);
			}
			
			return GateLiberi;
	
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new GateException(errore);
			
		}
		
	}

	

	

}
