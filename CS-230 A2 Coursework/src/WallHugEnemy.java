import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Making the Wall Hug Enemy Object
 * @author Noah Stebbings, Mohammed Raihan
 * @version 1.0
 */

public class WallHugEnemy extends Body {

	private int direction;
	
	private ArrayList<Object> objectList;
	
	/**
	 * The constructor for the Basic Enemy class.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public WallHugEnemy(int x, int y, GraphicsContext gc, int TILE_SIZE, int direction) {
		super(x, y, gc, TILE_SIZE);
		this.direction = direction;
		//Getting the list of objects in the map
		this.objectList = TrainCanvas.getObjects();
		this.image = Color.RED;
		drawObject();
	}
	
	public void move() {
		if (direction == 1) { //UP
			for (int i = 0; i < objectList.size(); i++) {
				//This if looks to see if the object is in the position 
				//the enemy is attempting to move into.
				if ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y - 1) {
					//Check if the next spot is next to an object
					if (((objectList.get(i).getX() == this.x - 1) 
							&& objectList.get(i).getY() == this.y - 1)
								|| ((objectList.get(i).getX() == this.x + 1) 
										&& objectList.get(i).getY() == this.y - 1)) {
						//If the spot is walkable, move there.
						if(objectList.get(i).isEnemyWalkable) {
							this.y--;
							//Without this return, the enemy would move as far 
							//in that direction as possible
							return;
						} else {
							direction = 3; //Change the direction so it moves down
						}
					}
				}
			}
		} else if (direction == 2) { //RIGHT
			for (int i = 0; i < objectList.size(); i++) {
				//This if looks to see if the object is in the position 
				//the enemy is attempting to move into.
				if ((objectList.get(i).getX() == this.x + 1) && objectList.get(i).getY() == this.y) {
					//Check if the next spot is next to an object
					if (((objectList.get(i).getX() == this.x + 1) 
							&& objectList.get(i).getY() == this.y - 1)
								|| ((objectList.get(i).getX() == this.x + 1) 
										&& objectList.get(i).getY() == this.y + 1)) {
						//If the spot is walkable, move there.
						if(objectList.get(i).isEnemyWalkable) {
							this.x++;
							//Without this return, the enemy would move as far 
							//in that direction as possible
							return;
						} else {
							direction = 4; //Change the direction so it moves left
						}
					}
				}
			}
		} else if (direction == 3) { //DOWN
			for (int i = 0; i < objectList.size(); i++) {
				//This if looks to see if the object is in the position 
				//the enemy is attempting to move into.
				if  ((objectList.get(i).getX() == this.x) &&
						objectList.get(i).getY() == this.y + 1) {
					//Check if the next spot is next to an object
					if (((objectList.get(i).getX() == this.x + 1) 
							&& objectList.get(i).getY() == this.y + 1)
								|| ((objectList.get(i).getX() == this.x - 1) 
										&& objectList.get(i).getY() == this.y + 1)) {
						//If the spot is walkable, move there.
						if (objectList.get(i).isEnemyWalkable) {
							this.y++;
							//Without this return, the enemy would move as far 
							//in that direction as possible
							return;
						} else {
							direction = 1; //Change the direction so it moves up
						}
					}
				}
			}
		} else if (direction == 4) { //LEFT
			for (int i = 0; i < objectList.size(); i++) {
				//This if looks to see if the object is in the position 
				//the enemy is attempting to move into.
				if ((objectList.get(i).getX() == this.x - 1) && objectList.get(i).getY() == this.y) {
					//Check if the next spot is next to an object
					if (((objectList.get(i).getX() == this.x - 1) 
							&& objectList.get(i).getY() == this.y - 1)
								|| ((objectList.get(i).getX() == this.x - 1) 
										&& objectList.get(i).getY() == this.y + 1)) {
						//If the spot is walkable, move there.
						if(objectList.get(i).isEnemyWalkable) {
							this.x--;
							//Without this return, the enemy would move as far 
							//in that direction as possible
							return;
						} else {
							direction = 2; //Change the direction so it moves right
						}
					}
				}
			}
		}
	}
}