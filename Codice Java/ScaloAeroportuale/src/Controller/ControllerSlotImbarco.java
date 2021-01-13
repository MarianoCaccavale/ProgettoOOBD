package Controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import Classi.SlotImbarco;
import Classi.Volo;
import DAO.SlotImbarcoDAO;
import Eccezioni.SlotImbarcoException;

public class ControllerSlotImbarco {

	SlotImbarcoDAO DAO = new SlotImbarcoDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	
	public void insert(Aeroporto a, Volo volo, String nomeGate, String coda) {
		
		
		try {
			
			Timestamp dataInizio = new Timestamp(volo.getData().getTime());
			
			DAO.insert(a, volo, nomeGate, coda, dataInizio);
			
		}catch(SlotImbarcoException e) {
									
		}
		
	}

	public ArrayList<SlotImbarco> getImbarchi(String codAeroporto) {
		
		ArrayList<SlotImbarco> listaSlotImbarco = new ArrayList<SlotImbarco>();
		try {
			listaSlotImbarco = DAO.getAllImbarchi(codAeroporto);
		} catch (SlotImbarcoException e) {
			
			successo.setBounds(200,200,600,100);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return listaSlotImbarco;
	}

	public void closeSlotImbarco(String codVolo, Timestamp dataFine) {
		
		try {
			
			DAO.closeSlotImbarco(codVolo, dataFine);
			successo.setBounds(200,200,600,100);
			testo.setText("Chiusura avvenuta con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
		}catch(SlotImbarcoException e) {
			
			 if (e.getMessage().contains("la nuova riga per la relazione \"slotimbarco\" viola il vincolo di controllo \"oracorretta\"")) {
					JDialog successo = new JDialog();
					JTextField testo = new JTextField();
					successo.setBounds(200,200,600,100);
					testo.setText("Impossibile chiudere lo SlotImbarco. Verificare che la data inserita sia corretta e non antecedente al volo"); 
					successo.add(testo);
					successo.setVisible(true);
					
				}else {
					
					successo.setBounds(200,200,600,100);
					testo.setText(e.getMessage().toString()); 
					successo.add(testo);
					successo.setVisible(true);
					
				}
			
		}
		
		
		
	}

	public ArrayList<SlotImbarco> ricercaSlotDaChiudere(String codAeroporto) {
		
		ArrayList<SlotImbarco> SlotDaChiudere = new ArrayList<SlotImbarco>();
		
		try {
			
			SlotDaChiudere = DAO.getSlotDaChiudere(codAeroporto);
			
		} catch (SlotImbarcoException e) {
			
			successo.setBounds(200,200,600,100);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return SlotDaChiudere;
	}
	
	
}
