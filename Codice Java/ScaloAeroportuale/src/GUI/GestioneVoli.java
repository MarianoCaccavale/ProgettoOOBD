package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import Classi.Tratta;
import Classi.Volo;
import Controller.Controller;
import Controller.ControllerAeroporti;
import Controller.ControllerCompagnie;
import Controller.ControllerTratte;
import Controller.ControllerVoli;
import DAO.AeroportoDAO;
import DAO.CompagniaAereaDAO;
import DAO.TrattaDAO;
import Eccezioni.TrattaException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import javax.swing.SpinnerNumberModel;

public class GestioneVoli extends JFrame {

	private JPanel contentPane;

	Controller controller;
	
	
	public GestioneVoli(Controller c, Aeroporto a) {
		setTitle("Gestione Voli");
		controller = c;
		ControllerAeroporti controllerAeroporti = new ControllerAeroporti();
		ControllerVoli controllerVoli = new ControllerVoli();
		ControllerTratte controllerTratte = new ControllerTratte();
		ControllerCompagnie controllerCompagnie = new ControllerCompagnie();
		AeroportoDAO aeroportoDAO = new AeroportoDAO();
		TrattaDAO trattaDAO = new TrattaDAO();
		CompagniaAereaDAO compagniaDAO = new CompagniaAereaDAO();
		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		ArrayList<CompagniaAerea> CompagnieAeree = new ArrayList<CompagniaAerea>();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(226, 0, 531, 24);
		contentPane.add(panel);
		
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
		DateSpn.setModel(new SpinnerDateModel());
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
			String nomeAeroportoPartenza = new String();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoPartenza = (aeroportoDAO.getAeroportoByCod(tmp.getAeroportoDiPartenza())).getNomeAeroporto();
			nomeAeroportoArrivo = (aeroportoDAO.getAeroportoByCod(tmp.getAeroportoDiArrivo())).getNomeAeroporto();
			SceltaTrattaCombo.addItem(tmp.getCodTratta()+ ": " +nomeAeroportoArrivo + " - " + nomeAeroportoPartenza);
			
		}
		
		SceltaTrattaCombo.setBounds(166, 144, 192, 21);
		AggiuntaPanel.add(SceltaTrattaCombo);
		
		JLabel NumeroPostiLbl = new JLabel("Inserire il numero dei posti");
		NumeroPostiLbl.setBounds(183, 194, 158, 13);
		AggiuntaPanel.add(NumeroPostiLbl);
		
