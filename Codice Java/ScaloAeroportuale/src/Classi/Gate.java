package Classi;

public class Gate {

	private String NomeGate = new String();
	private Aeroporto AeroportoDiAppartenenza;
	
	
	public Gate(String nomeGate) {
		super();
		NomeGate = nomeGate;
	}

	public Gate() {
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
