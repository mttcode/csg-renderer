package math;

import java.io.Serializable;

public class Matrix implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1556079325409572822L;
	private double[][] A;

	/**
	 * Creates a matrix with i rows and j columns.
	 * 
	 * @param i
	 *            number of rows
	 * @param j
	 *            number of columns
	 */
	public Matrix(int i, int j) {
		this.A = new double[i][j];
		for (int u = 0; u < i; u++)
			for (int v = 0; v < j; v++)
				this.A[u][v] = 0;
	}

	/**
	 * Returns matrix element in i-th row and j-th column.
	 * 
	 * @param i
	 *            number of row
	 * @param j
	 *            number of column
	 * @return A[i][j]
	 */
	public double getA(int i, int j) {
		if (!inside(i, j))
			return 0;
		return this.A[i][j];
	}

	/**
	 * Sets matrix element in i-th row and j-th column to specified value.
	 * 
	 * @param i
	 *            number of row
	 * @param j
	 *            number of column
	 * @param value
	 *            value, to which will be set the element
	 */
	public void setA(int i, int j, double value) {
		if (!inside(i, j))
			return;
		this.A[i][j] = value;
	}

	/**
	 * Returns the number of rows.
	 * 
	 * @return number of rows
	 */
	public int numberRows() {
		if (this.A == null)
			return 0;
		return this.A.length;
	}

	/**
	 * Returns number of columns.
	 * 
	 * @return number of columns
	 */
	public int numberColumns() {
		if (this.A == null)
			return 0;
		return this.A[0].length;
	}

	/**
	 * Returns true if element [i,j] is inside the matrix, returns false
	 * otherwise.
	 * 
	 * @param i
	 *            number of row
	 * @param j
	 *            number of column
	 * @return
	 */
	private boolean inside(int i, int j) {
		if (this.A == null)
			return false;
		if (i >= 0 && i < this.A.length && j >= 0 && j < this.A[i].length)
			return true;
		else
			return false;
	}

	/**
	 * Returns the product of two matrices A.B
	 * 
	 * @param A
	 *            first matrix
	 * @param B
	 *            second matrix
	 * @return A.B
	 */
	public static final Matrix getProduct(Matrix A, Matrix B) {
		if (A == null || B == null)
			return null;
		Matrix product = new Matrix(A.numberRows(), B.numberColumns());
		double pom;
		for (int i = 0; i < A.numberRows(); i++)
			for (int j = 0; j < B.numberColumns(); j++) {
				pom = 0;
				for (int n = 0; n < A.numberColumns(); n++)
					pom = pom + A.getA(i, n) * B.getA(n, j);
				product.setA(i, j, pom);
			}
		return product;
	}

	/**
	 * Returns a matrix without the i-th row and the j-th column.
	 * 
	 * @param i
	 *            number of row
	 * @param j
	 *            number of column
	 * @return minor of an element of the matrix
	 */
	public Matrix getMinor(int i, int j) {
		if (this.numberRows() < 2 || this.numberColumns() < 2
				|| !this.inside(i, j))
			return null;
		Matrix minor = new Matrix(this.numberRows() - 1,
				this.numberColumns() - 1);
		for (int u = 0; u < this.numberRows(); u++)
			for (int v = 0; v < this.numberColumns(); v++)
				if (u != i && v != j) {
					if (u < i && v < j)
						minor.setA(u, v, this.getA(u, v));
					if (u > i && v < j)
						minor.setA(u - 1, v, this.getA(u, v));
					if (u < i && v > j)
						minor.setA(u, v - 1, this.getA(u, v));
					if (u > i && v > j)
						minor.setA(u - 1, v - 1, this.getA(u, v));
				}
		return minor;
	}

	/**
	 * Computes determinant of the matrix.
	 * 
	 * @return |A|
	 */
	public static double getDeterminant(Matrix determinant) {

		if (determinant.numberColumns() < 2 || determinant.numberColumns() < 2)
			return 0;
		if (determinant.numberColumns() == 2 && determinant.numberRows() == 2) {
			return determinant.getA(0, 0) * determinant.getA(1, 1)
					- determinant.getA(0, 1) * determinant.getA(1, 0);
		}
		double result = 0;
		if (determinant.numberRows() > 2)
			for (int j = 0; j < determinant.numberColumns(); j++)
				result = result + determinant.getA(0, j) * Math.pow(-1, j)
						* getDeterminant(determinant.getMinor(0, j));
		else
			for (int i = 0; i < determinant.numberRows(); i++)
				result = result + determinant.getA(i, 0) * Math.pow(-1, i)
						* getDeterminant(determinant.getMinor(i, 0));
		return result;
	}

	/**
	 * Computes adjoint matrix.
	 * 
	 * @return adj(A)
	 */
	public Matrix getAdjointMatrix() {
		if (this.numberColumns() != this.numberRows())
			return null;
		Matrix adjoint = new Matrix(this.numberRows(), this.numberColumns());
		for (int i = 0; i < this.numberRows(); i++)
			for (int j = 0; j < this.numberColumns(); j++) {
				adjoint.setA(j, i, Math.pow(-1, i + j)
						* getDeterminant(this.getMinor(i, j)));
			}
		return adjoint;
	}
	
	/**
	 * Computes inverse matrix.
	 * @return inverse matrix
	 */
	public Matrix getInverseMatrix() {
		Matrix inverse = this.getAdjointMatrix();
		if (inverse == null)
			return null;
		double determinant = getDeterminant(this);
		for (int i = 0; i < inverse.numberRows(); i++)
			for (int j = 0; j < inverse.numberColumns(); j++)
				inverse.setA(i, j, inverse.getA(i, j) / determinant);
		return inverse;
	}
}
