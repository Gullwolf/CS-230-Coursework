import javafx.scene.canvas.GraphicsContext;

/**
 * A general class for all items.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Item extends Object {

	protected boolean pickedUp = false;

	/**
	 * The constructor for the Item Class.
	 * @param x The x Position on the map
	 * @param y The y Position on the map
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile
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