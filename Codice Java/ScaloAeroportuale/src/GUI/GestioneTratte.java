package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.Tratta;
import Controller.Controller;
import Controller.ControllerAeroporti;
import Controller.ControllerTratte;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class GestioneTratte extends JFrame {

	private JPanel contentPane;

	Controller controller;
	
	
	
	public GestioneTratte(Controller c, Aeroporto a) {
		setResizable(false);
		setTitle("Gestione Tratte");
		controller = c;
		ControllerTratte controllerTratte = new ControllerTratte();
		ControllerAeroporti controllerAeroporti = new ControllerAeroporti();
		ArrayList<Aeroporto> Aeroporti = new ArrayList<Aeroporto>();
		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 972, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel SceltaPanel = new JPanel();
		SceltaPanel.setBounds(10, 11, 155, 307);
		contentPane.add(SceltaPanel);
		SceltaPanel.setLayout(null);
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 400, 155, 59);
		contentPane.add(IndietroPanel);
		IndietroPanel.setLayout(null);
		
		JButton TratteIndietroBtn = new JButton("Indietro");
		TratteIndietroBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		TratteIndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.TratteToHub(a);
				
			}
		});
		TratteIndietroBtn.setBounds(10, 11, 138, 38);
		IndietroPanel.add(TratteIndietroBtn);
		

		JPanel panel = new JPanel();
		panel.setBounds(175, 11, 773, 19);
		contentPane.add(panel);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(175, 11, 773, 448);
		contentPane.add(tabbedPane);
		
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JComboBox<String> AggiuntaNomeCombo = new JComboBox<String>();
		AggiuntaNomeCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		AggiuntaNomeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere l'aeroporto"}));
		
		Aeroporti = controllerAeroporti.getAllAeroportiExceptThis(a);
		
		for (Aeroporto tmp: Aeroporti){
			
			AggiuntaNomeCombo.addItem(tmp.getNomeAeroporto());
			
		}
		
		AggiuntaNomeCombo.setBounds(230, 108, 330, 50);
		AggiuntaPanel.add(AggiuntaNomeCombo);
		
		JButton AggiungiTrattaBtn = new JButton("Aggiungi");
		AggiungiTrattaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		AggiungiTrattaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(AggiuntaNomeCombo.getSelectedIndex() != 0){
					Aeroporto aeroportoArrivo = controllerAeroporti.getAeroportoByNome(AggiuntaNomeCombo.getSelectedItem().toString());
					controllerTratte.insert(a, aeroportoArrivo);
				}
				
			}
		});
		AggiungiTrattaBtn.setBounds(620, 373, 138, 38);
		AggiuntaPanel.add(AggiungiTrattaBtn);
		
		
		
		
		JPanel EliminazionePanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminazionePanel, null);
		EliminazionePanel.setLayout(null);
		
		JComboBox<String> EliminazioneTrattaCombo = new JComboBox<String>();
		EliminazioneTrattaCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		EliminazioneTrattaCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Selezionare la tratta"}));
		
		Tratte = controllerTratte.getTratteFromThisAirport(a);
		
		for (Tratta tmp: Tratte) {
			
			String nomeAeroportoPartenza = tmp.getAeroportoDiPartenza().getNomeAeroporto();
			String nomeAeroportoArrivo = tmp.getAeroportoDiArrivo().getNomeAeroporto();
			EliminazioneTrattaCombo.addItem(nomeAeroportoPartenza + "   -   " + nomeAeroportoArrivo);
			
		}
		
		
		EliminazioneTrattaCombo.setBounds(138, 116, 508, 50);
		EliminazionePanel.add(EliminazioneTrattaCombo);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (EliminazioneTrattaCombo.getSelectedIndex() != 0) {
					String nomeAeroportoArrivo = new String(EliminazioneTrattaCombo.getSelectedItem().toString().substring(a.getNomeAeroporto().length()+7));
					Aeroporto aeroportoArrivo = controllerAeroporti.getAeroportoByNome(nomeAeroportoArrivo);
					controllerTratte.delete(a, aeroportoArrivo);
				}	
				
			}
		});
		EliminaBtn.setBounds(620, 373, 138, 38);
		EliminazionePanel.add(EliminaBtn);
		
		//ELENCO
		JPanel ElencoPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		ElencoPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 93, 758, 271);
		ElencoPanel.add(scrollPane);
		
		JTextPane ElencoTextPane = new JTextPane();
		ElencoTextPane.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(ElencoTextPane);
		
		for (Tratta tmp: Tratte) {
			
			String nomeAeroportoArrivo = new String();
			String nomeCitt‡Arrivo = new String();
			nomeAeroportoArrivo = tmp.getAeroportoDiPartenza().getNomeAeroporto();
			nomeCitt‡Arrivo = tmp.getAeroportoDiArrivo().getCitt‡();
			ElencoTextPane.setText(ElencoTextPane.getText() + "\n");
			ElencoTextPane.setText(ElencoTextPane.getText() + "Aeroporto di arrivo: " + nomeAeroportoArrivo +"\tCitt‡: " + nomeCitt‡Arrivo +"");
		}
		
		JPanel RicercaPanel = new JPanel();
		RicercaPanel.setBounds(0, 0, 528, 85);
		ElencoPanel.add(RicercaPanel);
		RicercaPanel.setLayout(null);
		
		JLabel SceltaLbl = new JLabel("Scegli l'aeroporto di arrivo della tratta da cercare");
		SceltaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		SceltaLbl.setBounds(10, 10, 422, 24);
		RicercaPanel.add(SceltaLbl);
		
		JComboBox<String> RicercaTratteCombo = new JComboBox<String>();
		RicercaTratteCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		RicercaTratteCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Selezionare l'aeroporto"}));
		RicercaTratteCombo.setBounds(10, 44, 270, 40);
		RicercaPanel.add(RicercaTratteCombo);
				
		for (Tratta tmp: Tratte) {
			
			String nomeAeroportoArrivo = new String();
			nomeAeroportoArrivo = (tmp.getAeroportoDiArrivo()).getNomeAeroporto();
			RicercaTratteCombo.addItem(nomeAeroportoArrivo);
		}
		
		
		JButton RicercaBtn = new JButton("Cerca");
		RicercaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ElencoTextPane.setText("");
				
				if (RicercaTratteCombo.getSelectedIndex() != 0) {
					
					Tratta TrattaTrovate = controllerTratte.getTrattaByAeroporti(a.getNomeAeroporto(), RicercaTratteCombo.getSelectedItem().toString());
										
					
					String nomeAeroportoArrivo = new String();
					String nomeCitt‡Arrivo = new String();
					if(TrattaTrovate.getAeroportoDiArrivo() != null) {
						
						nomeAeroportoArrivo = TrattaTrovate.getAeroportoDiArrivo().getNomeAeroporto();
						nomeCitt‡Arrivo = TrattaTrovate.getAeroportoDiPartenza().getNomeAeroporto();
						ElencoTextPane.setText(ElencoTextPane.getText() + "\n");
						ElencoTextPane.setText(ElencoTextPane.getText() + "Aeroporto di arrivo: " + nomeAeroportoArrivo +"\tCitt‡: " + nomeCitt‡Arrivo +"");
						
					}
					
				}
				
				
			}
		});
		RicercaBtn.setBounds(620, 374, 138, 38);
		ElencoPanel.add(RicercaBtn);
		
		JPanel ModificaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ModificaPanel, null);
		ModificaPanel.setLayout(null);
		
		JLabel ModificaVecchiaTrattaLbl = new JLabel("Scegliere la tratta di cui modificare l'aeroporto di destinazione");
		ModificaVecchiaTrattaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaVecchiaTrattaLbl.setBounds(172, 62, 452, 27);
		ModificaPanel.add(ModificaVecchiaTrattaLbl);
		
		JComboBox<String> ModificaVecchiaTrattaCombo = new JComboBox<String>();
		ModificaVecchiaTrattaCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		ModificaVecchiaTrattaCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere la tratta"}));
		
		for (Tratta tmp: Tratte) {
			
			String nomeAeroportoPartenza = new String();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoArrivo = tmp.getAeroportoDiArrivo().getNomeAeroporto();
			nomeAeroportoPartenza = tmp.getAeroportoDiPartenza().getNomeAeroporto();
			ModificaVecchiaTrattaCombo.addItem(nomeAeroportoPartenza + "   -   " + nomeAeroportoArrivo);
			
		}
		
		ModificaVecchiaTrattaCombo.setBounds(137, 99, 508, 50);
		ModificaPanel.add(ModificaVecchiaTrattaCombo);
		
		JLabel ModificaNuovaTrattaLbl = new JLabel("Scegli il nuovo aeroporto di destinazione");
		ModificaNuovaTrattaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaNuovaTrattaLbl.setBounds(222, 186, 345, 27);
		ModificaPanel.add(ModificaNuovaTrattaLbl);
		
		JComboBox<String> ModificaNuovaTrattaCombo = new JComboBox<String>();
		ModificaNuovaTrattaCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		ModificaNuovaTrattaCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere l'aeroporto"}));
		
		Aeroporti = controllerAeroporti.getAllAeroportiExceptThis(a);
		Iterator<Aeroporto> NuoviAeroporti = Aeroporti.iterator();
		
		while (NuoviAeroporti.hasNext()) {
			
			Aeroporto tmp = NuoviAeroporti.next();
			ModificaNuovaTrattaCombo.addItem(tmp.getNomeAeroporto());
			
		}
		
		ModificaNuovaTrattaCombo.setBounds(222, 223, 330, 50);
		ModificaPanel.add(ModificaNuovaTrattaCombo);
		
		JButton ModificaBtn = new JButton("Modifica");
		ModificaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ModificaNuovaTrattaCombo.getSelectedIndex() != 0 && ModificaVecchiaTrattaCombo.getSelectedIndex() != 0){
					
					String vecchioNomeAeroporto = new String(ModificaVecchiaTrattaCombo.getSelectedItem().toString().substring((a.getNomeAeroporto()).length()+7));
					Aeroporto vecchioAeroporto = controllerAeroporti.getAeroportoByNome(vecchioNomeAeroporto);
					
					Aeroporto nuovoAeroporto = controllerAeroporti.getAeroportoByNome(ModificaNuovaTrattaCombo.getSelectedItem().toString());
					controllerTratte.update(vecchioAeroporto,nuovoAeroporto, a);
					
				}
				
			}
		});
		ModificaBtn.setBounds(649, 371, 109, 40);
		ModificaPanel.add(ModificaBtn);
		
		JButton TratteAggiuntaBtn = new JButton("Aggiunta");
		TratteAggiuntaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		TratteAggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		TratteAggiuntaBtn.setBounds(10, 11, 138, 38);
		SceltaPanel.add(TratteAggiuntaBtn);
		
		
		JButton TratteEliminaBtn = new JButton("Eliminazione");
		TratteEliminaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		TratteEliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		TratteEliminaBtn.setBounds(10, 107, 138, 38);
		SceltaPanel.add(TratteEliminaBtn);
		
		
		JButton TratteElencoBtn = new JButton("Ricerca");
		TratteElencoBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		TratteElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		TratteElencoBtn.setBounds(10, 155, 138, 38);
		SceltaPanel.add(TratteElencoBtn);
		
		JButton TrattaModificaBtn = new JButton("Modifica");
		TrattaModificaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		TrattaModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(3);
				
			}
		});
		TrattaModificaBtn.setBounds(10, 59, 138, 38);
		SceltaPanel.add(TrattaModificaBtn);
		
		
	}
}
