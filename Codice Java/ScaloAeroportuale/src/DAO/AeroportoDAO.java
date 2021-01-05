package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

import Classi.Aeroporto;
import Connessione.ConnessioneDB;


public class AeroportoDAO{
	
	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	
	public boolean insertAeroporto(String Nome, String Città) {
		
		boolean buonfine = false;
		
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
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
	
	
	public boolean updateAeroporto(String Codice, String Nome) {
			
			boolean buonfine = false;
			
			try {
				connessioneDB = ConnessioneDB.getIstanza();
				conn = connessioneDB.getConnection();
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
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
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
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
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
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
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
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
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
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
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

	public ArrayList<Aeroporto> getAllAeroportiExceptThis(String codAeroporto) {
		
		Aeroporto Aeroporto = new Aeroporto();
		ArrayList<Aeroporto> Aeroporti = new ArrayList<Aeroporto>();
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement st = conn.prepareStatement("Select * from Aeroporto where codaeroporto <> ?");
			st.setString(1, codAeroporto);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				Aeroporto = new Aeroporto(rs.getString("CodAeroporto"), rs.getString("NomeAeroporto"), rs.getString("Città"));
				Aeroporti.add(Aeroporto);
				
			}
			
			st.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return Aeroporti;
	}
	
	
}	
