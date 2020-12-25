package Controller;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import Classi.Tratta;
import DAO.AeroportoDAO;
import DAO.TrattaDao;
import Eccezioni.TrattaException;

public class ControllerTratte {

	TrattaDao DAO = new TrattaDao();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	
	
	public void InsertTratta(String codAeroportoPartenza, String nomeAeroportoArrivo) {
		
		Aeroporto AeroportoArrivo = new Aeroporto();
		AeroportoDAO DAOAer = new AeroportoDAO();
		AeroportoArrivo = DAOAer.getAeroportoByNome(nomeAeroportoArrivo);
		
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

	public void delete(String codAeroportoPartenza, String codAeroportoArrivo) {
		
		try {
			DAO.deleteTratta(codAeroportoPartenza, codAeroportoArrivo);
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

}
