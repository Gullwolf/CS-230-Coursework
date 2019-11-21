import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the Teleporter object.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Teleporter extends Useable {
	/**
	 * Creating a Teleporter object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Teleporter(int x, int y, GraphicsContext gc, int TILE_SIZE, int linkX, int linkY) {
		super(x, y, gc, TILE_SIZE);
		this.image = Color.CYAN;
		drawObject();
	}

}
