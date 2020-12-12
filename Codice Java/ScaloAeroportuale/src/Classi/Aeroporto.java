package Classi;

import java.util.ArrayList;

public class Aeroporto {

	private String CodAeroporto = new String();
	private String NomeAeroporto = new String();
	private String Citt� = new String();
	private ArrayList<CompagniaAerea> CompagnieStanziate;
	
	public Aeroporto() {
		super();
	}
	
	
	public Aeroporto(String codAeroporto, String nomeAeroporto, String citt�) {
		super();
		CodAeroporto = codAeroporto;
		NomeAeroporto = nomeAeroporto;
		Citt� = citt�;
	}

	public String getCodAeroporto() {
		return CodAeroporto;
	}
	
	public void setCodAeroporto(String codAeroporto) {
		CodAeroporto = codAeroporto;
	}
	
	public String getNomeAeroporto() {
		return NomeAeroporto;
	}
	
	public void setNomeAeroporto(String nomeAeroporto) {
		NomeAeroporto = nomeAeroporto;
	}
	
	public String getCitt�() {
		return Citt�;
	}
	
	public void setCitt�(String citt�) {
		Citt� = citt�;
	}


	public ArrayList<CompagniaAerea> getCompagnieStanziate() {
		return CompagnieStanziate;
	}


	public void addCompagniaAerea(CompagniaAerea compagnia) {
		CompagnieStanziate.add(compagnia);
	}
	
	
	public void setCompagnieStanziate(ArrayList<CompagniaAerea> compagnieStanziate) {
		CompagnieStanziate = compagnieStanziate;
	}
	
	
	
}
