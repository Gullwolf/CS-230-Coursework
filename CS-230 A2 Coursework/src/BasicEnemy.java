import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

 /**
 * Making the Basic Enemy Object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class BasicEnemy extends Body {

	//Storing the direction the basic enemy moves.
	//1 = up, 2 = right, 3 = down, 4 = left
	public int direction;
	private ArrayList<Object> objectList;
	/**
	 * The constructor for the Basic Enemy class.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public BasicEnemy(int x, int y, GraphicsContext gc, int TILE_SIZE, int direction) {
		super(x, y, gc, TILE_SIZE);
		this.direction = direction;
		//Getting the list of objects in the map
		this.objectList = TrainCanvas.getObjects();
		this.image = new Image("file:Art/Enemy1Right.png");
		this.tileType = "Enemy 1 1";
	}

	/**
	 * Making the enemy object move.
	 */
	public void move() {
		if (direction == 1) { //UP
			for (int i = 0; i < objectList.size(); i++) {
				//This if looks to see if the object is in the position 
				//the enemy is attempting to move into.
				if ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y - 1) {
					//If the spot is walkable, move there.
					if(objectList.get(i).isEnemyWalkable) {
						this.y--;
						//Without this return, the enemy would move as far 
						//in that direction as possible
						i = objectList.size(); //Exiting the loop;
					} else {
						direction = 3; //Change the direction so it moves down
					}
				}
			}
		} else if (direction == 2) { //RIGHT
			for (int i = 0; i < objectList.size(); i++) {
				//This if looks to see if the object is in the position 
				//the enemy is attempting to move into.
				if ((objectList.get(i).getX() == this.x + 1) && objectList.get(i).getY() == this.y) {
					//If the spot is walkable, move there.
					if(objectList.get(i).isEnemyWalkable) {
						this.x++;
						//Without this return, the enemy would move as far 
						//in that direction as possible
						i = objectList.size(); //Exiting the loop
					} else {
						direction = 4; //Change the direction so it moves left
					}
				}
			}
		} else if (direction == 3) { //DOWN
			for (int i = 0; i < objectList.size(); i++) {
				//This if looks to see if the object is in the position 
				//the enemy is attempting to move into.
				if  ((objectList.get(i).getX() == this.x) &&
						objectList.get(i).getY() == this.y + 1) {
					//If the spot is walkable, move there.
					if (objectList.get(i).isEnemyWalkable) {
						this.y++;
						//Without this return, the enemy would move as far 
						//in that direction as possible
						i = objectList.size(); //Exiting the loop
					} else {
						direction = 1; //Change the direction so it moves up
					}
				}
			}
		} else if (direction == 4) { //LEFT
			for (int i = 0; i < objectList.size(); i++) {
				//This if looks to see if the object is in the position 
				//the enemy is attempting to move into.
				if ((objectList.get(i).getX() == this.x - 1) && objectList.get(i).getY() == this.y) {
					//If the spot is walkable, move there.
					if(objectList.get(i).isEnemyWalkable) {
						this.x--;
						//Without this return, the enemy would move as far 
						//in that direction as possible
						i = objectList.size(); //Exiting the loop
					} else {
						direction = 2; //Change the direction so it moves right
					}
				}
			}
		}
	this.interact();
	}
}