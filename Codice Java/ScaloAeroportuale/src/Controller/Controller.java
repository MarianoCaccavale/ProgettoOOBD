package Controller;


import Classi.Aeroporto;
import Classi.Volo; 
import GUI.GestioneClientiBusiness;
import GUI.GestioneCompagnie;
import GUI.GestioneGate;
import GUI.GestioneSlotImbarco;
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
	GestioneSlotImbarco ImbarcoWindow;
	Statistiche StatisticheWindow;
	ServizioClienti ClientiWindow;
	GestioneClientiBusiness BusinessWindow;
	
	
	
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		Controller c = new Controller();
		
	}
	
	public Controller() {
		
		LoginWindow = new Login(this);
		LoginWindow.setVisible(true);
		
	}
	
	public void LoginToHub(String nomeAeroporto) {
		
		ControllerAeroporti controllerAeroporti = new ControllerAeroporti();
		Aeroporto a = new Aeroporto();
		LoginWindow.setVisible(false);
		a = controllerAeroporti.getAeroportoByNome(nomeAeroporto);
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
	
	public void VoliToSlotImbarco(Aeroporto aer, Volo volo) {
		
		ImbarcoWindow = new GestioneSlotImbarco(aer, volo);
		ImbarcoWindow.setVisible(true);
		
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

	public void ServizioClientiToClientiBusiness(Aeroporto a) {
		
		BusinessWindow = new GestioneClientiBusiness(this, a);
		BusinessWindow.setVisible(true);
		ClientiWindow.setVisible(false);
		
		
	}

	public void ClientiBusinessToServizioClienti(Aeroporto a) {
		
		BusinessWindow.setVisible(false);
		ClientiWindow.setVisible(true);
	}

}