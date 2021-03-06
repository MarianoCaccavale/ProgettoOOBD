package Controller;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import Classi.Tratta;
import DAO.TrattaDAO;
import Eccezioni.TrattaException;

public class ControllerTratte {

	TrattaDAO DAO = new TrattaDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	
	
	public void insert(Aeroporto aeroportoPartenza, Aeroporto aeroportoArrivo) {

				
		try {
			DAO.Insert(aeroportoPartenza.getCodAeroporto(), aeroportoArrivo.getCodAeroporto());
			successo.setBounds(200,200,600,100);
			testo.setText("Inserimento avvenuto con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
		} catch (TrattaException e) {

			successo.setBounds(200,200,600,100);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
				
		}
		
	}

	public void update(Aeroporto vecchioAeroporto, Aeroporto nuovoAeroporto, Aeroporto a) {
		
		try {
			

			DAO.update(vecchioAeroporto.getCodAeroporto(), nuovoAeroporto.getCodAeroporto(), a.getCodAeroporto());
			successo.setBounds(200,200,600,100);
			testo.setText("Modifica avvenuta con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (TrattaException e) {
			
			successo.setBounds(200,200,600,100);
			testo.setText(e.getMessage()); 
			successo.add(testo);
			successo.setVisible(true);
		}
		
		
	}

	public void delete(Aeroporto aeroportoPartenza, Aeroporto aeroportoArrivo) {
		
		try {
			
			DAO.deleteTratta(aeroportoPartenza.getCodAeroporto(), aeroportoArrivo.getCodAeroporto());
			testo.setText("Cancellazione avvenuta con successo!"); 
			successo.setBounds(200,200,600,100);
			successo.add(testo);
			successo.setVisible(true);
	
		} catch (TrattaException e) {

			successo.setBounds(200,200,600,100);
			testo.setText(e.getMessage()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		
	}

	
	public ArrayList<Tratta> getTratteFromThisAirport(Aeroporto aeroporto) {

		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		
		try {
			Tratte = DAO.getTratteByAeroportoDiPartenza(aeroporto);
		} catch (TrattaException e) {
			
			successo.setBounds(200,200,600,100);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		} 

		
		return Tratte;
	}

	public Tratta getTrattaByAeroporti(String AeroportoPartenza, String AeroportoArrivo) {

		TrattaDAO DAO = new TrattaDAO();
		Tratta Tratta = new Tratta();
		
		try {
			
			Tratta = DAO.getTratte(AeroportoPartenza, AeroportoArrivo);
			
		}catch(TrattaException e){
			
			if (e.getMessage().contains("Il �ResultSet� non � correttamente posizionato; forse � necessario invocare �next()�.")) {
				JDialog successo = new JDialog();
				JTextField testo = new JTextField();
				successo.setBounds(200,200,600,100);
				testo.setText("Tratta non trovata"); 
				successo.add(testo);
				successo.setVisible(true);
				
			}else {
				
				successo.setBounds(200,200,600,100);
				testo.setText(e.getMessage().toString()); 
				successo.add(testo);
				successo.setVisible(true);
				
			}
		
		}

		return Tratta;
		
	}
}
