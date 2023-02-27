package abstractClass;

import java.io.Serializable;
import java.util.LinkedList;

import math.*;

public abstract class Intersectable implements Serializable {

	/**
	 * zisti ca ma objekt priesecnik s polpriamkou
	 * 
	 * @param polpriamka
	 * @return
	 */
	abstract public boolean intersect(Ray ray);

	/**
	 * vrati pole priesecnikov objektu s polpriamkou ak ziadne objekty
	 * neexistuju vrati null
	 * 
	 * @param polpriamka
	 * @return
	 */
	abstract public LinkedList<Ray> getIntersects(Ray ray);

	/**
	 * vrati pole normalovych vektorov v priesecnikoch s polpriamkou ak ziadne
	 * priesecniky neexistuju vrati null
	 * 
	 * @param polpriamka
	 * @return
	 */
	// abstract public LinkedList<Vector> getIntersectionVectors(Ray
	// polpriamka);
}
