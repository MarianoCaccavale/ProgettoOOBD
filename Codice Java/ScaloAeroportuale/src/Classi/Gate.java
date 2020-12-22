package Classi;

public class Gate {

	private String CodGate = new String();
	private String NomeGate = new String();
	private Aeroporto AeroportoDiAppartenenza;
	
	
	public Gate(String codGate, String nomeGate) {
		super();
		CodGate = codGate;
		NomeGate = nomeGate;
	}

	public Gate() {
	}

	public String getCodGate() {
		return CodGate;
	}
	
	public void setCodGate(String codGate) {
		CodGate = codGate;
	}
	
	public String getNomeGate() {
		return NomeGate;
	}
	
	public void setNomeGate(String nomeGate) {
		NomeGate = nomeGate;
	}
	
	public Aeroporto getAeroportoDiAppartenenza() {
		return AeroportoDiAppartenenza;
	}
	
	public void setAeroportoDiAppartenenza(Aeroporto aeroportoDiAppartenenza) {
		AeroportoDiAppartenenza = aeroportoDiAppartenenza;
	}
	
	
}
