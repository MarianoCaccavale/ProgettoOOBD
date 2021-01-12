package GUI;


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
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

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
		setBounds(100, 100, 883, 501);
		BasePanel = new JPanel();
		BasePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BasePanel);
		BasePanel.setLayout(null);
		
		/*Inizio TabbedPanel*/
		JPanel panel = new JPanel();
		panel.setBounds(178, 10, 681, 20);
		BasePanel.add(panel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(178, 10, 681, 444);
		BasePanel.add(tabbedPane);
		
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JLabel AggiuntaNomeLbl = new JLabel("Inserire il nome del gate:");
		AggiuntaNomeLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		AggiuntaNomeLbl.setBounds(250, 113, 189, 32);
		AggiuntaPanel.add(AggiuntaNomeLbl);
		
		JButton AggiuntaBtn = new JButton("Aggiungi");
		AggiuntaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		AggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerGate.insertGate(AggiuntaNomeTxt.getText(), a.getCodAeroporto());
				AggiuntaNomeTxt.setText("");
			}
		});
		AggiuntaBtn.setBounds(528, 369, 138, 38);
		AggiuntaPanel.add(AggiuntaBtn);
		
		AggiuntaNomeTxt = new JTextField();
		AggiuntaNomeTxt.setFont(new Font("Arial", Font.PLAIN, 16));
		AggiuntaNomeTxt.setBounds(250, 155, 189, 53);
		AggiuntaPanel.add(AggiuntaNomeTxt);
		AggiuntaNomeTxt.setColumns(10);
		
		JPanel ModificaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ModificaPanel, null);
		ModificaPanel.setLayout(null);
		
		JLabel ModificaNuovoNomeLbl = new JLabel("Inserire il nuovo nome del Gate:");
		ModificaNuovoNomeLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaNuovoNomeLbl.setBounds(193, 141, 226, 29);
		ModificaPanel.add(ModificaNuovoNomeLbl);
		
		JComboBox<String> ModificaVecchioNomeSpn = new JComboBox<String>();
		ModificaVecchioNomeSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaVecchioNomeSpn.setModel(new DefaultComboBoxModel(new String[] {"Scegliere il gate"}));
		
		AllGate = controllerGate.getAllGate(a.getCodAeroporto());
		
		Iterator<Gate> iModificaGate = AllGate.iterator();
		
		while(iModificaGate.hasNext()) {
			
			Gate tmp = new Gate();
			tmp = iModificaGate.next();
			ModificaVecchioNomeSpn.addItem(tmp.getNomeGate());
			
		}
		
		ModificaVecchioNomeSpn.setBounds(193, 69, 226, 50);
		ModificaPanel.add(ModificaVecchioNomeSpn);
		
		ModificaNuovoNomeTf = new JTextField();
		ModificaNuovoNomeTf.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaNuovoNomeTf.setBounds(193, 181, 226, 56);
		ModificaPanel.add(ModificaNuovoNomeTf);
		ModificaNuovoNomeTf.setColumns(10);
		
		JButton ModificaBtn = new JButton("Modifica");
		ModificaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ModificaVecchioNomeSpn.getSelectedIndex() != 0){
					
					controllerGate.update(ModificaVecchioNomeSpn.getSelectedItem().toString(), ModificaNuovoNomeTf.getText(), a.getCodAeroporto());
					ModificaNuovoNomeTf.setText("");
					
				}
				
			}
		});
		ModificaBtn.setBounds(541, 368, 125, 39);
		ModificaPanel.add(ModificaBtn);
		
		JPanel EliminaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminaPanel, null);
		EliminaPanel.setLayout(null);
		
		JComboBox<String> EliminaNomeSpn = new JComboBox<String>();
		EliminaNomeSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminaNomeSpn.setModel(new DefaultComboBoxModel(new String[] {"Selezionare il gate"}));
		EliminaNomeSpn.setBounds(212, 152, 230, 50);
		
		
		AllGate = controllerGate.getAllGate(a.getCodAeroporto());
		Iterator<Gate> iEliminaGate = AllGate.iterator();
		
		while(iEliminaGate.hasNext()) {
			
			Gate tmp = new Gate();
			tmp = iEliminaGate.next();
			EliminaNomeSpn.addItem(tmp.getNomeGate());
			
		}
		
		EliminaPanel.add(EliminaNomeSpn);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(EliminaNomeSpn.getSelectedIndex() != 0) {
					
					controllerGate.deleteByName(EliminaNomeSpn.getSelectedItem().toString(), a.getCodAeroporto());
					
				}
				
			}
		});
		EliminaBtn.setBounds(528, 369, 138, 38);
		EliminaPanel.add(EliminaBtn);
		
		
		//ELENCO
		JPanel ElencoPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		ElencoPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 676, 417);
		ElencoPanel.add(scrollPane);
		
		JTextPane ElencoTextPane = new JTextPane();
		ElencoTextPane.setEditable(false);
		ElencoTextPane.setFont(new Font("Arial", Font.PLAIN, 20));
		scrollPane.setViewportView(ElencoTextPane);
		
		AllGate = controllerGate.getAllGate(a.getCodAeroporto());
		Iterator<Gate> iGate = AllGate.iterator();
		
		while(iGate.hasNext()) {
			Gate tmp = iGate.next();
			ElencoTextPane.setText(ElencoTextPane.getText() + "\n");
			ElencoTextPane.setText(ElencoTextPane.getText() + "Nome Gate: " + tmp.getNomeGate() +"");
		}
		
		/*Inizio pannello dei bottoni*/
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setBounds(10, 10, 158, 252);
		BasePanel.add(BottoniPanel);
		
		JButton AggiungereBtn = new JButton("Aggiunta");
		AggiungereBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		AggiungereBtn.setBounds(10, 10, 138, 38);
		AggiungereBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		BottoniPanel.setLayout(null);
		BottoniPanel.add(AggiungereBtn);
		
		JButton ModificareBtn = new JButton("Modifica");
		ModificareBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificareBtn.setBounds(10, 58, 138, 38);
		ModificareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		BottoniPanel.add(ModificareBtn);
		
		JButton EliminareBtn = new JButton("Eliminazione");
		EliminareBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminareBtn.setBounds(10, 106, 138, 38);
		EliminareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		BottoniPanel.add(EliminareBtn);
		
		JButton ElencoBtn = new JButton("Ricerca");
		ElencoBtn.setFont(new Font("Arial", Font.PLAIN, 16));
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
		IndietroBtn.setFont(new Font("Arial", Font.PLAIN, 16));
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
