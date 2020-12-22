package Controller;

import java.util.ArrayList;
import java.util.Iterator;

import Classi.Aeroporto;
import DAO.AeroportoDAO;

public class ControllerAeroporti {

	public ArrayList<Aeroporto> getAllAeroportiExceptThis(Aeroporto aer) {
		
		ArrayList<Aeroporto> Aeroporti = new ArrayList<Aeroporto>();
		AeroportoDAO DAO = new AeroportoDAO();
		Aeroporti = DAO.getAllAeroporti();
		
		Iterator<Aeroporto> i = Aeroporti.iterator();
		
		while (i.hasNext()) {
			
			Aeroporto tmp = new Aeroporto();
			tmp = i.next();
			
			if(tmp.getCodAeroporto() != aer.getCodAeroporto()) {
				
				Aeroporti.add(tmp);
				
			}
		}
		
		return Aeroporti;
	}

}
