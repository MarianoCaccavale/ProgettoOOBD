package DAO;

import java.sql.*;
import java.util.ArrayList;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import Eccezioni.CompagniaException;


public class CompagniaAereaDao{

	Connection conn = null;
	
	public ArrayList<CompagniaAerea> getAllCompagnie(){
		
		ArrayList<CompagniaAerea> result = new ArrayList<CompagniaAerea>();
		CompagniaAerea tmp;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
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
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	public void InsertCompagnia(String Nome, int Flotta, Aeroporto AerAppartenenza) throws CompagniaException {
		
		String errore = new String("");
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
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
		
		String errore = new String("");
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
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
				
				throw new CompagniaException("Grandezza flotta errata, inserire un valore compreso tr 50 e 500");
				
			}
			
		}
	}

	public ArrayList<CompagniaAerea> getCompagnieByAeroporto(String codAeroporto) {
		
		ArrayList<CompagniaAerea> result = new ArrayList<CompagniaAerea>();
		CompagniaAerea tmp;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
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
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	public void deleteByNome(String nome) {
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement pr = conn.prepareStatement("Delete from compagniaaerea where nomecompagnia = ?");
			
			pr.setString(1, nome);
			pr.executeUpdate();
			
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
		
}
	