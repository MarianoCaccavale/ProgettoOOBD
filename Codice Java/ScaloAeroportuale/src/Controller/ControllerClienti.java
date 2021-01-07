package Controller;

import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JTextField;

import DAO.ClientiDAO;
import Eccezioni.ClientiException;

public class ControllerClienti {

	ClientiDAO DAO = new ClientiDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	

	public void insert(String email, String nome, String cognome, Date dataNascita, String codCompagnia) {
		
		try {
			
			DAO.insert(email, nome, cognome, dataNascita, codCompagnia);
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
			
			DAO.delete(email);
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
	
}
