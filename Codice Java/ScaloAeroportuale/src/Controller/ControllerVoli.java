package Controller;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import Classi.Tratta;
import Classi.Volo;
import DAO.VoloDAO;
import Eccezioni.VoloException;

public class ControllerVoli {
		
	VoloDAO voloDAO = new VoloDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	
	public void InsertVolo(Volo volo){
		
		try {

			voloDAO.Insert(volo);
			
			successo.setBounds(200,200,400,200);
			testo.setText("Inserimento avvenuto con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (VoloException e) {
			
			if (e.getMessage().contains("controllo_voli_flotta()")) {
				
				successo.setBounds(200,200,400,200);
				testo.setText("Questa compagnia non ha più aerei disponibili"); 
				successo.add(testo);
				successo.setVisible(true);
				
			}else {
				
				successo.setBounds(200,200,400,200);
				testo.setText(e.getMessage().toString()); 
				successo.add(testo);
				successo.setVisible(true);
				
			}
				
		}
		
	}

	public ArrayList<Volo> getAllVoli(Aeroporto a) {
		
		ArrayList<Volo> Voli = new ArrayList<Volo>();
		
		try {
			Voli = voloDAO.getAllVoli(a);
		} catch (VoloException e) {
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
		}
		
		
		return Voli;
	}
	
	public void deleteVolo(String codVolo) {
		
		try {
			
			voloDAO.delete(codVolo);
			successo.setBounds(200,200,400,200);
			testo.setText("Cancellazione del volo eseguita correttamente!"); 
			successo.add(testo);
			successo.setVisible(true);
			
		}catch(VoloException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		
		
	}

	public void updateVolo(int numeroPosti, String codVolo) {

		try {
			
			voloDAO.updateVolo(numeroPosti, codVolo);
			successo.setBounds(200,200,400,200);
			testo.setText("Modifica effettuata con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
		}catch(VoloException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		
	}

	public ArrayList<Volo> ricercaVoliByTratta(Tratta tratta) {
		
		ArrayList<Volo> VoliTrovati = new ArrayList<Volo>();
		
		try {
			
			VoliTrovati = voloDAO.ricercaVoloByTratta(tratta);
			
		}catch(VoloException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return VoliTrovati;
	}

	public void generateTicket(String codVolo, Integer numBiglietti) {

		try {
			
			voloDAO.generateTicket(codVolo, numBiglietti);
			successo.setBounds(200,200,400,200);
			testo.setText("Biglietto/i generati con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
			
		}catch(VoloException e) {
			
			if (e.getMessage().contains("numeropostiprenotaticorretti")) {
				
				successo.setBounds(200,200,400,200);
				testo.setText("Posti esauriti per questo volo"); 
				successo.add(testo);
				successo.setVisible(true);
				
			}else {
				successo.setBounds(200,200,400,200);
				testo.setText(e.getMessage().toString()); 
				successo.add(testo);
				successo.setVisible(true);
			}
			
		}
		
		
	}

	public void generateBusinessTicket(String codVolo, int numBiglietti, String email) {

		try {
			
			voloDAO.generateBusinessTicket(codVolo, numBiglietti, email);
			successo.setBounds(200,200,400,200);
			testo.setText("Biglietto/i generati con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
			
		}catch(VoloException e) {

			if (e.getMessage().contains("numeropostiprenotaticorretti")) {
				
				successo.setBounds(200,200,400,200);
				testo.setText("Posti esauriti per questo volo"); 
				successo.add(testo);
				successo.setVisible(true);
				
			}else {
				successo.setBounds(200,200,400,200);
				testo.setText(e.getMessage().toString()); 
				successo.add(testo);
				successo.setVisible(true);
			}
			
		}
		
	}
	
	

}
