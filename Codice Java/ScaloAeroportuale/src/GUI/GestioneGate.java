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
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;

public class GestioneGate extends JFrame {

	private JPanel BasePanel;
	
	Controller controller;
	private JTextField AggiuntaNomeTxt;
	private JTextField ModificaNuovoNomeTf;

	
	
	public GestioneGate(Controller c, Aeroporto a) {
		setResizable(false);
		setTitle("Gestione Gate");
		controller = c;
		ControllerGate controllerGate = new ControllerGate();
		ArrayList<Gate> AllGate = new ArrayList<Gate>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 501);
		BasePanel = new JPanel();
		BasePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BasePanel);
		BasePanel.setLayout(null);
		
		/*Inizio TabbedPanel*/
		JPanel panel = new JPanel();
		panel.setBounds(178, 10, 549, 23);
		BasePanel.add(panel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(178, 10, 542, 444);
		BasePanel.add(tabbedPane);
		
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JLabel AggiuntaNomeLbl = new JLabel("Inserire il nome del gate:");
		AggiuntaNomeLbl.setBounds(152, 48, 189, 32);
		AggiuntaPanel.add(AggiuntaNomeLbl);
		
		JButton AggiuntaBtn = new JButton("Inserisci");
		AggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerGate.insertGate(AggiuntaNomeTxt.getText(), a.getCodAeroporto());
				AggiuntaNomeTxt.setText("");
			}
		});
		AggiuntaBtn.setBounds(438, 384, 89, 23);
		AggiuntaPanel.add(AggiuntaBtn);
		
		AggiuntaNomeTxt = new JTextField();
		AggiuntaNomeTxt.setBounds(152, 90, 189, 32);
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
		ModificaVecchioNomeSpn.setModel(new DefaultComboBoxModel<String>(new String[] {"Selezionare il gate"}));
		
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
				
				if(ModificaVecchioNomeSpn.getSelectedIndex() != 0){
					
					controllerGate.update(ModificaVecchioNomeSpn.getSelectedItem().toString(), ModificaNuovoNomeTf.getText(), a.getCodAeroporto());
					ModificaNuovoNomeTf.setText("");
					
				}
				
			}
		});
		ModificaBtn.setBounds(402, 368, 125, 39);
		ModificaPanel.add(ModificaBtn);
		
		JPanel EliminaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminaPanel, null);
		EliminaPanel.setLayout(null);
		
		JLabel EliminaNomeLbl = new JLabel("Inserire il nome del gate da cancellare:");
		EliminaNomeLbl.setBounds(127, 11, 230, 33);
		EliminaPanel.add(EliminaNomeLbl);
		
		JComboBox<String> EliminaNomeSpn = new JComboBox<String>();
		EliminaNomeSpn.setModel(new DefaultComboBoxModel(new String[] {"Selezionare il gate"}));
		EliminaNomeSpn.setBounds(127, 54, 230, 33);
		
		
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
				
				if(EliminaNomeSpn.getSelectedIndex() != 0) {
					
					controllerGate.deleteByName(EliminaNomeSpn.getSelectedItem().toString(), a.getCodAeroporto());
					
				}
				
			}
		});
		EliminaBtn.setBounds(438, 384, 89, 23);
		EliminaPanel.add(EliminaBtn);
		
		
		//ELENCO
		JPanel ElencoPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		ElencoPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 537, 417);
		ElencoPanel.add(scrollPane);
		
		JTextPane ElencoTextPane = new JTextPane();
		scrollPane.setViewportView(ElencoTextPane);
		
		AllGate = controllerGate.getAllGate(a.getCodAeroporto());
		Iterator<Gate> iGate = AllGate.iterator();
		
		while(iGate.hasNext()) {
			Gate tmp = iGate.next();
			ElencoTextPane.setText(ElencoTextPane.getText() + "\n");
			ElencoTextPane.setText(ElencoTextPane.getText() + "Codice del Gate: " + tmp.getCodGate() +"\t\tNome Gate: " + tmp.getNomeGate() +"");
		}
		
		/*Inizio pannello dei bottoni*/
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setBounds(10, 10, 158, 252);
		BasePanel.add(BottoniPanel);
		
		JButton AggiungereBtn = new JButton("Aggiungere");
		AggiungereBtn.setBounds(10, 10, 138, 38);
		AggiungereBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		BottoniPanel.setLayout(null);
		BottoniPanel.add(AggiungereBtn);
		
		JButton ModificareBtn = new JButton("Modificare");
		ModificareBtn.setBounds(10, 58, 138, 38);
		ModificareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		BottoniPanel.add(ModificareBtn);
		
		JButton EliminareBtn = new JButton("Eliminare");
		EliminareBtn.setBounds(10, 106, 138, 38);
		EliminareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		BottoniPanel.add(EliminareBtn);
		
		JButton ElencoBtn = new JButton("Elenco");
		ElencoBtn.setBounds(10, 154, 138, 38);
		ElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(3);
				
			}
		});
		BottoniPanel.add(ElencoBtn);
		
		/*Pannello bottone di ritorno*/
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 393, 158, 61);
		BasePanel.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.setBounds(10, 10, 138, 41);
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.GateToHub(a);
				
			}
		});
		IndietroPanel.setLayout(null);
		IndietroPanel.add(IndietroBtn);
	}
}
