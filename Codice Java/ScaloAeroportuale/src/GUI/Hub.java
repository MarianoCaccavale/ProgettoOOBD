package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.SlotImbarco;
import Controller.Controller;
import Controller.ControllerSlotImbarco;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

public class Hub extends JFrame {

	private JPanel BasePanel;
	Controller controller;


	public Hub(Controller c, Aeroporto a) {
		setResizable(false);
		setTitle("Hub");
		controller = c;
		ControllerSlotImbarco controllerSlot = new ControllerSlotImbarco();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1122, 525);
		BasePanel = new JPanel();
		BasePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BasePanel);
		BasePanel.setLayout(null);
		
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setBounds(10, 10, 218, 333);
		BasePanel.add(BottoniPanel);
		BottoniPanel.setLayout(null);
		
		JButton StatisticheBtn = new JButton("Statistiche");
		StatisticheBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		StatisticheBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.HubToStatistiche(a);
				
			}
		});
		StatisticheBtn.setBounds(13, 5, 195, 38);
		BottoniPanel.add(StatisticheBtn);
		
		JButton GestioneTratteBtn = new JButton("Gestione Tratte");
		GestioneTratteBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		GestioneTratteBtn.setBounds(13, 53, 195, 38);
		GestioneTratteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.HubToTratte(a);
				
			}
		});
		BottoniPanel.add(GestioneTratteBtn);
		
		JButton GestioneVoliBtn = new JButton("Gestione Voli");
		GestioneVoliBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		GestioneVoliBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.HubToVoli(a);
			}
		});
		GestioneVoliBtn.setBounds(13, 101, 195, 38);
		BottoniPanel.add(GestioneVoliBtn);
		
		JButton GestioneCompagnieBtn = new JButton("Gestione Compagnie");
		GestioneCompagnieBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		GestioneCompagnieBtn.setBounds(13, 149, 195, 38);
		GestioneCompagnieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.HubToCompagnie(a);
			}
		});
		BottoniPanel.add(GestioneCompagnieBtn);
		
		JButton GestioneGateBtn = new JButton("Gestione Gate");
		GestioneGateBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		GestioneGateBtn.setBounds(13, 197, 195, 38);
		GestioneGateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.HubToGate(a);
				
			}
		});
		BottoniPanel.add(GestioneGateBtn);
		
		JButton ServizioClientiBtn = new JButton("Servizio Clienti");
		ServizioClientiBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ServizioClientiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.HubToServizioClienti(a);
				
			}
		});
		ServizioClientiBtn.setBounds(13, 245, 195, 38);
		BottoniPanel.add(ServizioClientiBtn);
		
		JPanel HubPanel = new JPanel();
		HubPanel.setBounds(238, 10, 860, 468);
		BasePanel.add(HubPanel);
		HubPanel.setLayout(null);
		
		JLabel AeroportoInformazioniLbl = new JLabel("");
		AeroportoInformazioniLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		AeroportoInformazioniLbl.setBounds(10, 11, 283, 43);
		AeroportoInformazioniLbl.setText(a.getCodAeroporto() + ": " +a.getNomeAeroporto());
		HubPanel.add(AeroportoInformazioniLbl);
		
		JLabel InformazioniCitt‡Lbl = new JLabel("New label");
		InformazioniCitt‡Lbl.setFont(new Font("Arial", Font.PLAIN, 16));
		InformazioniCitt‡Lbl.setBounds(717, 11, 122, 43);
		InformazioniCitt‡Lbl.setText(a.getCitt‡());
		HubPanel.add(InformazioniCitt‡Lbl);
		
		JTextPane AvvisoSlotImbarchi = new JTextPane();
		AvvisoSlotImbarchi.setEditable(false);
		AvvisoSlotImbarchi.setFont(new Font("Arial", Font.PLAIN, 14));
		AvvisoSlotImbarchi.setBounds(10, 186, 840, 272);
		HubPanel.add(AvvisoSlotImbarchi);
		
		JLabel AvvisoLbl = new JLabel("Questi voli potrebbero essere partiti. Potrebbe essere necessario chiudere gli SlotImbarchi ad essi associati.");
		AvvisoLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		AvvisoLbl.setBounds(10, 144, 840, 43);
		HubPanel.add(AvvisoLbl);
		ArrayList<SlotImbarco> SlotDaChiudere = new ArrayList<SlotImbarco>();
		
		SlotDaChiudere = controllerSlot.ricercaSlotDaChiudere(a.getCodAeroporto());
		
		for (SlotImbarco tmp: SlotDaChiudere) {

			AvvisoSlotImbarchi.setText(AvvisoSlotImbarchi.getText() + "\n");
			AvvisoSlotImbarchi.setText(AvvisoSlotImbarchi.getText() + "Codice volo: " + tmp.getVolo().getCodVolo() + "\tTratta: " + tmp.getVolo().getTrattaAssociata().getAeroportoDiPartenza().getNomeAeroporto() + "  -  "+ tmp.getVolo().getTrattaAssociata().getAeroportoDiArrivo().getNomeAeroporto() + "\tOra di partenza: " + tmp.getOraInizio() + "");
		}
		
		
		JPanel LogoutPanel = new JPanel();
		LogoutPanel.setBounds(10, 406, 218, 72);
		BasePanel.add(LogoutPanel);
		
		JButton LogoutBtn = new JButton("Logout");
		LogoutBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		LogoutBtn.setBounds(10, 24, 195, 38);
		LogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.Logout();
				
			}
		});
		LogoutPanel.setLayout(null);
		LogoutPanel.add(LogoutBtn);
	}
}
