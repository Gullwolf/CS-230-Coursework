import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Making the Wall Hug Enemy Object.
 * @author Noah Stebbings, Mohammed Raihan
 * @version 1.0
 */

public class WallHugEnemy extends Body {

	private int direction = 1;

	private boolean hasMoved = false;

	private ArrayList<Object> objectList;

	/**
	 * The constructor for the Wall Hug Enemy class.
	 * @param x The x Position of the Wall Hug enemy on the map.
	 * @param y The y Position of the Wall Hug enemy on the map.
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile
	 */
	public WallHugEnemy(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		//Getting the list of objects in the map
		this.objectList = TrainCanvas.getObjects();
		this.image = new Image("file:Art/Enemy2Right.png");
		this.tileType = "Enemy 2";
	}

	/**
	 * This moves the enemy around the making sure that it follows the walls.
	 */
	public void move() {
		hasMoved = false;
		while (!hasMoved) {
			if (canMoveUp() && direction == 1) {
				this.y--;
				hasMoved = true;
			} else if (canMoveLeft() && direction == 2) {
				this.x--;
				hasMoved = true;
				this.image = new Image("file:Art/Enemy2Left.png");
			} else if (canMoveDown() && direction == 3) {
				this.y++;
				hasMoved = true;
			} else if (canMoveRight() && direction == 4) {
				this.x++;
				hasMoved = true;
				this.image = new Image("file:Art/Enemy2Right.png");
			}
		}
		this.interact();
	}
	
	/**
	 * This method checks if the wall hug enemy can move to the tile above.
	 * @return boolean
	 */
	public boolean canMoveUp() {
		for (int i = 0; i < objectList.size(); i++) {
			Object currentObj = objectList.get(i);
			//If the space above has an impassable tile to the left or right
			if (currentObj.getX() == this.x + 1 || currentObj.getX() == this.x - 1 && currentObj.getY() == this.y - 1 && currentObj.isEnemyWalkable) {
				//Checking if the tile is walkable
				if (checkValidMove(this.x, this.y - 1)) {
					return true;
				}
			//Otherwise if the tile two above is a wall
			} else if (currentObj.getX() == this.x && currentObj.getY() == this.y - 2) {
				//Checking if the tile above is walkable
				if (checkValidMove(this.x, this.y - 1)) {
					return true;
				}
			}
		}
		if (this.direction == 1) {
			this.direction++;
		}
		return false; // If the conditions are not met
	}

	/**
	 * This method checks if the wall hug enemy can move to the tile below.
	 * @return boolean
	 */
	public boolean canMoveDown() {
		for (int i = 0; i < objectList.size(); i++) {
			Object currentObj = objectList.get(i);
			//If the space below has an impassable tile to the left or right
			if (currentObj.getX() == this.x + 1 || currentObj.getX() == this.x - 1 && currentObj.getY() == this.y + 1 && currentObj.isEnemyWalkable) {
				//Checking if the tile is walkable
				if (checkValidMove(this.x, this.y + 1)) {
					return true;
				}
			//Otherwise if the tile two below is a wall
			} else if (currentObj.getX() == this.x && currentObj.getY() == this.y + 2) {
				//Checking if the tile above is walkable
				if (checkValidMove(this.x, this.y + 1)) {
					return true;
				}
			}
		}
		if (this.direction == 3) {
			this.direction++;
		}
		return false; // If the conditions are not met
	}

	/**
	 * This method checks if the wall hug enemy can move to the tile right of it.
	 * @return boolean
	 */
	public boolean canMoveRight() {
		for (int i = 0; i < objectList.size(); i++) {
			Object currentObj = objectList.get(i);
			//If the space right has an impassable tile above or below
			if (currentObj.getX() == this.y + 1 || currentObj.getX() == this.y - 1 && currentObj.getY() == this.x + 1 && currentObj.isEnemyWalkable) {
				//Checking if the tile is walkable
				if (checkValidMove(this.x + 1, this.y)) {
					return true;
				}
			//Otherwise if the tile two right is a wall
			} else if (currentObj.getX() == this.x + 2 && currentObj.getY() == this.y) {
				//Checking if the tile right is walkable
				if (checkValidMove(this.x + 2, this.y)) {
					return true;
				}
			}
		}
		if (this.direction == 4) {
			this.direction = 1;
		}
		return false; // If the conditions are not met
	}

	/**
	 * This method checks if the wall hug enemy can move to the tile left of it.
	 * @return boolean
	 */
	public boolean canMoveLeft() {
		for (int i = 0; i < objectList.size(); i++) {
			Object currentObj = objectList.get(i);
			//If the space left has an impassable tile above or below
			if (currentObj.getX() == this.y + 1 || currentObj.getX() == this.y - 1 && currentObj.getY() == this.x - 1 && currentObj.isEnemyWalkable) {
				//Checking if the tile is walkable
				if (checkValidMove(this.x - 1, this.y)) {
					return true;
				}
			//Otherwise if the tile two left is a wall
			} else if (currentObj.getX() == this.x - 2 && currentObj.getY() == this.y) {
				//Checking if the tile left is walkable
				if (checkValidMove(this.x - 2, this.y)) {
					return true;
				}
			}
		}
		if (this.direction == 2) {
			this.direction++;
		}
		return false; // If the conditions are not met
	}

	
	/**
	 * This method returns a boolean based on whether the move to a given set of coordinates is valid.
	 * @param givenX The new x position that the enemy wants to move to.
	 * @param givenY The new y position that the enemy wants to move to.
	 * @return boolean If it is a valid move or not.
	 */
	public boolean checkValidMove(int givenX, int givenY) {
		for (int j = 0; j < objectList.size(); j++) {
			if (objectList.get(j).getX() == givenX && objectList.get(j).getY() == givenY && objectList.get(j).isEnemyWalkable) {
				return true;
			}
		}
		return false;
	}
}