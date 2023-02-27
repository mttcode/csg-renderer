package csg;

import java.util.Iterator;
import java.util.LinkedList;

import math.Ray;
import math.Vector;

public class IntersectionNode extends OperationNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 972418649728788104L;

	public IntersectionNode(CSGTreeNode left, CSGTreeNode right) {
		super(left, right);
	}

	@Override
	public LinkedList<Ray> getIntersections(Ray ray) {
		LinkedList<Ray> leftIntersection = this.getLeft().getIntersections(ray);
		LinkedList<Ray> rightIntersection = this.getRight().getIntersections(
				ray);
		if (leftIntersection == null || rightIntersection == null)
			return null;
		LinkedList<Ray> intersections = new LinkedList<Ray>();
		Iterator<Ray> leftIterator = leftIntersection.iterator();
		Iterator<Ray> rightIterator = rightIntersection.iterator();
		Ray leftRay = leftIterator.next();
		Ray rightRay = rightIterator.next();
		while (leftRay != null && rightRay != null) {
			if (leftRay.getPoint().distance(ray.getPoint()) > rightRay
					.getPoint().distance(ray.getPoint())) {
				if (Vector.dotProduct(ray.getVector(), leftRay.getVector()) > 0)
					intersections.add(rightRay);
				if (rightIterator.hasNext())
					rightRay = rightIterator.next();
				else
					rightRay = null;
			} else if (leftRay.getPoint().distance(ray.getPoint()) == rightRay
					.getPoint().distance(ray.getPoint())) {
				intersections.add(leftRay);
				if (leftIterator.hasNext())
					leftRay = leftIterator.next();
				else
					leftRay = null;
				if (rightIterator.hasNext())
					rightRay = rightIterator.next();
				else
					rightRay = null;
			} else {
				if (Vector.dotProduct(ray.getVector(), rightRay.getVector()) > 0)
					intersections.add(leftRay);
				if (leftIterator.hasNext())
					leftRay = leftIterator.next();
				else
					leftRay = null;
			}
		}
		if (intersections.size() == 0)
			return null;
		return intersections;
	}
}
