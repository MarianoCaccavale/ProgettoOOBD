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
		panel.setBounds(178, 10, 681, 26);
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
		
		JComboBox<String> ModificaVecchioNomeCombo = new JComboBox<String>();
		JComboBox<String> EliminaNomeCombo = new JComboBox<String>();
		
		JButton AggiuntaBtn = new JButton("Aggiungi");
		AggiuntaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		AggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerGate.insert(AggiuntaNomeTxt.getText(), a.getCodAeroporto());
				ModificaVecchioNomeCombo.addItem(AggiuntaNomeTxt.getText());
				EliminaNomeCombo.addItem(AggiuntaNomeTxt.getText());
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
		
		
		ModificaVecchioNomeCombo.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaVecchioNomeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere il gate"}));
		
		AllGate = controllerGate.getAllGate(a.getCodAeroporto());
		
		for (Gate tmp : AllGate) {
			
			ModificaVecchioNomeCombo.addItem(tmp.getNomeGate());
			
		}
		
		ModificaVecchioNomeCombo.setBounds(193, 69, 226, 50);
		ModificaPanel.add(ModificaVecchioNomeCombo);
		
		ModificaNuovoNomeTf = new JTextField();
		ModificaNuovoNomeTf.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaNuovoNomeTf.setBounds(193, 181, 226, 56);
		ModificaPanel.add(ModificaNuovoNomeTf);
		ModificaNuovoNomeTf.setColumns(10);
		
		JButton ModificaBtn = new JButton("Modifica");
		ModificaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ModificaVecchioNomeCombo.getSelectedIndex() != 0){
					
					controllerGate.update(ModificaVecchioNomeCombo.getSelectedItem().toString(), ModificaNuovoNomeTf.getText(), a.getCodAeroporto());
					EliminaNomeCombo.removeItemAt(ModificaVecchioNomeCombo.getSelectedIndex());
					EliminaNomeCombo.addItem(ModificaNuovoNomeTf.getText());
					ModificaVecchioNomeCombo.removeItemAt(ModificaVecchioNomeCombo.getSelectedIndex());
					ModificaVecchioNomeCombo.addItem(ModificaNuovoNomeTf.getText());
					
					ModificaNuovoNomeTf.setText("");
					
				}
				
			}
		});
		ModificaBtn.setBounds(541, 368, 125, 39);
		ModificaPanel.add(ModificaBtn);
		
		JPanel EliminaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminaPanel, null);
		EliminaPanel.setLayout(null);
		EliminaNomeCombo.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminaNomeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Selezionare il gate"}));
		EliminaNomeCombo.setBounds(212, 152, 230, 50);
		
		for(Gate tmp : AllGate){
			
			EliminaNomeCombo.addItem(tmp.getNomeGate());
			
		}
		
		EliminaPanel.add(EliminaNomeCombo);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(EliminaNomeCombo.getSelectedIndex() != 0) {
					
					controllerGate.deleteByName(EliminaNomeCombo.getSelectedItem().toString(), a.getCodAeroporto());
					ModificaVecchioNomeCombo.removeItemAt(EliminaNomeCombo.getSelectedIndex());
					EliminaNomeCombo.removeItemAt(EliminaNomeCombo.getSelectedIndex());
					
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
		
		for(Gate tmp: AllGate) {
			
			ElencoTextPane.setText(ElencoTextPane.getText() + "Nome Gate: " + tmp.getNomeGate() +"");
			
		}
		
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
