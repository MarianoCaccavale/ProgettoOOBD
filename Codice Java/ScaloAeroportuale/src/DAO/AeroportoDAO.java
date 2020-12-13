package DAO;
import java.sql.*;
import java.util.LinkedList;

import Classi.Aeroporto;


public class AeroportoDAO{
	
	Connection conn = null;
	
	
	public boolean insertAeroporto(String Codice, String Nome, String Citt�) {
		
		boolean buonfine = false;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement pst = conn.prepareStatement("Insert into Aeroporto values(?,?,?)");
			pst.setString(1, Codice);
			pst.setString(2, Nome);
			pst.setString(3, Citt�);
			pst.execute();
			buonfine = true;
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return buonfine;
	} 
	
	
	public Aeroporto getAeroportoByCod(String Codice) {
		
		Aeroporto Aeroporto = new Aeroporto("", "", "");
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from Aeroporto Where CodAeroporto = '"+ Codice+ "'");
			
			if (rs.next()) {
				
				Aeroporto = new Aeroporto(rs.getString("CodAeroporto"), rs.getString("NomeAeroporto"), rs.getString("Citt�"));
				
			}
			st.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Aeroporto;
		
	}
	
	public Aeroporto getAeroportoByNome(String Nome) {
		
		
		Aeroporto Aeroporto = new Aeroporto("", "", "");
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from Aeroporto Where NomeAeroporto = '"+ Nome + "'");
			
			if (rs.next()) {
				
				Aeroporto = new Aeroporto(rs.getString("CodAeroporto"), rs.getString("NomeAeroporto"), rs.getString("Citt�"));
				
			}
			st.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Aeroporto;
			
	}
	
	public LinkedList<Aeroporto> getAeroportoByCitt�(String Citt�) {
		
		Aeroporto Aeroporto = new Aeroporto();
		LinkedList<Aeroporto> ListaAeroporti = new LinkedList<Aeroporto>();
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from Aeroporto Where Citt� = '"+ Citt� + "'");
			
			while (rs.next()) {
				
				Aeroporto = new Aeroporto(rs.getString("CodAeroporto"), rs.getString("NomeAeroporto"), rs.getString("Citt�"));
				ListaAeroporti.add(Aeroporto);
				
			}
			st.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return ListaAeroporti;
			
	}
	
	
}	
