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
import java.awt.Font;

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
		setBounds(100, 100, 1136, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(198, 11, 914, 20);
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
		IndietroBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.ServizioClientiToHub(a);
				
			}
		});
		IndietroBtn.setBounds(10, 11, 158, 37);
		ChiusuraPane.add(IndietroBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(198, 11, 914, 468);
		contentPane.add(tabbedPane);
		
		JButton BigliettiBtn = new JButton("Biglietti");
		BigliettiBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		BigliettiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		BigliettiBtn.setBounds(10, 11, 158, 33);
		BottoniPane.add(BigliettiBtn);
		
		JButton ClientiBtn = new JButton("Clienti Business");
		ClientiBtn.setFont(new Font("Arial", Font.PLAIN, 16));
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
		SceltaNumeroSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		SceltaNumeroSpn.setBounds(307, 96, 268, 27);
		BigliettiPanel.add(SceltaNumeroSpn);
		
		JSpinner SceltaNumerpSpn = new JSpinner();
		SceltaNumerpSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		SceltaNumerpSpn.setModel(new SpinnerNumberModel(1, 1, 500, 1));
		SceltaNumerpSpn.setBounds(335, 134, 125, 38);
		BigliettiPanel.add(SceltaNumerpSpn);
		
		JComboBox<String> SceltaVoloSpn = new JComboBox<String>();
		SceltaVoloSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		SceltaVoloSpn.setModel(new DefaultComboBoxModel<String>(new String[] {"Selezionare il volo"}));
		SceltaVoloSpn.setBounds(10, 23, 889, 53);
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
		GeneraBigliettoBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		GeneraBigliettoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (SceltaVoloSpn.getSelectedIndex() != 0) {
					
					String stringa = SceltaVoloSpn.getSelectedItem().toString();
					String codVolo = stringa.substring(0, stringa.indexOf("-")-1);
					controllerVoli.generateTicket(codVolo, (Integer)SceltaNumerpSpn.getValue());
					
				}
					
				
			}
		});
		GeneraBigliettoBtn.setBounds(791, 393, 108, 38);
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
