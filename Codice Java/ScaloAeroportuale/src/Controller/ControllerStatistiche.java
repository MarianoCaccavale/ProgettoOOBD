package Controller;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JTextField;

import DAO.ArchivioVoliDAO;
import Eccezioni.StatisticheException;

public class ControllerStatistiche {

	public ArrayList<String[]> getGateChiusi(String codAeroporto, Date dataInizio, Date dataFine) {
		
		ArchivioVoliDAO DAO = new ArchivioVoliDAO();
		ArrayList<String[]> listaTempi = new ArrayList<String[]>();
		
		JDialog successo = new JDialog();
		JTextField testo = new JTextField();
		
		try {
			
			listaTempi = DAO.getGateChiusi(codAeroporto, dataInizio, dataFine);
			
		}catch(StatisticheException e){
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return listaTempi;
	}


}
