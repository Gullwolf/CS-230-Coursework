import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the player object.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Player extends Body {
	
	private ArrayList<Object> objectList;
	/**
	 * Creating a player object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Player(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = Color.MAGENTA;
		this.objectList = TrainCanvas.getObjects();
		drawObject();
	}
	
	/**
	 * A method that takes the key inputted by the user.
	 * @param inputKey
	 */
	public void takeInput(String inputKey) {
		if (inputKey.equals("UP") || inputKey.equals("W")) {
			moveUp();
		}  else if (inputKey.equals("DOWN") || inputKey.equals("S")) {
			moveDown();
		} else if (inputKey.equals("LEFT") || inputKey.equals("A")) {
			moveLeft();
		} else if (inputKey.equals("RIGHT") || inputKey.equals("D")) {
			moveRight();
		}
	}
	
	/**
	 * A method that attempts to move the player Up.
	 */
	private void moveUp() {
		for (int i = 0; i < objectList.size(); i++) {
			//This if looks to see if the object is in the position 
			//the player is attempting to move into.
			if ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y - 1) {
				//If the spot is walkable, move there.
				if(objectList.get(i).isPlayerWalkable) {
					this.y--;
					//Without this return, the player would move as far 
					//in that direction as possible
					return;
				} else {
					//TODO add sound for failing to move into a spot.
				}
			}
		}
	}
	
	/**
	 * A method that attempts to move the player Down.
	 */
	private void moveDown() {
		for (int i = 0; i < objectList.size(); i++) {
			//This if looks to see if the object is in the position 
			//the player is attempting to move into.
			if ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y + 1) {
				//If the spot is walkable, move there.
				if(objectList.get(i).isPlayerWalkable) {
					this.y++;
					//Without this return, the player would move as far 
					//in that direction as possible
					return;
				} else {
					//TODO add sound for failing to move into a spot.
				}
			}
		}
	}
	
	/**
	 * A method that attempts to move the player Left.
	 */
	private void moveLeft() {
		for (int i = 0; i < objectList.size(); i++) {
			//This if looks to see if the object is in the position 
			//the player is attempting to move into.
			if ((objectList.get(i).getX() == this.x - 1) && objectList.get(i).getY() == this.y) {
				//If the spot is walkable, move there.
				if(objectList.get(i).isPlayerWalkable) {
					this.x--;
					//Without this return, the player would move as far 
					//in that direction as possible
					return; 
				} else {
					//TODO add sound for failing to move into a spot.
				}
			}
		}
	}
	
	/**
	 * A method that attempts to move the player Right.
	 */
	private void moveRight() {
		for (int i = 0; i < objectList.size(); i++) {
			//This if looks to see if the object is in the position 
			//the player is attempting to move into.
			if ((objectList.get(i).getX() == this.x + 1) && objectList.get(i).getY() == this.y) {
				//If the spot is walkable, move there.
				if(objectList.get(i).isPlayerWalkable) {
					this.x++;
					//Without this return, the player would move as far 
					//in that direction as possible
					return;
				} else {
					//TODO add sound for failing to move into a spot.
				}
			}
		}
	}
}
