import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the token object.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Token extends Item {

	private ArrayList<Object> objectList;
	
	/**
	 * Creating a token object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Token(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = Color.GOLD;
		this.objectList = TrainCanvas.getObjects();
	}

	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		Sound.getSound("TokenPickUp");
		if (!pickedUp) {
			this.pickedUp = true;
			objectList.add(new Floor(this.x, this.y, gc, TILE_SIZE));
			TrainCanvas.removePickedUp();
			TrainCanvas.getPlayer().pickupItem(1);
		}
		
	}
}
