package DAO;

import java.sql.*;
import java.util.ArrayList;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import Connessione.ConnessioneDB;
import Eccezioni.CompagniaException;


public class CompagniaAereaDAO{

	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	
	public ArrayList<CompagniaAerea> getAllCompagnie() throws CompagniaException{
		
		ArrayList<CompagniaAerea> result = new ArrayList<CompagniaAerea>();
		CompagniaAerea tmp;
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from compagniaaerea");
			
			while (rs.next()) {
				
				tmp = new CompagniaAerea(rs.getString("CodCompagnia"), rs.getString("NomeCompagnia"), rs.getInt("GrandezzaFlotta"));
				result.add(tmp);
				
			}
			st.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			
			errore = e.getMessage();
			throw new CompagniaException(errore);
			
		}
		
		return result;
	}
	
	public void InsertCompagnia(String Nome, int Flotta, Aeroporto AerAppartenenza) throws CompagniaException {
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement pr = conn.prepareStatement("Insert into compagniaaerea values(nextval('sequenza_compagnia'),?,?,?)");
			
			pr.setString(1, Nome.toUpperCase());
			pr.setInt(2, Flotta);
			pr.setString(3, AerAppartenenza.getCodAeroporto());
			pr.executeUpdate();
			pr.close();
			conn.close();
			
		} catch (SQLException e) {
			
			errore = e.getMessage().toString();
			
			if (errore.contains("compagniaaerea_grandezzaflotta_check")) {
				
				throw new CompagniaException("Inserimento fallito! La dimensione della flotta è errata. La grandezza dev'essere compresa tra 50 e 500 unità.");
				
				
			}else if (errore.contains("La chiave (nomecompagnia)=")) {
				
				
				throw new CompagniaException("Inserimento fallito! Esiste già una compagnia con questo nome.");
				
				
			}else if (errore.contains("nomevuoto")) {
				
				
				throw new CompagniaException("Inserimento fallito! Non è possibile inserire una compagnia con nome vuoto.");
				
				
			}else {
				
				
				throw new CompagniaException("Inserimento fallito!");
				
				
			}
			
			
		}
		
		
	}

	public void updateByNome(String nome, Integer nuovaGrandezza, String codAeroporto) throws CompagniaException {
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			Statement ricerca = conn.createStatement();
			ResultSet rs = ricerca.executeQuery("select nomecompagnia from compagniaaerea where nomecompagnia ='"+ nome + "' AND aeroportoappartenenza = '"+ codAeroporto + "'");
			if (rs.next()) {
				
				PreparedStatement pr = conn.prepareStatement("update compagniaaerea set grandezzaflotta = ? where nomecompagnia = ?");
				pr.setInt(1, nuovaGrandezza);
				pr.setString(2, nome);
				pr.executeUpdate();
				pr.close();
				conn.close();
				
			}else {
				
				conn.close();
				throw new CompagniaException("Modifica vuota. Non c'è nessuna compagnia con quel nome in questo aeroporto!");
				
			}
			
			
		} catch (SQLException e) {
		
			errore = e.getMessage().toString();
			
			if (errore.contains("compagniaaerea_grandezzaflotta_check")) {
				
				throw new CompagniaException("Grandezza flotta errata, inserire un valore compreso tra 50 e 500");
				
			}
			
		}
	}

	public ArrayList<CompagniaAerea> getCompagnieByAeroporto(String codAeroporto) throws CompagniaException {
		
		ArrayList<CompagniaAerea> result = new ArrayList<CompagniaAerea>();
		CompagniaAerea tmp;
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from compagniaaerea where aeroportoappartenenza = '" + codAeroporto + "'");
			
			while (rs.next()) {
				
				tmp = new CompagniaAerea(rs.getString("CodCompagnia"), rs.getString("NomeCompagnia"), rs.getInt("GrandezzaFlotta"));
				result.add(tmp);
				
			}
			st.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			
			errore = e.getMessage();
		
			throw new CompagniaException(errore);
			
			
		}
		
		return result;
	}

	public void deleteByNome(String nome) throws CompagniaException {
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
	
			PreparedStatement pr = conn.prepareStatement("Delete from compagniaaerea where nomecompagnia = ?");
			
			pr.setString(1, nome);
			pr.executeUpdate();
			
			pr.close();
			conn.close();
	
		} catch (SQLException e) {

			errore = e.getMessage();
			
			throw new CompagniaException(errore);
			
		}
		
	}

	public ArrayList<CompagniaAerea> ricercaByFlotta(Integer min, Integer max, String codAeroporto) throws CompagniaException {

		ArrayList<CompagniaAerea> Compagnie = new ArrayList<CompagniaAerea>();
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement pr = conn.prepareStatement("select * from compagniaaerea where grandezzaflotta >= ? AND grandezzaflotta <= ? AND aeroportoappartenenza like ?");
			
			pr.setInt(1, min);
			pr.setInt(2, max);
			pr.setString(3, codAeroporto);
			ResultSet rs = pr.executeQuery();
			
			while (rs.next()) {
				
				CompagniaAerea tmp = new CompagniaAerea(rs.getString(1), rs.getString(2), rs.getInt(3));
				Compagnie.add(tmp);
			}
			
			pr.close();
			rs.close();
			conn.close();
			return Compagnie;
			
	
		} catch (SQLException e) {
			
			throw new CompagniaException(errore = e.getMessage().toString());
		
		}
	}

	public ArrayList<CompagniaAerea> ricercaByAll(String nome, Integer min, Integer max, String codAeroporto) {

		ArrayList<CompagniaAerea> Compagnie = new ArrayList<CompagniaAerea>();
		/*ricerca con like*/
		return Compagnie;
	}

	public CompagniaAerea getCompagniaByCod(String compagniaDiAppartenenza) throws CompagniaException {
		
		CompagniaAerea compagnia = new CompagniaAerea();
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from compagniaaerea where codcompagnia = ?");
			
			ps.setString(1, compagniaDiAppartenenza);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			compagnia = new CompagniaAerea(rs.getString(1), rs.getString(2), rs.getInt(3));
			
			ps.close();
			rs.close();
			conn.close();
			
		}catch (SQLException e) {
			
			errore = e.getMessage();
			
			throw new CompagniaException(errore);
			
			
		}
		
		
		return compagnia;
	}
	
	
		
}
	