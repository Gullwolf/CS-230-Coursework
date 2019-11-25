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
		isPlayerWalkable = true;
		isEnemyWalkable = true;
	}
	
	/**
	 * A method that is overwritten in its subclasses.
	 */
	public void move() {
		System.out.println("THIS SHOULD BE OVERWRITTEN");
	}
	
	/**
	 * A method that is overwritten in its subclasses.
	 */
	@Override
	public void interact() {	}
}
