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

public class Login extends JFrame {

	private JPanel contentPane;
	Controller controller;
	/**
	 * Create the frame.
	 */
	public Login(Controller c) {
		controller = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> ListaAeroportiCombo = new JComboBox<String>();
		ListaAeroportiCombo.setBounds(270, 168, 187, 32);
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
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.LoginToHub(ListaAeroportiCombo.getSelectedItem().toString());
			}
		});
		
		LoginBtn.setBounds(594, 416, 89, 23);
		contentPane.add(LoginBtn);
		
		
	}
}
