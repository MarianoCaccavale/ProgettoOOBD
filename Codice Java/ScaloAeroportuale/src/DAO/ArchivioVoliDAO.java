package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import Connessione.ConnessioneDB;
import Eccezioni.StatisticheException;

public class ArchivioVoliDAO {

	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	public ArrayList<String[]> getGateChiusi(String codAeroporto, Date tmpDataInizio, Date tmpDataFine) throws StatisticheException {
		
		ArrayList<String[]> listaTempi = new ArrayList<String[]>();
		
		try {
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			PreparedStatement pt = conn.prepareStatement("select g.codgate, sum(extract(hour from(tempomax-datainizio))) as tempostimato, sum(extract(hour from(datafine - datainizio)))as tempoeffettivo from archiviovoli as s natural join gate as g where g.codaeroporto = ? AND ( ? <= s.datainizio AND s.datainizio <= ?) AND (? <= s.datafine AND s.datafine <= ?) group by g.codgate");
			
			Timestamp dataInizio = new Timestamp(tmpDataInizio.getTime());
			Timestamp dataFine = new Timestamp(tmpDataFine.getTime());
			
			pt.setString(1, codAeroporto);
			pt.setTimestamp(2, dataInizio);
			pt.setTimestamp(3, dataFine);
			pt.setTimestamp(4, dataInizio);
			pt.setTimestamp(5, dataFine);
			
			
			ResultSet rs = pt.executeQuery();
			
			while (rs.next()) {
				
				String tmp[] = new String[3];
				tmp[0] = rs.getString(1);
				tmp[1] = rs.getString(2);
				tmp[2] = rs.getString(3);
				
				listaTempi.add(tmp);
				
			}
			
			pt.close();
			rs.close();
			conn.close();
			
			
			
		}catch(SQLException e) {
			
			throw new StatisticheException(e.getMessage());
			
		}
		
		
		return listaTempi;
	}
	
	
}
