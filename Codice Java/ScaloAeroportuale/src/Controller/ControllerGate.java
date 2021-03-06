package Controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;

import Classi.Gate;
import DAO.GateDAO;
import Eccezioni.GateException;

public class ControllerGate {

	GateDAO DAO = new GateDAO();
	JDialog successo = new JDialog();
	JLabel testo = new JLabel();
	
	public void insert(String nomeGate, String codAeroporto) {
		
		
		if (nomeGate.isBlank() == false) {
			
			try {
				DAO.insert(nomeGate, codAeroporto);
				testo.setText("Inserimento avvenuto con successo!");
				successo.setBounds(200,200,600,100);
				successo.add(testo);
				successo.setVisible(true);
				
			} catch (GateException e) {
				
				testo.setText(e.getMessage().toString());
				successo.setBounds(200,200,600,100);
				successo.add(testo);
				successo.setVisible(true);
			}
			
		}else {
			
			testo.setText("Impossibile aggiungere un gate con nome nullo!");
			successo.setBounds(200,200,600,100);
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		
	}
	
	public void update(String vecchioNome, String nuovoNome, String codAeroporto) {
		
		try {
			DAO.update(vecchioNome, nuovoNome, codAeroporto);
			testo.setText("Modifica avvenuta con successo!");
			successo.setBounds(200,200,600,100);
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (GateException e) {
			
			testo.setText(e.getMessage().toString());
			successo.setBounds(200,200,600,100);
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
	}
	
	public void deleteByName(String nomeGate, String codAeroporto) {
		
		try {
			DAO.delete(nomeGate, codAeroporto);
			testo.setText("Eliminazione avvenuta con successo!");
			successo.setBounds(200,200,600,100);
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (GateException e) {
			
			testo.setText(e.getMessage());
			successo.setBounds(200,200,600,100);
			successo.add(testo);
			successo.setVisible(true);
		}
		
	}

	public ArrayList<Gate> getAllGate(String codAeroporto) {

		ArrayList<Gate> AllGate = new ArrayList<Gate>();
		
		AllGate= DAO.getAllGate(codAeroporto);
		
		return AllGate;
	}

	
	public ArrayList<Gate> getGateLiberi(String codAeroporto, Timestamp dataVolo) {
		
		ArrayList<Gate> GateLiberi = new ArrayList<Gate>();
		
		try {
			GateLiberi = DAO.getGateLiberi(codAeroporto, dataVolo);
		} catch (GateException e) {
			System.out.println(e.getMessage());
		}
		
		return GateLiberi;
	}


}
