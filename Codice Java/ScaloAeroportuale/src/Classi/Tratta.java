package Classi;

import java.util.ArrayList;

public class Tratta {

	private Aeroporto AeroportoDiPartenza;
	private Aeroporto AeroportoDiArrivo;
	private ArrayList<SlotImbarco> slotImbarcoOspitati = new ArrayList<SlotImbarco>();
	
	
	public Tratta() {}
	
	public Tratta(Aeroporto aeroportoDiPartenza, Aeroporto aeroportoDiArrivo) {
		super();
		AeroportoDiPartenza = aeroportoDiPartenza;
		AeroportoDiArrivo = aeroportoDiArrivo;
	}

	public Aeroporto getAeroportoDiPartenza() {
		return AeroportoDiPartenza;
	}
	
	public void setAeroportoDiPartenza(Aeroporto aeroportoDiPartenza) {
		AeroportoDiPartenza = aeroportoDiPartenza;
	}
	
	public Aeroporto getAeroportoDiArrivo() {
		return AeroportoDiArrivo;
	}
	
	public void setAeroportoDiArrivo(Aeroporto aeroportoDiArrivo) {
		AeroportoDiArrivo = aeroportoDiArrivo;
	}

	public ArrayList<SlotImbarco> getSlotImbarcoOspitati() {
		return slotImbarcoOspitati;
	}

	public void setSlotImbarcoOspitati(ArrayList<SlotImbarco> slotImbarcoOspitati) {
		this.slotImbarcoOspitati = slotImbarcoOspitati;
	}
	
}
