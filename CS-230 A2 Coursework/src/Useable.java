import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A general class for player interactable tiles.
 * @author Noah Stebbings
 * @version 1.0
 *
 */
public class Useable extends Object {

	/**
	 * The constructor for this class.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Useable(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		isPlayerWalkable = true;
		isEnemyWalkable = false;
	}
	
	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted with.
	 */
	@Override
	public void interact() {
		System.out.println("interacted!");
		this.image = Color.LIGHTGREY;
	}
}
