package Controller;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Classi.SlotImbarco;
import DAO.SlotImbarcoDAO;
import Eccezioni.SlotImbarcoException;

public class ControllerSlotImbarco {

	SlotImbarcoDAO DAO = new SlotImbarcoDAO();
	
	JDialog successo = new JDialog();
	JTextField testo = new JTextField();

	public ArrayList<SlotImbarco> getImbarchi(String codAeroporto) {
		
		ArrayList<SlotImbarco> listaSlotImbarco = new ArrayList<SlotImbarco>();
		try {
			listaSlotImbarco = DAO.getAllImbarchi(codAeroporto);
		} catch (SlotImbarcoException e) {

			System.out.println(e.getMessage());
			
//			successo.setBounds(200,200,400,200);
//			testo.setText(e.getMessage().toString()); 
//			successo.add(testo);
//			successo.setVisible(true);
			
		}
		
		return listaSlotImbarco;
	}
	
	
}
