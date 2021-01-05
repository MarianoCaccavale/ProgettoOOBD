package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.Volo;
import Controller.Controller;
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

public class ServizioClienti extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	
	public ServizioClienti(Controller c, Aeroporto a) {
		
		Controller controller = c;
		ControllerVoli controllerVoli = new ControllerVoli();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(198, 11, 558, 23);
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
		tabbedPane.setBounds(198, 11, 558, 468);
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
		
		JLabel SceltaVoloLbl = new JLabel("Scegliere il volo per cui generare il biglietto:");
		SceltaVoloLbl.setBounds(10, 11, 533, 27);
		BigliettiPanel.add(SceltaVoloLbl);
		
		JComboBox<String> SceltaVoloSpn = new JComboBox<String>();
		SceltaVoloSpn.setBounds(10, 49, 533, 27);
		ArrayList<Volo> voli = new ArrayList<Volo>();
		
		voli = controllerVoli.getAllVoli(a);
		
		for (Volo v:voli) {
			
			
			
		}
		
		BigliettiPanel.add(SceltaVoloSpn);
		
		JButton GeneraBigliettoBtn = new JButton("Genera");
		GeneraBigliettoBtn.setBounds(435, 391, 108, 38);
		BigliettiPanel.add(GeneraBigliettoBtn);
		
		JLabel SceltaNumeroSpn = new JLabel("Numero di biglietti da generare:");
		SceltaNumeroSpn.setBounds(10, 87, 268, 27);
		BigliettiPanel.add(SceltaNumeroSpn);
		
		JSpinner SceltaNumerpSpn = new JSpinner();
		SceltaNumerpSpn.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		SceltaNumerpSpn.setBounds(10, 125, 125, 27);
		BigliettiPanel.add(SceltaNumerpSpn);
		
		JPanel ClientiBusiness = new JPanel();
		tabbedPane.addTab("New tab", null, ClientiBusiness, null);
		ClientiBusiness.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(232, 180, 86, 20);
		ClientiBusiness.add(textField);
		textField.setColumns(10);
		
		
	}
}
