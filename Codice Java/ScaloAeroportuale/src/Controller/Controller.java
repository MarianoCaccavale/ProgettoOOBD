package Controller;


import Classi.Aeroporto;
import DAO.AeroportoDAO;
import GUI.GestioneCompagnie;
import GUI.GestioneGate;
import GUI.GestioneTratte;
import GUI.GestioneVoli;
import GUI.Hub;
import GUI.Login;
import GUI.ServizioClienti;
import GUI.Statistiche;

public class Controller {

	Login LoginWindow;
	Hub HubWindow;
	GestioneCompagnie CompagnieWindow;
	GestioneGate GateWindow;
	GestioneTratte TratteWindow;
	GestioneVoli VoliWindow;
	Statistiche StatisticheWindow;
	ServizioClienti ClientiWindow;
	
	
	
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
		a = aDAO.getAeroportoByNome(nomeAeroporto);
		HubWindow = new Hub(this, a);
		HubWindow.setVisible(true);
		
	}
	
	public void Logout() {
		
		HubWindow.setVisible(false);
		LoginWindow.setVisible(true);
		
	}
	
	public void HubToCompagnie(Aeroporto a) {
		
		HubWindow.setVisible(false);
		CompagnieWindow = new GestioneCompagnie(this, a);
		CompagnieWindow.setVisible(true);
		
	}
	public void CompagnieToHub(Aeroporto a) {
		
		CompagnieWindow.setVisible(false);
		HubWindow.setVisible(true);
		
	}
	
	public void HubToGate(Aeroporto a) {
		
		HubWindow.setVisible(false);
		GateWindow = new GestioneGate(this, a);
		GateWindow.setVisible(true);
	}
	public void GateToHub(Aeroporto a) {
		
		GateWindow.setVisible(false);
		HubWindow.setVisible(true);
		
	}
	
	public void HubToTratte(Aeroporto a) {
		
		HubWindow.setVisible(false);
		TratteWindow = new GestioneTratte(this, a);
		TratteWindow.setVisible(true);
		
		
	}
	public void TratteToHub(Aeroporto a) {
		
		TratteWindow.setVisible(false);
		HubWindow.setVisible(true);
		
	}

	public void HubToVoli(Aeroporto a) {
		
		HubWindow.setVisible(false);
		VoliWindow = new GestioneVoli(this, a);
		VoliWindow.setVisible(true);
		
		
	}
	public void VoliToHub(Aeroporto a) {
		
		VoliWindow.setVisible(false);
		HubWindow.setVisible(true);
		
	}
	
	public void HubToStatistiche(Aeroporto a) {
		
		HubWindow.setVisible(false);
		StatisticheWindow = new Statistiche(this, a);
		StatisticheWindow.setVisible(true);
	}

	public void statisticheToHub(Aeroporto a) {
		
		StatisticheWindow.setVisible(false);
		HubWindow.setVisible(true);
		
	}
	
	public void HubToServizioClienti(Aeroporto a) {
		
		ClientiWindow = new ServizioClienti(this, a);
		ClientiWindow.setVisible(true);
		HubWindow.setVisible(false);
		
		
	}

	public void ServizioClientiToHub(Aeroporto a) {
		
		ClientiWindow.setVisible(false);
		HubWindow.setVisible(true);
		
	}

}