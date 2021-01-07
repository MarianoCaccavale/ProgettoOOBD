package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import Connessione.ConnessioneDB;
import Eccezioni.ClientiException;

public class ClientiDAO {
	
	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");

	public void insert(String email, String nome, String cognome, Date dataNascitaTmp, String codCompagnia) throws ClientiException {
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into clientibusiness values(?,?,?,?, now() ,?,?)");
			ps.setString(1, email);
			ps.setString(2, nome);
			ps.setString(3, cognome);
			
			Timestamp dataNascita = new Timestamp(dataNascitaTmp.getTime());
			ps.setTimestamp(4, dataNascita);
			
			ps.setInt(5, 0);
			ps.setString(6, "CK"+codCompagnia);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new ClientiException(errore);
			
		}
		
		
	}

	public void delete(String email) throws ClientiException {
		
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from clientibusiness where email = ?");
			ps.setString(1, email);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new ClientiException(errore);
			
		}
		
		
	}

}
