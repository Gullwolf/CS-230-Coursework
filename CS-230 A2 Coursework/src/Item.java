import javafx.scene.canvas.GraphicsContext;

/**
 * A general class for all items.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Item extends Object {

	protected boolean pickedUp = false;
	
	/**
	 * The constructor for the item class.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Item(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		isPlayerWalkable = true;
		isEnemyWalkable = false;
	}
	
	/**
	 * This method is empty as it is overwritten in its subclass.
	 */
	@Override
	public void interact() { }
}