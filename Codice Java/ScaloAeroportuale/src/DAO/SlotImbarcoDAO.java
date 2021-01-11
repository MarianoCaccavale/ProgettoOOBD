package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import Classi.Aeroporto;
import Classi.Gate;
import Classi.SlotImbarco;
import Classi.Tratta;
import Classi.Volo;
import Connessione.ConnessioneDB;
import Eccezioni.SlotImbarcoException;

public class SlotImbarcoDAO {

	private Connection conn = null;
	private ConnessioneDB connessioneDB;
	String errore = new String("");
	
	@SuppressWarnings("deprecation")
	public void insert(Aeroporto aer, Volo volo, String nomeGate, String coda, Timestamp dataInizio) throws SlotImbarcoException {
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement codiceGate = conn.prepareStatement("select codgate from gate where nomegate = ? AND codaeroporto = ?");
			codiceGate.setString(1, nomeGate);
			codiceGate.setString(2, aer.getCodAeroporto());
			ResultSet rs_codiceGate = codiceGate.executeQuery();
			rs_codiceGate.next();
			String codGate = rs_codiceGate.getString("codgate");
			
			PreparedStatement pst = conn.prepareStatement("Insert into SlotImbarco values(?, ?, ?, ?, ?, ?)");
			
			pst.setString(1, volo.getCodVolo());
			pst.setString(2, codGate);
			
			pst.setTimestamp(4, dataInizio);
			
			Timestamp dataChiusura = dataInizio;
			dataChiusura.setHours(dataInizio.getHours() + 1);
			
			pst.setTimestamp(3, dataChiusura);
			pst.setTimestamp(5, null);
			pst.setString(6, coda);
					
			pst.executeUpdate();
			pst.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			if (errore.contains("tempomax_check")) {
				
				throw new SlotImbarcoException("Impossibile inserire un volo con chiusura gate stimata antecedente al volo.");
				
			}else{
				
				throw new SlotImbarcoException(errore);
				
			}
			
		}
		
	}

	public ArrayList<SlotImbarco> getAllImbarchi(String codAeroporto) throws SlotImbarcoException{
		
		ArrayList<SlotImbarco> risultato = new ArrayList<SlotImbarco>();
		Volo voloTmp = new Volo();
		Tratta trattaTmp = new Tratta();
		Gate gateTmp = new Gate();
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement pst = conn.prepareStatement("select vt.codvolo, si.datainizio, vt.numeroposti, vt.numeropostiprenotati, a1.nomeaeroporto as nomea1, a2.nomeaeroporto as nomea2, g.nomegate from slotimbarco as si natural join (volo as v natural join tratta as t) as vt natural join gate as g join aeroporto as a1 on vt.aeroportopartenza = a1.codaeroporto join aeroporto as a2 on vt.aeroportoarrivo = a2.codaeroporto where vt.aeroportopartenza = ? AND si.datafine is null and si.datainizio < now()");
			
			pst.setString(1, codAeroporto);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				voloTmp = new Volo(rs.getString("codvolo"), rs.getTimestamp("datainizio"), rs.getInt("numeroposti"), rs.getInt("numeropostiprenotati"));
				Aeroporto aeroportoPartenza = new Aeroporto(rs.getString("nomea1"));
				Aeroporto aeroportoArrivo = new Aeroporto(rs.getString("nomea2"));
				trattaTmp = new Tratta(aeroportoPartenza, aeroportoArrivo);
				gateTmp = new Gate(rs.getString("nomegate"));
				
				SlotImbarco tmp = new SlotImbarco(voloTmp, trattaTmp, gateTmp, rs.getTimestamp("datainizio"));
				risultato.add(tmp);
				
			}
			pst.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new SlotImbarcoException(errore);
			
			
		}
		
		
		return risultato;
	}

	public void closeSlotImbarco(String codVolo, Timestamp dataFine) throws SlotImbarcoException {
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement pst = conn.prepareStatement("update SlotImbarco set datafine = ? where codVolo = ?");
			
			pst.setTimestamp(1, dataFine);
			pst.setString(2, codVolo);
			
			pst.executeUpdate();
			
			pst.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new SlotImbarcoException(errore);
			
			
		}
		
	}

	public ArrayList<SlotImbarco> getSlotDaChiudere(String codAeroporto) throws SlotImbarcoException {
		
		ArrayList<SlotImbarco> SlotDaChiudere = new ArrayList<SlotImbarco>();
		Volo voloTmp = new Volo();
		Tratta trattaTmp = new Tratta();
		Gate gateTmp = new Gate();
		
		try{
			
			connessioneDB = ConnessioneDB.getIstanza();
			conn = connessioneDB.getConnection();
			
			PreparedStatement pst = conn.prepareStatement("select vt.codvolo, si.datainizio, vt.numeroposti, vt.numeropostiprenotati, a1.nomeaeroporto as nomea1, a2.nomeaeroporto as nomea2, g.nomegate from slotimbarco as si natural join (volo as v natural join tratta as t) as vt natural join gate as g join aeroporto as a1 on vt.aeroportopartenza = a1.codaeroporto join aeroporto as a2 on vt.aeroportoarrivo = a2.codaeroporto where vt.aeroportopartenza = ? AND si.datafine is null and si.datainizio < now()");
			
			pst.setString(1, codAeroporto);
						
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				voloTmp = new Volo(rs.getString("codvolo"), rs.getTimestamp("datainizio"), rs.getInt("numeroposti"), rs.getInt("numeropostiprenotati"));
				Aeroporto aeroportoPartenza = new Aeroporto(rs.getString("nomea1"));
				Aeroporto aeroportoArrivo = new Aeroporto(rs.getString("nomea2"));
				trattaTmp = new Tratta(aeroportoPartenza, aeroportoArrivo);
				gateTmp = new Gate(rs.getString("nomegate"));
				
				SlotImbarco tmp = new SlotImbarco(voloTmp, trattaTmp, gateTmp, rs.getTimestamp("datainizio"));
				SlotDaChiudere.add(tmp);
				
			}
			pst.close();
			conn.close();
			
		}catch(SQLException e) {
			
			errore = e.getMessage();
			
			throw new SlotImbarcoException(errore);
			
		}
		
		return SlotDaChiudere;
		
	}
	
	
	
	
	
}
