import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the Water object.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Water extends Terrain {
	/**
	 * Creating a Water object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Water(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = Color.BLUE;
		drawObject();
	}

}
