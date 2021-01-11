package Classi;

public class CompagniaAerea {

	private String NomeCompagnia = new String();
	private int GrandezzaFlotta;
	private CompagniaAerea AeroportoDiAppartenenza;
	private String CodCentoKilometri;
	
	public CompagniaAerea() {}
	
	public CompagniaAerea(String nomeCompagnia, int grandezzaFlotta) {
		super();
		NomeCompagnia = nomeCompagnia;
		GrandezzaFlotta = grandezzaFlotta;
	}
	
	public CompagniaAerea(String nomeCompagnia, int grandezzaFlotta, String codCentoKilometri) {
		super();
		NomeCompagnia = nomeCompagnia;
		GrandezzaFlotta = grandezzaFlotta;
		CodCentoKilometri = codCentoKilometri;
	}

	public String getNomeCompagnia() {
		return NomeCompagnia;
	}
	
	public void setNomeCompagnia(String nomeCompagnia) {
		NomeCompagnia = nomeCompagnia;
	}
	
	public int getGrandezzaFlotta() {
		return GrandezzaFlotta;
	}
	
	public void setGrandezzaFlotta(int grandezzaFlotta) {
		GrandezzaFlotta = grandezzaFlotta;
	}
	
	public CompagniaAerea getAeroportoDiAppartenenza() {
		return AeroportoDiAppartenenza;
	}
	
	public void setAeroportoDiAppartenenza(CompagniaAerea aeroportoDiAppartenenza) {
		AeroportoDiAppartenenza = aeroportoDiAppartenenza;
	}

	public String getCodCentoKilometri() {
		return CodCentoKilometri;
	}

	public void setCodCentoKilometri(String codCentoKilometri) {
		CodCentoKilometri = codCentoKilometri;
	}
	
	
}
