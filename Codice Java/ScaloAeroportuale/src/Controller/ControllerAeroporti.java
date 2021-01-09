package Controller;

import java.util.ArrayList;
import java.util.Iterator;

import Classi.Aeroporto;
import DAO.AeroportoDAO;

public class ControllerAeroporti {

	AeroportoDAO DAO = new AeroportoDAO();
	
	public ArrayList<Aeroporto> getAllAeroportiExceptThis(Aeroporto aer) {
		
		ArrayList<Aeroporto> AllAeroporti = new ArrayList<Aeroporto>();
		
		AllAeroporti = DAO.getAllAeroportiExceptThis(aer.getNomeAeroporto());
		
		return AllAeroporti;
		
	}

	public Aeroporto getAeroportoByCod(String codAeroporto) {

		Aeroporto aeroporto = new Aeroporto();
		
		aeroporto = DAO.getAeroportoByCod(codAeroporto);
		
		return aeroporto;
	}

}
