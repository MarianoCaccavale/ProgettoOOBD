package Controller;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JTextField;

import DAO.StatisticheDAO;
import Eccezioni.StatisticheException;

public class ControllerStatistiche {

	StatisticheDAO DAO = new StatisticheDAO();
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	
	public ArrayList<String[]> getGateChiusi(String codAeroporto, Date dataInizio, Date dataFine) {
		
		ArrayList<String[]> listaTempi = new ArrayList<String[]>();
		
		
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

	public String[] statisticheVoli(String codAeroporto, Date inizio, Date fine) {
		
		String[] statisticheVoli = new String[2];
		
		try {
			
			statisticheVoli = DAO.statisticheVoli(codAeroporto, inizio, fine);
			
		}catch(StatisticheException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
			
		
		return statisticheVoli;
	}

	public ArrayList<String[]> getStatisticheCompagnie(String codAeroporto, Date dataInizio, Date dataFine) {
		
		ArrayList<String[]> listaStatisticheCompagnie = new ArrayList<String[]>();
		
		try {
			
			listaStatisticheCompagnie = DAO.getStatisticheCompagnie(codAeroporto, dataInizio, dataFine);
			
		}catch(StatisticheException e){
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		return listaStatisticheCompagnie;
	}


}
