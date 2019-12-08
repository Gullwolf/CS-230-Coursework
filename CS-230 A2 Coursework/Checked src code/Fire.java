import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the fire object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class Fire extends Terrain {
	/**
	 * The constructor for the Fire Object.
	 * @param x The x Position of the Fire on the map
	 * @param y The y Position of the Fire on the map
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile
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