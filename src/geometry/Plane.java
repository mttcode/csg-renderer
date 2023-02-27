package geometry;

import java.util.LinkedList;

import abstractClass.Intersectable;
import math.*;

public class Plane extends Intersectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4616046592782643570L;

	private Vector n; // normal vector of the plane

	private double d; // d = dotProduct(n,p) for given point p on the plane

	public Plane(Point point1, Point point2, Point point3) {
		Vector A = Vector.getVector(point1, point2);
		Vector B = Vector.getVector(point1, point3);
		this.n = Vector.crossProduct(A, B);
		n.normalize();
		d = Vector.dotProduct(n, point1);
	}

	public boolean intersect(Ray ray) {
		double t = (this.d - Vector.dotProduct(this.n, ray.getPoint()))
				/ Vector.dotProduct(this.n, ray.getVector());
		if (t >= 0)
			return true;
		else
			return false;
	}

	public LinkedList<Ray> getIntersects(Ray ray) {
		double t = (this.d - Vector.dotProduct(this.n, ray.getPoint()))
				/ Vector.dotProduct(this.n, ray.getVector());
		if (t < 0)
			return null;
		LinkedList<Ray> intersections = new LinkedList<Ray>();
		Point intersection = new Point((t * ray.getVector().getX())
				+ ray.getPoint().getX(), (t * ray.getVector().getY())
				+ ray.getPoint().getY(), (t * ray.getVector().getZ())
				+ ray.getPoint().getZ());
		intersections.add(new Ray(intersection, this.n));
		return intersections;
	}

	/*public void print() {
		System.out.println("Plane{");
		System.out.println(this.a + "x + " + this.b + "y + " + this.c + "z + "
				+ this.d + " = 0");
		System.out.println("}");
	}*/

}
