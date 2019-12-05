import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the fire object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class Fire extends Terrain {
	/**
	 * Creating a fire object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Fire(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = new Image("file:Art/Fire.png");
		this.tileType = "Fire";
	}
	
	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		if (TrainCanvas.getPlayer().hasFireBoots()) {
			this.isPlayerWalkable = true;
		}
	}
}
