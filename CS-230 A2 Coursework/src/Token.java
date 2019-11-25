import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the token object.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Token extends Item {

	/**
	 * Creating a token object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Token(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = Color.GOLD;
	}

	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		this.image = Color.LIGHTGREY;
		TrainCanvas.getPlayer().pickupItem(1);		
	}
}
