import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the key door object.
 * @author Noah Stebbings
 * @version 1.1
 */
public class TokenDoor extends Door {
	
	protected int requirements;
	
	/**
	 * Creating a Key Door object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 * @param doorColour
	 */
	public TokenDoor(int x, int y, GraphicsContext gc, int TILE_SIZE, int requirements) {
		super(x, y, gc, TILE_SIZE);
		this.requirements = requirements;
		this.image = Color.BROWN;
	}

	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		if (TrainCanvas.getPlayer().getTokens() >= this.requirements) {
			this.image = Color.LIGHTGREY;
			this.isPlayerWalkable = true;
		}
	}
}
