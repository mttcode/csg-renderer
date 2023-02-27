package math;

import java.io.Serializable;
import java.lang.reflect.Array;

public class Point implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2878505996045194808L;

	private double x;

	private double y;

	private double z;

	public Point(double x, double y, double z) {
		this.set(x, y, z);
	}

	public Point(Point point) {
		this.x = point.getX();
		this.y = point.getY();
		this.z = point.getZ();
	}

	// --------------------------

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}

	// --------------------------

	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public static double getMaxX(Point[] points) {
		if (points == null)
			return 0;
		double max = points[0].getX();
		for (int i = 1; i < Array.getLength(points); i++) {
			if (max < points[i].getX())
				max = points[i].getX();
		}
		return max;
	}

	public static double getMaxY(Point[] points) {
		if (points == null)
			return 0;
		double max = points[0].getY();
		for (int i = 1; i < Array.getLength(points); i++) {
			if (max < points[i].getY())
				max = points[i].getY();
		}
		return max;
	}

	public static double getMaxZ(Point[] points) {
		if (points == null)
			return 0;
		double max = points[0].getZ();
		for (int i = 1; i < Array.getLength(points); i++) {
			if (max < points[i].getZ())
				max = points[i].getZ();
		}
		return max;
	}

	public static double getMinX(Point[] points) {
		if (points == null)
			return 0;
		double min = points[0].getX();
		for (int i = 1; i < Array.getLength(points); i++) {
			if (min > points[i].getX())
				min = points[i].getX();
		}
		return min;
	}

	public static double getMinY(Point[] points) {
		if (points == null)
			return 0;
		double min = points[0].getY();
		for (int i = 1; i < Array.getLength(points); i++) {
			if (min > points[i].getY())
				min = points[i].getY();
		}
		return min;
	}

	public static double getMinZ(Point[] points) {
		if (points == null)
			return 0;
		double min = points[0].getZ();
		for (int i = 1; i < Array.getLength(points); i++) {
			if (min > points[i].getZ())
				min = points[i].getZ();
		}
		return min;
	}
	
	/**
	 * Determines if two points differ from each other.
	 * @param point
	 * @return Returns false if point == this, true otherwise.
	 */
	public boolean different(Point point) {
		return (this.getX() != point.getX() || this.getY() != point.getY() || this
				.getZ() != point.getZ());
	}
	
	/**
	 * computes distance between two points.
	 * @param point
	 * @return Distance of point from actual point.
	 */
	public double distance(Point point) {
		return Math.sqrt((this.x - point.getX()) * (this.x - point.getX())
				+ (this.y - point.getY()) * (this.y - point.getY())
				+ (this.z - point.getZ()) * (this.z - point.getZ()));
	}

	public void print() {
		/*
		 * System.out.println("Point:"); System.out.println("x = " +
		 * this.getX()); System.out.println("y = " + this.getY());
		 * System.out.println("z = " + this.getZ());
		 */
		System.out.print("(" + this.getX() + " ," + this.getY() + " ,"
				+ this.getZ() + ")");
	}

}
