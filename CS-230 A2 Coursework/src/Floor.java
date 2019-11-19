import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the floor object.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Floor extends Terrain {
	/**
	 * Creating a floor object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Floor(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		drawObject(Color.LIGHTGREY);
	}

}
