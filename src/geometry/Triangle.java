package geometry;

import java.util.LinkedList;

import math.Point;
import math.Ray;
import math.Vector;
import abstractClass.Intersectable;

public class Triangle extends Intersectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -145594721312990503L;
	private Point A;
	private Point B;
	private Point C;
	private Plane plane;

	public Triangle(Point A, Point B, Point C) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.plane = new Plane(A, B, C);
	}

	public void setA(Point A) {
		this.A = A;
	}

	public void setB(Point B) {
		this.B = B;
	}

	public void setC(Point C) {
		this.C = C;
	}

	/*
	 * public LinkedList<Vector> getIntersectionVectors(Ray polpriamka) { if
	 * (this.intersect(polpriamka)) { Plane plane = new Plane(A, B, C); return
	 * plane.getIntersectionVectors(polpriamka); } else return null; }
	 */

	@Override
	public LinkedList<Ray> getIntersects(Ray ray) {
		if (this.intersect(ray)) {
			Plane plane = new Plane(A, B, C);
			LinkedList<Ray> intersections = plane.getIntersects(ray);
			if (Vector.dotProduct(intersections.getFirst().getVector(), Vector
					.crossProduct(Vector.getVector(A, B), Vector
							.getVector(A, C))) < 0) {
				intersections.getFirst().getVector().opacny();
			}
			return plane.getIntersects(ray);
		} else
			return null;
	}

	public boolean sameSide(Point p1, Point p2, Point A, Point B) {
		Vector cp1 = Vector.crossProduct(Vector.getVector(A, B), Vector
				.getVector(A, p1));
		Vector cp2 = Vector.crossProduct(Vector.getVector(A, B), Vector
				.getVector(A, p2));
		return (Vector.dotProduct(cp1, cp2) >= 0);
	}
	
	public boolean intersect1(Ray ray) {
		// Plane plane = new Plane(A, B, C);
		LinkedList<Ray> intersections = this.plane.getIntersects(ray);
		if (intersections == null)
			return false;
		return (sameSide(intersections.getFirst().getPoint(), A, B, C)
				&& sameSide(intersections.getFirst().getPoint(), B, A, C)
				&& sameSide(intersections.getFirst().getPoint(), C, A, B));
	}
	
	@Override
	public boolean intersect(Ray ray) {
		LinkedList<Ray> intersections = this.plane.getIntersects(ray);
		if (intersections == null)
			return false;
		Vector v0 = Vector.getVector(A, C);
		Vector v1 = Vector.getVector(A, B);
		Vector v2 = Vector.getVector(A, intersections.getFirst().getPoint());
		double dot00 = Vector.dotProduct(v0, v0);
		double dot01 = Vector.dotProduct(v0, v1);
		double dot02 = Vector.dotProduct(v0, v2);
		double dot11 = Vector.dotProduct(v1, v1);
		double dot12 = Vector.dotProduct(v1, v2);
		double inv = 1 / (dot00 * dot11 - dot01 * dot01);
		double u = (dot11 * dot02 - dot01 * dot12) * inv;
		double v = (dot00 * dot12 - dot01 * dot02) * inv;
		return ((u > 0) && (v > 0) && (u + v < 1));
	}

	public void print() {
		System.out.print("Triangle ");
		System.out.print("{");
		this.A.print();
		System.out.print(",");
		this.B.print();
		System.out.print(",");
		this.C.print();
		System.out.println("}");
	}

}
