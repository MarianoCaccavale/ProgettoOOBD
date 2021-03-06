package Controller;

import java.util.ArrayList;

import Classi.Aeroporto;
import DAO.AeroportoDAO;

public class ControllerAeroporti {

	AeroportoDAO DAO = new AeroportoDAO();
	
	public ArrayList<Aeroporto> getAeroporti() {
		
		ArrayList<Aeroporto> listaAeroporti = new ArrayList<>();
		
		AeroportoDAO ad = new AeroportoDAO();
		
		listaAeroporti = ad.getAllAeroporti();
		
		return listaAeroporti;
	}
	
	public ArrayList<Aeroporto> getAllAeroportiExceptThis(Aeroporto aer) {
		
		ArrayList<Aeroporto> AllAeroporti = new ArrayList<Aeroporto>();
		
		AllAeroporti = DAO.getAllAeroportiExceptThis(aer.getNomeAeroporto());
		
		return AllAeroporti;
		
	}

	public Aeroporto getAeroportoByNome(String nomeAeroporto) {
		
		Aeroporto aeroporto = new Aeroporto();
		
		aeroporto = DAO.getAeroportoByNome(nomeAeroporto);
		
		return aeroporto;
	}

}
