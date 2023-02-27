package csg;

import java.io.Serializable;
import java.util.LinkedList;

import math.Ray;

public abstract class CSGTreeNode implements Serializable{
	public abstract LinkedList<Ray> getIntersections(Ray ray);

}
