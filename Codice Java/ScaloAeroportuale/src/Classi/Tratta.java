package Classi;

public class Tratta {

	private Aeroporto AeroportoDiPartenza;
	private Aeroporto AeroportoDiArrivo;
	
	
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

}
