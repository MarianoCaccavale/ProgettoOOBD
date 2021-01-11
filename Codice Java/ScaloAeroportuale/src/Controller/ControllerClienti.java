package Controller;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.ClienteBusiness;
import Classi.CompagniaAerea;
import DAO.ClientiDAO;
import Eccezioni.ClientiException;

public class ControllerClienti {

	ClientiDAO DAOClienti = new ClientiDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	

	public void insert(String email, String nome, String cognome, Date dataNascita, CompagniaAerea compagnia) {
		
		try {
			
			DAOClienti.insert(email, nome, cognome, dataNascita, compagnia);
			successo.setBounds(200,200,400,200);
			testo.setText("Inserimento avvenuto con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
		}catch(ClientiException e) {
			
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


	public ArrayList<ClienteBusiness> getClientiBusinessByCompagnia(CompagniaAerea compagnia) {
		
		ArrayList<ClienteBusiness> Clienti = new ArrayList<ClienteBusiness>();
		
		try {
			
			Clienti = DAOClienti.getClientiBusinessByCompagnia(compagnia);
						
		}catch(ClientiException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return Clienti;
		
	}

}

