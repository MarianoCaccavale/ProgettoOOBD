package Controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import Classi.SlotImbarco;
import DAO.SlotImbarcoDAO;
import Eccezioni.SlotImbarcoException;

public class ControllerSlotImbarco {

	SlotImbarcoDAO DAO = new SlotImbarcoDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();

	public ArrayList<SlotImbarco> getImbarchi(String codAeroporto) {
		
		ArrayList<SlotImbarco> listaSlotImbarco = new ArrayList<SlotImbarco>();
		try {
			listaSlotImbarco = DAO.getAllImbarchi(codAeroporto);
		} catch (SlotImbarcoException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return listaSlotImbarco;
	}

	public void closeSlotImbarco(String codVolo, String codGate, Timestamp dataFine) {
		
		try {
			
			DAO.closeSlotImbarco(codVolo, codGate, dataFine);
			successo.setBounds(200,200,400,200);
			testo.setText("Chiusura avvenuta con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
		}catch(SlotImbarcoException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		
		
	}

	public ArrayList<SlotImbarco> ricercaSlotDaChiudere(String codAeroporto) {
		
		ArrayList<SlotImbarco> SlotDaChiudere = new ArrayList<SlotImbarco>();
		
		try {
			
			SlotDaChiudere = DAO.getSlotDaChiudere(codAeroporto);
			
		} catch (SlotImbarcoException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return SlotDaChiudere;
	}
	
	
}
