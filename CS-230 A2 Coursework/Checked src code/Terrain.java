import javafx.scene.canvas.GraphicsContext;

/**
 * A general class for all terain objects.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Terrain extends Object {

	/**
	 * The constructor for the Terrain class.
	 * @param x The x Position of the Terrain on the map
	 * @param y The y Position of the Terrain on the map
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile
	 */
	public Terrain(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		isPlayerWalkable = false;
		isEnemyWalkable = false;
	}
	
	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted with.
	 */
	@Override
	public void interact() {
		//Does nothing, just here to override the method.
	}
}