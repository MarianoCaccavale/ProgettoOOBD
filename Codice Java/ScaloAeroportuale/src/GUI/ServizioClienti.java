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

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

public class ServizioClienti extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	
	public ServizioClienti(Controller c, Aeroporto a) {
		
		Controller controller = c;
		ControllerAeroporti controllerAeroporto = new ControllerAeroporti();
		ControllerCompagnie controllerCompagnie = new ControllerCompagnie();
		ControllerTratte controllerTratte = new ControllerTratte();
		ControllerVoli controllerVoli = new ControllerVoli();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(198, 11, 690, 23);
		contentPane.add(panel);
		
		JPanel BottoniPane = new JPanel();
		BottoniPane.setBounds(10, 11, 178, 398);
		contentPane.add(BottoniPane);
		BottoniPane.setLayout(null);
		
		JPanel ChiusuraPane = new JPanel();
		ChiusuraPane.setBounds(10, 420, 178, 59);
		contentPane.add(ChiusuraPane);
		ChiusuraPane.setLayout(null);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.ServizioClientiToHub(a);
				
			}
		});
		IndietroBtn.setBounds(10, 11, 158, 37);
		ChiusuraPane.add(IndietroBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(198, 11, 690, 468);
		contentPane.add(tabbedPane);
		
		JButton BigliettiBtn = new JButton("Biglietti");
		BigliettiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		BigliettiBtn.setBounds(10, 11, 158, 33);
		BottoniPane.add(BigliettiBtn);
		
		JButton ClientiBtn = new JButton("Clienti Business");
		ClientiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		ClientiBtn.setBounds(10, 55, 158, 33);
		BottoniPane.add(ClientiBtn);
		
		JPanel BigliettiPanel = new JPanel();
		tabbedPane.addTab("New tab", null, BigliettiPanel, null);
		BigliettiPanel.setLayout(null);
		
		JLabel SceltaNumeroSpn = new JLabel("Numero di biglietti da generare:");
		SceltaNumeroSpn.setBounds(211, 87, 268, 27);
		BigliettiPanel.add(SceltaNumeroSpn);
		
		JSpinner SceltaNumerpSpn = new JSpinner();
		SceltaNumerpSpn.setModel(new SpinnerNumberModel(1, 1, 500, 1));
		SceltaNumerpSpn.setBounds(239, 125, 125, 27);
		BigliettiPanel.add(SceltaNumerpSpn);
		
		JLabel SceltaVoloLbl = new JLabel("Scegliere il volo per cui generare il biglietto:");
		SceltaVoloLbl.setBounds(10, 11, 533, 27);
		BigliettiPanel.add(SceltaVoloLbl);
		
		JComboBox<String> SceltaVoloSpn = new JComboBox<String>();
		SceltaVoloSpn.setModel(new DefaultComboBoxModel<String>(new String[] {"Selezionare il volo"}));
		SceltaVoloSpn.setBounds(10, 49, 665, 27);
		ArrayList<Volo> voli = new ArrayList<Volo>();
		
		voli = controllerVoli.getAllVoli(a);
		
		for (Volo v:voli) {
			
			CompagniaAerea compagniaTmp = controllerCompagnie.getCompagniaByCod(v.getTrattaAssociata());
			Tratta trattaTmp = controllerTratte.getTrattaByCod(v.getCompagniaDiAppartenenza());
			Aeroporto aeroportoPartenzaTmp = controllerAeroporto.getAeroportoByCod(trattaTmp.getAeroportoDiPartenza());
			Aeroporto aeroportoArrivoTmp = controllerAeroporto.getAeroportoByCod(trattaTmp.getAeroportoDiArrivo());
			
			SceltaVoloSpn.addItem(v.getCodVolo()+ " - Compagnia del volo: " + compagniaTmp.getNomeCompagnia() + " - Tratta del volo: " + aeroportoPartenzaTmp.getNomeAeroporto() + " / " + aeroportoArrivoTmp.getNomeAeroporto() + " - Numero dei posti disponibili: " + v.getNumeroPostiDisponibili());
			
		}
		
		BigliettiPanel.add(SceltaVoloSpn);
		
		JButton GeneraBigliettoBtn = new JButton("Genera");
		GeneraBigliettoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (SceltaVoloSpn.getSelectedIndex() != 0) {
					
					String stringa = SceltaVoloSpn.getSelectedItem().toString();
					String codVolo = stringa.substring(0, stringa.indexOf("-")-1);
					controllerVoli.generateTicket(codVolo, (Integer)SceltaNumerpSpn.getValue());
					
				}
					
				
			}
		});
		GeneraBigliettoBtn.setBounds(567, 391, 108, 38);
		BigliettiPanel.add(GeneraBigliettoBtn);
		

		
		JPanel ClientiBusiness = new JPanel();
		tabbedPane.addTab("New tab", null, ClientiBusiness, null);
		ClientiBusiness.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(232, 180, 86, 20);
		ClientiBusiness.add(textField);
		textField.setColumns(10);
		
		
	}
}
