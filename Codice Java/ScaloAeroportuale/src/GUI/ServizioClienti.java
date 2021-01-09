package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.Volo;
import Controller.Controller;
import Controller.ControllerVoli;

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
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServizioClienti extends JFrame {

	private JPanel contentPane;
	private JTextField EmailBusinessTf;

	
	public ServizioClienti(Controller c, Aeroporto a) {
		
		Controller controller = c;
		ControllerVoli controllerVoli = new ControllerVoli();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1136, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JButton ClientiBtn = new JButton("Clienti Business");
		ClientiBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ClientiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ServizioClientiToClientiBusiness(a);
				
			}
		});
		ClientiBtn.setBounds(10, 11, 158, 33);
		BottoniPane.add(ClientiBtn);
		
		JPanel BigliettiPanel = new JPanel();
		BigliettiPanel.setLayout(null);
		BigliettiPanel.setBounds(198, 11, 909, 471);
		contentPane.add(BigliettiPanel);
		
		JLabel SceltaNumeroSpn = new JLabel("Numero di biglietti da generare:");
		SceltaNumeroSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		SceltaNumeroSpn.setBounds(307, 131, 268, 27);
		BigliettiPanel.add(SceltaNumeroSpn);
		
		JSpinner SceltaNumerpSpn = new JSpinner();
		SceltaNumerpSpn.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		SceltaNumerpSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		SceltaNumerpSpn.setBounds(335, 169, 125, 38);
		BigliettiPanel.add(SceltaNumerpSpn);
		
		JComboBox<String> SceltaVoloSpn = new JComboBox<String>();
		SceltaVoloSpn.setModel(new DefaultComboBoxModel<String>(new String[] {"Selezionare il volo"}));
		SceltaVoloSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		SceltaVoloSpn.setBounds(10, 58, 889, 53);
		BigliettiPanel.add(SceltaVoloSpn);
		
		JButton GeneraBigliettoBtn = new JButton("Genera");
		
		GeneraBigliettoBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		GeneraBigliettoBtn.setBounds(791, 419, 108, 38);
		BigliettiPanel.add(GeneraBigliettoBtn);
		
		
			
		GeneraBigliettoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(SceltaVoloSpn.getSelectedIndex() != 0) {
					if(EmailBusinessTf.isEditable()){
						
						String codVolo = SceltaVoloSpn.getSelectedItem().toString().substring(0, SceltaVoloSpn.getSelectedItem().toString().indexOf("-")-1);
						controllerVoli.generateBusinessTicket(codVolo, (int) SceltaNumerpSpn.getValue(), EmailBusinessTf.getText());
						
					}else {
							
						String codVolo = SceltaVoloSpn.getSelectedItem().toString().substring(0, SceltaVoloSpn.getSelectedItem().toString().indexOf("-")-1);
						controllerVoli.generateTicket(codVolo, (int)SceltaNumerpSpn.getValue());
							
					}
				}
			}
		});
			
		
		
		JLabel BigliettiLbl = new JLabel("Generazione dei biglietti");
		BigliettiLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		BigliettiLbl.setBounds(10, 11, 450, 36);
		BigliettiPanel.add(BigliettiLbl);
		
		JCheckBox ClientiBusinessCheck = new JCheckBox("Cliente Business");
		ClientiBusinessCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(ClientiBusinessCheck.isSelected()) {
					
					EmailBusinessTf.setEditable(true);
					
				}else {
					
					EmailBusinessTf.setEditable(false);
					
				}
				
			}
		});
		ClientiBusinessCheck.setFont(new Font("Arial", Font.PLAIN, 16));
		ClientiBusinessCheck.setBounds(307, 270, 194, 23);
		BigliettiPanel.add(ClientiBusinessCheck);
		
		EmailBusinessTf = new JTextField();
		EmailBusinessTf.setEditable(false);
		
		EmailBusinessTf.setBounds(307, 311, 262, 38);
		BigliettiPanel.add(EmailBusinessTf);
		EmailBusinessTf.setColumns(10);
		
		ArrayList<Volo> voli = new ArrayList<Volo>();
		
		voli = controllerVoli.getAllVoli(a);
		
		for (Volo v:voli) {
			
			SceltaVoloSpn.addItem(v.getCodVolo()+ " - Compagnia del volo: " + v.getCompagniaDiAppartenenza().getNomeCompagnia() + " - Tratta del volo: " + v.getTrattaAssociata().getAeroportoDiPartenza().getNomeAeroporto() + " / " + v.getTrattaAssociata().getAeroportoDiArrivo().getNomeAeroporto() + " - Numero dei posti prenotati: " + v.getNumeroPostiPrenotati());
			
		}
		
		
	}
}
