package Controller;

import java.util.ArrayList;

import Classi.Aeroporto;
import DAO.AeroportoDAO;
import GUI.Hub;
import GUI.Login;

public class Controller {

	Login LoginWindow;
	
	
	
	public static void main(String[] args) {
		
		Controller c = new Controller();
		
	}
	
	public Controller() {
		
		LoginWindow = new Login(this);
		LoginWindow.setVisible(true);
	}
	
	public void LogintoHub() {
		
		LoginWindow.setVisible(false);
		Hub HubWindow = new Hub(this);
		HubWindow.setVisible(true);
		
	}

}