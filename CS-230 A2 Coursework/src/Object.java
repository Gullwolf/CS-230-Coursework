import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This is the superclass of all objects.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Object {
	
	//Storing the coordinates of the object.
	protected int x;
	protected int y;
	protected GraphicsContext gc;
	protected int TILE_SIZE;
	
	protected boolean isPlayerWalkable;
	protected boolean isEnemyWalkable;
	
	//TODO change from COLOR to image, which will be gotten from subclass
	protected Color image;
	
	
	public Object(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.TILE_SIZE = TILE_SIZE;
	}
	
	/**
	 * This method draws the wall object on the canvas. 
	 * @param image
	 */
	protected void drawObject() {
		gc.setFill(image);
		
		//Draw a square at the set coordinates
		gc.fillRect((x * TILE_SIZE), (y * TILE_SIZE), TILE_SIZE, TILE_SIZE);
	}
	
	/**
	 * This method returns the x position of the object.
	 * @return x
	 */
	protected int getX() {
		return this.x;
	}
	
	/**
	 * This method returns the y position of the object.
	 * @return y
	 */
	protected int getY() {
		return this.y;
	}

	/**
	 * A method that is overwritten in its subclasses.
	 */
	public void move() {
		System.out.println("THIS SHOULD BE OVERWRITTEN");
	}
}
