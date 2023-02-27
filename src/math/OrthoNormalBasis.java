package math;

import java.io.Serializable;

public class OrthoNormalBasis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3550803905232962732L;
	private Vector e1;
	private Vector e2;
	private Vector e3;
	Matrix conversionMatrix;

	/**
	 * Creates orthonormal basis from vector u and v. Basis will consist of
	 * three vectors(e1,e2,e3). e3 will be u, e1 will be perpendicular to
	 * vectors u and v, e2 will be perpendicular to vectors e1 and e2
	 * 
	 * @param u
	 * @param v
	 */
	public OrthoNormalBasis(Vector u, Vector v) {
		u.normalize();
		this.e3 = u;
		this.e1 = Vector.crossProduct(u, v);
		this.e1.normalize();
		this.e2 = Vector.crossProduct(this.e1, this.e3);
		this.conversionMatrix = new Matrix(3, 3);
		this.conversionMatrix.setA(0, 0, e1.getX());
		this.conversionMatrix.setA(1, 0, e1.getY());
		this.conversionMatrix.setA(2, 0, e1.getZ());
		this.conversionMatrix.setA(0, 1, e2.getX());
		this.conversionMatrix.setA(1, 1, e2.getY());
		this.conversionMatrix.setA(2, 1, e2.getZ());
		this.conversionMatrix.setA(0, 2, e3.getX());
		this.conversionMatrix.setA(1, 2, e3.getY());
		this.conversionMatrix.setA(2, 2, e3.getZ());
	}

	/**
	 * Transforms a vector from reference basis to this basis.
	 * 
	 * @param a
	 *            vector to be transformed
	 * @return coordinates of a vector in this basis
	 */
	public Vector transform(Vector a) {
		Vector vector = new Vector();
		Matrix v1 = new Matrix(3, 1);
		v1.setA(0, 0, a.getX());
		v1.setA(1, 0, a.getY());
		v1.setA(2, 0, a.getZ());
		Matrix v = Matrix.getProduct(this.conversionMatrix, v1);
		vector.set(v.getA(0, 0), v.getA(1, 0), v.getA(2, 0));
		return vector;
	}

	/**
	 * Transforms a vector from this basis to reference basis.
	 * 
	 * @param a
	 *            vector to be transformed
	 * @return coordinates of a vector in reference basis
	 */
	public Vector untransform(Vector a) {
		Vector vector = new Vector();
		vector.setX(Vector.dotProduct(a, e1));
		vector.setY(Vector.dotProduct(a, e2));
		vector.setZ(Vector.dotProduct(a, e3));
		return vector;
	}

}
