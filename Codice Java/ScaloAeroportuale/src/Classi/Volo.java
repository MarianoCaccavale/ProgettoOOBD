package Classi;

import java.util.Date;

public class Volo {

	private String CodVolo = new String();
	private Date Data = new Date();
	private int NumeroPosti;
	private int NumeroPostiDisponibili;
	private String CompagniaDiAppartenenza;
	private String TrattaAssociata;
	
	public Volo() {}
	
	
	public Volo(String codVolo, Date data, int numeroPosti, int numeroPostiDisponibili) {
		super();
		CodVolo = codVolo;
		Data = data;
		NumeroPosti = numeroPosti;
		NumeroPostiDisponibili = numeroPostiDisponibili;
	}
	
	
	public String getCodVolo() {
		return CodVolo;
	}
	public void setCodVolo(String codVolo) {
		CodVolo = codVolo;
	}
	public Date getData() {
		return Data;
	}
	public void setData(Date data) {
		Data = data;
	}
	public int getNumeroPosti() {
		return NumeroPosti;
	}
	public void setNumeroPosti(int numeroPosti) {
		NumeroPosti = numeroPosti;
	}
	public int getNumeroPostiDisponibili() {
		return NumeroPostiDisponibili;
	}
	public void setNumeroPostiDisponibili(int numeroPostiDisponibili) {
		NumeroPostiDisponibili = numeroPostiDisponibili;
	}
	public String getCompagniaDiAppartenenza() {
		return CompagniaDiAppartenenza;
	}
	public void setCompagniaDiAppartenenza(String compagniaDiAppartenenza) {
		CompagniaDiAppartenenza = compagniaDiAppartenenza;
	}
	public String getTrattaAssociata() {
		return TrattaAssociata;
	}
	public void setTrattaAssociata(String trattaAssociata) {
		TrattaAssociata = trattaAssociata;
	}
	
	
}
