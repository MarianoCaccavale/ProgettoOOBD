package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import Classi.Volo;
import Connessione.ConnessioneDB;
import Eccezioni.VoloException;

public class VoloDAO {
	
	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	
	
	public void Insert (Volo volo) throws VoloException {
		try {
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Insert into volo values(nextval('sequenza_volo'), ?, ?, ?, ?, ?)");
			
			java.util.Date dataTmp = (java.util.Date)volo.getData();
			Timestamp dataVolo = new Timestamp(dataTmp.getTime());
			
			
			ps.setTimestamp(1, dataVolo);
			ps.setInt(2, volo.getNumeroPosti());
			ps.setInt(3, 0);
			ps.setString(4, volo.getTrattaAssociata());
			ps.setString(5, volo.getCompagniaDiAppartenenza());
			ps.executeUpdate();
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select last_value from sequenza_volo");
			rs.next();
			volo.setCodVolo(rs.getString("last_value"));
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			errore = e.getMessage();	
			
			if (errore.contains("validificadata()")){
				throw new VoloException("Impossibile inserire un volo antecedente ad ora");
			}
				
		}
	}

}
