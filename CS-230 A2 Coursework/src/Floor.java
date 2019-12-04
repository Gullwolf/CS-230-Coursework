import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the floor object.
 * @author Noah Stebbings
 * @version 1.3
 */
public class Floor extends Terrain {
	/**
	 * Creating a floor object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Floor(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		isPlayerWalkable = true;
		isEnemyWalkable = true;
		this.image = new Image("file:Art/Floor.png");
		this.tileType = "Floor";
	}

}
