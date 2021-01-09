package Classi;

public class Tratta {

	
	private String CodTratta = new String();
	private Aeroporto AeroportoDiPartenza;
	private Aeroporto AeroportoDiArrivo;
	
	
	public Tratta() {}
	
	public Tratta(Aeroporto aeroportoDiPartenza, Aeroporto aeroportoDiArrivo) {
		super();
		AeroportoDiPartenza = aeroportoDiPartenza;
		AeroportoDiArrivo = aeroportoDiArrivo;
	}

	public String getCodTratta() {
		return CodTratta;
	}
	
	public void setCodTratta(String codTratta) {
		CodTratta = codTratta;
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
