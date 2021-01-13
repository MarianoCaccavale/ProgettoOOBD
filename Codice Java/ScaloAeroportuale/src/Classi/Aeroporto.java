package Classi;

import java.util.ArrayList;

public class Aeroporto {

	private String CodAeroporto = new String();
	private String NomeAeroporto = new String();
	private String Città = new String();
	private ArrayList<Gate> Gate = new ArrayList<Gate>();			
	private ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
	private ArrayList<CompagniaAerea> CompagnieStanziate;
	
	public Aeroporto() {
		super();
	}
	
	public Aeroporto(String nome) {
		super();
		NomeAeroporto = nome;
	}
	
	public Aeroporto(String codAeroporto, String nomeAeroporto, String città) {
		super();
		CodAeroporto = codAeroporto;
		NomeAeroporto = nomeAeroporto;
		Città = città;
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
	
	public String getCittà() {
		return Città;
	}
	
	public void setCittà(String città) {
		Città = città;
	}
	
	public ArrayList<Gate> getGate() {
		return Gate;
	}

	public void setGate(ArrayList<Gate> gate) {
		Gate = gate;
	}
	
	public ArrayList<Tratta> getTratte() {
		return Tratte;
	}

	public void setTratte(ArrayList<Tratta> tratte) {
		Tratte = tratte;
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
