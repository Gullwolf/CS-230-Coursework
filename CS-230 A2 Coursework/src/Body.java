import javafx.scene.canvas.GraphicsContext;

/**
 * A general class for enemies and the player.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Body extends Object {

	/**
	 * The constructor for the body class.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Body(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
	}
	
	/**
	 * A method that allows the body to move position.
	 * This method should be overwritten.
	 */
	@Override
	public void move() {
		System.out.println("THIS NEEDS TO BE OVERWRITTEN");
	}

}
