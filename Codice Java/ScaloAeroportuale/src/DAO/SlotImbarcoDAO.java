package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetTime;
import java.time.Period;

import Connessione.ConnessioneDB;
import Eccezioni.SlotImbarcoException;
import Eccezioni.VoloException;

public class SlotImbarcoDAO {

	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	
	public void insert(String codVolo, String codGate, String coda, Timestamp dataInizio, Timestamp dataChiusura) throws SlotImbarcoException {
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement pst = conn.prepareStatement("Insert into SlotImbarco values(?, ?, ?, ?, null, ?)");
			pst.setString(1, codVolo);
			pst.setString(2, codGate);
			pst.setTimestamp(3, dataChiusura);
			pst.setTimestamp(4, dataInizio);
			pst.setString(5, coda);
					
			pst.executeUpdate();
			pst.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			if (errore.contains("tempomax_check")) {
				throw new SlotImbarcoException("Impossibile inserire un volo con chiusura gate stimata antecedente al volo.");
			}
			
		}
		
	}
	
	
	
	
	
	
}
