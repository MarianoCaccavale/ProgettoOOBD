package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	
	
	
	
	
}
