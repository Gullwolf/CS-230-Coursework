import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the Flippers object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class Flippers extends Item {

	private ArrayList<Object> objectList;

	/**
	 * The constructor for the Flippers object.
	 * @param x The x Position of the basic enemy on the map
	 * @param y The y Position of the basic enemy on the map
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile
	 */
	public Flippers(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.objectList = TrainCanvas.getObjects();
		this.image = new Image("file:Art/Flippers.png");
		this.tileType = "Flippers";
	}

	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		if (!pickedUp) {
			Sound.getSound("TokenPickUp");
			this.pickedUp = true;
			objectList.add(new Floor(this.x, this.y, gc, TILE_SIZE));
			TrainCanvas.getPlayer().pickupItem(5);		
		}
	}
}