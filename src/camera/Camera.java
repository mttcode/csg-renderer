package camera;

import java.io.Serializable;

import math.Ray;

/**
 * Represents a mapping from the 3D scene onto the final image. A camera is
 * responsible for determining what ray to cast through each pixel.
 */
public abstract class Camera implements Serializable{

	private int imageHeight;
	private int imageWidth;

	/**
	 * Default constructor, sets the default resolution of the image plane the camera
	 * will project to.
	 * 
	 * @param imageWidth
	 * @param imageHeight
	 */
	public Camera(int imageHeight, int imageWidth) {
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
	}

	/**
	 * Gets the height of the image plane associated to this camera.
	 * 
	 * @return the height of the image plane
	 */
	public int getImageHeight() {
		return this.imageHeight;
	}

	/**
	 * Gets the width of the image plane associated to this camera.
	 * 
	 * @return the width of the image plane
	 */
	public int getImageWidth() {
		return this.imageWidth;
	}
	
	/**
	 * Sets resolution of the camera.
	 * @param imageHeight Height of an image which will be rendered.
	 * @param imageWidth Width of an image which will be rendered.
	 */
	public void setResolution(int imageHeight, int imageWidth){
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
	}

	/**
	 * Creates a new Ray to be cast through image pixel with coordinates [x,y]
	 * 
	 * @param x
	 *            x coordinates of pixel
	 * @param y
	 *            y coordinates of pixel
	 * @return Ray passing though the given pixel
	 */
	public abstract Ray getRay(int x, int y);

}
