import javafx.scene.canvas.GraphicsContext;

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
	}

}
