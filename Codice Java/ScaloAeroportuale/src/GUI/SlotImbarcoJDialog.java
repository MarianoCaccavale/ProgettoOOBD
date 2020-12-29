package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classi.Aeroporto;
import Classi.Gate;
import Controller.ControllerGate;
import Controller.ControllerVoli;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Calendar;

public class SlotImbarcoJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControllerGate controllerGate = new ControllerGate();
	ArrayList<Gate> ElencoGate = new ArrayList<Gate>();

	/**
	 * Create the dialog.
	 */
	public SlotImbarcoJDialog(Aeroporto a) {
		setTitle("Creazione Slot Imbarco");
		setBounds(100, 100, 320, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel SceltaGateLbl = new JLabel("Scegli il gate");
			SceltaGateLbl.setBounds(102, 23, 98, 13);
			contentPanel.add(SceltaGateLbl);
		}
		{
			JComboBox<String> GateCombo = new JComboBox<String>();
			ElencoGate = controllerGate.getGate(a.getCodAeroporto());
			Iterator<Gate> GateDaCaricare = ElencoGate.iterator();
			
			while(GateDaCaricare.hasNext()) {
				Gate tmp = GateDaCaricare.next();
				GateCombo.addItem(tmp.getCodGate());
			}
			
			GateCombo.setBounds(78, 46, 120, 21);
			contentPanel.add(GateCombo);
		}
		{
			JLabel SceltaCodaLbl = new JLabel("Scegli il tipo di coda");
			SceltaCodaLbl.setBounds(78, 104, 135, 13);
			contentPanel.add(SceltaCodaLbl);
		}
		{
			JComboBox<String> CodaCombo = new JComboBox<String>();
			CodaCombo.addItem("Prima Classe");
			CodaCombo.addItem("Business");
			CodaCombo.addItem("Economy");
			CodaCombo.addItem("Priority");
			CodaCombo.addItem("Diversamente abili");
			CodaCombo.addItem("Famiglia");
			CodaCombo.setBounds(65, 127, 147, 21);
			contentPanel.add(CodaCombo);
		}
		{
			JLabel TempoMaxLbl = new JLabel("Data stimata di chiusura del gate");
			TempoMaxLbl.setBounds(48, 182, 210, 13);
			contentPanel.add(TempoMaxLbl);
		}
		{
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerDateModel(new Date(1609196400000L), null, null, Calendar.DAY_OF_YEAR));
			spinner.setBounds(65, 205, 135, 30);
			contentPanel.add(spinner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
