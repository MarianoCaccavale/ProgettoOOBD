package Controller;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import DAO.CompagniaAereaDao;

public class ControllerCompagnie {

	public ControllerCompagnie() {}
	
	public void Insert(String Nome, int Flotta, Aeroporto a) {
		
		CompagniaAereaDao comp = new CompagniaAereaDao();
		
		if (comp.InsertCompagnia(Nome, Flotta, a)) {
			
			JDialog successo = new JDialog();
			JTextField testo = new JTextField("Inserimento avvenuto con successo!");
			successo.setBounds(100,100,100,100);
			successo.add(testo);
			successo.setVisible(true);
			
		}else {
			
			JDialog successo = new JDialog();
			JTextField testo = new JTextField("Inserimento fallito!");
			successo.setBounds(100,100,100,100);
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
	}
	
	
	
}
