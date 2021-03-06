package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.Gate;
import Classi.Volo;
import Controller.ControllerGate;
import Controller.ControllerSlotImbarco;
import Controller.ControllerVoli;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.util.Iterator;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Timestamp;
import java.awt.Font;

public class GestioneSlotImbarco extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControllerGate controllerGate = new ControllerGate();
	ArrayList<Gate> ElencoGate = new ArrayList<Gate>();

	
	public GestioneSlotImbarco(Aeroporto a, Volo volo) {
		setResizable(false);
		setTitle("Creazione Slot Imbarco");
		ControllerVoli controllerVoli = new ControllerVoli();
		ControllerSlotImbarco controllerSlotImbarco = new ControllerSlotImbarco();
		
		setBounds(100, 100, 320, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			JLabel SceltaGateLbl = new JLabel("Scegli il gate");
			SceltaGateLbl.setFont(new Font("Arial", Font.PLAIN, 16));
			SceltaGateLbl.setBounds(106, 54, 160, 21);
			contentPanel.add(SceltaGateLbl);
		
		
			JComboBox<String> GateCombo = new JComboBox<String>();
			GateCombo.setFont(new Font("Arial", Font.PLAIN, 16));
			java.util.Date dataTmp = (java.util.Date) volo.getData();
			Timestamp dataVolo = new Timestamp(dataTmp.getTime());
			ElencoGate = controllerGate.getGateLiberi(a.getCodAeroporto(), dataVolo);
			
			for (Gate tmp : ElencoGate) {
				
				GateCombo.addItem(tmp.getNomeGate());
				
			}
			
			GateCombo.setBounds(88, 85, 145, 41);
			contentPanel.add(GateCombo);
		
	
			JLabel SceltaCodaLbl = new JLabel("Scegli il tipo di coda");
			SceltaCodaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
			SceltaCodaLbl.setBounds(83, 144, 183, 32);
			contentPanel.add(SceltaCodaLbl);
		
		
			JComboBox<String> CodaCombo = new JComboBox<String>();
			CodaCombo.setFont(new Font("Arial", Font.PLAIN, 16));
			CodaCombo.addItem("Prima Classe");
			CodaCombo.addItem("Business");
			CodaCombo.addItem("Economy");
			CodaCombo.addItem("Priority");
			CodaCombo.addItem("Diversamente abili");
			CodaCombo.addItem("Famiglia");
			CodaCombo.setBounds(69, 186, 197, 41);
			contentPanel.add(CodaCombo);
		
		
			JPanel ButtonPanel = new JPanel();
			ButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(ButtonPanel, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Arial", Font.PLAIN, 16));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						if (GateCombo.getSelectedItem().toString() != null) {
							
							controllerVoli.insert(volo);
							controllerSlotImbarco.insert(a, volo, GateCombo.getSelectedItem().toString(),  CodaCombo.getSelectedItem().toString());
							dispose();
							
						}else {
							
							JDialog successo = new JDialog();
							successo.setBounds(200,200,400,200);
							JLabel testo = new JLabel("Non � possibile inserire un volo senza Gate!\n "
									+ "Se non ne � disponibile nessuno vuol dire che tutti i gate in questa fascia oraria sono occupati.");
							successo.getContentPane().add(testo);
							successo.setVisible(true);
							dispose();
							
						}
						
					}
				});
				okButton.setActionCommand("OK");
				ButtonPanel.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
}


