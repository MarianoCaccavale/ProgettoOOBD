package Controller;

import java.util.ArrayList;
import java.util.Iterator;

import Classi.Aeroporto;
import DAO.AeroportoDAO;

public class ControllerAeroporti {

	public ArrayList<Aeroporto> getAllAeroportiExceptThis(Aeroporto aer) {
		
		ArrayList<Aeroporto> AllAeroporti = new ArrayList<Aeroporto>();
		AeroportoDAO DAO = new AeroportoDAO();
		AllAeroporti = DAO.getAllAeroportiExceptThis(aer.getCodAeroporto());
		
		return AllAeroporti;
		
	}

}
