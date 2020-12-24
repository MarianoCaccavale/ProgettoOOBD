package Classi;

public class Tratta {

	
	private String CodTratta = new String();
	private String AeroportoDiPartenza;
	private String AeroportoDiArrivo;
	
	
	public Tratta() {}
	
	public Tratta(String codTratta, String aeroportoDiPartenza, String aeroportoDiArrivo) {
		super();
		CodTratta = codTratta;
		AeroportoDiPartenza = aeroportoDiPartenza;
		AeroportoDiArrivo = aeroportoDiArrivo;
	}

	public String getCodTratta() {
		return CodTratta;
	}
	
	public void setCodTratta(String codTratta) {
		CodTratta = codTratta;
	}
	
	public String getAeroportoDiPartenza() {
		return AeroportoDiPartenza;
	}
	
	public void setAeroportoDiPartenza(String aeroportoDiPartenza) {
		AeroportoDiPartenza = aeroportoDiPartenza;
	}
	
	public String getAeroportoDiArrivo() {
		return AeroportoDiArrivo;
	}
	
	public void setAeroportoDiArrivo(String aeroportoDiArrivo) {
		AeroportoDiArrivo = aeroportoDiArrivo;
	}
	
}
