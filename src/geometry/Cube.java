package geometry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import math.Point;
import math.Ray;
import abstractClass.Intersectable;

public class Cube extends Intersectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 454962234485207577L;
	private ArrayList<Triangle> triangles;

	// defined by eight
	// points:(0,0,0),(0,0,1),(0,1,0),(1,0,0),(1,1,0),(1,0,1),(0,1,1),(1,1,1)
	// consist of 12 triangles
	public Cube() {
		triangles = new ArrayList<Triangle>();
		triangles.add(new Triangle(new Point(0, 0, 0), new Point(1, 0, 0),
				new Point(0, 0, 1)));
		triangles.add(new Triangle(new Point(1, 0, 0), new Point(1, 0, 1),
				new Point(0, 0, 1)));
		triangles.add(new Triangle(new Point(0, 0, 0), new Point(1, 1, 0),
				new Point(1, 0, 0)));
		triangles.add(new Triangle(new Point(0, 0, 0), new Point(0, 1, 0),
				new Point(1, 1, 0)));
		triangles.add(new Triangle(new Point(0, 0, 0), new Point(0, 0, 1),
				new Point(0, 1, 1)));
		triangles.add(new Triangle(new Point(0, 0, 0), new Point(0, 1, 1),
				new Point(0, 1, 0)));
		triangles.add(new Triangle(new Point(0, 0, 1), new Point(1, 0, 1),
				new Point(1, 1, 1)));
		triangles.add(new Triangle(new Point(0, 0, 1), new Point(1, 1, 1),
				new Point(0, 1, 1)));
		triangles.add(new Triangle(new Point(1, 0, 1), new Point(1, 0, 0),
				new Point(1, 1, 1)));
		triangles.add(new Triangle(new Point(1, 0, 0), new Point(1, 1, 0),
				new Point(1, 1, 1)));
		triangles.add(new Triangle(new Point(0, 1, 0), new Point(1, 1, 1),
				new Point(1, 1, 0)));
		triangles.add(new Triangle(new Point(0, 1, 0), new Point(0, 1, 1),
				new Point(1, 1, 1)));

	}

	@Override
	public LinkedList<Ray> getIntersects(Ray ray) {
		if (!this.intersect(ray))
			return null;
		LinkedList<Ray> intersections = new LinkedList<Ray>();
		Iterator<Triangle> iterator = triangles.iterator();
		while (iterator.hasNext()) {
			Triangle triangle = iterator.next();
			if (triangle.intersect(ray))
				intersections.add(triangle.getIntersects(ray).getFirst()); // can
			// return
			// only
			// one
		}
		/*for (int i = 0; i < intersections.size(); i++) {
			for (int j = i + 1; j < intersections.size(); j++) {
				if (!intersections.get(i).getPoint().different(
						intersections.get(j).getPoint())) {
					intersections.remove(j); // dokoncit - overit
				}
			}
		}*/
		/*
		 * Iterator<Ray> iterator2 = intersections.iterator();
		 * while(iterator.hasNext()){ Ray pom = iterator2.next(); if
		 * (this.getIntersects(pom).size()>1) pom.getVector().opacny(); }
		 */

		/*if (intersections != null
				&& intersections.get(0).getPoint().distance(ray.getPoint()) > intersections
						.get(1).getPoint().distance(ray.getPoint())) {
			Ray pom = intersections.get(0);
			intersections.set(0, intersections.get(1));
			intersections.set(1, pom);
		}*/
		if (intersections.size() == 0) return null;
		Iterator<Ray> intersectionsIterator = intersections.iterator();
		Ray pom1 = intersectionsIterator.next();
		Ray pom2;
		boolean complete = false;
		while (intersectionsIterator.hasNext() && !complete){
			pom2 = intersectionsIterator.next();
			if(pom2.getPoint().different(pom1.getPoint())){
				intersections = new LinkedList<Ray>();
				complete = true;
				if(pom1.getPoint().distance(ray.getPoint())> pom2.getPoint().distance(ray.getPoint())){	
					intersections.add(pom2);
					intersections.add(pom1);
				} else {
					intersections.add(pom1);
					intersections.add(pom2);
				}
			}
		}
		return intersections;
	}

	@Override
	public boolean intersect(Ray ray) {
		Iterator<Triangle> iterator = triangles.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().intersect(ray))
				return true;
		}
		return false;
	}

}
