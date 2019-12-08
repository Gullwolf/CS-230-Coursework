import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the key door object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class KeyDoor extends Door {

	protected int doorColour;

	private ArrayList<Object> objectList;


	/**
	 * The constructor for the Key Door object.
	 * @param x The x Position of the basic enemy on the map
	 * @param y The y Position of the basic enemy on the map
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile.
	 * @param doorColour The colour of the door.
	 */
	public KeyDoor(int x, int y, GraphicsContext gc, int TILE_SIZE, int doorColour) {
		super(x, y, gc, TILE_SIZE);
		this.doorColour = doorColour;
		this.objectList = TrainCanvas.getObjects();

		if ( doorColour == 1 ) {
			this.image = new Image("file:Art/GreenKeyDoor.png");
			this.tileType = "KeyDoor Green";
		} else if (doorColour == 2) {
			this.image = new Image("file:Art/RedKeyDoor.png");
			this.tileType = "KeyDoor Red";
		} else if (doorColour == 3) {
			this.image = new Image("file:Art/BlueKeyDoor.png");
			this.tileType = "KeyDoor Blue";
		}
	}

	/**
	 * This method makes the object look like a floor tile.
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		if (!pickedUp) {
			//If this is a red door that is not open and the player has a key.
			if ( !this.isPlayerWalkable && TrainCanvas.getPlayer().hasRedKey() && this.doorColour == 2 ) {
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