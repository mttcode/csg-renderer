package csg;

import java.util.Iterator;
import java.util.LinkedList;

import math.Ray;
import transformations.Transformation;

public class TransformationNode extends CSGTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8349047489333310844L;
	private Transformation transformation;
	private CSGTreeNode descendant;

	public TransformationNode(Transformation transformation,
			CSGTreeNode descendant) {
		this.transformation = transformation;
		this.descendant = descendant;
	}

	public Transformation getTransformation() {
		return this.transformation;
	}

	public CSGTreeNode getDescendant() {
		return this.descendant;
	}

	@Override
	public LinkedList<Ray> getIntersections(Ray ray) {
		LinkedList<Ray> intersections = new LinkedList<Ray>();
		Ray inverseTransformedRay =transformation.inverseTransform(ray);
		LinkedList<Ray> int2 = descendant
				.getIntersections(inverseTransformedRay);
		if (int2 == null)
			return null;
		Iterator<Ray> iterator = int2.iterator();
		while (iterator.hasNext())
			intersections.add(transformation.transform(iterator.next()));
		return intersections;
	}

}
