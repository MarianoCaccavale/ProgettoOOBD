package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class GestioneGate extends JFrame {

	private JPanel BasePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioneGate frame = new GestioneGate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestioneGate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 507);
		BasePanel = new JPanel();
		BasePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BasePanel);
		BasePanel.setLayout(null);
		
		JPanel BottoniPanel = new JPanel();
		BottoniPanel.setLayout(null);
		BottoniPanel.setBounds(10, 10, 158, 252);
		BasePanel.add(BottoniPanel);
		
		JButton AggiungereBtn = new JButton("Aggiungere");
		AggiungereBtn.setBounds(10, 10, 138, 22);
		BottoniPanel.add(AggiungereBtn);
		
		JButton ModificareBtn = new JButton("Modificare");
		ModificareBtn.setBounds(10, 42, 138, 21);
		BottoniPanel.add(ModificareBtn);
		
		JButton EliminareBtn = new JButton("Eliminare");
		EliminareBtn.setBounds(10, 73, 138, 21);
		BottoniPanel.add(EliminareBtn);
		
		JButton ElencoBtn = new JButton("Elenco");
		ElencoBtn.setBounds(10, 104, 138, 21);
		BottoniPanel.add(ElencoBtn);
		
		JPanel StatistichePanel = new JPanel();
		StatistichePanel.setBounds(178, 10, 761, 450);
		BasePanel.add(StatistichePanel);
		
		JPanel IndietroPanel = new JPanel();
		IndietroPanel.setBounds(10, 429, 77, 31);
		BasePanel.add(IndietroPanel);
		
		JButton IndietroBtn = new JButton("Indietro");
		IndietroPanel.add(IndietroBtn);
	}
}
