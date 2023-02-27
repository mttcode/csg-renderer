package math;

public class Ray {

	private Vector vector;

	private Point point;

	public Ray(Point point, Vector vector) {
		this.vector = vector;
		this.point = point;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}

	public void setVector(double x, double y, double z) {
		this.vector.set(x, y, z);
	}
	
	public void setPoint(Point point){
		this.point = point;
	}
	
	public void setPoint(double x, double y, double z){
		this.point.set(x,y,z);
	}

	public Vector getVector() {
		return this.vector;
	}

	public Point getPoint() {
		return this.point;
	}
	
	public void print() {
		System.out.print("Ray {Point:");
		this.point.print();
		System.out.print(" Vector:");
		this.vector.print();
		System.out.println("}");
	}

}
