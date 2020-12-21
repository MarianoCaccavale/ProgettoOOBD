package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

import Classi.Aeroporto;


public class AeroportoDAO{
	
	Connection conn = null;
	
	public boolean insertAeroporto(String Nome, String Città) {
		
		boolean buonfine = false;
		int offset = 10;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement pst = conn.prepareStatement("Insert into Aeroporto values(?,?,?)");
			
			pst.setString(1, "nextval('sequenza_aeroporto')");
			pst.setString(2, Nome);
			pst.setString(3, Città);
			pst.execute();
			buonfine = true;
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return buonfine;
	}
	
	//aggiungere "Città" solo per completezza?
	public boolean updateAeroporto(String Codice, String Nome) {
			
			boolean buonfine = false;
			
			try {
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("Select * from Aeroporto Where CodAeroporto = '"+Codice+"'");
				PreparedStatement pst = conn.prepareStatement("Update Aeroporto set Nome = ? Where CodAeroporto = '"+Codice+"'");
				
				//il nome dell'aeroporto viene mai usato? se sì, va trovato il modo di aggiornarlo "on cascade"
				pst.setString(2, Nome);
	
				buonfine = true;
				
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			return buonfine;
	}

	public boolean deleteAeroporto(String Codice) {
		
		boolean buonfine = false;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Delete from Aeroporto Where CodAeroporto = '"+Codice+"'");
	
			buonfine = true;
			
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
				
				Aeroporto = new Aeroporto(rs.getString("CodAeroporto"), rs.getString("NomeAeroporto"), rs.getString("Città"));
				
			}
			st.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
				
				Aeroporto = new Aeroporto(rs.getString("CodAeroporto"), rs.getString("NomeAeroporto"), rs.getString("Città"));
				
			}
			st.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return Aeroporto;
			
	}
	
	public LinkedList<Aeroporto> getAeroportoByCittà(String Città) {
		
		Aeroporto Aeroporto = new Aeroporto();
		LinkedList<Aeroporto> ListaAeroporti = new LinkedList<Aeroporto>();
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from Aeroporto Where Città = '"+ Città + "'");
			
			while (rs.next()) {
				
				Aeroporto = new Aeroporto(rs.getString("CodAeroporto"), rs.getString("NomeAeroporto"), rs.getString("Città"));
				ListaAeroporti.add(Aeroporto);
				
			}
			
			st.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return ListaAeroporti;
			
	}

	public ArrayList<Aeroporto> getAllAeroporti() {
		
		Aeroporto Aeroporto = new Aeroporto();
		ArrayList<Aeroporto> ListaAeroporti = new ArrayList<Aeroporto>();
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from Aeroporto");
			
			while (rs.next()) {
				
				Aeroporto = new Aeroporto(rs.getString("CodAeroporto"), rs.getString("NomeAeroporto"), rs.getString("Città"));
				ListaAeroporti.add(Aeroporto);
				
			}
			
			st.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return ListaAeroporti;
	}
	
	
}	
