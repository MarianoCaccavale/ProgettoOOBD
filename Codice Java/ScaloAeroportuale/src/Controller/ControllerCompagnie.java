package Controller;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import DAO.CompagniaAereaDao;
import Eccezioni.CompagniaException;

public class ControllerCompagnie {

	public ControllerCompagnie() {}
	
	public void Insert(String Nome, int Flotta, Aeroporto a) {
		
		CompagniaAereaDao DAO = new CompagniaAereaDao();
		JDialog successo = new JDialog();
		
		JTextField testo = new JTextField();
		
		
		try {
			DAO.InsertCompagnia(Nome, Flotta, a);
			
			testo.setText("Inserimento avvenuto con successo!");
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (CompagniaException e) {
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
		}	
				
	}
	
	public void Update(String Nome, Integer NuovaGrandezza, Aeroporto aer) {
		
		CompagniaAereaDao DAO = new CompagniaAereaDao();
		
		JDialog successo = new JDialog();
		JTextField testo = new JTextField();
		
		try {
			DAO.updateByNome(Nome, NuovaGrandezza, aer.getCodAeroporto());
			testo.setText("Modifica avvenuta con successo!");
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (CompagniaException e) {
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
		}
		
	}
	
	public ArrayList<CompagniaAerea> getCompagnie(Aeroporto aer) {
		
		ArrayList<CompagniaAerea> Compagnie = new ArrayList<CompagniaAerea>();
		CompagniaAereaDao DAO = new CompagniaAereaDao();
		Compagnie = DAO.getCompagnieByAeroporto(aer.getCodAeroporto());
	
		return Compagnie;
	}

	public void delete(String nome) {
		
		CompagniaAereaDao DAO = new CompagniaAereaDao();
		
		JDialog successo = new JDialog();
		JTextField testo = new JTextField();
		
		DAO.deleteByNome(nome);
		testo.setText("Cancellazione avvenuta con successo!");
		successo.setBounds(200,200,400,200);
		successo.add(testo);
		successo.setVisible(true);
		
		
	}
	
}
