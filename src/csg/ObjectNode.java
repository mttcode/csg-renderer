package csg;

import java.util.LinkedList;

import math.Ray;
import abstractClass.Intersectable;

public class ObjectNode extends CSGTreeNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4015548113106745261L;
	private Intersectable object;
	
	public ObjectNode(Intersectable object){
		this.object = object;
	}

	@Override
	public LinkedList<Ray> getIntersections(Ray ray) {
		return object.getIntersects(ray);
	}
}
