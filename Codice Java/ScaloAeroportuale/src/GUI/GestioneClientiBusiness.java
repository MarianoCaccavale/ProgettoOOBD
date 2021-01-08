package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.ClienteBusiness;
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
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

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
		
		JPanel panel = new JPanel();
		panel.setBounds(177, 11, 623, 25);
		contentPane.add(panel);
		
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setBounds(10, 11, 157, 365);
		contentPane.add(BottoniPanel);
		BottoniPanel.setLayout(null);
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 386, 157, 92);
		contentPane.add(IndietroPanel);
		IndietroPanel.setLayout(null);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		IndietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ClientiBusinessToServizioClienti(a);
				
			}
		});
		IndietroBtn.setBounds(10, 43, 138, 38);
		IndietroPanel.add(IndietroBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(177, 11, 623, 467);
		contentPane.add(tabbedPane);
		
		JPanel AggiuntaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, AggiuntaPanel, null);
		AggiuntaPanel.setLayout(null);
		
		JLabel NomeLbl = new JLabel("Nome:");
		NomeLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		NomeLbl.setBounds(44, 28, 81, 23);
		AggiuntaPanel.add(NomeLbl);
		
		NomeTf = new JTextField();
		NomeTf.setFont(new Font("Arial", Font.PLAIN, 16));
		NomeTf.setBounds(167, 24, 168, 38);
		AggiuntaPanel.add(NomeTf);
		NomeTf.setColumns(10);
		
		JLabel CognomeLbl = new JLabel("Cognome:");
		CognomeLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		CognomeLbl.setBounds(44, 95, 113, 23);
		AggiuntaPanel.add(CognomeLbl);
		
		CognomeTf = new JTextField();
		CognomeTf.setFont(new Font("Arial", Font.PLAIN, 16));
		CognomeTf.setBounds(167, 91, 168, 38);
		AggiuntaPanel.add(CognomeTf);
		CognomeTf.setColumns(10);
		
		JComboBox<String> CompagniaCombo = new JComboBox<String>();
		CompagniaCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		CompagniaCombo.setModel(new DefaultComboBoxModel(new String[] {"Scegliere la compagnia"}));
		CompagniaCombo.setBounds(44, 286, 330, 50);
		
		ArrayList<CompagniaAerea> compagniaLista = new ArrayList<CompagniaAerea>();
		compagniaLista = controllerCompagnie.getAllCompagnie();
		
		for(CompagniaAerea tmp:compagniaLista) {
			
			CompagniaCombo.addItem(tmp.getCodCompagnia() + ": " + tmp.getNomeCompagnia());
			
		}
		
		AggiuntaPanel.add(CompagniaCombo);
		
		JSpinner DataNascitaSpn = new JSpinner();
		DataNascitaSpn.setFont(new Font("Arial", Font.PLAIN, 16));
		DataNascitaSpn.setModel(new SpinnerDateModel());
		DataNascitaSpn.setBounds(167, 217, 164, 38);
		AggiuntaPanel.add(DataNascitaSpn);
		
		JButton InserisciBtn = new JButton("Registra cliente");
		InserisciBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		InserisciBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(EmailTf.getText().isBlank()) && !(NomeTf.getText().isBlank()) && !(CognomeTf.getText().isBlank())) {
					
					String codCompagnia = CompagniaCombo.getSelectedItem().toString().substring(0, CompagniaCombo.getSelectedItem().toString().indexOf(":"));
					controllerClienti.insert(EmailTf.getText(), NomeTf.getText(), CognomeTf.getText(), (Date) DataNascitaSpn.getValue(), codCompagnia);
				}
				
			}
		});
		InserisciBtn.setBounds(448, 392, 160, 38);
		AggiuntaPanel.add(InserisciBtn);
		
		JLabel EmailLbl = new JLabel("Email:");
		EmailLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		EmailLbl.setBounds(44, 163, 81, 23);
		AggiuntaPanel.add(EmailLbl);
		
		JLabel DataLbl = new JLabel("Data di nascita:");
		DataLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		DataLbl.setBounds(44, 224, 211, 23);
		AggiuntaPanel.add(DataLbl);
		
		
		EmailTf = new JTextField();
		EmailTf.setFont(new Font("Arial", Font.PLAIN, 16));
		EmailTf.setBounds(167, 156, 287, 38);
		AggiuntaPanel.add(EmailTf);
		EmailTf.setColumns(10);
		
		
		JPanel EliminazionePanel = new JPanel();
		tabbedPane.addTab("New tab", null, EliminazionePanel, null);
		EliminazionePanel.setLayout(null);
		
		JLabel EliminazioneNomeLbl = new JLabel("Inserire l'email del cliente:");
		EliminazioneNomeLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminazioneNomeLbl.setBounds(155, 89, 277, 27);
		EliminazionePanel.add(EliminazioneNomeLbl);
		
		EliminazioneEmailTf = new JTextField();
		EliminazioneEmailTf.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminazioneEmailTf.setBounds(155, 127, 277, 38);
		EliminazionePanel.add(EliminazioneEmailTf);
		EliminazioneEmailTf.setColumns(10);
		
		JButton EliminaBtn = new JButton("Elimina");
		EliminaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!(EliminazioneEmailTf.getText().isBlank())) {
					
					controllerClienti.delete(EliminazioneEmailTf.getText());
					
				}
				
			}
		});
		EliminaBtn.setBounds(476, 390, 132, 40);
		EliminazionePanel.add(EliminaBtn);
		
		JButton AggiuntaBtn = new JButton("Aggiunta");
		AggiuntaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		AggiuntaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		AggiuntaBtn.setBounds(10, 11, 138, 38);
		BottoniPanel.add(AggiuntaBtn);
		
		JButton EliminazioneBtn = new JButton("Eliminazione");
		EliminazioneBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		EliminazioneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		EliminazioneBtn.setBounds(10, 59, 138, 38);
		BottoniPanel.add(EliminazioneBtn);
		
		JButton ElencoBtn = new JButton("Ricerca");
		ElencoBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		ElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				
			}
		});
		ElencoBtn.setBounds(10, 107, 138, 38);
		BottoniPanel.add(ElencoBtn);
		
		JPanel RicercaPanel = new JPanel();
		tabbedPane.addTab("New tab", null, RicercaPanel, null);
		RicercaPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 598, 290);
		RicercaPanel.add(scrollPane);
		
		JPanel RicercaClientiPanel = new JPanel();
		RicercaClientiPanel.setBounds(10, 10, 598, 66);
		RicercaPanel.add(RicercaClientiPanel);
		RicercaClientiPanel.setLayout(null);
		
		JComboBox CercaCompagnieCombo = new JComboBox();
		CercaCompagnieCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		CercaCompagnieCombo.setModel(new DefaultComboBoxModel(new String[] {"Scegliere la compagnia"}));
		CercaCompagnieCombo.setBounds(10, 10, 330, 50);
		RicercaClientiPanel.add(CercaCompagnieCombo);
		
		for(CompagniaAerea tmp:compagniaLista) {
			
			CercaCompagnieCombo.addItem(tmp.getCodCompagnia() + ": " + tmp.getNomeCompagnia());
			
		}
		
		JTextPane ElencoPane = new JTextPane();
		ElencoPane.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(ElencoPane);
		
		ArrayList<ClienteBusiness> ClientiDaStampare = new ArrayList<ClienteBusiness>();
		ClientiDaStampare = controllerClienti.getAllClientiBusiness();
		
		for(ClienteBusiness tmp:ClientiDaStampare) {
				ElencoPane.setText(ElencoPane.getText() + "\n");
				ElencoPane.setText(ElencoPane.getText() + "Nome: " + tmp.getNome() + "\tCognome: " + tmp.getCognome() +"\tEmail: " + tmp.getEmail() + "\tPunti: " + tmp.getPunti() + "\tCodice CentoKilometri: " + tmp.getCodCentoKilometri() + "");
		}
		
		
		JButton RicercaBtn = new JButton("Cerca");
		RicercaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ElencoPane.setText("");
				
				if (CercaCompagnieCombo.getSelectedIndex() != 0 ) {
					
					ArrayList<ClienteBusiness> ClientiDaStampare = new ArrayList<ClienteBusiness>();
					String codCompagnia = CercaCompagnieCombo.getSelectedItem().toString().substring(0, CercaCompagnieCombo.getSelectedItem().toString().indexOf(":"));
					ClientiDaStampare = controllerClienti.getClientiBusinessByCompagnia(codCompagnia);
					
					for(ClienteBusiness tmp:ClientiDaStampare) {
						ElencoPane.setText(ElencoPane.getText() + "\n");
						ElencoPane.setText(ElencoPane.getText() + "Nome: " + tmp.getNome() + "\tCognome: " + tmp.getCognome() +"\tEmail: " + tmp.getEmail() + "\tPunti: " + tmp.getPunti() + "\tCodice CentoKilometri: " + tmp.getCodCentoKilometri() + "");
					}
					
				}
				
			}
		});
		RicercaBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		RicercaBtn.setBounds(470, 392, 138, 38);
		RicercaPanel.add(RicercaBtn);
		
		
		
	}
}
