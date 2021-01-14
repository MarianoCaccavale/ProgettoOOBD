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
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

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
		setBounds(100, 100, 1015, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(178, 11, 813, 19);
		contentPane.add(panel);
		
		
		//Pannello Ritorno Hub
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 441, 158, 57);
		contentPane.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		IndietroBtn.setBounds(10, 10, 138, 38);
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.CompagnieToHub(a);
			}
		});
		IndietroPanel.setLayout(null);
		IndietroPanel.add(IndietroBtn);
		
		
		
		//Sezione TabbedPanel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(178, 11, 813, 487);
		contentPane.add(tabbedPane);
		
		//Pannello Aggiunta
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("Aggiunta", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JLabel InsertLbl = new JLabel("Inserire nome compagnia:");
		InsertLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		InsertLbl.setBounds(274, 81, 263, 25);
		AggiuntaPanel.add(InsertLbl);
		
		NomeTf = new JTextField();
		NomeTf.setFont(new Font("Arial", Font.PLAIN, 16));
		NomeTf.setBounds(274, 117, 236, 40);
		AggiuntaPanel.add(NomeTf);
		NomeTf.setColumns(10);
		
		JLabel FlottaLbl = new JLabel("Inserire Grandezza Flotta:");
		FlottaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		FlottaLbl.setBounds(305, 167, 190, 25);
		AggiuntaPanel.add(FlottaLbl);
		
		JSpinner FlottaSpn = new JSpinner();
		FlottaSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		FlottaSpn.setModel(new SpinnerNumberModel(1, 1, 500, 1));
		FlottaSpn.setBounds(315, 203, 133, 40);
		AggiuntaPanel.add(FlottaSpn);
		
		JButton btnNewButton = new JButton("Aggiungi");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerCompagnia.Insert(NomeTf.getText(), (Integer) FlottaSpn.getValue(), a);
				NomeTf.setText("");
				FlottaSpn.setValue(0);
				
				}
		});
		btnNewButton.setBounds(692, 415, 106, 35);
		AggiuntaPanel.add(btnNewButton);
		
		
		
		//Pannello Modifica
		JPanel ModifcaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ModifcaPanel, null);
		ModifcaPanel.setLayout(null);
		
		JComboBox<String> ModificaNomeCombo = new JComboBox<String>();
		ModificaNomeCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		ModificaNomeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere la compagnia"}));
		
		Compagnie = controllerCompagnia.getCompagnie(a);
		
		for (CompagniaAerea tmp : Compagnie) {
			
			ModificaNomeCombo.addItem(tmp.getNomeCompagnia());
			
		}
		
		ModificaNomeCombo.setBounds(220, 92, 330, 50);
		ModifcaPanel.add(ModificaNomeCombo);
		
		
		JLabel FlottaModificaLbl = new JLabel("Nuova grandezza della flotta: ");
		FlottaModificaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		FlottaModificaLbl.setBounds(269, 177, 281, 26);
		ModifcaPanel.add(FlottaModificaLbl);
		
		JSpinner ModificaGrandezzaFlottaSpn = new JSpinner();
		ModificaGrandezzaFlottaSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaGrandezzaFlottaSpn.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		ModificaGrandezzaFlottaSpn.setBounds(315, 213, 144, 46);
		ModifcaPanel.add(ModificaGrandezzaFlottaSpn);
		
		JButton ModificaBtn = new JButton("Modifica");
		ModificaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (ModificaNomeCombo.getSelectedIndex() != 0) {
					
					controllerCompagnia.Update(ModificaNomeCombo.getSelectedItem().toString(), (Integer) ModificaGrandezzaFlottaSpn.getValue(), a);
					ModificaNomeCombo.setSelectedIndex(0);
					ModificaGrandezzaFlottaSpn.setValue(0);
					
				}
				
			}
		});
		ModificaBtn.setBounds(681, 414, 117, 36);
		ModifcaPanel.add(ModificaBtn);
		
		
		
		//Pannello Eliminazione
		JPanel EliminaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminaPanel, null);
		EliminaPanel.setLayout(null);
		
		JComboBox<String> CancellazioneNomeComboBox = new JComboBox<String>();
		CancellazioneNomeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere la compagnia"}));
		CancellazioneNomeComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		
		for (CompagniaAerea tmp : Compagnie) {
			
			CancellazioneNomeComboBox.addItem(tmp.getNomeCompagnia());
			
		}
				
		CancellazioneNomeComboBox.setBounds(243, 167, 330, 50);
		EliminaPanel.add(CancellazioneNomeComboBox);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(CancellazioneNomeComboBox.getSelectedItem() != null) {
					
					if(CancellazioneNomeComboBox.getSelectedIndex() != 0) {
						
						controllerCompagnia.delete(CancellazioneNomeComboBox.getSelectedItem().toString());
						
					}
				}	
				
			}
		});
		EliminaBtn.setBounds(660, 412, 138, 38);
		EliminaPanel.add(EliminaBtn);
		
		
		
		
		//Pannello Elenco
		JPanel ElencoPanel = new JPanel();
		ElencoPanel.setBorder(null);
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		ElencoPanel.setLayout(null);
		
		JPanel RicercaPanel = new JPanel();
		RicercaPanel.setBounds(10, 11, 788, 75);
		ElencoPanel.add(RicercaPanel);
		RicercaPanel.setLayout(null);
		
		JLabel RicercaNomeLbl = new JLabel("Inserire il nome della compagnia:");
		RicercaNomeLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaNomeLbl.setBounds(10, 11, 258, 26);
		RicercaPanel.add(RicercaNomeLbl);
		
		RicercaNomeTxt = new JTextField();
		RicercaNomeTxt.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaNomeTxt.setBounds(10, 44, 233, 31);
		RicercaPanel.add(RicercaNomeTxt);
		RicercaNomeTxt.setColumns(10);
		
		JLabel RicercaGrandezzaLbl = new JLabel("Scegliere la grandezza della flotta:");
		RicercaGrandezzaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaGrandezzaLbl.setBounds(389, 11, 349, 26);
		RicercaPanel.add(RicercaGrandezzaLbl);
		
		JSpinner RicercaGrandezzaMinimaSpn = new JSpinner();
		RicercaGrandezzaMinimaSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaGrandezzaMinimaSpn.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		RicercaGrandezzaMinimaSpn.setBounds(399, 44, 122, 31);
		RicercaPanel.add(RicercaGrandezzaMinimaSpn);
		
		JSpinner RicercaGrandezzaMassimaSpn = new JSpinner();
		RicercaGrandezzaMassimaSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaGrandezzaMassimaSpn.setModel(new SpinnerNumberModel(500, 0, 500, 1));
		RicercaGrandezzaMassimaSpn.setBounds(531, 44, 122, 31);
		RicercaPanel.add(RicercaGrandezzaMassimaSpn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 788, 302);
		ElencoPanel.add(scrollPane);
		
		JTextPane RicercaTextPane = new JTextPane();
		RicercaTextPane.setFont(new Font("Arial", Font.PLAIN, 14));
		RicercaTextPane.setEditable(false);
		scrollPane.setViewportView(RicercaTextPane);
		
		for (CompagniaAerea tmp : Compagnie) {
			
			RicercaTextPane.setText(RicercaTextPane.getText() + "\n");
			RicercaTextPane.setText(RicercaTextPane.getText() + "Nome compagnia: " + tmp.getNomeCompagnia() + "\tGrandezza Flotta: " + tmp.getGrandezzaFlotta() +" ");
			
		}
		
		JButton RicercaBtn = new JButton("Cerca");
		RicercaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RicercaTextPane.setText("");
				
				ArrayList<CompagniaAerea> CompagnieTrovate = controllerCompagnia.ricerca(RicercaNomeTxt.getText(), (Integer) RicercaGrandezzaMinimaSpn.getValue(), (Integer) RicercaGrandezzaMassimaSpn.getValue(), a);

				for(CompagniaAerea tmp : CompagnieTrovate) {
					
					RicercaTextPane.setText(RicercaTextPane.getText() + "\n");
					RicercaTextPane.setText(RicercaTextPane.getText() + "Nome compagnia: " + tmp.getNomeCompagnia() + " Grandezza Flotta: " + tmp.getGrandezzaFlotta() +" ");
					
					
				}
			}
		});
		RicercaBtn.setBounds(660, 412, 138, 38);
		ElencoPanel.add(RicercaBtn);
		
		
		//Pannello dei bottoni per le scelte
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setLayout(null);
		BottoniPanel.setBounds(10, 25, 158, 252);
		contentPane.add(BottoniPanel);
		
		JButton AggiungereBtn = new JButton("Aggiunta");
		AggiungereBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		AggiungereBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
				tabbedPane.setSelectedIndex(0);
			
			}
		});
		AggiungereBtn.setBounds(10, 10, 138, 38);
		BottoniPanel.add(AggiungereBtn);
				
		JButton ModificareBtn = new JButton("Modifica");
		ModificareBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
				
		ModificareBtn.setBounds(10, 58, 138, 38);
		BottoniPanel.add(ModificareBtn);
			
		JButton EliminareBtn = new JButton("Eliminazione");
		EliminareBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		EliminareBtn.setBounds(10, 106, 138, 38);
		BottoniPanel.add(EliminareBtn);
			
		JButton ElencoBtn = new JButton("Ricerca");
		ElencoBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(3);
				
			}
		});
		ElencoBtn.setBounds(10, 154, 138, 38);
		BottoniPanel.add(ElencoBtn);
		}
}