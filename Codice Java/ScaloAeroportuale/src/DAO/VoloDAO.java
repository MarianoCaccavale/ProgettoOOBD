package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import Classi.Tratta;
import Classi.Volo;
import Connessione.ConnessioneDB;
import Eccezioni.VoloException;

public class VoloDAO {
	
	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	
	
	public void insert (Volo volo) throws VoloException {
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Insert into volo values(nextval('sequenza_volo'), ?, ?, ?, ?, ?)");
			
			Statement st = conn.createStatement();
			ResultSet rs_codAeroportoPartenza = st.executeQuery("select codaeroporto from aeroporto where nomeaeroporto = '" + volo.getTrattaAssociata().getAeroportoDiPartenza().getNomeAeroporto()+ "'");
			rs_codAeroportoPartenza.next();
			ResultSet rs_codAeroportoArrivo = st.executeQuery("select codaeroporto from aeroporto where nomeaeroporto = '" + volo.getTrattaAssociata().getAeroportoDiArrivo().getNomeAeroporto()+ "'");
			rs_codAeroportoArrivo.next();
			PreparedStatement codiceTratta = conn.prepareStatement("select codtratta from tratta where aeroportopartenza = ? AND aeroportoarrivo = ?");
			codiceTratta.setString(1, rs_codAeroportoPartenza.getString("codaeroporto"));
			codiceTratta.setString(2, rs_codAeroportoArrivo.getString("codaeroporto"));
			ResultSet rs_codiceTratta = codiceTratta.executeQuery();
			rs_codiceTratta.next();
			String codTratta = rs_codiceTratta.getString("codtratta");
			
			
			PreparedStatement codiceCompagnia = conn.prepareStatement("select codcompagnia from compagniaaerea where nomecompagnia= ?");
			codiceCompagnia.setString(1, volo.getCompagniaDiAppartenenza().getNomeCompagnia());
			ResultSet rs_codiceCompagnia = codiceCompagnia.executeQuery();
			rs_codiceCompagnia.next();
			String codCompagnia = rs_codiceCompagnia.getString("codcompagnia");
			
			
			java.util.Date dataTmp = (java.util.Date)volo.getData();
			Timestamp dataVolo = new Timestamp(dataTmp.getTime());
			
			
			ps.setTimestamp(1, dataVolo);
			ps.setInt(2, volo.getNumeroPosti());
			ps.setInt(3, 0);
			ps.setString(4, codTratta);
			ps.setString(5, codCompagnia);
			ps.executeUpdate();
			
			Statement sequenza = conn.createStatement();
			ResultSet rs = sequenza.executeQuery("select last_value from sequenza_volo");
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


	public ArrayList<Volo> getAllVoli(Aeroporto aeroporto) throws VoloException {
		
		ArrayList<Volo> risultato = new ArrayList<Volo>();
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Select * from ((volo as v natural join compagniaaerea as c) inner join tratta as t on v.codtratta = t.codtratta) as vt inner join aeroporto as a on vt.aeroportoarrivo = a.codaeroporto where vt.aeroportopartenza = ?");
			ps.setString(1, aeroporto.getCodAeroporto());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Aeroporto aeroportoTmp = new Aeroporto(rs.getString("aeroportoarrivo"), rs.getString("nomeaeroporto"), rs.getString("città"));
				Tratta trattaTmp = new Tratta(aeroporto, aeroportoTmp);
				CompagniaAerea compagniaTmp = new CompagniaAerea(rs.getString("nomecompagnia"), rs.getInt("grandezzaflotta"));
				Volo tmp = new Volo(rs.getString("codvolo"), rs.getTimestamp("datavolo"), rs.getInt("numeroposti"), rs.getInt("numeropostiprenotati"), compagniaTmp, trattaTmp);
				
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


	public void update(int numeroPosti, String codVolo) throws VoloException {
		
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


	public ArrayList<Volo> ricercaVoloByTratta(Tratta tratta) throws VoloException {
		
		ArrayList<Volo> VoliTrovati = new ArrayList<Volo>();
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select *, a1.nomeaeroporto as nomepartenza, a2.nomeaeroporto as nomearrivo from volo natural join compagniaaerea as c natural join tratta as t inner join aeroporto as a1 on t.aeroportopartenza = a1.codaeroporto inner join aeroporto as a2 on t.aeroportoarrivo = a2.codaeroporto where a1.nomeaeroporto = ? AND a2.nomeaeroporto = ?");
			ps.setString(1, tratta.getAeroportoDiPartenza().getNomeAeroporto());
			ps.setString(2, tratta.getAeroportoDiArrivo().getNomeAeroporto());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				CompagniaAerea compagniaTmp = new CompagniaAerea(rs.getString("nomecompagnia"), rs.getInt("grandezzaflotta"));
				
				Aeroporto aeroportoPartenza = new Aeroporto(rs.getString("nomepartenza"));
				Aeroporto aeroportoArrivo = new Aeroporto(rs.getString("nomearrivo"));
				
				Tratta trattaTmp = new Tratta(aeroportoPartenza, aeroportoArrivo);
				Volo tmp = new Volo(rs.getString("codvolo"), rs.getTimestamp("datavolo"), rs.getInt("numeroposti"), rs.getInt("numeropostiprenotati"), compagniaTmp, trattaTmp);
				
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
