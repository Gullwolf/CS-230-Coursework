import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the Flippers object.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Flippers extends Item {

	/**
	 * Creating a Flippers object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Flippers(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = Color.NAVY;
	}

	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		this.image = Color.LIGHTGREY;
		TrainCanvas.getPlayer().pickupItem(5);		
	}
}
