	package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import Classi.SlotImbarco;
import Classi.Tratta;
import Classi.Volo;
import Controller.Controller;
import Controller.ControllerCompagnie;
import Controller.ControllerSlotImbarco;
import Controller.ControllerTratte;
import Controller.ControllerVoli;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class GestioneVoli extends JFrame {

	private JPanel contentPane;

	Controller controller;
	
	
	public GestioneVoli(Controller c, Aeroporto a) {
		setResizable(false);
		setTitle("Gestione Voli");
		controller = c;
		ControllerVoli controllerVoli = new ControllerVoli();
		ControllerTratte controllerTratte = new ControllerTratte();
		ControllerCompagnie controllerCompagnie = new ControllerCompagnie();
		ControllerSlotImbarco controllerSlotImbarco = new ControllerSlotImbarco();
		
		ArrayList<Tratta> Tratte = new ArrayList<Tratta>();
		ArrayList<CompagniaAerea> CompagnieAeree = new ArrayList<CompagniaAerea>();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setFocusable(false);
		panel.setBounds(232, 0, 840, 20);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel SceltaPanel = new JPanel();
		SceltaPanel.setLayout(null);
		SceltaPanel.setBounds(10, 10, 212, 307);
		contentPane.add(SceltaPanel);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(232, 0, 840, 509);
		contentPane.add(tabbedPane);
		Tratte = controllerTratte.getTratteFromThisAirport(a);
		Iterator<Tratta> TratteDaCaricare = Tratte.iterator();
		
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
				
		JComboBox<String> SceltaTrattaCombo = new JComboBox<String>();
		SceltaTrattaCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		SceltaTrattaCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere la tratta"}));
		
		SceltaTrattaCombo.setBounds(155, 126, 510, 38);
		AggiuntaPanel.add(SceltaTrattaCombo);
		
		JComboBox<String> SceltaCompagniaCombo = new JComboBox<String>();
		SceltaCompagniaCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		SceltaCompagniaCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere la compagnia aerea"}));
		
		SceltaCompagniaCombo.setBounds(265, 306, 312, 38);
		AggiuntaPanel.add(SceltaCompagniaCombo);
		
		while(TratteDaCaricare.hasNext()) {
			
			Tratta tmp = TratteDaCaricare.next();
			String nomeAeroportoPartenza = new String();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoPartenza = (tmp.getAeroportoDiPartenza()).getNomeAeroporto();
			nomeAeroportoArrivo = (tmp.getAeroportoDiArrivo()).getNomeAeroporto();
			SceltaTrattaCombo.addItem(nomeAeroportoPartenza + "<->" + nomeAeroportoArrivo);
			
		}
		CompagnieAeree = controllerCompagnie.getCompagnie(a);
		Iterator<CompagniaAerea> CompagnieDaCaricare = CompagnieAeree.iterator();
		
		while(CompagnieDaCaricare.hasNext()) {
			
			CompagniaAerea tmp = CompagnieDaCaricare.next();
			SceltaCompagniaCombo.addItem(tmp.getNomeCompagnia());
			
		}
		
		JPanel ModificaPanel = new JPanel();
		ModificaPanel.setLayout(null);
		tabbedPane.addTab("New tab", null, ModificaPanel, null);
		
		
		JSpinner ModificaNumeroPostiSpn = new JSpinner();
		ModificaNumeroPostiSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaNumeroPostiSpn.setModel(new SpinnerNumberModel(50, 50, 5000, 1));
		ModificaNumeroPostiSpn.setBounds(363, 169, 127, 50);
		ModificaPanel.add(ModificaNumeroPostiSpn);
		
		
		JComboBox<String> ModificaComboBox = new JComboBox<String>();
		ModificaComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		ModificaComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere il volo"}));
		ModificaComboBox.setBounds(30, 38, 760, 50);
		
		ArrayList<Volo> VoliModifica = new ArrayList<Volo>();
		VoliModifica = controllerVoli.getAllVoli(a);
		
		for(Volo tmp:VoliModifica) {				
			
			String nomeAeroportoPartenza = new String();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoPartenza = tmp.getTrattaAssociata().getAeroportoDiPartenza().getNomeAeroporto();
			nomeAeroportoArrivo = tmp.getTrattaAssociata().getAeroportoDiArrivo().getNomeAeroporto();
			ModificaComboBox.addItem(tmp.getCodVolo()+ ":" + nomeAeroportoPartenza +"<->" + nomeAeroportoArrivo +" - Svolto da: " + tmp.getCompagniaDiAppartenenza().getNomeCompagnia() + " - " + tmp.getData().toString());;
			
		}
		
		ModificaPanel.add(ModificaComboBox);
		
		JButton ModificaBtn = new JButton("Modifica");
		ModificaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ModificaComboBox.getSelectedIndex() != 0) {
					
					controllerVoli.update((Integer) ModificaNumeroPostiSpn.getValue(),(String) ModificaComboBox.getSelectedItem().toString().subSequence(0, ModificaComboBox.getSelectedItem().toString().indexOf(":")));

				}
				
			}
		});
		ModificaBtn.setBounds(687, 434, 138, 38);
		ModificaPanel.add(ModificaBtn);
		
		
		
		JLabel ModificaNumeroPostiLbl = new JLabel("Scegliere il nuovo numero di posti del volo:");
		ModificaNumeroPostiLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		ModificaNumeroPostiLbl.setBounds(221, 136, 326, 23);
		ModificaPanel.add(ModificaNumeroPostiLbl);
		
		JPanel ElencoPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		ElencoPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 107, 815, 320);
		ElencoPanel.add(scrollPane);
		
		JTextPane ElencoTextPane = new JTextPane();
		ElencoTextPane.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(ElencoTextPane);
		
		ArrayList<Volo> Voli = new ArrayList<Volo>();
		Voli = controllerVoli.getAllVoli(a);
		Iterator<Volo> iVoli = Voli.iterator();
		
		while(iVoli.hasNext()) {
			
			Volo tmp = iVoli.next();
			ElencoTextPane.setText(ElencoTextPane.getText() + "\n");
			ElencoTextPane.setText(ElencoTextPane.getText() + "Codice del volo: " + tmp.getCodVolo() +"\tData e ora: " + tmp.getData() +"\tNumero posti: " + tmp.getNumeroPosti() + "\tNumero posti prenotati: " + tmp.getNumeroPostiPrenotati() +"\tCompagnia aerea: " + tmp.getCompagniaDiAppartenenza().getNomeCompagnia() + "");
		}
		
		
		JPanel RicercaPanel = new JPanel();
		RicercaPanel.setBounds(10, 10, 815, 87);
		ElencoPanel.add(RicercaPanel);
		RicercaPanel.setLayout(null);
		
		JComboBox<String> RicercaTrattaCombo = new JComboBox<String>();
		RicercaTrattaCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		RicercaTrattaCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere la tratta"}));
		
		for (Tratta tmp:Tratte){
			
			String nomeAeroportoPartenza = new String();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoArrivo = tmp.getAeroportoDiArrivo().getNomeAeroporto();
			nomeAeroportoPartenza = tmp.getAeroportoDiPartenza().getNomeAeroporto();
			RicercaTrattaCombo.addItem(nomeAeroportoPartenza + "<->" + nomeAeroportoArrivo);
			
		}
		RicercaTrattaCombo.setBounds(10, 27, 562, 50);
		RicercaPanel.add(RicercaTrattaCombo);
		
		JButton RicercaBtn = new JButton("Cerca");
		RicercaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ElencoTextPane.setText("");
				
				if(RicercaTrattaCombo.getSelectedIndex() != 0) {
					
					String nomeAeroportoPartenza = RicercaTrattaCombo.getSelectedItem().toString().substring(0, RicercaTrattaCombo.getSelectedItem().toString().indexOf("<"));
					String nomeAeroportoArrivo = RicercaTrattaCombo.getSelectedItem().toString().substring(RicercaTrattaCombo.getSelectedItem().toString().indexOf(">")+1);
					
					Tratta tratta = controllerTratte.getTrattaByAeroporti(nomeAeroportoPartenza, nomeAeroportoArrivo);
					
					ArrayList<Volo> VoliTrovati = controllerVoli.ricercaVoliByTratta(tratta);
						
					for(Volo tmpVolo:VoliTrovati) {
						
						ElencoTextPane.setText(ElencoTextPane.getText() + "\n");
						ElencoTextPane.setText(ElencoTextPane.getText() + "Codice del volo: " + tmpVolo.getCodVolo() +"\tData e ora: " + tmpVolo.getData() +"\tNumero posti: " + tmpVolo.getNumeroPosti() + "\tNumero posti prenotati: " + tmpVolo.getNumeroPostiPrenotati() +"\t Compagnia aerea: " + tmpVolo.getCompagniaDiAppartenenza().getNomeCompagnia());
						
					}
						
				}
					
			}
		});
		RicercaBtn.setBounds(687, 434, 138, 38);
		ElencoPanel.add(RicercaBtn);
		
		JComboBox<String> ChiusuraComboBox = new JComboBox<String>();
		ChiusuraComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
		ChiusuraComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Scegliere lo SlotImbarco da chiudere"}));
		ChiusuraComboBox.setBounds(20, 52, 793, 50);
		
		ArrayList<SlotImbarco> listaSlotImbarco = new ArrayList<SlotImbarco>();
		listaSlotImbarco = controllerSlotImbarco.getImbarchi(a.getCodAeroporto());
		
		Iterator<SlotImbarco> iSlotImbarco = listaSlotImbarco.iterator();
		
		while(iSlotImbarco.hasNext()) {
			
			SlotImbarco tmp = iSlotImbarco.next();
			ChiusuraComboBox.addItem("Codice volo: " + tmp.getVolo().getCodVolo() + " - " + tmp.getTratta().getAeroportoDiPartenza().getNomeAeroporto() + "<->"+ tmp.getTratta().getAeroportoDiArrivo().getNomeAeroporto() + " - Ora di partenza: " + tmp.getOraInizio() +" - Gate: " + tmp.getGate().getNomeGate());
			
		}
		
		JPanel EliminazionePanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminazionePanel, null);
		EliminazionePanel.setLayout(null);
		
		JComboBox<String> EliminaVoloComboBox = new JComboBox<String>();
		EliminaVoloComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		EliminaVoloComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Selezionare il volo:"}));
		EliminaVoloComboBox.setBounds(22, 127, 792, 50);
		
		ArrayList<Volo> VoliElimina = new ArrayList<Volo>();
		VoliElimina = controllerVoli.getAllVoli(a);
		
		for (Volo tmp: VoliElimina) {
			
			String nomeAeroportoPartenza = new String();
			String nomeAeroportoArrivo = new String();
			nomeAeroportoPartenza = tmp.getTrattaAssociata().getAeroportoDiPartenza().getNomeAeroporto();
			nomeAeroportoArrivo = tmp.getTrattaAssociata().getAeroportoDiPartenza().getNomeAeroporto();
			EliminaVoloComboBox.addItem(tmp.getCodVolo() + " - " + nomeAeroportoPartenza +" - " + nomeAeroportoArrivo +" - " + tmp.getCompagniaDiAppartenenza().getNomeCompagnia() + " - " + tmp.getData().toString());;
			
		}
		
		EliminazionePanel.add(EliminaVoloComboBox);
		
		JButton CancellaBtn = new JButton("Cancella");
		CancellaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		CancellaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (EliminaVoloComboBox.getSelectedIndex() != 0) {
					
					String voloDaCancellare = new String(EliminaVoloComboBox.getSelectedItem().toString());
					String tmp = voloDaCancellare.substring(0, voloDaCancellare.indexOf("-")-1);
					System.out.println(tmp);
					controllerVoli.delete(voloDaCancellare.substring(0, voloDaCancellare.indexOf("-")-1));
					
				}
				
				
			}
		});
		CancellaBtn.setBounds(700, 429, 125, 43);
		EliminazionePanel.add(CancellaBtn);
		
		JSpinner DateSpn = new JSpinner();
		DateSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		DateSpn.setModel(new SpinnerDateModel());
		DateSpn.setBounds(305, 49, 164, 39);
		AggiuntaPanel.add(DateSpn);
		
		JLabel AggiuntaDataLbl = new JLabel("Inserire la data del volo");
		AggiuntaDataLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		AggiuntaDataLbl.setBounds(290, 22, 238, 29);
		AggiuntaPanel.add(AggiuntaDataLbl);
		
		
		JLabel NumeroPostiLbl = new JLabel("Inserire il numero dei posti");
		NumeroPostiLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		NumeroPostiLbl.setBounds(305, 189, 250, 19);
		AggiuntaPanel.add(NumeroPostiLbl);
		
		JSpinner NumeroPostiPrenotatiSpn = new JSpinner();
		NumeroPostiPrenotatiSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		NumeroPostiPrenotatiSpn.setModel(new SpinnerNumberModel(50, 50, 500, 1));
		NumeroPostiPrenotatiSpn.setBounds(373, 217, 96, 39);
		AggiuntaPanel.add(NumeroPostiPrenotatiSpn);
		
		JButton AggiuntaVoloBtn = new JButton("Aggiungi");
		AggiuntaVoloBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		AggiuntaVoloBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (SceltaCompagniaCombo.getSelectedIndex() != 0 && SceltaTrattaCombo.getSelectedIndex() != 0) {
					
					Date datatmp = (Date) DateSpn.getValue();
					Timestamp data = new Timestamp(datatmp.getTime());
					Volo volo = new Volo();
					volo.setData(data);
					volo.setNumeroPosti((Integer) NumeroPostiPrenotatiSpn.getValue());
					volo.setNumeroPostiPrenotati((Integer) NumeroPostiPrenotatiSpn.getValue());
					CompagniaAerea tmpCompagnia = controllerCompagnie.getCompagniaByNome(SceltaCompagniaCombo.getSelectedItem().toString());
					volo.setCompagniaDiAppartenenza(tmpCompagnia);
					String aeroportoPartenza = SceltaTrattaCombo.getSelectedItem().toString().substring(0, SceltaTrattaCombo.getSelectedItem().toString().indexOf("<->"));
					String aeroportoArrivo = SceltaTrattaCombo.getSelectedItem().toString().substring(SceltaTrattaCombo.getSelectedItem().toString().indexOf("<->")+3);
					Tratta tmpTratta = controllerTratte.getTrattaByAeroporti(aeroportoPartenza, aeroportoArrivo);
					volo.setTrattaAssociata(tmpTratta);
					controller.VoliToSlotImbarco(a, volo);
					
				}
			
			}
		});
		AggiuntaVoloBtn.setBounds(687, 434, 138, 38);
		AggiuntaPanel.add(AggiuntaVoloBtn);
		
		JPanel ChiusuraPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ChiusuraPanel, null);
		ChiusuraPanel.setLayout(null);
		
		ChiusuraPanel.add(ChiusuraComboBox);
		
		JSpinner ChiusuraDataSpn = new JSpinner();
		ChiusuraDataSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		ChiusuraDataSpn.setModel(new SpinnerDateModel());
		ChiusuraDataSpn.setBounds(348, 127, 164, 39);
		ChiusuraPanel.add(ChiusuraDataSpn);
		
		JButton ChiudiBtn = new JButton("Chiudi");
		ChiudiBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ChiudiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (ChiusuraComboBox.getSelectedIndex() != 0) {
					
					String itemSelezionato = new String(ChiusuraComboBox.getSelectedItem().toString());
					
					String codVolo = new String(itemSelezionato.substring(13, itemSelezionato.indexOf("-")-1));
					Date dataFineTmp = (Date) ChiusuraDataSpn.getValue();
					
					Timestamp dataFine = new Timestamp(dataFineTmp.getTime());
					
					controllerSlotImbarco.closeSlotImbarco(codVolo, dataFine);
					
				}
								
			}
		});
		ChiudiBtn.setBounds(687, 434, 138, 38);
		ChiusuraPanel.add(ChiudiBtn);
		
		
		
		JButton VoliAggiuntaBtn = new JButton("Aggiunta");
		VoliAggiuntaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		VoliAggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		VoliAggiuntaBtn.setBounds(10, 11, 195, 38);
		SceltaPanel.add(VoliAggiuntaBtn);
		
		JButton VoliElencoBtn = new JButton("Ricerca");
		VoliElencoBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		VoliElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		VoliElencoBtn.setBounds(10, 206, 195, 38);
		SceltaPanel.add(VoliElencoBtn);
		
		JButton VoliModificaBtn = new JButton("Modifica");
		VoliModificaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		VoliModificaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		VoliModificaBtn.setBounds(10, 59, 195, 38);
		SceltaPanel.add(VoliModificaBtn);
		
		JButton ChiudiImbarcoBtn = new JButton("Chiusura Slot Imbarco");
		ChiudiImbarcoBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ChiudiImbarcoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(4);
				
			}
		});
		ChiudiImbarcoBtn.setBounds(10, 155, 195, 38);
		SceltaPanel.add(ChiudiImbarcoBtn);
		
		JButton EliminaBtn = new JButton("Eliminazione");
		EliminaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(3);
			}
		});
		EliminaBtn.setBounds(10, 107, 192, 38);
		SceltaPanel.add(EliminaBtn);
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setLayout(null);
		IndietroPanel.setBounds(10, 450, 155, 59);
		contentPane.add(IndietroPanel);
		
		JButton VoliIndietroBtn = new JButton("Indietro");
		VoliIndietroBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		VoliIndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.VoliToHub(a);
			}
		});
		VoliIndietroBtn.setBounds(10, 10, 138, 38);
		IndietroPanel.add(VoliIndietroBtn);
		
		
		
	}
}
