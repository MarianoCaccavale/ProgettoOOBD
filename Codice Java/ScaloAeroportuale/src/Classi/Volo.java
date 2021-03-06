package Classi;


import java.sql.Timestamp;

public class Volo {

	private String CodVolo = new String();
	private Timestamp Data;
	private int NumeroPosti;
	private int NumeroPostiPrenotati;
	private CompagniaAerea CompagniaDiAppartenenza;
	private Tratta TrattaAssociata;
	private SlotImbarco slotImbarco = new SlotImbarco();
	
	public Volo() {}
	
	
	public Volo(String codVolo, Timestamp data, int numeroPosti, int numeroPostiPrenotati) {
		super();
		CodVolo = codVolo;
		Data = data;
		NumeroPosti = numeroPosti;
		NumeroPostiPrenotati = numeroPostiPrenotati;
	}
		
	public Volo(String codVolo, Timestamp data, int numeroPosti, int numeroPostiPrenotati, CompagniaAerea compagniaDiAppartenenza,
			Tratta trattaAssociata) {
		super();
		CodVolo = codVolo;
		Data = data;
		NumeroPosti = numeroPosti;
		NumeroPostiPrenotati = numeroPostiPrenotati;
		CompagniaDiAppartenenza = compagniaDiAppartenenza;
		TrattaAssociata = trattaAssociata;
	}


	public String getCodVolo() {
		return CodVolo;
	}
	public void setCodVolo(String codVolo) {
		CodVolo = codVolo;
	}
	public Timestamp getData() {
		return Data;
	}
	public void setData(Timestamp data) {
		Data = data;
	}
	public int getNumeroPosti() {
		return NumeroPosti;
	}
	public void setNumeroPosti(int numeroPosti) {
		NumeroPosti = numeroPosti;
	}
	public int getNumeroPostiPrenotati() {
		return NumeroPostiPrenotati;
	}
	public void setNumeroPostiPrenotati(int numeroPostiPrenotati) {
		NumeroPostiPrenotati = numeroPostiPrenotati;
	}
	public CompagniaAerea getCompagniaDiAppartenenza() {
		return CompagniaDiAppartenenza;
	}
	public void setCompagniaDiAppartenenza(CompagniaAerea compagniaDiAppartenenza) {
		CompagniaDiAppartenenza = compagniaDiAppartenenza;
	}
	public Tratta getTrattaAssociata() {
		return TrattaAssociata;
	}
	public void setTrattaAssociata(Tratta trattaAssociata) {
		TrattaAssociata = trattaAssociata;
	}

	public SlotImbarco getSlotImbarco() {
		return slotImbarco;
	}

	public void setSlotImbarco(SlotImbarco slotImbarco) {
		this.slotImbarco = slotImbarco;
	}
	
	
}
