package Controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import Classi.Tratta;
import Classi.Volo;
import DAO.CompagniaAereaDAO;
import DAO.SlotImbarcoDAO;
import DAO.TrattaDAO;
import DAO.VoloDAO;
import Eccezioni.CompagniaException;
import Eccezioni.SlotImbarcoException;
import Eccezioni.TrattaException;
import Eccezioni.VoloException;
import GUI.SlotImbarcoJDialog;

public class ControllerVoli {
		
	VoloDAO voloDAO = new VoloDAO();
	TrattaDAO trattaDAO = new TrattaDAO();
	CompagniaAereaDAO compagniaDAO = new CompagniaAereaDAO();
	SlotImbarcoDAO imbarcoDAO = new SlotImbarcoDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	
	public void InsertVoloAndImbarco(Aeroporto aeroporto, Volo volo, String codGate, String coda){
		
		try {

			voloDAO.Insert(volo);
			
			java.util.Date dataTmp = (java.util.Date) volo.getData();
			Timestamp dataInizio = new Timestamp(dataTmp.getTime());
			
			imbarcoDAO.insert(aeroporto, volo.getCodVolo(), codGate, coda, dataInizio);
			successo.setBounds(200,200,400,200);
			testo.setText("Inserimento avvenuto con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (VoloException | SlotImbarcoException e) {
			
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
	
	public void apriSlotImbarco(Aeroporto aer, Volo volo) {
		SlotImbarcoJDialog SlotImbarco = new SlotImbarcoJDialog(aer, volo);
		SlotImbarco.setVisible(true);
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

	public ArrayList<Volo> ricercaVoliByTratta(String codTratta) {
		
		ArrayList<Volo> VoliTrovati = new ArrayList<Volo>();
		
		try {
			
			VoliTrovati = voloDAO.ricercaVoloByTratta(trattaDAO.getTrattaByCod(codTratta));
			
		}catch(VoloException | TrattaException e) {
			
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
