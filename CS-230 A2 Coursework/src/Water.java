import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
		this.image = new Image("file:Art/Water.png");
	}

	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		if (TrainCanvas.getPlayer().hasFlippers()) {
			this.isPlayerWalkable = true;
		}
	}
}
