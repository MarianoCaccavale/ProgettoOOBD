import java.sql.*;
import java.util.*;

import Classi.Aeroporto;
import DAO.AeroportoDAO;

public class prova {

	public static String treconsonanti(String s) {
		
		String stringa = new String();
		stringa = s;
		String result = new String();
		int i = 0;
		
		for (char c : stringa.toCharArray()){
			
			if(c != 'a' && c != 'e' && c != 'o' && c != 'u' && c != 'i') {
				
				result = result + c;
				i++;
				
			}else if  (i >= 3) {
				
				break;
				
			}
			
		}
		
		return result;
		
		
		
	}
	
	public static void main(String[] args) {
		
		
//		String s = new String();
//		String trelettere = new String();
		Aeroporto a = new Aeroporto();
		Aeroporto b = new Aeroporto();
		LinkedList<Aeroporto> LA = new LinkedList<Aeroporto>();
		AeroportoDAO aeroporto = new AeroportoDAO();
		a = aeroporto.getAeroportoByCod("NPL34");
		b = aeroporto.getAeroportoByNome("Diego Armando Maradona");
		LA = aeroporto.getAeroportoByCittà("Ascoli");
//		
//		s = "perugia";
//		trelettere = treconsonanti(s);
//
//		
//		System.out.println(trelettere);
		if (a.getCodAeroporto() != "") {
			
			System.out.println("Codice aereo: " + a.getCodAeroporto() + " " + "Nome Aeroporto: " + a.getNomeAeroporto() + " " + "Città: " + a.getCittà());
			
		}else {
			
			System.out.println("Aeroporto non trovato");
			
		}
		
		if (b.getNomeAeroporto() != "") {
			
			System.out.println("Codice aereo: " + b.getCodAeroporto() + " " + "Nome Aeroporto: " + b.getNomeAeroporto() + " " + "Città: " + b.getCittà());
			
		}else {
			
			System.out.println("Aeroporto non trovato");
			
		}
		
		Iterator<Aeroporto> i = LA.iterator();
		
		if (i.hasNext()) {
			
			Aeroporto tmp = new Aeroporto();
			while(i.hasNext()) {
				tmp = i.next();
				System.out.println("Codice aereo: " + tmp.getCodAeroporto() + " " + "Nome Aeroporto: " + tmp.getNomeAeroporto() + " " + "Città: " + tmp.getCittà());
			}
			
		}else{
			
			System.out.println("In questa città non ci sono aeroporti");
			
		}
		
		if(aeroporto.insertAeroporto("MLN1", "Manzoni", "Milano") == false) {
			
			System.out.println("Errore nell'inserimento");
			
		}
		
		
		
		
	}

}
