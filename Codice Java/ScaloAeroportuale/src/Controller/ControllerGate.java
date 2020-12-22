package Controller;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;

import Classi.Gate;
import DAO.GateDao;
import Eccezioni.GateException;

public class ControllerGate {

	public void insertGate(String nomeGate, String codAeroporto) {
		
		GateDao DAO = new GateDao();
		JDialog successo = new JDialog();
		JLabel testo = new JLabel();
		
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

	public ArrayList<Gate> getGate(String codAeroporto) {

		ArrayList<Gate> AllGate = new ArrayList<Gate>();
		
		GateDao DAO = new GateDao();
		AllGate= DAO.getAllGate(codAeroporto);
		
		return AllGate;
	}

	public void deleteByName(String nomeGate, String codAeroporto) {
		
		GateDao DAO = new GateDao();
		
		DAO.delete(nomeGate, codAeroporto);
		
	}

}
