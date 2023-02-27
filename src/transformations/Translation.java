package transformations;

import math.Matrix;
import math.Ray;
import math.Vector;

public class Translation extends Transformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -215875380702398139L;

	public Translation(Vector vector) {
		Matrix matrix = new Matrix(4, 4);
		matrix.setA(0, 0, 1);
		matrix.setA(1, 1, 1);
		matrix.setA(2, 2, 1);
		matrix.setA(3, 3, 1);
		matrix.setA(0, 3, vector.getX());
		matrix.setA(1, 3, vector.getY());
		matrix.setA(2, 3, vector.getZ());
		this.transformationMatrix = matrix;
		this.inverseTransformationMatrix = matrix.getInverseMatrix();
	}

	public Ray transform(Ray ray) {
		return new Ray(getPoint(Matrix.getProduct(this.transformationMatrix,
				getMatrix(ray.getPoint()))), ray.getVector());
	}

	public Ray inverseTransform(Ray ray) {
		return new Ray(getPoint(Matrix.getProduct(
				this.inverseTransformationMatrix, getMatrix(ray.getPoint()))),
				ray.getVector());
	}

}
