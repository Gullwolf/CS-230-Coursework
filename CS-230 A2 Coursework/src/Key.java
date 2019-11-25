import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the key object.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Key extends Item {
	
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
	}
	
	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		this.image = Color.LIGHTGREY;
		if (keyColour == 1) {
			TrainCanvas.getPlayer().pickupItem(4);//Green
		} else if (keyColour == 2) {
			TrainCanvas.getPlayer().pickupItem(2);//Red
		} else if (keyColour == 3) {
			TrainCanvas.getPlayer().pickupItem(3);//Blue
		}		
	}

}
