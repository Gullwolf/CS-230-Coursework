import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the Flippers object.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Flippers extends Item {

	/**
	 * Creating a Flippers object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 * @param keyColour
	 */
	public Flippers(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = Color.NAVY;
	}

}
