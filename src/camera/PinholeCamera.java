package camera;

import math.OrthoNormalBasis;
import math.Point;
import math.Ray;
import math.Vector;

public class PinholeCamera extends Camera {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1196254395687197227L;
	private Point eye;
	private OrthoNormalBasis basis;
	private double height;
	private double width;
	private double f;
	
	/**
	 * Creates a new pinnhole camera.
	 * @param imageHeight 
	 * @param imageWidth
	 * @param eye position of the eye
	 * @param target 
	 * @param up vector which directs apwards
	 * @param height 
	 */
	public PinholeCamera(int imageHeight, int imageWidth, Point eye,
			Point target, Vector up, double fov) {
		super(imageHeight, imageWidth);
		this.eye = eye;
		this.basis = new OrthoNormalBasis(Vector.getVector(eye, target), up);
		this.height = target.distance(eye) * Math.tan(fov);
		this.width = this.getImageWidth() * this.height / this.getImageHeight();
		this.f = eye.distance(target);
	}
	
	public void setResolution(int imageHeight, int imageWidth){
		super.setResolution(imageHeight, imageWidth);
		this.width = this.getImageWidth() * this.height / this.getImageHeight();
	}

	@Override
	public Ray getRay(int x, int y) {
		Point point = new Point((x - (this.getImageWidth() * 0.5)) * this.width
				/ this.getImageWidth(),
				(0-(y - (this.getImageHeight() * 0.5))) * this.height
						/ this.getImageHeight(), f);
		Vector vector = Vector.getVector(new Point(0, 0, 0), point);
		vector = this.basis.transform(vector);
		//vector.setX(-vector.getX());
		//vector.setZ(-vector.getZ());
		return new Ray(eye, vector);
	}

}