		JSpinner NumeroPostiDisponibiliSpn = new JSpinner();
		NumeroPostiDisponibiliSpn.setModel(new SpinnerNumberModel(50, 50, 500, 1));
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
			SceltaCompagniaCombo.addItem(tmp.getCodCompagnia() + ":" + tmp.getNomeCompagnia());
			
		}
		
		SceltaCompagniaCombo.setBounds(166, 290, 192, 21);
		AggiuntaPanel.add(SceltaCompagniaCombo);
		
		JButton AggiuntaVoloBtn = new JButton("Inserisci");
		AggiuntaVoloBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date data = (Date) DateSpn.getValue();
				SimpleDateFormat format = new SimpleDateFormat("YYYY:mm:dd HH:mm:ss");
				Volo volo = new Volo();
				volo.setData(data);
				volo.setNumeroPosti((Integer) NumeroPostiDisponibiliSpn.getValue());
				volo.setNumeroPostiDisponibili((Integer) NumeroPostiDisponibiliSpn.getValue());
				volo.setCompagniaDiAppartenenza(SceltaCompagniaCombo.getSelectedItem().toString().substring(0, SceltaCompagniaCombo.getSelectedItem().toString().indexOf(":")).toString());
				volo.setTrattaAssociata(SceltaTrattaCombo.getSelectedItem().toString().subSequence(0, SceltaTrattaCombo.getSelectedItem().toString().indexOf(":")).toString());
				controllerVoli.apriSlotImbarco(a, volo);
			}
		});
		AggiuntaVoloBtn.setBounds(433, 390, 85, 21);
		AggiuntaPanel.add(AggiuntaVoloBtn);
		
		JPanel ModificaPanel = new JPanel();
		ModificaPanel.setLayout(null);
		tabbedPane.addTab("New tab", null, ModificaPanel, null);
		
		
		JSpinner ModificaNumeroPostiSpn = new JSpinner();
		ModificaNumeroPostiSpn.setModel(new SpinnerNumberModel(50, 50, 5000, 1));
		ModificaNumeroPostiSpn.setBounds(147, 119, 162, 23);
		ModificaPanel.add(ModificaNumeroPostiSpn);
		
		
		JComboBox<String> ModificaComboBox = new JComboBox<String>();
		ModificaComboBox.setBounds(10, 54, 508, 27);
		
		ArrayList<Volo> VoliModifica = new ArrayList<Volo>();
		VoliModifica = controllerVoli.getAllVoli(a);
		Iterator<Volo> iVoloModifica = VoliModifica.iterator();
		
		while (iVoloModifica.hasNext()) {
			
			Tratta tratta = new Tratta();
			CompagniaAerea compagnia = new CompagniaAerea();
			Volo tmp = iVoloModifica.next();
				
			tratta = controllerTratte.getTratteByCod(tmp.getCompagniaDiAppartenenza());
			compagnia =  controllerCompagnie.getCompagniaByCod(tmp.getTrattaAssociata());
			String nomeAeroportoPartenza = new String();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoPartenza = (aeroportoDAO.getAeroportoByCod(tratta.getAeroportoDiPartenza())).getNomeAeroporto();
			nomeAeroportoArrivo = (aeroportoDAO.getAeroportoByCod(tratta.getAeroportoDiArrivo())).getNomeAeroporto();
			ModificaComboBox.addItem(tmp.getCodVolo() + " - " + nomeAeroportoPartenza +" - " + nomeAeroportoArrivo +" - " + compagnia.getNomeCompagnia() + " - " + tmp.getData().toString());;
			
		}
		
		ModificaPanel.add(ModificaComboBox);
		
		JButton ModificaBtn = new JButton("Modifica");
		ModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerVoli.updateVolo((Integer) ModificaNumeroPostiSpn.getValue(), ModificaComboBox.getSelectedItem().toString().substring(0, ModificaComboBox.getSelectedItem().toString().indexOf("-")-1));
				
			}
		});
		ModificaBtn.setBounds(396, 386, 122, 23);
		ModificaPanel.add(ModificaBtn);
		
		JLabel ModificaLbl = new JLabel("Scegliere il volo da modifiare:");
		ModificaLbl.setBounds(10, 8, 508, 35);
		ModificaPanel.add(ModificaLbl);
		
		
		
		JLabel ModificaNumeroPostiLbl = new JLabel("Scegliereil nuovo numero di posti del volo:");
		ModificaNumeroPostiLbl.setBounds(134, 92, 175, 23);
		ModificaPanel.add(ModificaNumeroPostiLbl);
		
		JPanel ElencoPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		
		JPanel EliminazionePanel = new JPanel();
		EliminazionePanel.setLayout(null);
		tabbedPane.addTab("New tab", null, EliminazionePanel, null);
		
		JComboBox<String> EliminazioneComboBox = new JComboBox<String>();
		EliminazioneComboBox.setBounds(0, 41, 528, 29);
		ArrayList<Volo> VoliElimina = new ArrayList<Volo>();
		VoliElimina = controllerVoli.getAllVoli(a);
		Iterator<Volo> iVoloElimina = VoliElimina.iterator();
		
		while (iVoloElimina.hasNext()) {
			
			Tratta tratta = new Tratta();
			CompagniaAerea compagnia = new CompagniaAerea();
			Volo tmp = iVoloElimina.next();
				
			tratta = controllerTratte.getTratteByCod(tmp.getCompagniaDiAppartenenza());
			compagnia =  controllerCompagnie.getCompagniaByCod(tmp.getTrattaAssociata());
			String nomeAeroportoPartenza = new String();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoPartenza = (aeroportoDAO.getAeroportoByCod(tratta.getAeroportoDiPartenza())).getNomeAeroporto();
			nomeAeroportoArrivo = (aeroportoDAO.getAeroportoByCod(tratta.getAeroportoDiArrivo())).getNomeAeroporto();
			EliminazioneComboBox.addItem(tmp.getCodVolo() + " - " + nomeAeroportoPartenza +" - " + nomeAeroportoArrivo +" - " + compagnia.getNomeCompagnia() + " - " + tmp.getData().toString());;
			
		}
		
		EliminazionePanel.add(EliminazioneComboBox);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controllerVoli.deleteVolo(EliminazioneComboBox.getSelectedItem().toString().substring(0, EliminazioneComboBox.getSelectedItem().toString().indexOf("-")-1));
				
			}
		});
		EliminaBtn.setBounds(415, 373, 103, 36);
		EliminazionePanel.add(EliminaBtn);
		
		JLabel EliminaLbl = new JLabel("Scegliere il volo da cancellare:");
		EliminaLbl.setBounds(0, 11, 528, 23);
		EliminazionePanel.add(EliminaLbl);
		
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
				tabbedPane.setSelectedIndex(3);
			}
		});
		VoliEliminaBtn.setBounds(10, 95, 135, 31);
		SceltaPanel.add(VoliEliminaBtn);
		
		JButton VoliElencoBtn = new JButton("Elenco");
		VoliElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
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
