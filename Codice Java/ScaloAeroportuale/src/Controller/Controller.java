package Controller;

import java.util.ArrayList;

import Classi.Aeroporto;
import DAO.AeroportoDAO;
import GUI.GestioneCompagnie;
import GUI.Hub;
import GUI.Login;

public class Controller {

	Login LoginWindow;
	Hub HubWindow;
	GestioneCompagnie CompagnieWindow;
	
	
	
	public static void main(String[] args) {
		
		Controller c = new Controller();
		
	}
	
	public Controller() {
		
		LoginWindow = new Login(this);
		LoginWindow.setVisible(true);
		
	}
	
	public void LoginToHub(String nomeAeroporto) {
		
		AeroportoDAO aDAO = new AeroportoDAO();
		Aeroporto a = new Aeroporto();
		LoginWindow.setVisible(false);
		a= aDAO.getAeroportoByNome(nomeAeroporto);
		HubWindow = new Hub(this, a);
		HubWindow.setVisible(true);
		
	}
	
	public void HubToCompagnie(Aeroporto a) {
		
		HubWindow.setVisible(false);
		CompagnieWindow = new GestioneCompagnie(this, a);
		CompagnieWindow.setVisible(true);
		
	}
	
	public void CompagnieToHub() {
		
		CompagnieWindow.setVisible(false);
		HubWindow.setVisible(true);
		
	}
	
	public void Logout() {
		
		HubWindow.setVisible(false);
		LoginWindow.setVisible(true);
		
	}

}