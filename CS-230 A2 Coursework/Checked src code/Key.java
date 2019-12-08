import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the key object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class Key extends Item {

	protected int keyColour;

	private ArrayList<Object> objectList;

	/**
	 * The constructor for the Key object.
	 * @param x The x Position of the basic enemy on the map
	 * @param y The y Position of the basic enemy on the map
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile.
	 * @param keyColour The colour of the key and the corresponding door that it will open.
	 */
	public Key(int x, int y, GraphicsContext gc, int TILE_SIZE, int keyColour) {
		super(x, y, gc, TILE_SIZE);
		this.keyColour = keyColour;
		this.objectList = TrainCanvas.getObjects();

		if (keyColour == 1) {
			this.image = new Image("file:Art/GreenKey.png");
			this.tileType = "Key Green";
		} else if (keyColour == 2) {
			this.image = new Image("file:Art/RedKey.png");
			this.tileType = "Key Red";
		} else if (keyColour == 3) {
			this.image = new Image("file:Art/BlueKey.png");
			this.tileType = "Key Blue";
		}
	}

	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		if (!pickedUp) {
			Sound.getSound("KeyPickUp");
			objectList.add(new Floor(this.x, this.y, gc, TILE_SIZE));
			this.pickedUp = true;
			if (keyColour == 1) {
				TrainCanvas.getPlayer().pickupItem(4); //Green
			} else if (keyColour == 2) {
				TrainCanvas.getPlayer().pickupItem(2); //Red
			} else if (keyColour == 3) {
				TrainCanvas.getPlayer().pickupItem(3); //Blue
			}		
		}
	}

}