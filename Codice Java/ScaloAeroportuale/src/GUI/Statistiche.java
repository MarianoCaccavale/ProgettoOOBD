package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Controller.Controller;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class Statistiche extends JFrame {

	private JPanel BasePanel;
	Controller controller;
	
	/**
	 * Create the frame.
	 */
	public Statistiche(Controller c, Aeroporto a) {
		controller = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 504);
		BasePanel = new JPanel();
		BasePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BasePanel);
		BasePanel.setLayout(null);
		
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setBounds(10, 10, 158, 252);
		BasePanel.add(BottoniPanel);
		BottoniPanel.setLayout(null);
		
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 426, 85, 31);
		BasePanel.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.statisticheToHub(a);
				
			}
		});
		IndietroPanel.add(IndietroBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(178, 11, 594, 446);
		BasePanel.add(tabbedPane);
		
		JPanel CompagniePanel = new JPanel();
		tabbedPane.addTab("New tab", null, CompagniePanel, null);
		
		JPanel VoliPanel = new JPanel();
		tabbedPane.addTab("New tab", null, VoliPanel, null);
		
		JPanel GatePanel = new JPanel();
		tabbedPane.addTab("New tab", null, GatePanel, null);
		
		
		JButton CompagnieBtn = new JButton("Compagnie");
		CompagnieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		CompagnieBtn.setBounds(23, 10, 98, 21);
		BottoniPanel.add(CompagnieBtn);
		
		JButton VoliBtn = new JButton("Voli");
		VoliBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		VoliBtn.setBounds(23, 42, 98, 21);
		BottoniPanel.add(VoliBtn);
		
		JButton GateBtn = new JButton("Gate");
		GateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		GateBtn.setBounds(23, 73, 98, 21);
		BottoniPanel.add(GateBtn);
	}
}
