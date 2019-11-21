import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the key door object.
 * @author Noah Stebbings
 * @version 1.0
 */
public class KeyDoor extends Door {
	
	protected int doorColour;
	/**
	 * Creating a Key Door object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 * @param doorColour
	 */
	public KeyDoor(int x, int y, GraphicsContext gc, int TILE_SIZE, int doorColour) {
		super(x, y, gc, TILE_SIZE);
		this.doorColour = doorColour;
		if (doorColour == 1) {
			this.image = Color.ROSYBROWN; //Green Door
		} else if (doorColour == 2) {
			this.image = Color.SANDYBROWN; //Red Door
		} else if (doorColour == 3) {
			this.image = Color.SADDLEBROWN; //Blue Door
		}
		drawObject();
	}

}
