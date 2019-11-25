import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the key door object.
 * @author Noah Stebbings
 * @version 1.1
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
	}
	
	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted
	 */
	@Override
	public void interact() {
		//If this is a red door that is not open and the player has a key.
		if (!this.isPlayerWalkable && TrainCanvas.getPlayer().hasRedKey() && this.doorColour == 2) {
			this.image = Color.LIGHTGREY;
			this.isPlayerWalkable = true;
		} else if (!this.isPlayerWalkable && TrainCanvas.getPlayer().hasBlueKey() && this.doorColour == 3) {
			//If this is a blue door that is not open and the player has a key.
			this.image = Color.LIGHTGREY;
			this.isPlayerWalkable = true;
		} else if (!this.isPlayerWalkable && TrainCanvas.getPlayer().hasGreenKey() && this.doorColour == 1) {
			//If this is a green door that is not open and the player has a key.
			this.image = Color.LIGHTGREY;
			this.isPlayerWalkable = true;
		}
		
	}

}
