import javafx.scene.canvas.GraphicsContext;

/**
 * A general class for enemies and the player.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Body extends Object {

	/**
	 * The constructor for the body class.
	 * @param x The starting x position for the body
	 * @param y The starting y position for the body
	 * @param gc
	 * @param TILE_SIZE The tile size of the body, this is a final
	 */
	public Body(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		isPlayerWalkable = true;
		isEnemyWalkable = true;
	}
	
	/**
	 * A method that is overwritten in its subclasses.
	 */
	@Override
	public void move() {
		System.out.println("THIS SHOULD BE OVERWRITTEN move body");
	}
	
	/**
	 * A method that is overwritten in its subclasses.
	 */
	@Override
	public void interact() { }
}