package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import Controller.Controller;
import Controller.ControllerCompagnie;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class GestioneCompagnie extends JFrame {

	private JPanel contentPane;
	Controller controller;
	private JTextField NomeTf;
	private JTextField RicercaNomeTxt;

	public GestioneCompagnie(Controller c, Aeroporto a) {
		setResizable(false);
		setTitle("Gestione Compagnia");
		
		
		controller = c;
		ControllerCompagnie controllerCompagnia = new ControllerCompagnie();
		ArrayList<CompagniaAerea> Compagnie = new ArrayList<CompagniaAerea>();
		
		
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
		
		
		//Pannello Ritorno Hub
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 467, 158, 31);
		contentPane.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.CompagnieToHub(a);
			}
		});
		IndietroPanel.add(IndietroBtn);
		
		
		
		//Sezione TabbedPanel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(178, 11, 547, 487);
		contentPane.add(tabbedPane);
		
		//Pannello Aggiunta
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("Aggiunta", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JLabel InsertLbl = new JLabel("Inserire nome compagnia:");
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
		FlottaSpn.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		FlottaSpn.setBounds(279, 47, 143, 25);
		AggiuntaPanel.add(FlottaSpn);
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerCompagnia.Insert(NomeTf.getText(), (Integer) FlottaSpn.getValue(), a);
				NomeTf.setText("");
				FlottaSpn.setValue(0);
				
				}
		});
		btnNewButton.setBounds(436, 424, 106, 35);
		AggiuntaPanel.add(btnNewButton);
		
		
		
		//Pannello Modifica
		JPanel ModifcaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ModifcaPanel, null);
		ModifcaPanel.setLayout(null);
		
		JLabel NomeModificaLbl = new JLabel("Scegli la compagnia da modificare:");
		NomeModificaLbl.setBounds(10, 11, 231, 26);
		ModifcaPanel.add(NomeModificaLbl);
		
		JComboBox<String> ModificaNomeCombo = new JComboBox<String>();
		
		Compagnie = controllerCompagnia.getCompagnie(a);
		Iterator<CompagniaAerea> modificaIterator = Compagnie.iterator();
		while (modificaIterator.hasNext()) {
			
			CompagniaAerea tmp = new CompagniaAerea();
			tmp = modificaIterator.next();
			ModificaNomeCombo.addItem(tmp.getNomeCompagnia());
			
		}
		
		ModificaNomeCombo.setBounds(10, 48, 231, 26);
		ModifcaPanel.add(ModificaNomeCombo);
		
		
		JLabel FlottaModificaLbl = new JLabel("Nuova grandezza della flotta: ");
		FlottaModificaLbl.setBounds(251, 11, 281, 26);
		ModifcaPanel.add(FlottaModificaLbl);
		
		JSpinner ModificaGrandezzaFlottaSpn = new JSpinner();
		ModificaGrandezzaFlottaSpn.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		ModificaGrandezzaFlottaSpn.setBounds(251, 48, 281, 26);
		ModifcaPanel.add(ModificaGrandezzaFlottaSpn);
		
		JButton ModificaBtn = new JButton("Modifica");
		ModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerCompagnia.Update(ModificaNomeCombo.getSelectedItem().toString(), (Integer) ModificaGrandezzaFlottaSpn.getValue(), a);
				ModificaNomeCombo.setSelectedIndex(0);
				ModificaGrandezzaFlottaSpn.setValue(0);
				
			}
		});
		ModificaBtn.setBounds(415, 412, 117, 36);
		ModifcaPanel.add(ModificaBtn);
		
		
		JPanel EliminaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminaPanel, null);
		EliminaPanel.setLayout(null);
		
		JLabel CancellazioneNomeLbl = new JLabel("Scegliere il nome della compagnia da cancellare:");
		CancellazioneNomeLbl.setBounds(107, 11, 309, 31);
		EliminaPanel.add(CancellazioneNomeLbl);
		
		JComboBox<String> CancellazioneNomeComboBox = new JComboBox<String>();
		
		Compagnie = controllerCompagnia.getCompagnie(a);
		Iterator<CompagniaAerea> cancellazioneIterator = Compagnie.iterator();
		
		while(cancellazioneIterator.hasNext()) {
			
			CompagniaAerea tmp = cancellazioneIterator.next();
			CancellazioneNomeComboBox.addItem(tmp.getNomeCompagnia());
			
		}
				
		CancellazioneNomeComboBox.setBounds(107, 53, 260, 31);
		EliminaPanel.add(CancellazioneNomeComboBox);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(CancellazioneNomeComboBox.getSelectedItem() != null) {
					controllerCompagnia.delete(CancellazioneNomeComboBox.getSelectedItem().toString());
				}	
				
			}
		});
		EliminaBtn.setBounds(411, 410, 121, 38);
		EliminaPanel.add(EliminaBtn);
		
		
		
		
		//Pannello Elenco
		JPanel ElencoPanel = new JPanel();
		ElencoPanel.setBorder(null);
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		ElencoPanel.setLayout(null);
		
		JPanel RicercaPanel = new JPanel();
		RicercaPanel.setBounds(10, 11, 522, 75);
		ElencoPanel.add(RicercaPanel);
		RicercaPanel.setLayout(null);
		
		JLabel RicercaNomeLbl = new JLabel("Inserire nome compagnia:");
		RicercaNomeLbl.setBounds(10, 11, 189, 26);
		RicercaPanel.add(RicercaNomeLbl);
		
		RicercaNomeTxt = new JTextField();
		RicercaNomeTxt.setBounds(10, 44, 189, 31);
		RicercaPanel.add(RicercaNomeTxt);
		RicercaNomeTxt.setColumns(10);
		
		JLabel RicercaGrandezzaLbl = new JLabel("Scegliere grandezza flotta:");
		RicercaGrandezzaLbl.setBounds(278, 11, 234, 26);
		RicercaPanel.add(RicercaGrandezzaLbl);
		
		JSpinner RicercaGrandezzaMinimaSpn = new JSpinner();
		RicercaGrandezzaMinimaSpn.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		RicercaGrandezzaMinimaSpn.setBounds(258, 44, 122, 31);
		RicercaPanel.add(RicercaGrandezzaMinimaSpn);
		
		JSpinner RicercaGrandezzaMassimaSpn = new JSpinner();
		RicercaGrandezzaMassimaSpn.setModel(new SpinnerNumberModel(500, 0, 500, 1));
		RicercaGrandezzaMassimaSpn.setBounds(390, 44, 122, 31);
		RicercaPanel.add(RicercaGrandezzaMassimaSpn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 522, 317);
		ElencoPanel.add(scrollPane);
		
		JTextPane RicercaTextPane = new JTextPane();
		RicercaTextPane.setEditable(false);
		scrollPane.setViewportView(RicercaTextPane);
		
		//ELENCO DA SISTEMARE
		
		Compagnie = controllerCompagnia.getCompagnie(a);
		Iterator<CompagniaAerea> i = Compagnie.iterator();
		while (i.hasNext()) {
			
			CompagniaAerea tmp = i.next();
			RicercaTextPane.setText(RicercaTextPane.getText() + "\n");
			RicercaTextPane.setText(RicercaTextPane.getText() + "Codice Compagnia: " + tmp.getCodCompagnia() + "\tNome compagnia: " + tmp.getNomeCompagnia() + "\tGrandezza Flotta: " + tmp.getGrandezzaFlotta() +" ");
			
		}
		
		JButton RicercaBtn = new JButton("Ricerca");
		RicercaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RicercaTextPane.setText("");
				
				ArrayList<CompagniaAerea> CompagnieTrovate = controllerCompagnia.ricerca(RicercaNomeTxt.getText(), (Integer) RicercaGrandezzaMinimaSpn.getValue(), (Integer) RicercaGrandezzaMassimaSpn.getValue(), a);
				Iterator<CompagniaAerea> i = CompagnieTrovate.iterator();
				while (i.hasNext()) {
					
					CompagniaAerea tmp = i.next();
					RicercaTextPane.setText(RicercaTextPane.getText() + "\n");
					RicercaTextPane.setText(RicercaTextPane.getText() + "Codice Compagnia: " + tmp.getCodCompagnia() + " Nome compagnia: " + tmp.getNomeCompagnia() + " Grandezza Flotta: " + tmp.getGrandezzaFlotta() +" ");
					
					
				}
			}
		});
		RicercaBtn.setBounds(443, 425, 89, 23);
		ElencoPanel.add(RicercaBtn);
		
		
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setLayout(null);
		BottoniPanel.setBounds(10, 34, 158, 252);
		contentPane.add(BottoniPanel);
		
		JButton AggiungereBtn = new JButton("Aggiungere");
		AggiungereBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
				tabbedPane.setSelectedIndex(0);
			
			}
		});
		AggiungereBtn.setBounds(10, 10, 138, 38);
		BottoniPanel.add(AggiungereBtn);
				
		JButton ModificareBtn = new JButton("Modificare");
		ModificareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
				
		ModificareBtn.setBounds(10, 58, 138, 38);
		BottoniPanel.add(ModificareBtn);
			
		JButton EliminareBtn = new JButton("Eliminare");
		EliminareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		EliminareBtn.setBounds(10, 106, 138, 38);
		BottoniPanel.add(EliminareBtn);
			
		JButton ElencoBtn = new JButton("Elenco");
		ElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(3);
				
			}
		});
		ElencoBtn.setBounds(10, 154, 138, 38);
		BottoniPanel.add(ElencoBtn);
		}
}