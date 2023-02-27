package csg;

import java.util.Iterator;
import java.util.LinkedList;

import math.Ray;
import math.Vector;

public class UnionNode extends OperationNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3821826309003327503L;

	public UnionNode(CSGTreeNode left, CSGTreeNode right) {
		super(left, right);
	}

	@Override
	public LinkedList<Ray> getIntersections(Ray ray) {
		LinkedList<Ray> leftIntersection = this.getLeft().getIntersections(ray);
		LinkedList<Ray> rightIntersection = this.getRight().getIntersections(
				ray);
		if (leftIntersection == null && rightIntersection == null)
			return null;
		if (leftIntersection == null)
			return rightIntersection;
		if (rightIntersection == null)
			return leftIntersection;
		LinkedList<Ray> intersections = new LinkedList<Ray>();
		Iterator<Ray> leftIterator = leftIntersection.iterator();
		Iterator<Ray> rightIterator = rightIntersection.iterator();
		Ray leftRay = leftIterator.next();
		Ray rightRay = rightIterator.next();

		while (leftRay != null || rightRay != null) {
			if (rightRay == null) {
				intersections.add(leftRay);
				if (leftIterator.hasNext())
					leftRay = leftIterator.next();
				else
					leftRay = null;
			} else {
				if (leftRay == null
						|| leftRay.getPoint().distance(ray.getPoint()) > rightRay
								.getPoint().distance(ray.getPoint())) {
					intersections.add(rightRay);
					if (rightIterator.hasNext())
						rightRay = rightIterator.next();
					else
						rightRay = null;
				} else if (leftRay.getPoint().distance(ray.getPoint()) > rightRay
						.getPoint().distance(ray.getPoint())) {
					if (Vector.dotProduct(ray.getVector(), leftRay.getVector()) < 0)
						intersections.add(rightRay);
					if (rightIterator.hasNext())
						rightRay = rightIterator.next();
					else
						rightRay = null;
				} else if (leftRay.getPoint().distance(ray.getPoint()) == rightRay
						.getPoint().distance(ray.getPoint())) {
					intersections.add(leftRay);
					if (rightIterator.hasNext())
						rightRay = rightIterator.next();
					else
						rightRay = null;
					if (leftIterator.hasNext())
						leftRay = leftIterator.next();
					else
						leftRay = null;

				} else {
					if (Vector
							.dotProduct(ray.getVector(), rightRay.getVector()) < 0)
						intersections.add(leftRay);
					if (leftIterator.hasNext())
						leftRay = leftIterator.next();
					else
						leftRay = null;
				}
			}
		}
		return intersections;
	}
}
