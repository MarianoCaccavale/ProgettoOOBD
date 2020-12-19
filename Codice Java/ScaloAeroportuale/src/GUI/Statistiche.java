package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class Statistiche extends JFrame {

	private JPanel BasePanel;

	/**
	 * Create the frame.
	 */
	public Statistiche() {
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
		CompagnieBtn.setBounds(38, 10, 83, 21);
		BottoniPanel.add(CompagnieBtn);
		
		JButton VoliBtn = new JButton("Voli");
		VoliBtn.setBounds(55, 42, 49, 21);
		BottoniPanel.add(VoliBtn);
		
		JButton GateBtn = new JButton("Gate");
		GateBtn.setBounds(53, 73, 53, 21);
		BottoniPanel.add(GateBtn);
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 426, 85, 31);
		BasePanel.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroPanel.add(IndietroBtn);
		
		JPanel StatistichePanel = new JPanel();
		StatistichePanel.setBounds(178, 10, 596, 447);
		BasePanel.add(StatistichePanel);
	}
}
