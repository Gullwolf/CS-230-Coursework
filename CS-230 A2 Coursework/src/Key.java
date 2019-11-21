import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the key object.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Key extends Door {
	
	protected int keyColour;
	/**
	 * Creating a Key object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 * @param keyColour
	 */
	public Key(int x, int y, GraphicsContext gc, int TILE_SIZE, int keyColour) {
		super(x, y, gc, TILE_SIZE);
		this.keyColour = keyColour;
		if (keyColour == 1) {
			this.image = Color.ROSYBROWN; //Green key
		} else if (keyColour == 2) {
			this.image = Color.SANDYBROWN; //Red key
		} else if (keyColour == 3) {
			this.image = Color.SADDLEBROWN; //Blue key
		}
		drawObject();
	}

}
