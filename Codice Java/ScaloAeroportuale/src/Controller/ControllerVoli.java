package Controller;

import java.sql.Date;
import java.sql.Timestamp;

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
	
	public void InsertVoloAndImbarco(Volo volo, String codGate, String coda, Timestamp dataChiusura) {
		
		try {
			VoloDAO.Insert(volo);
			
			java.util.Date dataTmp = (java.util.Date) volo.getData();
			Timestamp dataInizio = new Timestamp(dataTmp.getTime());
			
			ImbarcoDAO.insert(volo.getCodVolo(), codGate, coda, dataInizio, dataChiusura);
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

}
