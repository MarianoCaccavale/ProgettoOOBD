package Classi;


import java.sql.Timestamp;

public class SlotImbarco {

	private String Volo;
	private String Tratta;
	private String Gate;
	private Timestamp OraInizio;
	

	public SlotImbarco(String volo, String tratta, String gate, Timestamp oraInizio) {
		super();
		Volo = volo;
		Tratta = tratta;
		Gate = gate;
		OraInizio = oraInizio;
	}

	public String getVolo() {
		return Volo;
	}
	
	public void setVolo(String volo) {
		Volo = volo;
	}
	
	public String getTratta() {
		return Tratta;
	}
	
	public void setTratta(String tratta) {
		Tratta = tratta;
	}
	
	public String getGate() {
		return Gate;
	}
	
	public void setGate(String gate) {
		Gate = gate;
	}
	
	public Timestamp getOraInizio() {
		return OraInizio;
	}
	
	public void setOraInizio(Timestamp oraInizio) {
		OraInizio = oraInizio;
	} 
	
}
