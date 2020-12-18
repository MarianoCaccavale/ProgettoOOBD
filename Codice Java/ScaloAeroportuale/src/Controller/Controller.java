package Controller;

import java.util.ArrayList;

import Classi.Aeroporto;
import DAO.AeroportoDAO;
import GUI.Hub;
import GUI.Login;

public class Controller {

	Login LoginWindow;
	Hub HubWindow;
	
	
	
	public static void main(String[] args) {
		
		Controller c = new Controller();
		
	}
	
	public Controller() {
		
		LoginWindow = new Login(this);
		LoginWindow.setVisible(true);
		
	}
	
	public void LoginToHub() {
		
		LoginWindow = new Login(this);
		LoginWindow.setVisible(false);
		HubWindow = new Hub(this);
		HubWindow.setVisible(true);
		
	}
	
	public void Logout() {
		
		HubWindow = new Hub(this);
		HubWindow.setVisible(false);
		LoginWindow = new Login(this);
		LoginWindow.setVisible(true);
		
	}

}