import javafx.scene.canvas.GraphicsContext;

/**
 * A general class for all doors.
 * @author Noah Stebbings
 * @version 1.2
 */
public class Door extends Object {

	/**
	 * The constructor for the door class.
	 * @param x The starting x position of the door
	 * @param y The starting y position of the door
	 * @param gc
	 * @param TILE_SIZE The tile size of the body, this is a final
	 */
	public Door(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		isPlayerWalkable = false;
		isEnemyWalkable = false;
	}
	
	/**
	 * A blank method that is overwritten by subclasses.
	 */
	public void interact() { }

	/**
	 * This returns the requirements for the tokendoor.
	 * @return int
	 */
	public int getRequirments() {
		return 0;
	}
	
}