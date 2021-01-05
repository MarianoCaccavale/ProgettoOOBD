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
import java.awt.Font;

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
		BottoniPanel.setBounds(10, 10, 218, 333);
		BasePanel.add(BottoniPanel);
		BottoniPanel.setLayout(null);
		
		JButton StatisticheBtn = new JButton("Statistiche");
		StatisticheBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.HubToStatistiche(a);
				
			}
		});
		StatisticheBtn.setBounds(13, 5, 195, 38);
		BottoniPanel.add(StatisticheBtn);
		
		JButton GestioneTratteBtn = new JButton("Gestione Tratte");
		GestioneTratteBtn.setBounds(13, 53, 195, 38);
		GestioneTratteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.HubToTratte(a);
				
			}
		});
		BottoniPanel.add(GestioneTratteBtn);
		
		JButton GestioneVoliBtn = new JButton("Gestione Voli");
		GestioneVoliBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.HubToVoli(a);
			}
		});
		GestioneVoliBtn.setBounds(13, 101, 195, 38);
		BottoniPanel.add(GestioneVoliBtn);
		
		JButton GestioneCompagnieBtn = new JButton("Gestione Compagnie");
		GestioneCompagnieBtn.setBounds(13, 149, 195, 38);
		GestioneCompagnieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.HubToCompagnie(a);
			}
		});
		BottoniPanel.add(GestioneCompagnieBtn);
		
		JButton GestioneGateBtn = new JButton("Gestione Gate");
		GestioneGateBtn.setBounds(13, 197, 195, 38);
		GestioneGateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.HubToGate(a);
				
			}
		});
		BottoniPanel.add(GestioneGateBtn);
		
		JButton ServizioClientiBtn = new JButton("Servizio Clienti");
		ServizioClientiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.HubToServizioClienti(a);
				
			}
		});
		ServizioClientiBtn.setBounds(13, 245, 195, 38);
		BottoniPanel.add(ServizioClientiBtn);
		
		JPanel HubPanel = new JPanel();
		HubPanel.setBounds(238, 10, 581, 445);
		BasePanel.add(HubPanel);
		HubPanel.setLayout(null);
		
		JLabel AeroportoInformazioniLbl = new JLabel("");
		AeroportoInformazioniLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		AeroportoInformazioniLbl.setBounds(10, 11, 283, 43);
		AeroportoInformazioniLbl.setText(a.getCodAeroporto() + ": " +a.getNomeAeroporto());
		HubPanel.add(AeroportoInformazioniLbl);
		
		JLabel InformazioniCitt‡Lbl = new JLabel("New label");
		InformazioniCitt‡Lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		InformazioniCitt‡Lbl.setBounds(449, 11, 122, 43);
		InformazioniCitt‡Lbl.setText(a.getCitt‡());
		HubPanel.add(InformazioniCitt‡Lbl);
		
		
		JPanel LogoutPanel = new JPanel();
		LogoutPanel.setBounds(10, 373, 218, 72);
		BasePanel.add(LogoutPanel);
		
		JButton LogoutBtn = new JButton("Logout");
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
