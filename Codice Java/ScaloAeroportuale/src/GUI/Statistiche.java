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

public class Statistiche extends JFrame {

	private JPanel BasePanel;
	Controller controller;
	
	
	public Statistiche(Controller c, Aeroporto a) {
		controller = c;
		ControllerStatistiche controllerStatistiche = new ControllerStatistiche();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 504);
		BasePanel = new JPanel();
		BasePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BasePanel);
		BasePanel.setLayout(null);
		
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setBounds(10, 10, 158, 252);
		BasePanel.add(BottoniPanel);
		BottoniPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(178, 10, 594, 23);
		BasePanel.add(panel);		
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 426, 85, 31);
		BasePanel.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.statisticheToHub(a);
				
			}
		});
		IndietroPanel.add(IndietroBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(178, 11, 594, 446);
		BasePanel.add(tabbedPane);
		
		JPanel CompagniePanel = new JPanel();
		tabbedPane.addTab("New tab", null, CompagniePanel, null);
		
		JPanel VoliPanel = new JPanel();
		tabbedPane.addTab("New tab", null, VoliPanel, null);
		
		JPanel GatePanel = new JPanel();
		tabbedPane.addTab("New tab", null, GatePanel, null);
		GatePanel.setLayout(null);
		
		JLabel GateDataInizioRicercaLbl = new JLabel("Data di partenza della ricerca:");
		GateDataInizioRicercaLbl.setBounds(10, 11, 197, 26);
		GatePanel.add(GateDataInizioRicercaLbl);
		
		JLabel GateDataFineRicerca = new JLabel("Data di fine ricerca: ");
		GateDataFineRicerca.setBounds(355, 11, 197, 26);
		GatePanel.add(GateDataFineRicerca);
		
		JSpinner GateDataInizioSpn = new JSpinner();
		GateDataInizioSpn.setModel(new SpinnerDateModel());
		GateDataInizioSpn.setBounds(10, 47, 197, 26);
		GatePanel.add(GateDataInizioSpn);
		
		JSpinner GateDataFineSpn = new JSpinner();
		GateDataFineSpn.setModel(new SpinnerDateModel());
		GateDataFineSpn.setBounds(347, 48, 205, 26);
		GatePanel.add(GateDataFineSpn);
		
		JScrollPane RicercaGateScroll = new JScrollPane();
		RicercaGateScroll.setBounds(10, 84, 569, 291);
		GatePanel.add(RicercaGateScroll);
		
		JTextPane RicercaGateText = new JTextPane();
		RicercaGateText.setEditable(false);
		RicercaGateScroll.setViewportView(RicercaGateText);
		
		JButton RicercaGateBtn = new JButton("Ricerca");
		RicercaGateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RicercaGateText.setText("");
				ArrayList<String[]> listaTempi = new ArrayList<String[]>();
				listaTempi = controllerStatistiche.getGateChiusi(a.getCodAeroporto(), (Date) GateDataInizioSpn.getValue(), (Date)GateDataFineSpn.getValue());
				
				Iterator<String[]> iListaTempi = listaTempi.iterator();
				
				while (iListaTempi.hasNext()) {
					
					String[] tmp = iListaTempi.next();
					RicercaGateText.setText(RicercaGateText.getText() + "\n");
					RicercaGateText.setText(RicercaGateText.getText() + "Codice Gate: " + tmp[0] + "\t - Tempo di utilizzo stimato: " + tmp[1] + " ora/e\t - Tempo di utilizzo effettivo: " + tmp[2] + " ora/e");
					
				}
			}
		});
		RicercaGateBtn.setBounds(442, 384, 137, 23);
		GatePanel.add(RicercaGateBtn);
		
		
		JButton CompagnieBtn = new JButton("Compagnie");
		CompagnieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		CompagnieBtn.setBounds(10, 10, 138, 21);
		BottoniPanel.add(CompagnieBtn);
		
		JButton VoliBtn = new JButton("Voli");
		VoliBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		VoliBtn.setBounds(10, 42, 138, 21);
		BottoniPanel.add(VoliBtn);
		
		JButton GateBtn = new JButton("Gate");
		GateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		GateBtn.setBounds(10, 73, 138, 21);
		BottoniPanel.add(GateBtn);
		
		
	}
}
