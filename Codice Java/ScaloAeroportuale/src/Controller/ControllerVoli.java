package Controller;

import java.sql.Date;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import DAO.AeroportoDAO;
import DAO.VoloDAO;
import Eccezioni.TrattaException;
import Eccezioni.VoloException;
import GUI.SlotImbarcoJDialog;

public class ControllerVoli {
		
	VoloDAO DAO = new VoloDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	
	public void InsertVolo(Date data, int numPosti, String Compagnia, String Tratta) {
		
		try {
			DAO.Insert(data, numPosti, Compagnia, Tratta);
			successo.setBounds(200,200,400,200);
			testo.setText("Inserimento avvenuto con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
		} catch (VoloException e) {

			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
				
		}
		
	}
	
	public void apriSlotImbarco(Aeroporto aer) {
		SlotImbarcoJDialog SlotImbarco = new SlotImbarcoJDialog(aer);
		SlotImbarco.setVisible(true);
	}

}
