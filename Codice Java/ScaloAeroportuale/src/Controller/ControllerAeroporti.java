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
		
		
		/*ArrayList<Aeroporto> tmp = new ArrayList<Aeroporto>();
		AeroportoDAO DAO = new AeroportoDAO();
		tmp = DAO.getAllAeroporti();
		
		Iterator<Aeroporto> i = tmp.iterator();
		
		while(i.hasNext()) {
			
			Aeroporto tmp2 = i.next();
			
			if (tmp2.getNomeAeroporto() == aer.getNomeAeroporto()) {
				
				i.next();
				
			}else {
				
				AllAeroporti.add(tmp2);
				
			}
			
		}*/
	}

}
