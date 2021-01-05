package Controller;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import Classi.Tratta;
import DAO.AeroportoDAO;
import DAO.TrattaDAO;
import Eccezioni.TrattaException;
import GUI.GestioneTratte;

public class ControllerTratte {

	TrattaDAO DAO = new TrattaDAO();
	AeroportoDAO AerDAO = new AeroportoDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	
	
	public void InsertTratta(String codAeroportoPartenza, String codAeroportoArrivo) {
		
		Aeroporto AeroportoArrivo = new Aeroporto();
		AeroportoDAO DAOAer = new AeroportoDAO();
		AeroportoArrivo = DAOAer.getAeroportoByNome(codAeroportoArrivo);
		
		try {
			DAO.Insert(codAeroportoPartenza, AeroportoArrivo.getCodAeroporto());
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

	public ArrayList<Tratta> getTratteFromThisAirport(String codAeroporto) {

		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		try {
			Tratte = DAO.getTratteByAeroportoDiPartenza(codAeroporto);
		} catch (TrattaException e) {
			e.printStackTrace();
		} 
		
		return Tratte;
	}

	public void delete(String codAeroportoPartenza, String nomeAeroportoArrivo) {
		
		try {
			
			Aeroporto AeroportoArrivo = AerDAO.getAeroportoByNome(nomeAeroportoArrivo);
			DAO.deleteTratta(codAeroportoPartenza, AeroportoArrivo.getCodAeroporto());
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

	public void update(String vecchioNomeAeroporto, String nuovoNomeAeroporto, String codAeroporto) {
		
		try {
			AeroportoDAO DAOAer = new AeroportoDAO();
			Aeroporto nuovoAeroporto = DAOAer.getAeroportoByNome(nuovoNomeAeroporto);
			Aeroporto vecchioAeroporto = DAOAer.getAeroportoByNome(vecchioNomeAeroporto);
			
			
			DAO.update(vecchioAeroporto.getCodAeroporto(), nuovoAeroporto.getCodAeroporto(), codAeroporto);
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

	public Tratta getTratteByCod(String trattaAssociata) {
	
		Tratta tratta = new Tratta();
		
		try {
				
			tratta = DAO.getTratteByCod(trattaAssociata);
			
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

	public ArrayList<Tratta> ricerca(String AeroportoPartenza, String AeroportoArrivo) {

		TrattaDAO DAO = new TrattaDAO();
		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		
		try {
			
			Tratte = DAO.getTratte(AeroportoPartenza, AeroportoArrivo);
			
		}catch(TrattaException e){
			System.out.println(e.getMessage().toString());
		}
		
		return Tratte;
	}

}
