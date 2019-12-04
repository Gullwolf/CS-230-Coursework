import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Making the Wall Hug Enemy Object
 * @author Noah Stebbings, Mohammed Raihan
 * @version 1.0
 */

public class WallHugEnemy extends Body {

	private int direction = 1;
	private boolean hasMoved = false;

	private ArrayList<Object> objectList;

	/**
	 * The constructor for the Basic Enemy class.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public WallHugEnemy(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		//Getting the list of objects in the map
		this.objectList = TrainCanvas.getObjects();
		this.image = new Image("file:Art/Enemy2Right.png");
		drawObject();
	}

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
	 * @param givenX
	 * @param givenY
	 * @return boolean
	 */
	public boolean checkValidMove(int givenX, int givenY) {
		for (int j = 0; j < objectList.size(); j++) {
			if (objectList.get(j).getX() == givenX && objectList.get(j).getY() == givenY && objectList.get(j).isEnemyWalkable) {
				return true;
			}
		}
		return false;
	}
//	public void move() {
//		if (direction == 1) { //UP
//			for (int i = 0; i < objectList.size(); i++) {
//				//This if looks to see if the object is in the position 
//				//the enemy is attempting to move into.
//				if ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y - 1) {
//					//Check if the next spot is next to an object
//					if (((objectList.get(i).getX() == this.x - 1) 
//							&& objectList.get(i).getY() == this.y - 1)
//							|| ((objectList.get(i).getX() == this.x + 1) 
//									&& objectList.get(i).getY() == this.y - 1)) {
//						//If the spot is walkable, move there.
//						if(objectList.get(i).isEnemyWalkable) {
//							this.y--;
//							//Without this return, the enemy would move as far 
//							//in that direction as possible
//							return;
//						} else {
//							direction = 3; //Change the direction so it moves down
//						}
//					}
//				}
//			}
//		} else if (direction == 2) { //RIGHT
//			for (int i = 0; i < objectList.size(); i++) {
//				//This if looks to see if the object is in the position 
//				//the enemy is attempting to move into.
//				if ((objectList.get(i).getX() == this.x + 1) && objectList.get(i).getY() == this.y) {
//					//Check if the next spot is next to an object
//					if (((objectList.get(i).getX() == this.x + 1) 
//							&& objectList.get(i).getY() == this.y - 1)
//							|| ((objectList.get(i).getX() == this.x + 1) 
//									&& objectList.get(i).getY() == this.y + 1)) {
//						//If the spot is walkable, move there.
//						if(objectList.get(i).isEnemyWalkable) {
//							this.x++;
//							//Without this return, the enemy would move as far 
//							//in that direction as possible
//							return;
//						} else {
//							direction = 4; //Change the direction so it moves left
//						}
//					}
//				}
//			}
//		} else if (direction == 3) { //DOWN
//			for (int i = 0; i < objectList.size(); i++) {
//				//This if looks to see if the object is in the position 
//				//the enemy is attempting to move into.
//				if  ((objectList.get(i).getX() == this.x) &&
//						objectList.get(i).getY() == this.y + 1) {
//					//Check if the next spot is next to an object
//					if (((objectList.get(i).getX() == this.x + 1) 
//							&& objectList.get(i).getY() == this.y + 1)
//							|| ((objectList.get(i).getX() == this.x - 1) 
//									&& objectList.get(i).getY() == this.y + 1)) {
//						//If the spot is walkable, move there.
//						if (objectList.get(i).isEnemyWalkable) {
//							this.y++;
//							//Without this return, the enemy would move as far 
//							//in that direction as possible
//							return;
//						} else {
//							direction = 1; //Change the direction so it moves up
//						}
//					}
//				}
//			}
//		} else if (direction == 4) { //LEFT
//			for (int i = 0; i < objectList.size(); i++) {
//				//This if looks to see if the object is in the position 
//				//the enemy is attempting to move into.
//				if ((objectList.get(i).getX() == this.x - 1) && objectList.get(i).getY() == this.y) {
//					//Check if the next spot is next to an object
//					if (((objectList.get(i).getX() == this.x - 1) 
//							&& objectList.get(i).getY() == this.y - 1)
//							|| ((objectList.get(i).getX() == this.x - 1) 
//									&& objectList.get(i).getY() == this.y + 1)) {
//						//If the spot is walkable, move there.
//						if(objectList.get(i).isEnemyWalkable) {
//							this.x--;
//							//Without this return, the enemy would move as far 
//							//in that direction as possible
//							return;
//						} else {
//							direction = 2; //Change the direction so it moves right
//						}
//					}
//				}
//			}
//		}
//	}
}