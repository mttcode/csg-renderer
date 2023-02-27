package system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import camera.Camera;

import abstractClass.Intersectable;

/**
 * object in which are all objects<Intersectable> and a camera encapsulated.
 * @author chlado
 *
 */
public class Scene implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5080398523568773961L;
	private ArrayList<Intersectable> objects;
	private Camera camera;

	public Scene() {
		this.objects = new ArrayList<Intersectable>();
	}

	public void addObject(Intersectable object) {
		this.objects.add(object);
	}

	public void addCamera(Camera camera) {
		this.camera = camera;
	}

	public Camera getCamera() {
		return this.camera;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	/**
	 * Returns an iterator to iterate all object<Intersectable> in the scene.
	 * @return
	 */
	public Iterator<Intersectable> getObjectIterator() {
		return this.objects.iterator();
	}
	
	/**
	 * Returns an object with specified index.
	 * @param index Index of an object.
	 * @return Object with specified index.
	 */
	public Intersectable getObject(int index) {
		if (index >= 0 && index < this.objects.size())
			return this.objects.get(index);
		else
			return null;
	}
}
