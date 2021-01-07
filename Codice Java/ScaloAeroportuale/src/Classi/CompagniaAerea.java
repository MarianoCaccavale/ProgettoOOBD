package Classi;

public class CompagniaAerea {

	private String CodCompagnia = new String();
	private String NomeCompagnia = new String();
	private int GrandezzaFlotta;
	private String AeroportoDiAppartenenza;
	private String CodCentoKilometri;
	
	public CompagniaAerea() {}
	
	public CompagniaAerea(String codCompagnia, String nomeCompagnia, int grandezzaFlotta) {
		super();
		CodCompagnia = codCompagnia;
		NomeCompagnia = nomeCompagnia;
		GrandezzaFlotta = grandezzaFlotta;
	}



	public String getCodCompagnia() {
		return CodCompagnia;
	}
	
	public void setCodCompagnia(String codCompagnia) {
		CodCompagnia = codCompagnia;
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
	
	public String getAeroportoDiAppartenenza() {
		return AeroportoDiAppartenenza;
	}
	
	public void setAeroportoDiAppartenenza(String aeroportoDiAppartenenza) {
		AeroportoDiAppartenenza = aeroportoDiAppartenenza;
	}

	public String getCodCentoKilometri() {
		return CodCentoKilometri;
	}

	public void setCodCentoKilometri(String codCentoKilometri) {
		CodCentoKilometri = codCentoKilometri;
	}
	
	
}
