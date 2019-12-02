import javafx.scene.canvas.GraphicsContext;

/**
 * A general class for player interactable tiles.
 * @author Noah Stebbings
 * @version 1.0
 *
 */
public class Useable extends Object {

	/**
	 * The constructor for this class.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Useable(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		isPlayerWalkable = true;
		isEnemyWalkable = false;
	}
	
	/**
	 * This method is overwritten in its subclasses.
	 */
	@Override
	public void interact() {

	}
}
