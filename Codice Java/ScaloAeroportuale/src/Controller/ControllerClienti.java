package Controller;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.ClienteBusiness;
import Classi.CompagniaAerea;
import DAO.ClientiDAO;
import DAO.CompagniaAereaDAO;
import Eccezioni.ClientiException;
import Eccezioni.CompagniaException;

public class ControllerClienti {

	ClientiDAO DAOClienti = new ClientiDAO();
	CompagniaAereaDAO DAOCompagnia = new CompagniaAereaDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	

	public void insert(String email, String nome, String cognome, Date dataNascita, String codCompagnia) {
		
		try {
			
			CompagniaAerea compagnia = DAOCompagnia.getCompagniaByNome(codCompagnia);
			DAOClienti.insert(email, nome, cognome, dataNascita, compagnia);
			successo.setBounds(200,200,400,200);
			testo.setText("Inserimento avvenuto con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
		}catch(ClientiException | CompagniaException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
	}


	public void delete(String email) {
		
		
		try {
			
			DAOClienti.delete(email);
			successo.setBounds(200,200,400,200);
			testo.setText("Cliente eliminato con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
			
		}catch(ClientiException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
	}


	public ArrayList<ClienteBusiness> getAllClientiBusiness() {
		
		ArrayList<ClienteBusiness> Clienti = new ArrayList<ClienteBusiness>();
		
		try {
			
			Clienti = DAOClienti.getAllClientiBusiness();
						
		}catch(ClientiException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return Clienti;
		
	}


	public ArrayList<ClienteBusiness> getClientiBusinessByCompagnia(String nomeCompagnia) {
		
		ArrayList<ClienteBusiness> Clienti = new ArrayList<ClienteBusiness>();
		
		try {
			
			CompagniaAerea compagnia = DAOCompagnia.getCompagniaByNome(nomeCompagnia);
			Clienti = DAOClienti.getClientiBusinessByCompagnia(compagnia);
						
		}catch(ClientiException | CompagniaException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return Clienti;
		
	}

}

