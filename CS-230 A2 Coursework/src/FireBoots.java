import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the fire boots object.
 * @author Noah Stebbings
 * @version 1.1
 */
public class FireBoots extends Item {

	/**
	 * Creating a fire boots object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public FireBoots(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = Color.MAROON;
	}
	
	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		this.image = Color.LIGHTGREY;
		TrainCanvas.getPlayer().pickupItem(6);		
	}

}
