import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the player object.
 * @author Noah Stebbings
 * @version 1.3
 */
public class Player extends Body {

	private ArrayList<Object> objectList;
	private ArrayList<Object> enemyList;
	
	private int tokens = 0;
	private boolean blueKey = false;
	private boolean redKey = false;
	private boolean greenKey = false;
	private boolean fireBoots = false;
	private boolean flippers = false;
	
	//Holding a record of the last key pressed by the player
	protected String lastKey;
	
	/**
	 * Creating a player object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Player(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = new Image("file:Art/PlayerRight.png");
		this.objectList = TrainCanvas.getObjects();
		this.enemyList = TrainCanvas.getEnemies();
	}

	/**
	 * A getter for if the player has fire boots
	 * @return fireBoots
	 */
	public boolean hasFireBoots() {
		return this.fireBoots;
	}
	
	/**
	 * A getter for if the player has flippers
	 * @return flippers
	 */
	public boolean hasFlippers() {
		return this.flippers;
	}
	
	/**
	 * A getter for the number of tokens the player has.
	 * @return tokens
	 */
	public int getTokens() {
		return this.tokens;
	}
	
	/**
	 * A getter for if the player has the red key.
	 * @return redKey
	 */
	public boolean hasRedKey() {
		return this.redKey;
	}
	
	/**
	 * A getter for if the player has the blue key.
	 * @return blueKey
	 */
	public boolean hasBlueKey() {
		return this.blueKey;
	}
	
	/**
	 * A getter for if the player has the green key.
	 * @return greenKey
	 */
	public boolean hasGreenKey() {
		return this.greenKey;
	}
	
	/**
	 * A method that takes in an item type integer and adds the item to the player.
	 * @param itemType
	 */
	public void pickupItem(int itemType) {//1 = token, 2 = red key, 3 = blue key, 4 = green key
		if (itemType == 1) { //Pickup token
			this.tokens++;
		} else if (itemType == 2) { //Pickup red key
			this.redKey = true;
		} else if (itemType == 3) { //Pickup blue key
			this.blueKey = true;
		} else if (itemType == 4) { //Pickup green key
			this.greenKey = true;
		} else if (itemType == 5) { //Pickup flippers
			this.flippers = true;
		} else if (itemType == 6) { //Pickup fire boots
			this.fireBoots = true;
		}
	}
	
	/**
	 * This method teleports the player to specified coordinates
	 * along the X axis.
	 * @param newX
	 */
	public void teleportPlayerX(int newX) {
		this.x = newX;
	}
	
	/**
	 * This method teleports the player to specified coordinates
	 * along the Y axis.
	 * @param newY
	 */
	public void teleportPlayerY(int newY) {
		this.y = newY;
	}
	
	/**
	 * A method that takes the key inputted by the user.
	 * @param inputKey
	 */
	public void takeInput(String inputKey) {
		lastKey = inputKey;
		if (inputKey.equals("UP") || inputKey.equals("W")) {
//			this.image = new Image("file:Art/PlayerUp.png");
			moveUp();
		}  else if (inputKey.equals("DOWN") || inputKey.equals("S")) {
//			this.image = new Image("file:Art/PlayerDown.png");
			moveDown();
		} else if (inputKey.equals("LEFT") || inputKey.equals("A")) {
			this.image = new Image("file:Art/PlayerLeft.png");
			moveLeft();
		} else if (inputKey.equals("RIGHT") || inputKey.equals("D")) {
			this.image = new Image("file:Art/PlayerRight.png");
			moveRight();
		}
	}

	/**
	 * A method that attempts to move the player Up.
	 */
	private void moveUp() {
		for (int i = 0; i < objectList.size(); i++) {
			//This if looks to see if the object is in the position 
			//the player is attempting to move int, and that the spot is walkable
			if ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y - 1 && objectList.get(i).isPlayerWalkable) {
				for (int j = 0; j < objectList.size(); j++) {
					objectList.get(j).goUp();
				}
				for (int n = 0; n < enemyList.size(); n++) {
					enemyList.get(n).goUp();
				}
				i = objectList.size(); //Exiting the loop
			} else if ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y - 1) {
				objectList.get(i).interact(); //Attempt to interact with the object
				//TODO add sound for failing to move into a spot.
			}
		}
	}

	/**
	 * A method that attempts to move the player Down.
	 */
	private void moveDown() {
		for (int i = 0; i < objectList.size(); i++) {
			//This if looks to see if the object is in the position 
			//the player is attempting to move int, and that the spot is walkable
			if ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y + 1 && objectList.get(i).isPlayerWalkable) {
				for (int j = 0; j < objectList.size(); j++) {
					objectList.get(j).goDown();
				}
				for (int n = 0; n < enemyList.size(); n++) {
					enemyList.get(n).goDown();
				}
				i = objectList.size(); //Exiting the loop
			} else if ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y + 1) {
				objectList.get(i).interact(); //Attempt to interact with the object
				//TODO add sound for failing to move into a spot.
			}
		}
	}
	
	/**
	 * A method that attempts to move the player Left.
	 */
	private void moveLeft() {
		for (int i = 0; i < objectList.size(); i++) {
			//This if looks to see if the object is in the position 
			//the player is attempting to move int, and that the spot is walkable
			if ((objectList.get(i).getX() == this.x - 1) && objectList.get(i).getY() == this.y && objectList.get(i).isPlayerWalkable) {
				for (int j = 0; j < objectList.size(); j++) {
					objectList.get(j).goLeft();
				}
				for (int n = 0; n < enemyList.size(); n++) {
					enemyList.get(n).goLeft();
				}
				i = objectList.size(); //Exiting the loop
			} else if ((objectList.get(i).getX() == this.x - 1) && objectList.get(i).getY() == this.y) {
				objectList.get(i).interact(); //Attempt to interact with the object
				//TODO add sound for failing to move into a spot.
			}
		}
	}
	
	/**
	 * A method that attempts to move the player Right.
	 */
	private void moveRight() {
		for (int i = 0; i < objectList.size(); i++) {
			//This if looks to see if the object is in the position 
			//the player is attempting to move int, and that the spot is walkable
			if ((objectList.get(i).getX() == this.x + 1) && objectList.get(i).getY() == this.y && objectList.get(i).isPlayerWalkable) {
				for (int j = 0; j < objectList.size(); j++) {
					objectList.get(j).goRight();
				}
				for (int n = 0; n < enemyList.size(); n++) {
					enemyList.get(n).goRight();
				}
				i = objectList.size(); //Exiting the loop
			} else if ((objectList.get(i).getX() == this.x + 1) && objectList.get(i).getY() == this.y) {
				objectList.get(i).interact(); //Attempt to interact with the object
				//TODO add sound for failing to move into a spot.
			}
		}
	}
}
