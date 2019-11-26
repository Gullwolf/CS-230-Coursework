import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the Teleporter object.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Teleporter extends Useable {
	
	private ArrayList<Object> objectList;
	public boolean isTeleporter = true;
	
	private int linkX;
	private int linkY;
	
	/**
	 * Creating a Teleporter object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 * @param linkX
	 * @param linkY
	 */
	public Teleporter(int x, int y, GraphicsContext gc, int TILE_SIZE, int linkX, int linkY) {
		super(x, y, gc, TILE_SIZE);
		this.linkX = linkX;
		this.linkY = linkY;
		this.image = Color.CYAN;
		this.objectList = TrainCanvas.getObjects();
		this.isPlayerWalkable = false;
		fixLinks();
	}
	
	/**
	 * This method fixes the coordinates of the linked teleporter.
	 */
	@Override
	public void fixLinks() {
//		this.linkX = this.linkX - TrainCanvas.getPlayer().getX() + TrainCanvas.getCenter();
//		this.linkY = this.linkY - TrainCanvas.getPlayer().getY() + TrainCanvas.getCenter();
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.get(i).isTeleporter && objectList.get(i) != this) {
				this.linkX = objectList.get(i).getX();
				this.linkY = objectList.get(i).getY();
			}
		}
	}
	
	/**
	 * This method teleports the player
	 * when it has been interacted with.
	 */
	@Override
	public void interact() {
		String lastKey = TrainCanvas.getPlayer().lastKey;
		if (lastKey.equals("A") || lastKey.equals("LEFT")) {
			TrainCanvas.getPlayer().teleportPlayerX(linkX - 1);
		} else if (lastKey.equals("D") || lastKey.equals("RIGHT")) {
			TrainCanvas.getPlayer().teleportPlayerX(linkX + 1);
		} else if (lastKey.equals("W") || lastKey.equals("UP")) {
			TrainCanvas.getPlayer().teleportPlayerY(linkY - 1);
		} else if (lastKey.equals("S") || lastKey.equals("DOWN")) {
			TrainCanvas.getPlayer().teleportPlayerX(linkY + 1);
		}
		TrainCanvas.centerPlayer();
	}
}
