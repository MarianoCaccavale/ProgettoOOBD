package Controller;

import java.util.ArrayList;

import Classi.Aeroporto;
import Classi.Tratta;
import DAO.AeroportoDAO;
import DAO.TrattaDao;

public class ControllerTratte {

	TrattaDao DAO = new TrattaDao();
	
	public void InsertTratta(String codAeroportoPartenza, String nomeAeroportoArrivo) {
		
		Aeroporto AeroportoArrivo = new Aeroporto();
		AeroportoDAO DAOAer = new AeroportoDAO();
		AeroportoArrivo = DAOAer.getAeroportoByNome(nomeAeroportoArrivo);
		DAO.Insert(codAeroportoPartenza, AeroportoArrivo.getCodAeroporto());
		
	}

	public ArrayList<Tratta> getTratteFromThisAirport(String codAeroporto) {

		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		Tratte = DAO.getTratteByAeroportoDiPartenza(codAeroporto); 
		
		return Tratte;
	}

}
