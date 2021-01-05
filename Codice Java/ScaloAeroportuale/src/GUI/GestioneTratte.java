package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import Classi.Tratta;
import Classi.Volo;
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
		setBounds(100, 100, 734, 509);
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
		TratteIndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.TratteToHub(a);
				
			}
		});
		TratteIndietroBtn.setBounds(10, 11, 138, 38);
		IndietroPanel.add(TratteIndietroBtn);
		

		JPanel panel = new JPanel();
		panel.setBounds(175, 11, 533, 23);
		contentPane.add(panel);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(175, 11, 533, 448);
		contentPane.add(tabbedPane);
		
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JLabel TrattaAggiuntaLbl = new JLabel("Inserire l'aeroporto di destinazione:");
		TrattaAggiuntaLbl.setBounds(161, 11, 218, 36);
		AggiuntaPanel.add(TrattaAggiuntaLbl);
		
		JComboBox<String> AggiuntaNomeCombo = new JComboBox<String>();
		
		Aeroporti = controllerAeroporti.getAllAeroportiExceptThis(a);
		Iterator<Aeroporto> iAeroporti = Aeroporti.iterator();
		
		while (iAeroporti.hasNext()) {
			
			Aeroporto tmp = iAeroporti.next();
			AggiuntaNomeCombo.addItem(tmp.getNomeAeroporto());
			
		}
		
		AggiuntaNomeCombo.setBounds(161, 58, 189, 22);
		AggiuntaPanel.add(AggiuntaNomeCombo);
		
		JButton AggiungiTrattaBtn = new JButton("Aggiungi Tratta");
		AggiungiTrattaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(AggiuntaNomeCombo.getSelectedItem() != null){
					controllerTratte.InsertTratta(a.getCodAeroporto(), AggiuntaNomeCombo.getSelectedItem().toString());
				}
				
			}
		});
		AggiungiTrattaBtn.setBounds(383, 373, 135, 36);
		AggiuntaPanel.add(AggiungiTrattaBtn);
		
		
		
		
		JPanel EliminazionePanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminazionePanel, null);
		EliminazionePanel.setLayout(null);
		
		JLabel EliminazioneTrattaLbl = new JLabel("Scegliere la tratta da cancellare:");
		EliminazioneTrattaLbl.setBounds(80, 11, 376, 29);
		EliminazionePanel.add(EliminazioneTrattaLbl);
		
		JComboBox<String> EliminazioneTrattaCombo = new JComboBox<String>();
		
		Tratte = controllerTratte.getTratteFromThisAirport(a.getCodAeroporto());
		Iterator<Tratta> TratteDaCancellare = Tratte.iterator();
		
		while (TratteDaCancellare.hasNext()) {
			
			Tratta tmp = TratteDaCancellare.next();
			String nomeAeroportoPartenza = new String();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoArrivo = (controllerAeroporti.getAeroportoByCod(tmp.getAeroportoDiArrivo())).getNomeAeroporto();
			nomeAeroportoPartenza = (controllerAeroporti.getAeroportoByCod(tmp.getAeroportoDiPartenza())).getNomeAeroporto();
			EliminazioneTrattaCombo.addItem(nomeAeroportoPartenza + "   -   " + nomeAeroportoArrivo);
			
		}
		
		
		EliminazioneTrattaCombo.setBounds(80, 51, 376, 29);
		EliminazionePanel.add(EliminazioneTrattaCombo);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (EliminazioneTrattaCombo.getSelectedItem() != null) {
					String nomeAeroportoArrivo = new String(EliminazioneTrattaCombo.getSelectedItem().toString().substring(a.getNomeAeroporto().length()+7));
					controllerTratte.delete(a.getCodAeroporto(), nomeAeroportoArrivo);
				}	
				
			}
		});
		EliminaBtn.setBounds(405, 375, 113, 34);
		EliminazionePanel.add(EliminaBtn);
		
		//ELENCO
		JPanel ElencoPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		ElencoPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 93, 528, 271);
		ElencoPanel.add(scrollPane);
		
		JTextPane ElencoTextPane = new JTextPane();
		scrollPane.setViewportView(ElencoTextPane);
		
		Tratte = controllerTratte.getTratteFromThisAirport(a.getCodAeroporto());
		Iterator<Tratta> iTratte = Tratte.iterator();
		
		while(iTratte.hasNext()) {
			Tratta tmp = iTratte.next();
			String nomeAeroportoArrivo = new String();
			String nomeCitt�Arrivo = new String();
			nomeAeroportoArrivo = (controllerAeroporti.getAeroportoByCod(tmp.getAeroportoDiArrivo())).getNomeAeroporto();
			nomeCitt�Arrivo = (controllerAeroporti.getAeroportoByCod(tmp.getAeroportoDiArrivo())).getCitt�();
			ElencoTextPane.setText(ElencoTextPane.getText() + "\n");
			ElencoTextPane.setText(ElencoTextPane.getText() + "Codice della tratta: " + tmp.getCodTratta() +"\tAeroporto di arrivo: " + nomeAeroportoArrivo +"\tCitt�: " + nomeCitt�Arrivo +"");
		}
		
		JPanel RicercaPanel = new JPanel();
		RicercaPanel.setBounds(0, 0, 528, 85);
		ElencoPanel.add(RicercaPanel);
		RicercaPanel.setLayout(null);
		
		JLabel SceltaLbl = new JLabel("Scegli l'aeroporto di arrivo della tratta da cercare");
		SceltaLbl.setBounds(70, 10, 362, 13);
		RicercaPanel.add(SceltaLbl);
		
		JComboBox<String> RicercaTratteCombo = new JComboBox<String>();
		RicercaTratteCombo.setBounds(70, 33, 212, 42);
		RicercaPanel.add(RicercaTratteCombo);
		
		ArrayList<Tratta> CercaTratta = new ArrayList<Tratta>();
		CercaTratta = controllerTratte.getTratteFromThisAirport(a.getCodAeroporto());
		Iterator<Tratta> iTratteRicerca = CercaTratta.iterator();
		
		while(iTratteRicerca.hasNext()) {
			Tratta tmp = iTratteRicerca.next();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoArrivo = (controllerAeroporti.getAeroportoByCod(tmp.getAeroportoDiArrivo())).getNomeAeroporto();
			RicercaTratteCombo.addItem(tmp.getAeroportoDiArrivo()+ " - " + nomeAeroportoArrivo);
		}
		
		
		JButton RicercaBtn = new JButton("Cerca");
		RicercaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ElencoTextPane.setText("");
				
				if (EliminazioneTrattaCombo.getSelectedItem() != null) {
					
					ArrayList<Tratta> TratteTrovate = controllerTratte.ricerca(a.getCodAeroporto(), RicercaTratteCombo.getSelectedItem().toString().substring(0, RicercaTratteCombo.getSelectedItem().toString().indexOf("-")-1));
					Iterator<Tratta> iTratteTrovate = TratteTrovate.iterator();
					
					while (iTratteTrovate.hasNext()) {
						Tratta tmp = iTratteTrovate.next();
						String nomeAeroportoArrivo = new String();
						String nomeCitt�Arrivo = new String();
						nomeAeroportoArrivo = (controllerAeroporti.getAeroportoByCod(tmp.getAeroportoDiArrivo())).getNomeAeroporto();
						nomeCitt�Arrivo = (controllerAeroporti.getAeroportoByCod(tmp.getAeroportoDiArrivo())).getNomeAeroporto();
						ElencoTextPane.setText(ElencoTextPane.getText() + "\n");
						ElencoTextPane.setText(ElencoTextPane.getText() + "Codice della tratta: " + tmp.getCodTratta() +"\tAeroporto di arrivo: " + nomeAeroportoArrivo +"\tCitt�: " + nomeCitt�Arrivo +"");
					}
					
				}
				
				
			}
		});
		RicercaBtn.setBounds(380, 374, 138, 38);
		ElencoPanel.add(RicercaBtn);
		
		JPanel ModificaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ModificaPanel, null);
		ModificaPanel.setLayout(null);
		
		JLabel ModificaVecchiaTrattaLbl = new JLabel("Scegliere la tratta di cui modificare l'aeroporto di destinazione");
		ModificaVecchiaTrattaLbl.setBounds(96, 54, 364, 27);
		ModificaPanel.add(ModificaVecchiaTrattaLbl);
		
		JComboBox<String> ModificaVecchiaTrattaCombo = new JComboBox<String>();
		Tratte = controllerTratte.getTratteFromThisAirport(a.getCodAeroporto());
		Iterator<Tratta> TratteDaModificare = Tratte.iterator();
		
		while (TratteDaModificare.hasNext()) {
			
			Tratta tmp = TratteDaModificare.next();
			String nomeAeroportoPartenza = new String();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoArrivo = (controllerAeroporti.getAeroportoByCod(tmp.getAeroportoDiArrivo())).getNomeAeroporto();
			nomeAeroportoPartenza = (controllerAeroporti.getAeroportoByCod(tmp.getAeroportoDiPartenza())).getNomeAeroporto();
			ModificaVecchiaTrattaCombo.addItem(nomeAeroportoPartenza + "   -   " + nomeAeroportoArrivo);
			
		}
		
		ModificaVecchiaTrattaCombo.setBounds(123, 91, 306, 27);
		ModificaPanel.add(ModificaVecchiaTrattaCombo);
		
		JLabel ModificaNuovaTrattaLbl = new JLabel("Scegli il nuovo aeroporto di destinazione");
		ModificaNuovaTrattaLbl.setBounds(144, 171, 262, 27);
		ModificaPanel.add(ModificaNuovaTrattaLbl);
		
		JComboBox<String> ModificaNuovaTrattaCombo = new JComboBox<String>();
		
		Aeroporti = controllerAeroporti.getAllAeroportiExceptThis(a);
		Iterator<Aeroporto> NuoviAeroporti = Aeroporti.iterator();
		
		while (NuoviAeroporti.hasNext()) {
			
			Aeroporto tmp = NuoviAeroporti.next();
			ModificaNuovaTrattaCombo.addItem(tmp.getNomeAeroporto());
			
		}
		
		ModificaNuovaTrattaCombo.setBounds(180, 202, 192, 27);
		ModificaPanel.add(ModificaNuovaTrattaCombo);
		
		JButton ModificaBtn = new JButton("Modifica");
		ModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ModificaNuovaTrattaCombo.getSelectedItem() != null && ModificaVecchiaTrattaCombo.getSelectedObjects() != null){
					String vecchioNomeAeroporto = new String(ModificaVecchiaTrattaCombo.getSelectedItem().toString().substring((a.getNomeAeroporto()).length()+7));
					controllerTratte.update(vecchioNomeAeroporto, ModificaNuovaTrattaCombo.getSelectedItem().toString(), a.getCodAeroporto());
				}
				
			}
		});
		ModificaBtn.setBounds(409, 369, 109, 40);
		ModificaPanel.add(ModificaBtn);
		
		JButton TratteAggiuntaBtn = new JButton("Aggiungere");
		TratteAggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		TratteAggiuntaBtn.setBounds(10, 11, 138, 38);
		SceltaPanel.add(TratteAggiuntaBtn);
		
		
		JButton TratteEliminaBtn = new JButton("Elimina");
		TratteEliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		TratteEliminaBtn.setBounds(10, 107, 138, 38);
		SceltaPanel.add(TratteEliminaBtn);
		
		
		JButton TratteElencoBtn = new JButton("Elenco");
		TratteElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		TratteElencoBtn.setBounds(10, 155, 138, 38);
		SceltaPanel.add(TratteElencoBtn);
		
		JButton TrattaModificaBtn = new JButton("Modifica");
		TrattaModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(3);
				
			}
		});
		TrattaModificaBtn.setBounds(10, 59, 138, 38);
		SceltaPanel.add(TrattaModificaBtn);
		
		
	}
}
