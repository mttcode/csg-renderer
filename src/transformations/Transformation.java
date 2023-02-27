package transformations;

import java.io.Serializable;

import math.Matrix;
import math.Point;
import math.Ray;
import math.Vector;

public abstract class Transformation implements Serializable {
	protected Matrix transformationMatrix;
	protected Matrix inverseTransformationMatrix;

	/**
	 * Transforms the ray with transformation represented by
	 * transformationMatrix.
	 * 
	 * @param ray
	 *            ray which will be transformed
	 * @return transformed ray
	 */
	public abstract Ray transform(Ray ray);

	/**
	 * Transforms the ray with inverse transformation represented by
	 * inverseTransformationMatrix.
	 * 
	 * @param ray
	 *            ray which will be inverse transformed
	 * @return inverse transformed ray
	 */
	public abstract Ray inverseTransform(Ray ray);

	/**
	 * Converts from point representation to matrix representation.
	 * 
	 * @param point
	 * @return
	 */
	public static Matrix getMatrix(Point point) {
		Matrix matrix = new Matrix(4, 1);
		matrix.setA(0, 0, point.getX());
		matrix.setA(1, 0, point.getY());
		matrix.setA(2, 0, point.getZ());
		matrix.setA(3, 0, 1);
		return matrix;
	}

	/**
	 * Converts from matrix representation to matrix representation.
	 * 
	 * @param vector
	 * @return
	 */
	public static Matrix getMatrix(Vector vector) {
		Matrix matrix = new Matrix(4, 1);
		matrix.setA(0, 0, vector.getX());
		matrix.setA(1, 0, vector.getY());
		matrix.setA(2, 0, vector.getZ());
		matrix.setA(3, 0, 1);
		return matrix;
	}

	/**
	 * Converts from matrix representation to point representation.
	 * 
	 * @param matrix
	 * @return
	 */
	public static Point getPoint(Matrix matrix) {
		if (matrix.numberColumns() != 1 || matrix.numberRows() != 4)
			return null;
		return new Point(matrix.getA(0, 0), matrix.getA(1, 0), matrix
				.getA(2, 0));
	}

	/**
	 * Converts from matrix representation to vector representation.
	 * 
	 * @param matrix
	 * @return
	 */
	public static Vector getVector(Matrix matrix) {
		if (matrix.numberColumns() != 1 || matrix.numberRows() != 4)
			return null;
		return new Vector(matrix.getA(0, 0), matrix.getA(1, 0), matrix.getA(2,
				0));
	}
}
