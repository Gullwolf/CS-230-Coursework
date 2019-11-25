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
	private Object linkedTeleporter;
	
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
	}
	
	/**
	 * This method fixes the coordinates of the linked teleporter.
	 */
	@Override
	public void fixLinks() {
		this.linkX = this.linkX - TrainCanvas.getPlayer().getX() + TrainCanvas.getCenter();
		this.linkY = this.linkY - TrainCanvas.getPlayer().getY() + TrainCanvas.getCenter();
	}
	
	/**
	 * This method teleports the player
	 * when it has been interacted with.
	 */
	@Override
	public void interact() {
		if (TrainCanvas.getPlayer().moveXM) {
			TrainCanvas.getPlayer().teleportPlayerX(linkX - 1);
		} else if (TrainCanvas.getPlayer().moveXP) {
			TrainCanvas.getPlayer().teleportPlayerX(linkX + 1);
		} else if (TrainCanvas.getPlayer().moveYM) {
			TrainCanvas.getPlayer().teleportPlayerY(linkY - 1);
		} else if (TrainCanvas.getPlayer().moveYP) {
			TrainCanvas.getPlayer().teleportPlayerX(linkY + 1);
		}
		
		TrainCanvas.centerPlayer();
	}
}
