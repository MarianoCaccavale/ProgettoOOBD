package GUI;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Controller.Controller;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	Controller controller;
	
	public Login(Controller c) {
		setResizable(false);
		setTitle("Login Aeroporto");
		controller = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> ListaAeroportiCombo = new JComboBox<String>();
		ListaAeroportiCombo.setFont(new Font("Arial", Font.PLAIN, 20));
		ListaAeroportiCombo.setBounds(203, 165, 330, 50);
		contentPane.add(ListaAeroportiCombo);
		
		
		ArrayList<Aeroporto> Aeroporti = new ArrayList<>();
		Aeroporto aer = new Aeroporto();
		ListaAeroportiCombo.setModel(new DefaultComboBoxModel<String>());
		
		Aeroporti = aer.getAeroporti();
		Iterator<Aeroporto> i = Aeroporti.iterator();
		
		
		while(i.hasNext()) {
			
			Aeroporto tmp = i.next();
			ListaAeroportiCombo.addItem(tmp.getNomeAeroporto());
			
		}
		
		JButton LoginBtn = new JButton("Login");
		LoginBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.LoginToHub(ListaAeroportiCombo.getSelectedItem().toString());
			}
		});
		
		LoginBtn.setBounds(565, 404, 138, 38);
		contentPane.add(LoginBtn);
		
		
	}
}
