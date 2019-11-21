import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the fire boots object.
 * @author Noah Stebbings
 * @version 1.0
 */
public class FireBoots extends Item {

	/**
	 * Creating a fire boots object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 * @param keyColour
	 */
	public FireBoots(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = Color.MAROON;
	}

}
