package Controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import Classi.Volo;
import DAO.SlotImbarcoDAO;
import DAO.VoloDAO;
import Eccezioni.SlotImbarcoException;
import Eccezioni.VoloException;
import GUI.SlotImbarcoJDialog;

public class ControllerVoli {
		
	VoloDAO VoloDAO = new VoloDAO();
	SlotImbarcoDAO ImbarcoDAO = new SlotImbarcoDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	
	public void InsertVoloAndImbarco(Volo volo, String codGate, String coda){
		
		try {
			VoloDAO.Insert(volo);
			
			java.util.Date dataTmp = (java.util.Date) volo.getData();
			Timestamp dataInizio = new Timestamp(dataTmp.getTime());
			
			ImbarcoDAO.insert(volo.getCodVolo(), codGate, coda, dataInizio);
			successo.setBounds(200,200,400,200);
			testo.setText("Inserimento avvenuto con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
		} catch (VoloException | SlotImbarcoException e) {

			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
				
		}
		
	}
	
	public void apriSlotImbarco(Aeroporto aer, Volo volo) {
		SlotImbarcoJDialog SlotImbarco = new SlotImbarcoJDialog(aer, volo);
		SlotImbarco.setVisible(true);
	}

	public ArrayList<Volo> getAllVoli(Aeroporto a) {
		
		ArrayList<Volo> Voli = new ArrayList<Volo>();
		
		try {
			Voli = VoloDAO.getAllVoli(a.getCodAeroporto());
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
			
			VoloDAO.delete(codVolo);
			
		}catch(VoloException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		
		
	}

	public void updateVolo(int numeroPosti, String codVolo) {

		try {
			
			VoloDAO.updateVolo(numeroPosti, codVolo);
			
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
			
			VoliTrovati = VoloDAO.ricercaVoloByTratta(codTratta);
			
		}catch(VoloException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return VoliTrovati;
	}
	
	

}
