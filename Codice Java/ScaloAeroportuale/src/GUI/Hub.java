package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Controller.Controller;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class Hub extends JFrame {

	private JPanel BasePanel;
	Controller controller;

	/**
	 * Create the frame.
	 */
	public Hub(Controller c, Aeroporto a) {
		setTitle("Hub");
		controller = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 492);
		BasePanel = new JPanel();
		BasePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BasePanel);
		BasePanel.setLayout(null);
		
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setBounds(10, 10, 158, 252);
		BasePanel.add(BottoniPanel);
		BottoniPanel.setLayout(null);
		
		JButton StatisticheBtn = new JButton("Statistiche");
		StatisticheBtn.setBounds(13, 5, 131, 23);
		BottoniPanel.add(StatisticheBtn);
		
		JButton GestioneTratteBtn = new JButton("Gestione Tratte");
		GestioneTratteBtn.setBounds(13, 33, 131, 23);
		GestioneTratteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.HubToTratte(a);
				
			}
		});
		BottoniPanel.add(GestioneTratteBtn);
		
		JButton GestioneVoliBtn = new JButton("Gestione Voli");
		GestioneVoliBtn.setBounds(13, 61, 131, 23);
		BottoniPanel.add(GestioneVoliBtn);
		
		JButton GestioneCompagnieBtn = new JButton("Gestione Compagnie");
		GestioneCompagnieBtn.setBounds(13, 89, 131, 23);
		GestioneCompagnieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.HubToCompagnie(a);
			}
		});
		BottoniPanel.add(GestioneCompagnieBtn);
		
		JButton GestioneGateBtn = new JButton("Gestione Gate");
		GestioneGateBtn.setBounds(13, 117, 131, 23);
		GestioneGateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.HubToGate(a);
				
			}
		});
		BottoniPanel.add(GestioneGateBtn);
		
		JButton ServizioClientiBtn = new JButton("Servizio Clienti");
		ServizioClientiBtn.setBounds(13, 145, 131, 23);
		BottoniPanel.add(ServizioClientiBtn);
		
		JPanel HubPanel = new JPanel();
		HubPanel.setBounds(172, 10, 647, 445);
		BasePanel.add(HubPanel);
		HubPanel.setLayout(null);
		
		JLabel AeroportoInformazioniLbl = new JLabel("");
		AeroportoInformazioniLbl.setBounds(10, 11, 627, 23);
		/*migliorare la grafica*/
		AeroportoInformazioniLbl.setText(a.getCodAeroporto() + " - " +a.getNomeAeroporto() + " - " + a.getCitt�());
		HubPanel.add(AeroportoInformazioniLbl);
		
		
		JPanel LogoutPanel = new JPanel();
		LogoutPanel.setBounds(10, 414, 85, 31);
		BasePanel.add(LogoutPanel);
		
		JButton LogoutBtn = new JButton("Logout");
		LogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.Logout();
			}
		});
		LogoutPanel.add(LogoutBtn);
	}
}
