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

public class SlotImbarcoDAO {

	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	
	public void insert(String codVolo, String codGate, String coda, Timestamp dataInizio, Timestamp dataChiusura) throws SlotImbarcoException {
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement pst = conn.prepareStatement("Insert into SlotImbarco values(? ,? ,? ,? ,? ,?)");
			pst.setString(1, codVolo);
			pst.setString(2, codGate);
			pst.setString(3, coda);
			Timestamp tempoMax = dataInizio;
			tempoMax.setHours(dataInizio.getHours()+1);
			
			pst.setTimestamp(4, tempoMax);
			pst.setTimestamp(5, dataInizio);
			pst.setTimestamp(6, dataChiusura);
			
			pst.executeUpdate();
			pst.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new SlotImbarcoException(errore);
			
		}
		
	}
	
	
	
	
	
	
}
