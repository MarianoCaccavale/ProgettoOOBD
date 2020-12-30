package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.Gate;
import Controller.Controller;
import Controller.ControllerGate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class GestioneGate extends JFrame {

	private JPanel BasePanel;
	
	Controller controller;
	private JTextField AggiuntaNomeTxt;
	private JTextField ModificaNuovoNomeTf;

	
	
	public GestioneGate(Controller c, Aeroporto a) {
		setTitle("Gestione Gate");
		controller = c;
		ControllerGate controllerGate = new ControllerGate();
		ArrayList<Gate> AllGate = new ArrayList<Gate>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 507);
		BasePanel = new JPanel();
		BasePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BasePanel);
		BasePanel.setLayout(null);
		
		/*Inizio TabbedPanel*/
		JPanel panel = new JPanel();
		panel.setBounds(178, 11, 549, 23);
		BasePanel.add(panel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(178, 10, 549, 450);
		BasePanel.add(tabbedPane);
		
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JLabel AggiuntaNomeLbl = new JLabel("Inserire il nome del gate:");
		AggiuntaNomeLbl.setBounds(193, 11, 189, 32);
		AggiuntaPanel.add(AggiuntaNomeLbl);
		
		JButton AggiuntaBtn = new JButton("Inserisci");
		AggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerGate.insertGate(AggiuntaNomeTxt.getText(), a.getCodAeroporto());
				AggiuntaNomeTxt.setText("");
			}
		});
		AggiuntaBtn.setBounds(445, 388, 89, 23);
		AggiuntaPanel.add(AggiuntaBtn);
		
		AggiuntaNomeTxt = new JTextField();
		AggiuntaNomeTxt.setBounds(193, 54, 189, 32);
		AggiuntaPanel.add(AggiuntaNomeTxt);
		AggiuntaNomeTxt.setColumns(10);
		
		JPanel ModificaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ModificaPanel, null);
		ModificaPanel.setLayout(null);
		
		JLabel ModificaVecchioNomeLbl = new JLabel("Scegli il gate da modificare:");
		ModificaVecchioNomeLbl.setBounds(10, 11, 226, 29);
		ModificaPanel.add(ModificaVecchioNomeLbl);
		
		JLabel ModificaNuovoNomeLbl = new JLabel("Inserire il nuovo nome del Gate:");
		ModificaNuovoNomeLbl.setBounds(295, 11, 226, 29);
		ModificaPanel.add(ModificaNuovoNomeLbl);
		
		JComboBox<String> ModificaVecchioNomeSpn = new JComboBox<String>();
		
		AllGate = controllerGate.getAllGate(a.getCodAeroporto());
		
		Iterator<Gate> iModificaGate = AllGate.iterator();
		
		while(iModificaGate.hasNext()) {
			
			Gate tmp = new Gate();
			tmp = iModificaGate.next();
			ModificaVecchioNomeSpn.addItem(tmp.getNomeGate());
			
		}
		
		ModificaVecchioNomeSpn.setBounds(10, 51, 226, 29);
		ModificaPanel.add(ModificaVecchioNomeSpn);
		
		ModificaNuovoNomeTf = new JTextField();
		ModificaNuovoNomeTf.setBounds(295, 51, 226, 29);
		ModificaPanel.add(ModificaNuovoNomeTf);
		ModificaNuovoNomeTf.setColumns(10);
		
		JButton ModificaBtn = new JButton("Modifica");
		ModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerGate.update(ModificaVecchioNomeSpn.getSelectedItem().toString(), ModificaNuovoNomeTf.getText(), a.getCodAeroporto());
				ModificaNuovoNomeTf.setText("");
				
				
			}
		});
		ModificaBtn.setBounds(409, 372, 125, 39);
		ModificaPanel.add(ModificaBtn);
		
		JPanel EliminaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminaPanel, null);
		EliminaPanel.setLayout(null);
		
		JLabel EliminaNomeLbl = new JLabel("Inserire il nome del gate da cancellare:");
		EliminaNomeLbl.setBounds(177, 11, 230, 33);
		EliminaPanel.add(EliminaNomeLbl);
		
		JComboBox<String> EliminaNomeSpn = new JComboBox<String>();
		EliminaNomeSpn.setBounds(177, 55, 230, 33);
		
		
		AllGate = controllerGate.getAllGate(a.getCodAeroporto());
		Iterator<Gate> iEliminaGate = AllGate.iterator();
		
		while(iEliminaGate.hasNext()) {
			
			Gate tmp = new Gate();
			tmp = iEliminaGate.next();
			EliminaNomeSpn.addItem(tmp.getNomeGate());
			
		}
		
		EliminaPanel.add(EliminaNomeSpn);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerGate.deleteByName(EliminaNomeSpn.getSelectedItem().toString(), a.getCodAeroporto());
				
				
			}
		});
		EliminaBtn.setBounds(455, 399, 89, 23);
		EliminaPanel.add(EliminaBtn);
		
		
		
		JPanel ElencoPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		
		/*Inizio pannello dei bottoni*/
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setLayout(null);
		BottoniPanel.setBounds(10, 10, 158, 252);
		BasePanel.add(BottoniPanel);
		
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
		
		/*Pannello bottone di ritorno*/
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 429, 77, 31);
		BasePanel.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.GateToHub(a);
				
			}
		});
		IndietroPanel.add(IndietroBtn);
	}
}
