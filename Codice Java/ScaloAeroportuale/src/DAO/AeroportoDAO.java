package DAO;
import java.sql.*;
import java.util.LinkedList;

import Classi.Aeroporto;


public class AeroportoDAO{
	
	Connection conn = null;
	
	public static String treconsonanti(String s) {
		
		String stringa = new String();
		stringa = s;
		String result = new String();
		int i = 0;
		
		for (char c : stringa.toCharArray()){
			
			if(c != 'A' && c != 'E' && c != 'O' && c != 'U' && c != 'I') {
				
				result = result + c;
				i++;
				
			}else if  (i >= 3) {
				
				break;
				
			}
			
		}
		
		return result;
	}
	
	public boolean insertAeroporto(String Nome, String Città) {
		
		boolean buonfine = false;
		int offset = 10;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestione Scalo Aeroportuale", "postgres", "progettooobd");
			PreparedStatement pst = conn.prepareStatement("Insert into Aeroporto values(?,?,?)");
			Statement st_codice = conn.createStatement();
			ResultSet rs = st_codice.executeQuery("select count(distinct codaeroporto) from aeroporto where città = '"+ Città + "'");
			
			if (rs.next()) {
				
				offset = Integer.parseInt(rs.getString(1));
				offset += 1;
			}
		
			String CodiceCorretto = new String();
			CodiceCorretto = treconsonanti(Città.toUpperCase());
			pst.setString(1, CodiceCorretto + String.valueOf(offset).toString());
			pst.setString(2, Nome);
			pst.setString(3, Città);
			pst.execute();
			buonfine = true;
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
				e.printStackTrace();
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
				
				Aeroporto = new Aeroporto(rs.getString("CodAeroporto"), rs.getString("NomeAeroporto"), rs.getString("Città"));
				
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
				
				Aeroporto = new Aeroporto(rs.getString("CodAeroporto"), rs.getString("NomeAeroporto"), rs.getString("Città"));
				
			}
			st.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
		
		
		return ListaAeroporti;
			
	}
	
	
}	
