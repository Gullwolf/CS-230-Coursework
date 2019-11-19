import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the goal object.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Goal extends Useable {
	/**
	 * Creating a goal object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Goal(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		drawObject(Color.GOLD);
	}

}
