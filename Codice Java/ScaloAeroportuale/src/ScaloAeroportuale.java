import java.awt.EventQueue;

import javax.swing.JFrame;

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
	}

}
