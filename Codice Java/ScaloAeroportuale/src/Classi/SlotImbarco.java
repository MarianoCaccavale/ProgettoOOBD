package Classi;


import java.sql.Timestamp;

public class SlotImbarco {

	private Volo volo;
	private Gate Gate;
	private Timestamp OraInizio;
	
	
	public SlotImbarco() {}

	public SlotImbarco(Volo Volo, Gate gate, Timestamp oraInizio) {
		super();
		volo = Volo;
		Gate = gate;
		OraInizio = oraInizio;
	}

	public Volo getVolo() {
		return volo;
	}
	
	public void setVolo(Volo Volo) {
		volo = Volo;
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
