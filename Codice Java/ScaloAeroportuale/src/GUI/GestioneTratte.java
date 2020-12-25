package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class GestioneTratte extends JFrame {

	private JPanel contentPane;

	Controller controller;
	
	
	
	public GestioneTratte(Controller c, Aeroporto a) {
		setTitle("Gestione Tratte");
		controller = c;
		ControllerTratte controllerTratte = new ControllerTratte();
		ControllerAeroporti controllerAeroporti = new ControllerAeroporti();
		
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
		TratteIndietroBtn.setBounds(10, 11, 135, 37);
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
		
		ArrayList<Aeroporto> Aeroporti = new ArrayList<Aeroporto>();
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
				
				controllerTratte.InsertTratta(a.getCodAeroporto(), AggiuntaNomeCombo.getSelectedItem().toString());
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
		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		Tratte = controllerTratte.getTratteFromThisAirport(a.getCodAeroporto());
		Iterator<Tratta> TratteDaCancellare = Tratte.iterator();
		
		while (TratteDaCancellare.hasNext()) {
			
			Tratta tmp = TratteDaCancellare.next();
			EliminazioneTrattaCombo.addItem(tmp.getAeroportoDiPartenza() + " - " + tmp.getAeroportoDiArrivo());
			
		}
		
		
		EliminazioneTrattaCombo.setBounds(80, 51, 376, 29);
		EliminazionePanel.add(EliminazioneTrattaCombo);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomeAeroportoArrivo = new String(EliminazioneTrattaCombo.getSelectedItem().toString().substring(EliminazioneTrattaCombo.getSelectedItem().toString().indexOf("-")+2));
				controllerTratte.delete(a.getCodAeroporto(), nomeAeroportoArrivo);
				
			}
		});
		EliminaBtn.setBounds(405, 375, 113, 34);
		EliminazionePanel.add(EliminaBtn);
		
		JPanel ElencoPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		
		JButton TratteAggiuntaBtn = new JButton("Aggiungere");
		TratteAggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		TratteAggiuntaBtn.setBounds(10, 11, 135, 31);
		SceltaPanel.add(TratteAggiuntaBtn);
		
		
		JButton TratteEliminaBtn = new JButton("Elimina");
		TratteEliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		TratteEliminaBtn.setBounds(10, 53, 135, 31);
		SceltaPanel.add(TratteEliminaBtn);
		
		
		JButton TratteElencoBtn = new JButton("Elenco");
		TratteElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		TratteElencoBtn.setBounds(10, 95, 135, 31);
		SceltaPanel.add(TratteElencoBtn);
		
		
	}
}
