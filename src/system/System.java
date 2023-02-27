package system;

import geometry.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import transformations.*;

import csg.*;

import camera.PinholeCamera;

import math.Point;
import math.Vector;

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
public class System extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4305693247197442451L;
	private RenderCanvas canvas1;
	private Scene scene;
	private Renderer renderer;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				System inst = new System();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public System() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				canvas1 = new RenderCanvas();
				getContentPane().add(canvas1, BorderLayout.CENTER);
				canvas1.setBackground(new java.awt.Color(255, 255, 255));
				canvas1.setPreferredSize(new java.awt.Dimension(392, 276));
			}
			pack();
			this.setSize(400, 400);

		} catch (Exception e) {
			e.printStackTrace();
		}
		render(300, 300);
	}

	private void render(int x, int y) {
		scene = new Scene();
		Dimension dimension = new Dimension(x, y);
		scene.addCamera(new PinholeCamera((int) dimension.getHeight(),
				(int) dimension.getWidth(), new Point(-2.6, 3, -3), new Point(-1.7, 2.33,
						-2), new Vector(0, 1, 0), 0.5));
		// scene.addObject(new Cube());
		// CSGTreeNode node1 = new TransformationNode(new Scale(1, 1, 0.3),
		// new ObjectNode(new Cube()));
		// CSGTreeNode node2 = new TransformationNode(new Scale(0.4, 0.4, 2),
		// new ObjectNode(new Cube()));
		// node2 = new TransformationNode(new Translation(new Vector(0.3, 0.3 ,
		// -0.95)), node2);
		// CSG_Object object = new CSG_Object(new DifferenceNode(node2, node1));
		// CSG_Object object = new CSG_Object(new TransformationNode(new
		// Rotation(
		// -0.1, Rotation.AROUND_Y), new UnionNode(node1, node2)));
		// Intersectable object = new Cube();
		// scene.addObject(object);
		// scene.addObject(new Sphere(0,0,0,1));
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
		//CSG_Object object = new CSG_Object(komin);
		scene.addObject(object);
		renderer = new Renderer();
		renderer.renderImage(canvas1, scene, dimension);
	}

}
