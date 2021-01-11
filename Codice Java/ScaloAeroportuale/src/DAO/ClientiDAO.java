package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import Classi.ClienteBusiness;
import Classi.CompagniaAerea;
import Connessione.ConnessioneDB;
import Eccezioni.ClientiException;

public class ClientiDAO {
	
	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");

	public void insert(String email, String nome, String cognome, Date dataNascitaTmp, CompagniaAerea compagnia) throws ClientiException {
		
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
			ps.setString(6, compagnia.getCodCentoKilometri());
			
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

	public ArrayList<ClienteBusiness> getAllClientiBusiness() throws ClientiException {
		
		ArrayList<ClienteBusiness> Clienti = new ArrayList<ClienteBusiness>();

		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from clientibusiness");
			
			while (rs.next()) {
				
				ClienteBusiness tmp = new ClienteBusiness(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getInt("punti"));
				Clienti.add(tmp);
				
			}
			
			conn.close();
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new ClientiException(errore);
			
		}
		
		return Clienti;
	}

	public ArrayList<ClienteBusiness> getClientiBusinessByCompagnia(CompagniaAerea compagnia) throws ClientiException {
	
		ArrayList<ClienteBusiness> Clienti = new ArrayList<ClienteBusiness>();
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement ps = conn.prepareStatement("select cb.email, cb.nome, cb.cognome, cb.punti, ca.codcentokilometri from clientibusiness as cb natural join compagniaaerea as ca where ca.nomecompagnia = ?");
			ps.setString(1, compagnia.getNomeCompagnia());
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				ArrayList<CompagniaAerea> compagnie = new ArrayList<CompagniaAerea>();
				compagnie.add(compagnia);
				ClienteBusiness tmp = new ClienteBusiness(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getInt("punti"), compagnie);
				Clienti.add(tmp);
				
			}
			
			ps.close();
			conn.close();
			
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new ClientiException(errore);
			
		}
		
		
		return Clienti;
	}

}
