package transformations;

import math.Matrix;
import math.Ray;

public class Rotation extends Transformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3774877376285080940L;
	public static final int AROUND_X = 1;
	public static final int AROUND_Y = 2;
	public static final int AROUND_Z = 3;

	public Rotation(double angle, int around) {
		Matrix matrix = new Matrix(4, 4);
		matrix.setA(3, 3, 1);
		switch (around) {
		case 1:
			matrix.setA(0, 0, 1);
			matrix.setA(1, 1, Math.cos(angle));
			matrix.setA(2, 2, Math.cos(angle));
			matrix.setA(1, 2, Math.sin(angle));
			matrix.setA(2, 1, -Math.sin(angle));
			break;
		case 2:
			matrix.setA(1, 1, 1);
			matrix.setA(0, 0, Math.cos(angle));
			matrix.setA(2, 2, Math.cos(angle));
			matrix.setA(2, 0, Math.sin(angle));
			matrix.setA(0, 2, -Math.sin(angle));
			break;
		case 3:
			matrix.setA(2, 2, 1);
			matrix.setA(1, 1, Math.cos(angle));
			matrix.setA(0, 0, Math.cos(angle));
			matrix.setA(0, 1, Math.sin(angle));
			matrix.setA(1, 0, -Math.sin(angle));
			break;
		default:
			matrix.setA(0, 0, 1);
			matrix.setA(1, 1, 1);
			matrix.setA(2, 2, 1);
		}
		this.transformationMatrix = matrix;
		this.inverseTransformationMatrix = matrix.getInverseMatrix();
		matrix = Matrix.getProduct(this.inverseTransformationMatrix,
				this.transformationMatrix);
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
