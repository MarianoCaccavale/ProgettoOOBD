package DAO;
import java.sql.*;
import java.util.ArrayList;

import Classi.Aeroporto;
import Connessione.ConnessioneDB;


public class AeroportoDAO{
	
	private Connection conn = null;
	private ConnessioneDB connessioneDB;

	public Aeroporto getAeroportoByNome(String nomeAeroporto) {
		
		
		Aeroporto Aeroporto = new Aeroporto();
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from Aeroporto Where NomeAeroporto = '"+ nomeAeroporto + "'");
			
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

	public ArrayList<Aeroporto> getAllAeroportiExceptThis(String nomeAeroporto) {
		
		Aeroporto Aeroporto = new Aeroporto();
		ArrayList<Aeroporto> Aeroporti = new ArrayList<Aeroporto>();
		
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement st = conn.prepareStatement("Select * from Aeroporto where nomeaeroporto <> ?");
			st.setString(1, nomeAeroporto);
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
