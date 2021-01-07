package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import Classi.Volo;
import Connessione.ConnessioneDB;
import Eccezioni.VoloException;

public class VoloDAO {
	
	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	
	
	public void Insert (Volo volo) throws VoloException {
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Insert into volo values(nextval('sequenza_volo'), ?, ?, ?, ?, ?)");
			
			java.util.Date dataTmp = (java.util.Date)volo.getData();
			Timestamp dataVolo = new Timestamp(dataTmp.getTime());
			
			
			ps.setTimestamp(1, dataVolo);
			ps.setInt(2, volo.getNumeroPosti());
			ps.setInt(3, 0);
			ps.setString(4, volo.getTrattaAssociata());
			ps.setString(5, volo.getCompagniaDiAppartenenza());
			ps.executeUpdate();
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select last_value from sequenza_volo");
			rs.next();
			volo.setCodVolo(rs.getString("last_value"));
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			errore = e.getMessage();	
			
			if (errore.contains("validificadata()")){
				throw new VoloException("Impossibile inserire un volo antecedente ad ora");
			}else {
				
				throw new VoloException(errore);
				
			}
				
		}
	}


	public ArrayList<Volo> getAllVoli(String codAeroporto) throws VoloException {
		
		ArrayList<Volo> risultato = new ArrayList<Volo>();
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Select * from volo as v inner join tratta as t on v.codtratta = t.codtratta where t.aeroportopartenza = ?");
			ps.setString(1, codAeroporto);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Volo tmp = new Volo(rs.getString(1), (java.util.Date) rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
				
				risultato.add(tmp);
				
			}
			
			ps.close();
			rs.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
				
			throw new VoloException(errore);
			
		}
		
		return risultato;
	}


	public void delete(String codVolo) throws VoloException {
		
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Delete from volo where codvolo = ?");
			ps.setString(1, codVolo);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
				
			throw new VoloException(errore);
			
		}
		
	}


	public void updateVolo(int numeroPosti, String codVolo) throws VoloException {
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("update volo set numeroposti = ? where codvolo = ?");
			ps.setInt(1, numeroPosti);
			ps.setString(2, codVolo);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
				
			throw new VoloException(errore);
			
		}
		
		
	}


	public ArrayList<Volo> ricercaVoloByTratta(String codTratta) throws VoloException {
		
		ArrayList<Volo> VoliTrovati = new ArrayList<Volo>();
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select v.codvolo, v.datavolo, v.numeroposti, v.numeropostiprenotati, v.codtratta, v.codcompagnia from volo as v natural join tratta as t where t.codtratta = (select codtratta from tratta where codtratta = ?)");
			ps.setString(1, codTratta);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Volo tmp = new Volo(rs.getString(1), (java.util.Date) rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
				
				VoliTrovati.add(tmp);
				
			}
			ps.close();
			conn.close();
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
				
			throw new VoloException(errore);
			
		}
		
		return VoliTrovati;
	}


	public void generateTicket(String codVolo, Integer numBiglietti) throws VoloException {

		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Update volo set numeropostiprenotati = numeropostiPrenotati + ? where codvolo = ?");
			ps.setInt(1, numBiglietti);
			ps.setString(2, codVolo);
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new VoloException(errore);
			
		}
		
		
	}


	public void generateBusinessTicket(String codVolo, int numBiglietti, String email) throws VoloException {
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Update volo set numeropostiprenotati = numeropostiPrenotati + ? where codvolo = ?");
			ps.setInt(1, numBiglietti);
			ps.setString(2, codVolo);
			
			ps.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement("Insert into clienti_voli values(?,?)");
			ps2.setString(1, codVolo);
			ps2.setString(2, email);
			
			ps2.executeUpdate();
			
			ps2.close();
			ps.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new VoloException(errore);
			
		}
		
	}
	
	

}
