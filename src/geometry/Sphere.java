package geometry;

import java.util.LinkedList;
import abstractClass.Intersectable;
import math.*;

public class Sphere extends Intersectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7858721273148008622L;

	private Point middle;

	private double radius;

	// (x-x0)^2 + (y-y0)^2 + (z-z0)^2 = r^2

	/**
	 * Creates a sphere with middle (x,y,z) and radius r.
	 */
	public Sphere(double x, double y, double z, double r) {

		this.middle = new Point(x, y, z);
		this.radius = r;
	}

	public Sphere(Point middle, double r) {
		this.middle = middle;
		this.radius = r;
	}

	/**
	 * 
	 * @param polpriamka
	 * @return b^2 - 4*a*c
	 */
	private double Diskriminant(Ray ray) {
		double a1 = ray.getPoint().getX();
		double a2 = ray.getPoint().getY();
		double a3 = ray.getPoint().getZ();
		double v1 = ray.getVector().getX();
		double v2 = ray.getVector().getY();
		double v3 = ray.getVector().getZ();
		double x0 = this.middle.getX();
		double y0 = this.middle.getY();
		double z0 = this.middle.getZ();
		return (2 * ((a1 * v1) + (a2 * v2) + (a3 * v3) - (x0 * v1) - (y0 * v2) - (z0 * v3)))
				* (2 * ((a1 * v1) + (a2 * v2) + (a3 * v3) - (x0 * v1)
						- (y0 * v2) - (z0 * v3)))
				- (4 * ((v1 * v1) + (v2 * v2) + (v3 * v3)) * ((a1 * a1)
						+ (a2 * a2) + (a3 * a3) + (x0 * x0) + (y0 * y0)
						+ (z0 * z0) - (2 * ((a1 * x0) + (a2 * y0) + (a3 * z0))) - this.radius
						* this.radius));
	}

	public boolean intersect(Ray ray) {
		return Diskriminant(ray) >= 0;
	}

	public LinkedList<Ray> getIntersects(Ray ray) {
		double diskriminant = Diskriminant(ray);
		double a1 = ray.getPoint().getX();
		double a2 = ray.getPoint().getY();
		double a3 = ray.getPoint().getZ();
		double v1 = ray.getVector().getX();
		double v2 = ray.getVector().getY();
		double v3 = ray.getVector().getZ();
		double x0 = this.middle.getX();
		double y0 = this.middle.getY();
		double z0 = this.middle.getZ();
		double t1 = (-(2 * (a1 * v1 + a2 * v2 + a3 * v3 - x0 * v1 - y0 * v2 - z0
				* v3)) + Math.sqrt(diskriminant))
				/ (2 * (v1 * v1 + v2 * v2 + v3 * v3));
		if (diskriminant == 0) {
			if (t1 < 0)
				return null;
			LinkedList<Ray> intersections = new LinkedList<Ray>();
			Point intersection = new Point(a1 + t1 * v1, a2 + t1 * v2, a3 + t1
					* v3);
			intersections.add(new Ray(intersection, Vector.getVector(
					this.middle, intersection)));
			return intersections;
		}
		if (diskriminant > 0) {
			double t2 = (-(2 * (a1 * v1 + a2 * v2 + a3 * v3 - x0 * v1 - y0 * v2 - z0
					* v3)) - Math.sqrt(diskriminant))
					/ (2 * (v1 * v1 + v2 * v2 + v3 * v3));
			if (t1 < 0 && t2 < 0)
				return null;
			LinkedList<Ray> intersections = new LinkedList<Ray>();
			if (t1 >= 0) {
				Point intersection = new Point(a1 + t1 * v1, a2 + t1 * v2, a3
						+ t1 * v3);
				intersections.add(new Ray(intersection, Vector.getVector(
						this.middle, intersection)));
			}
			if (t2 >= 0) {
				Point intersection = new Point(a1 + t2 * v1, a2 + t2 * v2, a3
						+ t2 * v3);
				intersections.add(new Ray(intersection, Vector.getVector(
						this.middle, intersection)));
			}
			if (intersections.size() > 1
					&& intersections.get(0).getPoint().distance(ray.getPoint()) > intersections
							.get(1).getPoint().distance(ray.getPoint())) {
				Ray pom = intersections.get(0);
				intersections.set(0, intersections.get(1));
				intersections.set(1, pom);
			}
			return intersections;
		}
		return null;
	}

	public void print() {
		System.out.println("Sphere{");
		this.middle.print();
		System.out.println("radius = " + this.radius);
		System.out.println("}");
	}

}
