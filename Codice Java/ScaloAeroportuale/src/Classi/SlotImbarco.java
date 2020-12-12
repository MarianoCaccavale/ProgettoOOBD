package Classi;

import java.sql.Time;

public class SlotImbarco {

	private Volo Volo;
	private Tratta Tratta;
	private Gate Gate;
	private Time OraInizio = new Time(0);
	

	public Volo getVolo() {
		return Volo;
	}
	
	public void setVolo(Volo volo) {
		Volo = volo;
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
	
	public Time getOraInizio() {
		return OraInizio;
	}
	
	public void setOraInizio(Time oraInizio) {
		OraInizio = oraInizio;
	} 
	
}
