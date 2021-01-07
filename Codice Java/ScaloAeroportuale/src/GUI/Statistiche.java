package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Controller.Controller;
import Controller.ControllerStatistiche;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.util.Calendar;
import java.awt.Font;

public class Statistiche extends JFrame {

	private JPanel BasePanel;
	Controller controller;
	
	
	public Statistiche(Controller c, Aeroporto a) {
		setResizable(false);
		controller = c;
		ControllerStatistiche controllerStatistiche = new ControllerStatistiche();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1133, 520);
		BasePanel = new JPanel();
		BasePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BasePanel);
		BasePanel.setLayout(null);
		
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setBounds(10, 24, 158, 252);
		BasePanel.add(BottoniPanel);
		BottoniPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(178, 10, 931, 21);
		BasePanel.add(panel);		
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 419, 158, 54);
		BasePanel.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		IndietroBtn.setBounds(10, 10, 138, 38);
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.statisticheToHub(a);
				
			}
		});
		IndietroPanel.setLayout(null);
		IndietroPanel.add(IndietroBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(178, 11, 931, 462);
		BasePanel.add(tabbedPane);
		
		JPanel CompagniePanel = new JPanel();
		tabbedPane.addTab("New tab", null, CompagniePanel, null);
		CompagniePanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 82, 906, 295);
		CompagniePanel.add(scrollPane_1);
		
		JTextPane RicercaCompagniePanel = new JTextPane();
		RicercaCompagniePanel.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane_1.setViewportView(RicercaCompagniePanel);
		
		JSpinner DataInizioStatisticheCompagnieSpn = new JSpinner();
		DataInizioStatisticheCompagnieSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		DataInizioStatisticheCompagnieSpn.setModel(new SpinnerDateModel());
		DataInizioStatisticheCompagnieSpn.setBounds(31, 33, 164, 39);
		CompagniePanel.add(DataInizioStatisticheCompagnieSpn);
		
		JSpinner DataFineStatisticheCompagnieSpn = new JSpinner();
		DataFineStatisticheCompagnieSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		DataFineStatisticheCompagnieSpn.setModel(new SpinnerDateModel());
		DataFineStatisticheCompagnieSpn.setBounds(486, 33, 164, 39);
		CompagniePanel.add(DataFineStatisticheCompagnieSpn);
		
		JButton RicercaCompagnieBtn = new JButton("Cerca");
		RicercaCompagnieBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaCompagnieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RicercaCompagniePanel.setText("");
				ArrayList<String[]> listaStatisticheCompagnie = new ArrayList<String[]>();
				listaStatisticheCompagnie = controllerStatistiche.getStatisticheCompagnie(a.getCodAeroporto(), (Date)DataInizioStatisticheCompagnieSpn.getValue(), (Date)DataFineStatisticheCompagnieSpn.getValue());
				
				Iterator<String[]> iStatisticheCompagnie = listaStatisticheCompagnie.iterator();
				
				while (iStatisticheCompagnie.hasNext()) {
					
					String tmp[] = iStatisticheCompagnie.next();
					RicercaCompagniePanel.setText(RicercaCompagniePanel.getText() + "\n");
					RicercaCompagniePanel.setText(RicercaCompagniePanel.getText() + "Codice Compagnia: " + tmp[0] + "\tNome compagnia aerea: " + tmp[1] + "\tVoli effettuati nell'arco di tempo selezionato: " + tmp[2] + "");
					
				}
			}
		});
		RicercaCompagnieBtn.setBounds(778, 387, 138, 38);
		CompagniePanel.add(RicercaCompagnieBtn);
		
		JLabel InizioRicercaLbl = new JLabel("Data inizio per la ricerca delle compagnie:");
		InizioRicercaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		InizioRicercaLbl.setBounds(21, 10, 345, 19);
		CompagniePanel.add(InizioRicercaLbl);
		
		JLabel FineRicercaLbl = new JLabel("Data fine per la ricerca delle compagnie:");
		FineRicercaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		FineRicercaLbl.setBounds(476, 10, 369, 19);
		CompagniePanel.add(FineRicercaLbl);
		
		JPanel VoliPanel = new JPanel();
		tabbedPane.addTab("New tab", null, VoliPanel, null);
		VoliPanel.setLayout(null);
		
		JSpinner DataInizioRicercaVoliSpn = new JSpinner();
		DataInizioRicercaVoliSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		DataInizioRicercaVoliSpn.setBounds(41, 34, 164, 39);
		VoliPanel.add(DataInizioRicercaVoliSpn);
		DataInizioRicercaVoliSpn.setModel(new SpinnerDateModel());
		
		JSpinner DataFineRicercaVoliSpn = new JSpinner();
		DataFineRicercaVoliSpn.setBounds(422, 34, 164, 39);
		VoliPanel.add(DataFineRicercaVoliSpn);
		DataFineRicercaVoliSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		DataFineRicercaVoliSpn.setModel(new SpinnerDateModel());
		
		JLabel DataInizioRicercaVoliLbl = new JLabel("Data inizio per la ricerca dei voli:");
		DataInizioRicercaVoliLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		DataInizioRicercaVoliLbl.setBounds(31, 11, 304, 25);
		VoliPanel.add(DataInizioRicercaVoliLbl);
		
		JLabel DataFineRicercaVoliLlbl = new JLabel("Data fine per la ricerca dei voli:");
		DataFineRicercaVoliLlbl.setFont(new Font("Arial", Font.PLAIN, 16));
		DataFineRicercaVoliLlbl.setBounds(412, 11, 354, 25);
		VoliPanel.add(DataFineRicercaVoliLlbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 906, 296);
		VoliPanel.add(scrollPane);
		
		JTextPane RicercaTextPane = new JTextPane();
		RicercaTextPane.setFont(new Font("Arial", Font.PLAIN, 14));
		RicercaTextPane.setEditable(false);
		scrollPane.setViewportView(RicercaTextPane);
		
		JButton RicercaVoliBtn = new JButton("Cerca");
		RicercaVoliBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaVoliBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String statisticheVoli[] = new String[2];
				 statisticheVoli = controllerStatistiche.statisticheVoli(a.getCodAeroporto(), (Date)DataInizioRicercaVoliSpn.getValue(), (Date)DataFineRicercaVoliSpn.getValue());
				 RicercaTextPane.setText("Voli partiti in orario dal "+ DataInizioStatisticheCompagnieSpn.getValue() + " al " + DataFineStatisticheCompagnieSpn.getValue() + ":   " +  statisticheVoli[0] + "\n");
				 RicercaTextPane.setText(RicercaTextPane.getText() + "Voli partiti in ritardo dal "+ DataInizioRicercaVoliSpn.getValue() + " al " + DataFineRicercaVoliSpn.getValue() + ":   " + statisticheVoli[1]);
			
			}
		});
		RicercaVoliBtn.setBounds(774, 386, 142, 39);
		VoliPanel.add(RicercaVoliBtn);
		
		JPanel GatePanel = new JPanel();
		tabbedPane.addTab("New tab", null, GatePanel, null);
		GatePanel.setLayout(null);
		
		JLabel GateDataInizioRicercaLbl = new JLabel("Data di inizio per la ricerca dei gate:");
		GateDataInizioRicercaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		GateDataInizioRicercaLbl.setBounds(10, 11, 288, 26);
		GatePanel.add(GateDataInizioRicercaLbl);
		
		JLabel GateDataFineRicerca = new JLabel("Data di fine per la ricerca dei gate: ");
		GateDataFineRicerca.setFont(new Font("Arial", Font.PLAIN, 16));
		GateDataFineRicerca.setBounds(442, 11, 364, 26);
		GatePanel.add(GateDataFineRicerca);
		
		JSpinner GateDataInizioSpn = new JSpinner();
		GateDataInizioSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		GateDataInizioSpn.setModel(new SpinnerDateModel());
		GateDataInizioSpn.setBounds(20, 35, 164, 39);
		GatePanel.add(GateDataInizioSpn);
		
		JSpinner GateDataFineSpn = new JSpinner();
		GateDataFineSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		GateDataFineSpn.setModel(new SpinnerDateModel());
		GateDataFineSpn.setBounds(452, 35, 164, 39);
		GatePanel.add(GateDataFineSpn);
		
		JScrollPane RicercaGateScroll = new JScrollPane();
		RicercaGateScroll.setBounds(10, 84, 906, 291);
		GatePanel.add(RicercaGateScroll);
		
		JTextPane RicercaGateText = new JTextPane();
		RicercaGateText.setFont(new Font("Arial", Font.PLAIN, 14));
		RicercaGateText.setEditable(false);
		RicercaGateScroll.setViewportView(RicercaGateText);
		
		JButton RicercaGateBtn = new JButton("Cerca");
		RicercaGateBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaGateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RicercaGateText.setText("");
				ArrayList<String[]> listaTempi = new ArrayList<String[]>();
				listaTempi = controllerStatistiche.getGateChiusi(a.getCodAeroporto(), (Date) GateDataInizioSpn.getValue(), (Date)GateDataFineSpn.getValue());
				
				Iterator<String[]> iListaTempi = listaTempi.iterator();
				
				while (iListaTempi.hasNext()) {
					
					String tmp[] = iListaTempi.next();
					RicercaGateText.setText(RicercaGateText.getText() + "\n");
					RicercaGateText.setText(RicercaGateText.getText() + "Codice Gate: " + tmp[0] + "\t - Tempo di utilizzo stimato: " + tmp[1] + " ora/e\t - Tempo di utilizzo effettivo: " + tmp[2] + " ora/e");
					
				}
			}
		});
		RicercaGateBtn.setBounds(778, 387, 138, 38);
		GatePanel.add(RicercaGateBtn);
		
		
		JButton CompagnieBtn = new JButton("Compagnie");
		CompagnieBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		CompagnieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		CompagnieBtn.setBounds(10, 10, 138, 38);
		BottoniPanel.add(CompagnieBtn);
		
		JButton VoliBtn = new JButton("Voli");
		VoliBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		VoliBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		VoliBtn.setBounds(10, 58, 138, 38);
		BottoniPanel.add(VoliBtn);
		
		JButton GateBtn = new JButton("Gate");
		GateBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		GateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		GateBtn.setBounds(10, 106, 138, 38);
		BottoniPanel.add(GateBtn);
		
		
	}
}
