package Classi;

import java.sql.Date;

public class ClienteBusiness {
	
	private String email = new String();
	private String nome = new String();
	private String cognome = new String();
	private Date dataNascita = new Date(0);
	private Date dataIscrizione = new Date(0);
	private int punti;
	private String codCentoKilometri = new String();
	
	public void CLienteBusiness() {};
		
	public ClienteBusiness(String email, String nome, String cognome, int punti, String codCentoKilometri) {
		super();
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.punti = punti;
		this.codCentoKilometri = codCentoKilometri;
	}



	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}
	public String getCodCentoKilometri() {
		return codCentoKilometri;
	}
	public void setCodCentoKilometri(String codCentoKilometri) {
		this.codCentoKilometri = codCentoKilometri;
	}
	
}
