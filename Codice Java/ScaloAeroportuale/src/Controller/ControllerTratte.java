package Controller;

import Classi.Aeroporto;
import DAO.AeroportoDAO;
import DAO.TrattaDao;

public class ControllerTratte {

	public void InsertTratta(String codAeroportoPartenza, String nomeAeroportoArrivo) {
		
		Aeroporto AeroportoArrivo = new Aeroporto();
		AeroportoDAO DAOAer = new AeroportoDAO();
		AeroportoArrivo = DAOAer.getAeroportoByNome(nomeAeroportoArrivo);
		TrattaDao DAO = new TrattaDao();
		DAO.Insert(codAeroportoPartenza, AeroportoArrivo.getCodAeroporto());
		
	}

}
