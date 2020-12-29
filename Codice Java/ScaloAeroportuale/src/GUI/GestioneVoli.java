package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import Classi.Tratta;
import Controller.Controller;
import Controller.ControllerCompagnie;
import Controller.ControllerTratte;
import Controller.ControllerVoli;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class GestioneVoli extends JFrame {

	private JPanel contentPane;

	Controller controller;
	
	/**
	 * Create the frame.
	 */
	public GestioneVoli(Controller c, Aeroporto a) {
		setTitle("Gestione Voli");
		controller = c;
		ControllerVoli controllerVoli = new ControllerVoli();
		ControllerTratte controllerTratte = new ControllerTratte();
		ControllerCompagnie controllerCompagnie = new ControllerCompagnie();
		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		ArrayList<CompagniaAerea> CompagnieAeree = new ArrayList<CompagniaAerea>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel SceltaPanel = new JPanel();
		SceltaPanel.setLayout(null);
		SceltaPanel.setBounds(10, 10, 155, 307);
		contentPane.add(SceltaPanel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(226, 0, 533, 448);
		contentPane.add(tabbedPane);
		
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JSpinner DateSpn = new JSpinner();
		DateSpn.setModel(new SpinnerDateModel(new Date(1609196400000L), null, null, Calendar.DAY_OF_YEAR));
		DateSpn.setBounds(197, 49, 131, 33);
		AggiuntaPanel.add(DateSpn);
		
		JLabel AggiuntaDataLbl = new JLabel("Inserire la data del volo");
		AggiuntaDataLbl.setBounds(181, 26, 163, 13);
		AggiuntaPanel.add(AggiuntaDataLbl);
		
		JLabel SceltaTrattaLbl = new JLabel("Scegliere la tratta del volo");
		SceltaTrattaLbl.setBounds(166, 121, 162, 13);
		AggiuntaPanel.add(SceltaTrattaLbl);
		
		
		JComboBox<String> SceltaTrattaCombo = new JComboBox<String>();
		Tratte = controllerTratte.getTratteFromThisAirport(a.getCodAeroporto());
		Iterator<Tratta> TratteDaCaricare = Tratte.iterator();
		
		while(TratteDaCaricare.hasNext()) {
			
			Tratta tmp = TratteDaCaricare.next();
			SceltaTrattaCombo.addItem(tmp.getCodTratta());
			
		}
		
		SceltaTrattaCombo.setBounds(166, 144, 192, 21);
		AggiuntaPanel.add(SceltaTrattaCombo);
		
		JLabel NumeroPostiLbl = new JLabel("Inserire il numero dei posti");
		NumeroPostiLbl.setBounds(183, 194, 158, 13);
		AggiuntaPanel.add(NumeroPostiLbl);
		
		JSpinner NumeroPostiDisponibiliSpn = new JSpinner();
		NumeroPostiDisponibiliSpn.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		NumeroPostiDisponibiliSpn.setBounds(214, 217, 96, 33);
		AggiuntaPanel.add(NumeroPostiDisponibiliSpn);
		
		JLabel SceltaCompagniaLbl = new JLabel("Scegliere la compagnia aerea");
		SceltaCompagniaLbl.setBounds(149, 267, 192, 13);
		AggiuntaPanel.add(SceltaCompagniaLbl);
		
		JComboBox<String> SceltaCompagniaCombo = new JComboBox<String>();
		CompagnieAeree = controllerCompagnie.getCompagnie(a);
		Iterator<CompagniaAerea> CompagnieDaCaricare = CompagnieAeree.iterator();
		
		while(CompagnieDaCaricare.hasNext()) {
			
			CompagniaAerea tmp = CompagnieDaCaricare.next();
			SceltaCompagniaCombo.addItem(tmp.getCodCompagnia());
			
		}
		
		SceltaCompagniaCombo.setBounds(166, 290, 192, 21);
		AggiuntaPanel.add(SceltaCompagniaCombo);
		
		JButton AggiuntaVoloBtn = new JButton("Inserisci");
		AggiuntaVoloBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				controllerVoli.InsertVolo(DateSpn.getTime(), NumeroPostiDisponibiliSpn.getValue(), SceltaCompagniaCombo.getSelectedIndex(), SceltaTrattaCombo.getSelectedItem());
				controllerVoli.apriSlotImbarco(a);
			}
		});
		AggiuntaVoloBtn.setBounds(433, 390, 85, 21);
		AggiuntaPanel.add(AggiuntaVoloBtn);
		
		JPanel ModificaPanel = new JPanel();
		ModificaPanel.setLayout(null);
		tabbedPane.addTab("New tab", null, ModificaPanel, null);
		
		JPanel ElencoPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		
		JPanel EliminazionePanel = new JPanel();
		EliminazionePanel.setLayout(null);
		tabbedPane.addTab("New tab", null, EliminazionePanel, null);
		
		JButton VoliAggiuntaBtn = new JButton("Aggiungere");
		VoliAggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		VoliAggiuntaBtn.setBounds(10, 11, 135, 31);
		SceltaPanel.add(VoliAggiuntaBtn);
		
		JButton VoliEliminaBtn = new JButton("Elimina");
		VoliEliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		VoliEliminaBtn.setBounds(10, 95, 135, 31);
		SceltaPanel.add(VoliEliminaBtn);
		
		JButton VoliElencoBtn = new JButton("Elenco");
		VoliElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		VoliElencoBtn.setBounds(10, 137, 135, 31);
		SceltaPanel.add(VoliElencoBtn);
		
		JButton VoliModificaBtn = new JButton("Modifica");
		VoliModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		VoliModificaBtn.setBounds(10, 53, 135, 31);
		SceltaPanel.add(VoliModificaBtn);
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setLayout(null);
		IndietroPanel.setBounds(10, 389, 155, 59);
		contentPane.add(IndietroPanel);
		
		JButton VoliIndietroBtn = new JButton("Indietro");
		VoliIndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.VoliToHub(a);
			}
		});
		VoliIndietroBtn.setBounds(10, 10, 135, 37);
		IndietroPanel.add(VoliIndietroBtn);
		
		
	}
}
