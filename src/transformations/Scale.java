package transformations;

import math.Matrix;
import math.Ray;

public class Scale extends Transformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4704107494611529063L;

	public Scale(double xScale, double yScale, double zScale) {
		Matrix matrix = new Matrix(4, 4);
		matrix.setA(0, 0, xScale);
		matrix.setA(1, 1, yScale);
		matrix.setA(2, 2, zScale);
		matrix.setA(3, 3, 1);
		this.transformationMatrix = matrix;
		this.inverseTransformationMatrix = matrix.getInverseMatrix();
	}

	public Ray transform(Ray ray) {
		return new Ray(getPoint(Matrix.getProduct(this.transformationMatrix,
				getMatrix(ray.getPoint()))), getVector(Matrix.getProduct(
				this.transformationMatrix, getMatrix(ray.getVector()))));
	}

	public Ray inverseTransform(Ray ray) {
		return new Ray(getPoint(Matrix.getProduct(
				this.inverseTransformationMatrix, getMatrix(ray.getPoint()))),
				getVector(Matrix.getProduct(this.inverseTransformationMatrix,
						getMatrix(ray.getVector()))));
	}
}
