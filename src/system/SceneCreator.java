package system;

import geometry.CSG_Object;
import geometry.Cube;

import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import math.Point;
import math.Vector;
import transformations.Rotation;
import transformations.Scale;
import transformations.Translation;
import camera.PinholeCamera;
import csg.CSGTreeNode;
import csg.DifferenceNode;
import csg.ObjectNode;
import csg.TransformationNode;
import csg.UnionNode;

public class SceneCreator extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 292897539773778570L;
	private Scene scene;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SceneCreator inst = new SceneCreator();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public SceneCreator() {
		super();
		initGUI();
		generateScene();
		saveScene();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void generateScene() {
		scene = new Scene();
		Dimension dimension = new Dimension(100, 100);
		scene.addCamera(new PinholeCamera((int) dimension.getHeight(),
				(int) dimension.getWidth(), new Point(-2.6, 3, -3), new Point(-1.7, 2.33,
						-2), new Vector(0, 1, 0), 0.5));
		CSGTreeNode budova = new TransformationNode(new Scale(1, 1, 1.5),
				new ObjectNode(new Cube()));
		CSGTreeNode strecha = new TransformationNode(new Scale(1, 1, 1.5),
				new ObjectNode(new Cube()));
		CSGTreeNode komin = new TransformationNode(new Scale(0.2, 1, 0.2),
				new ObjectNode(new Cube()));
		CSGTreeNode dvere = new TransformationNode(new Scale(0.2, 0.7, 0.4),
				new ObjectNode(new Cube()));
		dvere = new TransformationNode(new Translation(new Vector(-0.1, -0.01, 0.3)), dvere);
		budova = new DifferenceNode(budova, dvere);
		strecha = new TransformationNode(new Rotation(Math.PI / 4,
				Rotation.AROUND_Z), strecha);
		CSGTreeNode node3 = new TransformationNode(new Translation(new Vector(
				-0.1, -1, -0.1)), new ObjectNode(new Cube()));
		node3 = new TransformationNode(new Scale(2, 1, 2), node3);
		strecha = new DifferenceNode(strecha, node3);
		komin = new TransformationNode(
				new Translation(new Vector(0.2, 1, 0.2)), komin);
		strecha = new TransformationNode(
				new Translation(new Vector(-0.24, 1, 0)), strecha);
		CSG_Object object = new CSG_Object(new UnionNode(new UnionNode(budova, strecha), komin));
		scene.addObject(object);
	}

	private void saveScene() {
		FileOutputStream fOut = null;
		ObjectOutputStream oOut = null;
		try {
			fOut = new FileOutputStream(
					"d:\\skola/eclipse2/bakalarska praca/dom.ser");
			oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(scene); // serializing employee
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oOut.flush();
				oOut.close();
				fOut.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}
