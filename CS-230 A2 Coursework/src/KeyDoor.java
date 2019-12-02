import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the key door object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class KeyDoor extends Door {

	private ArrayList<Object> objectList;
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
		this.objectList = TrainCanvas.getObjects();

		if (doorColour == 1) {
			this.image = new Image("file:Art/GreenKeyDoor.png");
		} else if (doorColour == 2) {
			this.image = new Image("file:Art/RedKeyDoor.png");
		} else if (doorColour == 3) {
			this.image = new Image("file:Art/BlueKeyDoor.png");
		}
	}

	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted
	 */
	@Override
	public void interact() {
		if (!pickedUp) {
			//If this is a red door that is not open and the player has a key.
			if (!this.isPlayerWalkable && TrainCanvas.getPlayer().hasRedKey() && this.doorColour == 2) {
				objectList.add(new Floor(this.x, this.y, gc, TILE_SIZE));
				Sound.getSound("DoorOpen");
				this.pickedUp = true;
				this.isPlayerWalkable = true;
			} else if (!this.isPlayerWalkable && TrainCanvas.getPlayer().hasBlueKey() && this.doorColour == 3) {
				//If this is a blue door that is not open and the player has a key.
				objectList.add(new Floor(this.x, this.y, gc, TILE_SIZE));
				Sound.getSound("DoorOpen");
				this.pickedUp = true;
				this.isPlayerWalkable = true;
			} else if (!this.isPlayerWalkable && TrainCanvas.getPlayer().hasGreenKey() && this.doorColour == 1) {
				//If this is a green door that is not open and the player has a key.
				objectList.add(new Floor(this.x, this.y, gc, TILE_SIZE));
				Sound.getSound("DoorOpen");
				this.pickedUp = true;
				this.isPlayerWalkable = true;
			}
		}
	}

}
