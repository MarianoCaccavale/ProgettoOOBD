package Controller;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import DAO.AeroportoDAO;
import DAO.CompagniaAereaDAO;
import Eccezioni.CompagniaException;

public class ControllerCompagnie {
	
	CompagniaAereaDAO DAO = new CompagniaAereaDAO();
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();
	
	public void Insert(String Nome, int Flotta, Aeroporto a) {
	
		
		try {
			DAO.InsertCompagnia(Nome, Flotta, a);
			
			testo.setText("Inserimento avvenuto con successo!");
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (CompagniaException e) {
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
		}	
				
	}
	
	public void Update(String Nome, Integer NuovaGrandezza, Aeroporto aer) {
		
		
		try {
			DAO.updateByNome(Nome, NuovaGrandezza, aer.getCodAeroporto());
			testo.setText("Modifica avvenuta con successo!");
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
			
		} catch (CompagniaException e) {
			
			successo.setBounds(200,200,400,200);
			testo.setText(e.getMessage().toString()); 
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
	}
	
	public ArrayList<CompagniaAerea> getCompagnie(Aeroporto aer) {
		
		ArrayList<CompagniaAerea> Compagnie = new ArrayList<CompagniaAerea>();
		try {
			Compagnie = DAO.getCompagnieByAeroporto(aer.getCodAeroporto());
		} catch (CompagniaException e) {
			
			testo.setText(e.getMessage());
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
		
		}
	
		return Compagnie;
	}

	public void delete(String nome) {
		
		try {
			DAO.deleteByNome(nome);
			testo.setText("Cancellazione avvenuta con successo!");
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
		} catch (CompagniaException e) {
			
			testo.setText(e.getMessage().toString());
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
			
		}
		
		
		
	}

	public ArrayList<CompagniaAerea> ricerca(String Nome, Integer Min, Integer Max, Aeroporto aer) {
		
		ArrayList<CompagniaAerea> Compagnie = new ArrayList<CompagniaAerea>();
		if (Nome.isBlank()) {
			
			try {
				Compagnie = DAO.ricercaByFlotta(Min, Max, aer.getCodAeroporto());
			} catch (CompagniaException e) {
				System.out.println(e.getMessage().toString());
			}
			
		}else {
			
			try{
				
				Compagnie = DAO.ricercaByAll(Nome, Min, Max, aer.getCodAeroporto());
				
			}catch(CompagniaException e) {
				
				testo.setText(e.getMessage().toString());
				successo.setBounds(200,200,400,200);
				successo.add(testo);
				successo.setVisible(true);
				
			}
			
		}
		
		return Compagnie;
		
	}

	public CompagniaAerea getCompagniaByCod(String compagniaDiAppartenenza) {
		
		CompagniaAerea risultato = new CompagniaAerea();
				
		try {
			
			risultato = DAO.getCompagniaByCod(compagniaDiAppartenenza);
			
		}catch(CompagniaException e){
			
			testo.setText(e.getMessage());
			successo.setBounds(200,200,400,200);
			successo.add(testo);
			successo.setVisible(true);
			
			
		}
		
		return risultato;
	}
	
}
