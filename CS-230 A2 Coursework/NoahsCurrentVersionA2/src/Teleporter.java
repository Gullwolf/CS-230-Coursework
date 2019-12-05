import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the Teleporter object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class Teleporter extends Useable {
	
	private ArrayList<Object> objectList;

	/**
	 * Creating a Teleporter object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Teleporter(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = new Image("file:Art/Teleporter.png");
		this.objectList = TrainCanvas.getObjects();
		this.isPlayerWalkable = false;
		this.isTeleporter = true;
		this.tileType = "Teleporter";
	}
	
	/**
	 * This method teleports the player
	 * when it has been interacted with.
	 */
	@Override
	public void interact() {
		Sound.getSound("Portal");
		String lastKey = TrainCanvas.getPlayer().lastKey;
		if (lastKey.equals("A") || lastKey.equals("LEFT")) {
			for (int i = 0; i < objectList.size(); i++) {
				//If the object is on the same y axis, is a teleporter and is not this object.
				if ((objectList.get(i).getY() == this.y) && (objectList.get(i).isTeleporter) && (objectList.get(i) != this)) {
					//Teleport the player one space left of the found teleporter
					TrainCanvas.getPlayer().teleportPlayerX(objectList.get(i).getX() - 1);
				}
			}
		} else if (lastKey.equals("D") || lastKey.equals("RIGHT")) {
			for (int i = 0; i < objectList.size(); i++) {
				//If the object is on the same y axis, is a teleporter and is not this object.
				if ((objectList.get(i).getY() == this.y) && (objectList.get(i).isTeleporter) && (objectList.get(i) != this)) {
					//Teleport the player one space right of the found teleporter
					TrainCanvas.getPlayer().teleportPlayerX(objectList.get(i).getX());
				}
			}
		} else if (lastKey.equals("W") || lastKey.equals("UP")) {
			for (int i = 0; i < objectList.size(); i++) {
				//If the object is on the same x axis, is a teleporter and is not this object.
				if (objectList.get(i).getX() == this.x && objectList.get(i).isTeleporter && objectList.get(i) != this) {
					//Teleport the player one space above of the found teleporter
					TrainCanvas.getPlayer().teleportPlayerX(objectList.get(i).getY() - 1);
				}
			}
		} else if (lastKey.equals("S") || lastKey.equals("DOWN")) {
			for (int i = 0; i < objectList.size(); i++) {
				//If the object is on the same x axis, is a teleporter and is not this object.
				if (objectList.get(i).getX() == this.x && objectList.get(i).isTeleporter && objectList.get(i) != this) {
					//Teleport the player one space below of the found teleporter
					TrainCanvas.getPlayer().teleportPlayerX(objectList.get(i).getY());
				}
			}
		}
		TrainCanvas.centerPlayer();
	}
}
