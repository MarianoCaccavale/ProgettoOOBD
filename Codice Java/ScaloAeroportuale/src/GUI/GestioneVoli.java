package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Controller.Controller;
import Controller.ControllerTratte;
import Controller.ControllerVoli;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestioneVoli extends JFrame {

	private JPanel contentPane;

	Controller controller;
	
	/**
	 * Create the frame.
	 */
	public GestioneVoli(Controller c, Aeroporto a) {
		setTitle("Gestione Voli");
		controller = c;
		ControllerVoli controllerVoli = new ControllerVoli();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel SceltaPanel = new JPanel();
		SceltaPanel.setLayout(null);
		SceltaPanel.setBounds(10, 10, 155, 307);
		contentPane.add(SceltaPanel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(226, 0, 533, 448);
		contentPane.add(tabbedPane);
		
		JPanel AggiuntaPanel = new JPanel();
		AggiuntaPanel.setLayout(null);
		tabbedPane.addTab("New tab", null, AggiuntaPanel, null);
		
		JPanel EliminazionePanel = new JPanel();
		EliminazionePanel.setLayout(null);
		tabbedPane.addTab("New tab", null, EliminazionePanel, null);
		
		JPanel ElencoPanel = new JPanel();
		tabbedPane.addTab("New tab", null, ElencoPanel, null);
		
		JPanel ModificaPanel = new JPanel();
		ModificaPanel.setLayout(null);
		tabbedPane.addTab("New tab", null, ModificaPanel, null);
		
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
				tabbedPane.setSelectedIndex(2);
			}
		});
		VoliEliminaBtn.setBounds(10, 95, 135, 31);
		SceltaPanel.add(VoliEliminaBtn);
		
		JButton VoliElencoBtn = new JButton("Elenco");
		VoliElencoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
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
