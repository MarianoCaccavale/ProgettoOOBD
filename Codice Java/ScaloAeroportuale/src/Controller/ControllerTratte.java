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
	
	
	public void InsertTratta(Aeroporto aeroportoPartenza, Aeroporto aeroportoArrivo) {
		
		
		try {
			DAO.Insert(aeroportoPartenza.getCodAeroporto(), aeroportoArrivo.getCodAeroporto());
			successo.setBounds(200,200,400,200);
			testo.setText("Inserimento avvenuto con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
		} catch (TrattaException e) {

			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
				
		}
		
	}

	public ArrayList<Tratta> getTratteFromThisAirport(Aeroporto codAeroporto) {

		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		try {
			Tratte = DAO.getTratteByAeroportoDiPartenza(codAeroporto);
		} catch (TrattaException e) {
			e.printStackTrace();
		} 
		
		return Tratte;
	}

	public void delete(Aeroporto aeroportoPartenza, Aeroporto aeroportoArrivo) {
		
		try {
			
			DAO.deleteTratta(aeroportoPartenza.getCodAeroporto(), aeroportoArrivo.getCodAeroporto());
			successo.setBounds(200,200,400,200);
			testo.setText("Cancellazione avvenuta con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (TrattaException e) {

			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage()); 
			successo.add(testo);
			successo.setVisible(true);
		}
		
		
	}

	public void update(Aeroporto vecchioAeroporto, Aeroporto nuovoAeroporto, Aeroporto a) {
		
		try {
			
			DAO.update(vecchioAeroporto.getCodAeroporto(), nuovoAeroporto.getCodAeroporto(), a.getCodAeroporto());
			successo.setBounds(200,200,400,200);
			testo.setText("Modifica avvenuta con successo!"); 
			successo.add(testo);
			successo.setVisible(true);
		} catch (TrattaException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage()); 
			successo.add(testo);
			successo.setVisible(true);
		}
		
		
	}

	public Tratta getTrattaByCod(String trattaAssociata) {
	
		Tratta tratta = new Tratta();
		
		try {
				
			tratta = DAO.getTrattaByCod(trattaAssociata);
			
		} catch (TrattaException e) {
	
			JDialog successo = new JDialog();
			JTextField testo = new JTextField();
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
		}
		
		return tratta;
		
	}

	public Tratta getTrattaByAeroporti(String AeroportoPartenza, String AeroportoArrivo) {

		TrattaDAO DAO = new TrattaDAO();
		Tratta Tratta = new Tratta();
		
		try {
			
			Tratta = DAO.getTratte(AeroportoPartenza, AeroportoArrivo);
			
		}catch(TrattaException e){
			
			JDialog successo = new JDialog();
			JTextField testo = new JTextField();
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return Tratta;
	}

}
