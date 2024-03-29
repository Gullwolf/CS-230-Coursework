import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the key door object.
 * @author Noah Stebbings
 * @version 1.3
 */
public class TokenDoor extends Door {

	protected int requirements;
	private ArrayList<Object> objectList;

	/**
	 * Creating a Key Door object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 * @param doorColour
	 */
	public TokenDoor(int x, int y, GraphicsContext gc, int TILE_SIZE, int requirements) {
		super(x, y, gc, TILE_SIZE);
		this.requirements = requirements;
		this.objectList = TrainCanvas.getObjects();
		this.image = new Image("file:Art/TokenDoor.png");
		this.tileType = "TokenDoor " + this.requirements;
	}

	/**
	 * This returns the number of tokens the door requires to opens.
	 * @return int
	 */
	@Override
	public int getRequirments() {
		return this.requirements;
	}
	
	
	/**
	 * This method makes the object look like a floor tile 
	 * when it has been interacted.
	 */
	@Override
	public void interact() {
		if (!pickedUp) {
			if (TrainCanvas.getPlayer().getTokens() >= this.requirements) {
				objectList.add(new Floor(this.x, this.y, gc, TILE_SIZE));
				Sound.getSound("DoorOpen");
				this.pickedUp = true;
				this.isPlayerWalkable = true;
			}
		}
	}
}