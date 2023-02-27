package system;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import abstractClass.Intersectable;

import math.Point;
import math.Ray;
import math.Vector;

public class Renderer {

	/**
	 * Draws rendered image of a scene on a canvas.
	 * 
	 * @param canvas
	 *            Canvas on which will be the rendered image drawn.
	 * @param scene
	 *            Scene which will be rendered.
	 * @param dimension
	 *            Dimensions of the rendered image.
	 */
	public void renderImage(RenderCanvas canvas, Scene scene,
			Dimension dimension) {
		if (scene.getCamera() == null)
			return;
		scene.getCamera().setResolution((int) dimension.getHeight(),
				(int) dimension.getWidth());
		BufferedImage image = new BufferedImage((int) dimension.getWidth(),
				(int) dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
		LinkedList<Ray> intersections;
		for (int x = 0; x < dimension.getWidth(); x++) {
			for (int y = 0; y < dimension.getHeight(); y++) {
				Ray ray = scene.getCamera().getRay(x, y);
				Iterator<Intersectable> iterator = scene.getObjectIterator();
				while (iterator.hasNext()) {
					intersections = iterator.next().getIntersects(ray);
					if (intersections == null) {
						image.setRGB(x, y, 0);
					} else {

						int rgb = angleToColor(Vector.angle(intersections
								.getFirst().getVector(), ray.getVector()));
						image.setRGB(x, y, rgb);
					}

				}
			}
		}
		canvas.drawImage(image);
	}

	/**
	 * Converts an angle in radians to a shade of gray.
	 * 
	 * @param angle
	 *            Angle in radians.
	 * @return Shade of gray in rgb.
	 */
	private int angleToColor(double angle) {
		double col = (angle / Math.PI) * 100;
		Color color = new Color((int) col, (int) col, (int) col);
		return color.getRGB();
	}

	/**
	 * Selects the closest intersection from a list to specified point.
	 * 
	 * @param point
	 * @param intersections
	 *            List of intersections.
	 * @return Closest intersection to specified point.
	 */
	private Ray getClosest(Point point, LinkedList<Ray> intersections) {
		if (intersections == null)
			return null;

		Iterator<Ray> iterator = intersections.iterator();
		if (!iterator.hasNext())
			return null;
		Ray closest = iterator.next();
		double distance = point.distance(closest.getPoint());
		while (iterator.hasNext()) {
			Ray pom = iterator.next();
			if (point.distance(pom.getPoint()) < distance) {
				distance = point.distance(pom.getPoint());
				closest = pom;
			}
		}
		return closest;
	}

}
