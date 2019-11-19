import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This is the superclass of all objects.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Object {
	
	//Storing the coordinates of the object.
	protected int x;
	protected int y;
	protected GraphicsContext gc;
	protected int TILE_SIZE;
	
	//TODO change from COLOR to image, which will be gotten from subclass
	private Color image;
	
	
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
	protected void drawObject(Color image) {
		this.image = image;
		gc.setFill(image);
		
		//Draw a square at the set coordinates
		gc.fillRect((x * TILE_SIZE), (y * TILE_SIZE), TILE_SIZE, TILE_SIZE);
	}

	/**
	 * A method that is allows the object to change its position.
	 * This method should be overwritten.
	 */
	public void move() {
		System.out.println("THIS SHOULD NOT BE CALLED.");
	}
}
