package system;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.*;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class CSGViewer extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4608382258073321550L;
	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private RenderCanvas canvas1;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem renderMenuItem;
	private JMenuItem openFileMenuItem;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
	
	private JFileChooser fc;
	private HelpJFrame frame;
	
	private Scene scene;
	private Renderer renderer;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CSGViewer inst = new CSGViewer();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public CSGViewer() {
		super();
		initGUI();
		fc = new JFileChooser();
	}

	private void initGUI() {
		try {
			{
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent evt) {
						thisWindowClosing(evt);
					}
				});
			}
			{
				canvas1 = new RenderCanvas();
				getContentPane().add(canvas1, BorderLayout.CENTER);
			}
			setSize(400, 300);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("File");
					jMenu3.getPopupMenu().setLightWeightPopupEnabled(false);
					{
						openFileMenuItem = new JMenuItem();
						jMenu3.add(openFileMenuItem);
						openFileMenuItem.setText("Open");
						openFileMenuItem
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										openFileMenuItemActionPerformed(evt);
									}
								});
					}
					{
						renderMenuItem = new JMenuItem();
						jMenu3.add(renderMenuItem);
						renderMenuItem.setText("Render");
						renderMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								renderMenuItemActionPerformed(evt);
							}
						});
					}
					{
						jSeparator2 = new JSeparator();
						jMenu3.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenu3.add(exitMenuItem);
						exitMenuItem.setText("Exit");
						exitMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								exitMenuItemActionPerformed(evt);
							}
						});
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("Help");
					jMenu5.getPopupMenu().setLightWeightPopupEnabled(false);
					{
						helpMenuItem = new JMenuItem();
						jMenu5.add(helpMenuItem);
						helpMenuItem.setText("Help");
						helpMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								helpMenuItemActionPerformed(evt);
							}
						});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void openFileMenuItemActionPerformed(ActionEvent evt) {
		//File dir = new File("D:\\skola/eclipse2/bakalarska praca");
		//fc.setCurrentDirectory(dir);
		MyFilter filter = new MyFilter();
		fc.addChoosableFileFilter(filter);
		int returnVal = fc.showOpenDialog(this);
		File file = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			String ext = null;
			String s = file.getName();
			int i = s.lastIndexOf('.');

			if (i > 0 && i < s.length() - 1) {
				ext = s.substring(i + 1).toLowerCase();
			}
			if (!ext.equals("ser"))
				file = null;
		}
		if (file == null)
			return;
		FileInputStream fIn = null;
		ObjectInputStream oIn = null;
		try {
			fIn = new FileInputStream(file.getAbsolutePath());
			oIn = new ObjectInputStream(fIn);
			scene = (Scene) oIn.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				oIn.close();
				fIn.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void exitMenuItemActionPerformed(ActionEvent evt) {
		this.dispose();
		Runtime.getRuntime().exit(1);
	}
	
	private void renderMenuItemActionPerformed(ActionEvent evt) {
		renderer = new Renderer();
		if (scene == null){
			return;
		}
		scene.getCamera().setResolution(canvas1.getWidth(), canvas1.getHeight());
		renderer.renderImage(canvas1, scene, canvas1.getSize());
	}
	
	private void thisWindowClosing(WindowEvent evt) {
		Runtime.getRuntime().exit(1);
	}
	
	private void helpMenuItemActionPerformed(ActionEvent evt) {
		frame = new HelpJFrame();
		frame.setVisible(true);
	}

}

class MyFilter extends javax.swing.filechooser.FileFilter {
    public boolean accept(File file) {
        String filename = file.getName();
        return filename.endsWith(".ser") || file.isDirectory();
    }
    public String getDescription() {
        return "*.ser";
    }
}
