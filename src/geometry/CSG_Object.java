package geometry;

import java.util.LinkedList;

import csg.CSGTreeNode;

import math.Ray;
import abstractClass.Intersectable;

public class CSG_Object extends Intersectable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 743461698345224905L;
	private CSGTreeNode CSGTree;
	
	public CSG_Object(CSGTreeNode node){
		this.CSGTree = node;
	}
	
	@Override
	public LinkedList<Ray> getIntersects(Ray ray) {
		return CSGTree.getIntersections(ray);
	}

	@Override
	public boolean intersect(Ray ray) {
		// TODO Auto-generated method stub
		return false;
	}

}
