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
			PreparedStatement pt = conn.prepareStatement("Select ris1.codgate, ris1.tempostimato, ris1.tempoeffettivo from (Select codgate, sum(extract(hour from(tempomax-datainizio))) as tempostimato, sum(extract(hour from(datafine - datainizio))) as tempoeffettivo from archiviovoli as av natural join gate as g where (? <= av.datainizio AND av.datainizio <= ?) AND (? <= av.datafine AND av.datafine <= ?)  GROUP BY codgate) as ris1 natural join aeroporto as a where a.codaeroporto = ?");
			
			Timestamp dataInizio = new Timestamp(tmpDataInizio.getTime());
			Timestamp dataFine = new Timestamp(tmpDataFine.getTime());
			
			pt.setTimestamp(1, dataInizio);
			pt.setTimestamp(2, dataFine);
			pt.setTimestamp(3, dataInizio);
			pt.setTimestamp(4, dataFine);
			pt.setString(5, codAeroporto);
			
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
