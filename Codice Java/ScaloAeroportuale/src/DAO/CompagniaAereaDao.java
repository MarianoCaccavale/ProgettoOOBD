package DAO;

import java.sql.*;

import Classi.Aeroporto;
import Classi.CompagniaAerea;


public class CompagniaAereaDao {

	Connection conn = null;
	
	public CompagniaAerea getCompagniaByNome(String Nome) {
		
		CompagniaAerea Comp = new CompagniaAerea();
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from CompagniaAeroporto Where nomecompagnia = '"+ Nome + "'");
			
			if (rs.next()) {
				
				Comp = new CompagniaAerea(rs.getString("CodCompagnia"), rs.getString("NomeCompagnia"), rs.getInt("GrandezzaFlotta"));
				
			}
			st.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return Comp;
		
	}
	
	public String InsertCompagnia(String Nome, int Flotta, Aeroporto AerAppartenenza) {
		
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
			
			return errore;
		} catch (SQLException e) {
			errore = e.getMessage().toString();
		}
		
		return errore;
		
	}
	
}
