package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Statistiche extends JFrame {

	private JPanel BasePanel;
	Controller controller;
	
	/**
	 * Create the frame.
	 */
	public Statistiche(Controller c) {
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
		
		JButton CompagnieBtn = new JButton("Compagnie");
		CompagnieBtn.setBounds(23, 10, 98, 21);
		BottoniPanel.add(CompagnieBtn);
		
		JButton VoliBtn = new JButton("Voli");
		VoliBtn.setBounds(23, 42, 98, 21);
		BottoniPanel.add(VoliBtn);
		
		JButton GateBtn = new JButton("Gate");
		GateBtn.setBounds(23, 73, 98, 21);
		BottoniPanel.add(GateBtn);
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 426, 85, 31);
		BasePanel.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*aggiungere transazione da statistiche ad hub*/
			}
		});
		IndietroPanel.add(IndietroBtn);
		
		JPanel StatistichePanel = new JPanel();
		StatistichePanel.setBounds(178, 10, 596, 447);
		BasePanel.add(StatistichePanel);
	}
}
