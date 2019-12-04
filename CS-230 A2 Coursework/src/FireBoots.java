import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the fire boots object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class FireBoots extends Item {

	private ArrayList<Object> objectList;

	/**
	 * Creating a fire boots object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public FireBoots(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.objectList = TrainCanvas.getObjects();
		this.image = new Image("file:Art/FireBoots.png");
		this.tileType = "FireBoots";
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
			TrainCanvas.getPlayer().pickupItem(6);		
		}
	}
}
