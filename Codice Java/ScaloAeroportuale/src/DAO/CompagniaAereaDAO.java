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
	
	
	public void insert(String Nome, int Flotta, Aeroporto AerAppartenenza) throws CompagniaException {
		
		String codCentoKilometri = new String();
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement pr = conn.prepareStatement("Insert into compagniaaerea values(nextval('sequenza_compagnia'),?,?,?,?)");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select last_value+1 from sequenza_compagnia");
			rs.next();
			codCentoKilometri = "CK"+rs.getString(1);
			
			pr.setString(1, Nome.toUpperCase());
			pr.setInt(2, Flotta);
			pr.setString(3, AerAppartenenza.getCodAeroporto());
			pr.setString(4, codCentoKilometri);
			pr.executeUpdate();
			pr.close();
			conn.close();
			
		} catch (SQLException e) {
			
			errore = e.getMessage().toString();
			
			if (errore.contains("compagniaaerea_grandezzaflotta_check")) {
				
				throw new CompagniaException("Inserimento fallito! La dimensione della flotta è errata. La grandezza dev'essere compresa tra 0 e 500 unità (estremi esclusi).");
				
				
			}else if (errore.contains("La chiave (nomecompagnia)=")) {
				
				
				throw new CompagniaException("Inserimento fallito! Esiste già una compagnia con questo nome.");
				
				
			}else if (errore.contains("nomevuoto")) {
				
				
				throw new CompagniaException("Inserimento fallito! Non è possibile inserire una compagnia con nome vuoto.");
				
				
			}else {
				
				
				throw new CompagniaException("Inserimento fallito!");
				
				
			}
			
			
		}
		
		
	}

	public void update(String nome, int nuovaGrandezza, String codAeroporto) throws CompagniaException {
		
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


	public void delete(String nome) throws CompagniaException {
		
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
	
	public CompagniaAerea getCompagniaByNome(String nome) throws CompagniaException {

		CompagniaAerea tmp = new CompagniaAerea();
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from compagniaaerea where nomecompagnia = ?");
			
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
				
			tmp = new CompagniaAerea(rs.getString("nomecompagnia"), rs.getInt("grandezzaflotta"), rs.getString("codcentokilometri"));
			
			ps.close();
			rs.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new CompagniaException(errore);
			
		}
		
		return tmp;
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
				
				tmp = new CompagniaAerea(rs.getString("NomeCompagnia"), rs.getInt("GrandezzaFlotta"));
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
	
	public ArrayList<CompagniaAerea> getAllCompagnie() throws CompagniaException{
		
		ArrayList<CompagniaAerea> result = new ArrayList<CompagniaAerea>();
		CompagniaAerea tmp;
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from compagniaaerea");
			
			while (rs.next()) {
				
				tmp = new CompagniaAerea(rs.getString("NomeCompagnia"), rs.getInt("GrandezzaFlotta"));
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
				
				CompagniaAerea tmp = new CompagniaAerea(rs.getString("nomecompagnia"), rs.getInt("grandezzaflotta"));
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

	public ArrayList<CompagniaAerea> ricercaByAll(String nome, Integer min, Integer max, String codAeroporto) throws CompagniaException {

		ArrayList<CompagniaAerea> compagnie = new ArrayList<CompagniaAerea>();
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from compagniaaerea where (nomecompagnia like ? AND (? <= grandezzaflotta AND grandezzaflotta <= ?))");
			
			ps.setString(1, "%"+nome.toUpperCase()+"%");
			ps.setInt(2, min);
			ps.setInt(3, max);
			
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				
				compagnie.add(new CompagniaAerea(rs.getString("nomecompagnia"), rs.getInt("grandezzaflotta")));
				
			}
				
			ps.close();
			rs.close();
			conn.close();
			
		}catch (SQLException e) {
			
			errore = e.getMessage();
			
			throw new CompagniaException(errore);
			
			
		}
		
		return compagnie;
	}


	
	
	
		
}
	