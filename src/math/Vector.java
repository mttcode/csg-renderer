package math;

import java.io.Serializable;

public class Vector implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3607198480369545860L;

	private double x;

	private double y;

	private double z;

	public Vector() {
	}

	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(Vector vector) {
		this.set(vector.getX(), vector.getY(), vector.getZ());
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

	// --------------------------

	/**
	 * Returns the vector directing from point1 to point2
	 * 
	 * @param point1
	 * @author point2
	 * @return Vector directing from point1 to point2.
	 */
	public static final Vector getVector(Point point1, Point point2) {
		return new Vector(point2.getX() - point1.getX(), point2.getY()
				- point1.getY(), point2.getZ() - point1.getZ());

	}

	/**
	 * Computes dot vector of two vectors.
	 * 
	 * @param vector1
	 * @param vector2
	 * @return scalar product of vector1 and vector2
	 */
	public static final double dotProduct(Vector vector1, Vector vector2) {
		return vector1.getX() * vector2.getX() + vector1.getY()
				* vector2.getY() + vector1.getZ() * vector2.getZ();
	}
	
	/**
	 * Computes dot vector of a vectors and a point.
	 * 
	 * @param vector
	 * @param point
	 * @return Scalar product of vector and point.
	 */
	public static final double dotProduct(Vector vector, Point point) {
		return vector.getX() * point.getX() + vector.getY()
				* point.getY() + vector.getZ() * point.getZ();
	}

	/**
	 * Computes cross product of two vectors.
	 * 
	 * @param vector1
	 * @param vector2
	 * @return Vector, which is perpendicular with vector1 and vector2.
	 */
	public static final Vector crossProduct(Vector vector1, Vector vector2) {
		return new Vector(vector1.getY() * vector2.getZ() - vector1.getZ()
				* vector2.getY(), -vector1.getX() * vector2.getZ()
				+ vector1.getZ() * vector2.getX(), vector1.getX()
				* vector2.getY() - vector1.getY() * vector2.getX());
	}

	/**
	 * Computes an angle between two vectors;
	 * 
	 * @param vector1
	 * @param vector2
	 * @return Angle in radians as a double.
	 */
	public static final double angle(Vector vector1, Vector vector2) {
		return Math.acos(Vector.dotProduct(vector1, vector2)
				/ (Math.sqrt(Vector.dotProduct(vector1, vector1)) * Math
						.sqrt(Vector.dotProduct(vector2, vector2))));
	}
	
	/**
	 * Changes the vectors size to 1, but keeps it direction.
	 */
	public void normalize() {
		double k = 1 / Math.sqrt(this.x * this.x + this.y * this.y + this.z
				* this.z);
		this.x = this.x * k;
		this.y = this.y * k;
		this.z = this.z * k;
	}
	
	public void opacny(){
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
	}

	public void print() {
		/*
		 * System.out.println("Vector:"); System.out.println("x = " +
		 * this.getX()); System.out.println("y = " + this.getY());
		 * System.out.println("z = " + this.getZ());
		 */
		System.out.print("(" + this.getX() + " ," + this.getY() + " ,"
				+ this.getZ() + ")");
	}

}
