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
		
		JButton StatisticheBtn = new JButton("Statistiche");
		BottoniPanel.add(StatisticheBtn);
		
		JButton GestioneTratteBtn = new JButton("Gestione Tratte");
		BottoniPanel.add(GestioneTratteBtn);
		
		JButton GestioneVoliBtn = new JButton("Gestione Voli");
		BottoniPanel.add(GestioneVoliBtn);
		
		JButton GestioneCompagnieBtn = new JButton("Gestione Compagnie");
		GestioneCompagnieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.HubToCompagnie(a);
			}
		});
		BottoniPanel.add(GestioneCompagnieBtn);
		
		JButton GestioneGateBtn = new JButton("Gestione Gate");
		BottoniPanel.add(GestioneGateBtn);
		
		JButton ServizioClientiBtn = new JButton("Servizio Clienti");
		BottoniPanel.add(ServizioClientiBtn);
		
		JPanel HubPanel = new JPanel();
		HubPanel.setBounds(172, 10, 647, 445);
		BasePanel.add(HubPanel);
		HubPanel.setLayout(null);
		
		JLabel AeroportoInformazioniLbl = new JLabel("");
		AeroportoInformazioniLbl.setBounds(10, 11, 627, 23);
		/*migliorare la grafica*/
		AeroportoInformazioniLbl.setText(a.getCodAeroporto() + " - " +a.getNomeAeroporto() + " - " + a.getCittà());
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
