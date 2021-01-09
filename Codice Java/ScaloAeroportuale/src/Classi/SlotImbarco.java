package Classi;


import java.sql.Timestamp;

public class SlotImbarco {

	private Volo volo;
	private Tratta Tratta;
	private Gate Gate;
	private Timestamp OraInizio;
	

	public SlotImbarco(Volo Volo, Tratta tratta, Gate gate, Timestamp oraInizio) {
		super();
		volo = Volo;
		Tratta = tratta;
		Gate = gate;
		OraInizio = oraInizio;
	}

	public Volo getVolo() {
		return volo;
	}
	
	public void setVolo(Volo Volo) {
		volo = Volo;
	}
	
	public Tratta getTratta() {
		return Tratta;
	}
	
	public void setTratta(Tratta tratta) {
		Tratta = tratta;
	}
	
	public Gate getGate() {
		return Gate;
	}
	
	public void setGate(Gate gate) {
		Gate = gate;
	}
	
	public Timestamp getOraInizio() {
		return OraInizio;
	}
	
	public void setOraInizio(Timestamp oraInizio) {
		OraInizio = oraInizio;
	} 
	
}
