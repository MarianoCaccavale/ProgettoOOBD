package Controller;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import DAO.CompagniaAereaDao;

public class ControllerCompagnie {

	public ControllerCompagnie() {}
	
	public void Insert(String Nome, int Flotta, Aeroporto a) {
		
		CompagniaAereaDao comp = new CompagniaAereaDao();
		
		String errore = comp.InsertCompagnia(Nome, Flotta, a);
		JTextField testo = new JTextField();
		
		if ( errore == "") {
			
			JDialog successo = new JDialog();
			testo.setText("Inserimento avvenuto con successo!");
			successo.setBounds(200,200,200,200);
			successo.add(testo);
			successo.setVisible(true);
			
		}else {
			
			JDialog successo = new JDialog();
			if (errore.contains("compagniaaerea_grandezzaflotta_check")) {
				
				testo.setText("Inserimento fallito! La dimensione della flotta è errata. La grandezza dev'essere compresa tra 50 e 500 unità.");
				
				
			}else if (errore.contains("La chiave (nomecompagnia)=")) {
				
				testo.setText("Inserimento fallito! Esiste già una compagnia con questo nome.");
				
			}else if (errore.contains("nomevuoto")) {
				
				testo.setText("Inserimento fallito! Non è possibile inserire una compagnia con nome vuoto.");
				
			}else {
				
				testo.setText("Inserimento fallito!");
				
			}
			
			successo.setBounds(200,200,200,200);
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
	}
	
	
	
}
