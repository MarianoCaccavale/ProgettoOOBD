package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.Gate;
import Classi.Volo;
import Controller.ControllerGate;
import Controller.ControllerVoli;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Iterator;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Timestamp;

public class SlotImbarcoJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControllerGate controllerGate = new ControllerGate();
	ArrayList<Gate> ElencoGate = new ArrayList<Gate>();

	
	public SlotImbarcoJDialog(Aeroporto a, Volo volo) {
		setTitle("Creazione Slot Imbarco");
		ControllerVoli controllerVoli = new ControllerVoli();
		
		setBounds(100, 100, 320, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			JLabel SceltaGateLbl = new JLabel("Scegli il gate");
			SceltaGateLbl.setBounds(102, 23, 98, 13);
			contentPanel.add(SceltaGateLbl);
		
		
			JComboBox<String> GateCombo = new JComboBox<String>();
			java.util.Date dataTmp = (java.util.Date) volo.getData();
			Timestamp dataVolo = new Timestamp(dataTmp.getTime());
			ElencoGate = controllerGate.getGateLiberi(a.getCodAeroporto(), dataVolo);
			Iterator<Gate> GateDaCaricare = ElencoGate.iterator();
			
			while(GateDaCaricare.hasNext()) {
				
				Gate tmp = GateDaCaricare.next();
				GateCombo.addItem(tmp.getCodGate() + ":" + tmp.getNomeGate());
				
			}
			
			GateCombo.setBounds(78, 46, 120, 21);
			contentPanel.add(GateCombo);
		
	
			JLabel SceltaCodaLbl = new JLabel("Scegli il tipo di coda");
			SceltaCodaLbl.setBounds(78, 104, 135, 13);
			contentPanel.add(SceltaCodaLbl);
		
		
			JComboBox<String> CodaCombo = new JComboBox<String>();
			CodaCombo.addItem("Prima Classe");
			CodaCombo.addItem("Business");
			CodaCombo.addItem("Economy");
			CodaCombo.addItem("Priority");
			CodaCombo.addItem("Diversamente abili");
			CodaCombo.addItem("Famiglia");
			CodaCombo.setBounds(65, 127, 147, 21);
			contentPanel.add(CodaCombo);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						if (GateCombo.getSelectedItem().toString() != null) {
							
							controllerVoli.InsertVoloAndImbarco(volo, GateCombo.getSelectedItem().toString().substring(0,  GateCombo.getSelectedItem().toString().indexOf(":")), CodaCombo.getSelectedItem().toString());
							dispose();
							
						}else {
							
							JDialog successo = new JDialog();
							successo.setBounds(200,200,400,200);
							JLabel testo = new JLabel("Non è possibile inserire un volo senza Gate!\n "
									+ "Se non ne è disponibile nessuno vuol dire che tutti i gate in questa fascia oraria sono occupati.");
							successo.add(testo);
							successo.setVisible(true);
							dispose();
							
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
}


