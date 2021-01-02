package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Controller.Controller;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ServizioClienti extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	
	public ServizioClienti(Controller c, Aeroporto a) {
		
		Controller controller = c;
		
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
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(241, 83, 46, 14);
		BigliettiPanel.add(lblNewLabel);
		
		JPanel ClientiBusiness = new JPanel();
		tabbedPane.addTab("New tab", null, ClientiBusiness, null);
		ClientiBusiness.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(232, 180, 86, 20);
		ClientiBusiness.add(textField);
		textField.setColumns(10);
		
		
	}
}
