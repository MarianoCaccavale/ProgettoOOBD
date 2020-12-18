package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hub extends JFrame {

	private JPanel contentPane;
	Controller controller;

	/**
	 * Create the frame.
	 */
	public Hub(Controller c) {
		controller = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 158, 172);
		contentPane.add(panel);
		
		JButton StatisticheBtn = new JButton("Statistiche");
		panel.add(StatisticheBtn);
		
		JButton GestioneTratteBtn = new JButton("Gestione Tratte");
		panel.add(GestioneTratteBtn);
		
		JButton GestioneVoliBtn = new JButton("Gestione Voli");
		panel.add(GestioneVoliBtn);
		
		JButton GestioneCompagnieBtn = new JButton("Gestione Compagnie");
		panel.add(GestioneCompagnieBtn);
		
		JButton GestioneGateBtn = new JButton("Gestione Gate");
		panel.add(GestioneGateBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 474, 85, 31);
		contentPane.add(panel_1);
		
		JButton LogoutBtn = new JButton("Logout");
		LogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.Logout();
			}
		});
		panel_1.add(LogoutBtn);
	}
}
