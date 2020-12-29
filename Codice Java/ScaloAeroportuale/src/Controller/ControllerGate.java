package Controller;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;

import Classi.Aeroporto;
import Classi.Gate;
import DAO.GateDAO;
import Eccezioni.GateException;

public class ControllerGate {

	GateDAO DAO = new GateDAO();
	JDialog successo = new JDialog();
	JLabel testo = new JLabel();
	
	public void insertGate(String nomeGate, String codAeroporto) {
		
		
		
		if (nomeGate.isBlank() == false) {
			
			try {
				DAO.insertGate(nomeGate, codAeroporto);
				testo.setText("Inserimento avvenuto con successo!");
				successo.setBounds(200,200,400,200);
				successo.add(testo);
				successo.setVisible(true);
				
			} catch (GateException e) {
				
				testo.setText(e.getMessage().toString());
				successo.setBounds(200,200,400,200);
				successo.add(testo);
				successo.setVisible(true);
			}
			
		}else {
			
			testo.setText("Impossibile aggiungere un gate con nome nullo!");
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		
	}
	
	public void update(String vecchioNome, String nuovoNome, String codAeroporto) {
		
		try {
			DAO.updateGate(vecchioNome, nuovoNome, codAeroporto);
			testo.setText("Modifica avvenuta con successo!");
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (GateException e) {
			
			testo.setText(e.getMessage().toString());
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
	}
	
	public void deleteByName(String nomeGate, String codAeroporto) {
		
		try {
			DAO.delete(nomeGate, codAeroporto);
			testo.setText("Eliminazione avvenuta con successo!");
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (GateException e) {
			
			testo.setText(e.getMessage());
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
		}
		
	}

	public ArrayList<Gate> getGate(String codAeroporto) {

		ArrayList<Gate> AllGate = new ArrayList<Gate>();
		
		AllGate= DAO.getAllGate(codAeroporto);
		
		return AllGate;
	}


}
