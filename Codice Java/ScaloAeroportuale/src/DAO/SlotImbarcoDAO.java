package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import Classi.SlotImbarco;
import Connessione.ConnessioneDB;
import Eccezioni.SlotImbarcoException;

public class SlotImbarcoDAO {

	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	
	public void insert(String codVolo, String codGate, String coda, Timestamp dataInizio) throws SlotImbarcoException {
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement pst = conn.prepareStatement("Insert into SlotImbarco values(?, ?, ?, ?, ?)");
			
			pst.setString(1, codVolo);
			pst.setString(2, codGate);
			pst.setString(3, coda);
			pst.setTimestamp(5, dataInizio);
			
			Timestamp dataChiusura = dataInizio;
			dataChiusura.setHours(dataInizio.getHours() + 1);
			
			pst.setTimestamp(4, dataChiusura);
			
					
			pst.executeUpdate();
			pst.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			if (errore.contains("tempomax_check")) {
				throw new SlotImbarcoException("Impossibile inserire un volo con chiusura gate stimata antecedente al volo.");
			}else{
				
				throw new SlotImbarcoException(errore);
				
			}
			
		}
		
	}

	public ArrayList<SlotImbarco> getAllImbarchi(String codAeroporto) throws SlotImbarcoException{
		
		ArrayList<SlotImbarco> risultato = new ArrayList<SlotImbarco>();
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement pst = conn.prepareStatement("select part.codvolo, v.codtratta, part.codgate, part.datainizio from gate_view as part natural join volo as v where part.codaeroporto = ? AND part.datafine IS NULL");
			
			pst.setString(1, codAeroporto);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				SlotImbarco tmp = new SlotImbarco(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
				risultato.add(tmp);
				
			}
			pst.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new SlotImbarcoException(errore);
			
			
		}
		
		
		return risultato;
	}

	public void closeSlotImbarco(String codVolo, String codGate, Timestamp dataFine) throws SlotImbarcoException {
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement pst = conn.prepareStatement("update SlotImbarco set datafine = ? where codVolo = ? AND codGate = ?");
			
			pst.setTimestamp(1, dataFine);
			pst.setString(2, codVolo);
			pst.setString(3, codGate);
			
			pst.executeUpdate();
			
			pst.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new SlotImbarcoException(errore);
			
			
		}
		
	}
	
	
	
	
	
}
