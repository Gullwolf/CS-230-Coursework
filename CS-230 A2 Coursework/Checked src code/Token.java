import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the token object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class Token extends Item {

	private ArrayList<Object> objectList;

	/**
	 * The constructor for the Token object.
	 * @param x The x Position of the Token on the map
	 * @param y The y Position of the Token on the map
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile
	 */
	public Token(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = new Image("file:Art/Token.png");
		this.objectList = TrainCanvas.getObjects();
		this.tileType = "Token";
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
			TrainCanvas.getPlayer().pickupItem(1);
		}
	}
}