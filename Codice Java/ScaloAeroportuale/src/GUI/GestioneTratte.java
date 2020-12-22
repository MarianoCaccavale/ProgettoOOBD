package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
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
		
		
		JComboBox<String> AggiungereNomeCombo = new JComboBox<String>();
		ArrayList<Aeroporto> Aeroporti = new ArrayList<Aeroporto>();
		
		ControllerAeroporti controllerAeroporti = new ControllerAeroporti();
		Aeroporti = controllerAeroporti.getAllAeroportiExceptThis(a);
		Iterator<Aeroporto> iAeroporti = Aeroporti.iterator();
		
		Aeroporto tmp = new Aeroporto();
		
		while(iAeroporti.hasNext()) {
			
			tmp = iAeroporti.next();
			AggiungereNomeCombo.addItem(tmp.getNomeAeroporto());
			
		}
		
		AggiungereNomeCombo.setBounds(161, 58, 218, 36);
		AggiuntaPanel.add(AggiungereNomeCombo);
		
		JPanel EliminazionePanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminazionePanel, null);
		
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
