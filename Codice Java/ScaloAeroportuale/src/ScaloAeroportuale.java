import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ScaloAeroportuale {

	private JFrame frmScaloAeroportuale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScaloAeroportuale window = new ScaloAeroportuale();
					window.frmScaloAeroportuale.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScaloAeroportuale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmScaloAeroportuale = new JFrame();
		frmScaloAeroportuale.setTitle("Scalo Aeroportuale");
		frmScaloAeroportuale.setBounds(100, 100, 721, 502);
		frmScaloAeroportuale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmScaloAeroportuale.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Scegliere Aeroporto in cui lavorare");
		lblNewLabel.setBounds(84, 220, 181, 31);
		frmScaloAeroportuale.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setSelectedIndex(2);
		comboBox.setBounds(349, 196, 122, 22);
		frmScaloAeroportuale.getContentPane().add(comboBox);
	}
}
