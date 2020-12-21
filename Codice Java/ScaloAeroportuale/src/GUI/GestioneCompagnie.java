package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Controller.Controller;
import Controller.ControllerCompagnie;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class GestioneCompagnie extends JFrame {

	private JPanel contentPane;
	Controller controller;
	private JTextField NomeTf;

	/**
	 * Create the frame.
	 */
	public GestioneCompagnie(Controller c, Aeroporto a) {
		controller = c;
		ControllerCompagnie cc = new ControllerCompagnie();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(178, 11, 547, 23);
		contentPane.add(panel);
		
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setLayout(null);
		BottoniPanel.setBounds(10, 11, 158, 252);
		contentPane.add(BottoniPanel);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(178, 11, 547, 487);
		contentPane.add(tabbedPane);
		
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("Aggiunta", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JLabel InsertLbl = new JLabel("Inserire Nome Aeroporto:");
		InsertLbl.setBounds(10, 11, 143, 25);
		AggiuntaPanel.add(InsertLbl);
		
		NomeTf = new JTextField();
		NomeTf.setBounds(10, 47, 143, 25);
		AggiuntaPanel.add(NomeTf);
		NomeTf.setColumns(10);
		
		JLabel FlottaLbl = new JLabel("Inserire Grandezza Flotta:");
		FlottaLbl.setBounds(279, 11, 190, 25);
		AggiuntaPanel.add(FlottaLbl);
		
		JSpinner FlottaSpn = new JSpinner();
		FlottaSpn.setBounds(279, 47, 143, 25);
		AggiuntaPanel.add(FlottaSpn);
		
		JPanel ModifcaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ModifcaPanel, null);
		
		JLabel lblNewLabel_1 = new JLabel("secondo");
		ModifcaPanel.add(lblNewLabel_1);
		
		JPanel EliminaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminaPanel, null);
		
		JPanel ElencoPanel = new JPanel();
		ElencoPanel.setBorder(null);
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		
		JLabel NomeVuotoLbl = new JLabel();
		NomeVuotoLbl.setBounds(10, 83, 143, 25);
		AggiuntaPanel.add(NomeVuotoLbl);
		
		JLabel FlottaInvalidaLbl = new JLabel();
		FlottaInvalidaLbl.setBounds(279, 83, 143, 25);
		AggiuntaPanel.add(FlottaInvalidaLbl);
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (NomeTf.getText() == "") {
					
					NomeVuotoLbl.setText("Nome Inserito vuoto!");
					
				}else if ((Integer) FlottaSpn.getValue() < 100 && (Integer) FlottaSpn.getValue() > 500) {
					
					FlottaInvalidaLbl.setText("Valore non valido! Inserire un valore tra 100(min) e 500(max)");
					
				}else {
					
					cc.Insert(NomeTf.getText().toString(), (Integer) FlottaSpn.getValue(), a);
					NomeTf.setText("");
					FlottaSpn.setValue(0);
					
				}
				
				
			}
		});
		btnNewButton.setBounds(436, 424, 106, 35);
		AggiuntaPanel.add(btnNewButton);
		
		
		
		
		
		JButton AggiungereBtn = new JButton("Aggiungere");
		AggiungereBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		AggiungereBtn.setBounds(10, 10, 138, 22);
		BottoniPanel.add(AggiungereBtn);
		
		JButton ModificareBtn = new JButton("Modificare");
		ModificareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		
		ModificareBtn.setBounds(10, 42, 138, 21);
		BottoniPanel.add(ModificareBtn);
		
		JButton EliminareBtn = new JButton("Eliminare");
		EliminareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		EliminareBtn.setBounds(10, 73, 138, 21);
		BottoniPanel.add(EliminareBtn);
		
		JButton ElencoBtn = new JButton("Elenco");
		ElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(3);
				
			}
		});
		ElencoBtn.setBounds(10, 104, 138, 21);
		BottoniPanel.add(ElencoBtn);
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 467, 158, 31);
		contentPane.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.CompagnieToHub();
			}
		});
		IndietroPanel.add(IndietroBtn);
	}
}
