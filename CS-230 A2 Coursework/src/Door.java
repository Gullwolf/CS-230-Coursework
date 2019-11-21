import javafx.scene.canvas.GraphicsContext;

/**
 * A general class for all doors.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Door extends Object {

	/**
	 * The constructor for the door class.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Door(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		isPlayerWalkable = true;
		isEnemyWalkable = false;
	}
}
