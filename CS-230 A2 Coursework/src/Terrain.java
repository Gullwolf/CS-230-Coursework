import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A general class for all terain objects.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Terrain extends Object {

	/**
	 * The constructor for the terrain class.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
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

