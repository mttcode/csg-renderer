package system;
import javax.swing.JLabel;

import javax.swing.WindowConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class HelpJFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3660710797237713470L;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;

	/**
	* Auto-generated main method to display this JFrame
	*/

	public HelpJFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Open a file with scene (File -> Open).");
				jLabel1.setBounds(23, 19, 329, 14);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Resize the window to specified size.");
				jLabel2.setBounds(23, 39, 210, 14);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Render the scene (File -> Render).");
				jLabel3.setBounds(23, 59, 240, 14);
			}
			pack();
			this.setSize(268, 126);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
