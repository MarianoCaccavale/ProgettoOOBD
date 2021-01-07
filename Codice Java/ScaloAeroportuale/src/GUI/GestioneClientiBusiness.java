package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.CompagniaAerea;
import Controller.Controller;
import Controller.ControllerClienti;
import Controller.ControllerCompagnie;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GestioneClientiBusiness extends JFrame {

	private JPanel contentPane;
	private JTextField NomeTf;
	private JTextField CognomeTf;
	private JTextField EmailTf;
	private JTextField EliminazioneEmailTf;

	public GestioneClientiBusiness(Controller c, Aeroporto a) {
		Controller controller = c;
		ControllerClienti controllerClienti = new ControllerClienti();
		ControllerCompagnie controllerCompagnie = new ControllerCompagnie();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setBounds(10, 11, 188, 365);
		contentPane.add(BottoniPanel);
		BottoniPanel.setLayout(null);
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 387, 188, 91);
		contentPane.add(IndietroPanel);
		IndietroPanel.setLayout(null);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ClientiBusinessToServizioClienti(a);
				
			}
		});
		IndietroBtn.setBounds(10, 11, 168, 69);
		IndietroPanel.add(IndietroBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(208, 11, 592, 467);
		contentPane.add(tabbedPane);
		
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JLabel NomeLbl = new JLabel("Inserire nome cliente:");
		NomeLbl.setBounds(44, 124, 168, 23);
		AggiuntaPanel.add(NomeLbl);
		
		NomeTf = new JTextField();
		NomeTf.setBounds(44, 158, 168, 23);
		AggiuntaPanel.add(NomeTf);
		NomeTf.setColumns(10);
		
		JLabel CognomeLbl = new JLabel("Inserire il cognome del cliente:");
		CognomeLbl.setBounds(358, 126, 168, 23);
		AggiuntaPanel.add(CognomeLbl);
		
		CognomeTf = new JTextField();
		CognomeTf.setBounds(358, 158, 168, 23);
		AggiuntaPanel.add(CognomeTf);
		CognomeTf.setColumns(10);
		
		JLabel CompagniaLbl = new JLabel("Scegliere la compagnia a cui registrare il cliente:");
		CompagniaLbl.setBounds(144, 272, 293, 23);
		AggiuntaPanel.add(CompagniaLbl);
		
		JComboBox<String> CompagniaCombo = new JComboBox<String>();
		CompagniaCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Selezionare la compagnia"}));
		CompagniaCombo.setBounds(144, 306, 293, 23);
		
		ArrayList<CompagniaAerea> compagniaLista = new ArrayList<CompagniaAerea>();
		compagniaLista = controllerCompagnie.getAllCompagnie();
		
		for(CompagniaAerea tmp:compagniaLista) {
			
			CompagniaCombo.addItem(tmp.getCodCompagnia() + ": " + tmp.getNomeCompagnia());
			
		}
		
		AggiuntaPanel.add(CompagniaCombo);
		
		JSpinner DataNascitaSpn = new JSpinner();
		DataNascitaSpn.setModel(new SpinnerDateModel());
		DataNascitaSpn.setBounds(216, 238, 103, 23);
		AggiuntaPanel.add(DataNascitaSpn);
		
		JButton InserisciBtn = new JButton("Registra cliente");
		InserisciBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(EmailTf.getText().isBlank()) && !(NomeTf.getText().isBlank()) && !(CognomeTf.getText().isBlank())) {
					
					String codCompagnia = CompagniaCombo.getSelectedItem().toString().substring(0, CompagniaCombo.getSelectedItem().toString().indexOf(":"));
					controllerClienti.insert(EmailTf.getText(), NomeTf.getText(), CognomeTf.getText(), (Date) DataNascitaSpn.getValue(), codCompagnia);
				}
				
			}
		});
		InserisciBtn.setBounds(451, 392, 126, 36);
		AggiuntaPanel.add(InserisciBtn);
		
		JLabel EmailLbl = new JLabel("Inserire la mail con cui registrare l'utente:");
		EmailLbl.setBounds(126, 23, 330, 23);
		AggiuntaPanel.add(EmailLbl);
		
		JLabel DataLbl = new JLabel("Inserisci la data di nascita:");
		DataLbl.setBounds(184, 204, 211, 23);
		AggiuntaPanel.add(DataLbl);
		
		
		EmailTf = new JTextField();
		EmailTf.setBounds(126, 57, 287, 23);
		AggiuntaPanel.add(EmailTf);
		EmailTf.setColumns(10);
		
		
		JPanel EliminazionePanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminazionePanel, null);
		EliminazionePanel.setLayout(null);
		
		JLabel EliminazioneNomeLbl = new JLabel("Inserire l'email del cliente da cancellare:");
		EliminazioneNomeLbl.setBounds(154, 21, 277, 27);
		EliminazionePanel.add(EliminazioneNomeLbl);
		
		EliminazioneEmailTf = new JTextField();
		EliminazioneEmailTf.setBounds(154, 59, 277, 27);
		EliminazionePanel.add(EliminazioneEmailTf);
		EliminazioneEmailTf.setColumns(10);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!(EliminazioneEmailTf.getText().isBlank())) {
					
					controllerClienti.delete(EliminazioneEmailTf.getText());
					
				}
				
			}
		});
		EliminaBtn.setBounds(445, 388, 132, 40);
		EliminazionePanel.add(EliminaBtn);
		
		JButton AggiuntaBtn = new JButton("Aggiunta");
		AggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		AggiuntaBtn.setBounds(10, 11, 168, 50);
		BottoniPanel.add(AggiuntaBtn);
		
		JButton EliminazioneBtn = new JButton("Eliminazione");
		EliminazioneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		EliminazioneBtn.setBounds(10, 72, 168, 50);
		BottoniPanel.add(EliminazioneBtn);
		
		JButton ElencoBtn = new JButton("Ricerca");
		ElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		ElencoBtn.setBounds(10, 133, 168, 50);
		BottoniPanel.add(ElencoBtn);
		
		JPanel RicercaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, RicercaPanel, null);
		
		JPanel panel = new JPanel();
		panel.setBounds(208, 11, 568, 28);
		contentPane.add(panel);
	}
}
