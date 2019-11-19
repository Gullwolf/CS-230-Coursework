import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the fire object.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Fire extends Terrain {
	/**
	 * Creating a fire object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Fire(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		drawObject(Color.ORANGE);
	}

}
