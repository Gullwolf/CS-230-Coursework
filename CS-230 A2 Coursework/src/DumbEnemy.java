import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/** This class is create a enemy which can find the shortest way to attack the player
*@author Hao Wu
*@version 1.1
*/
public class DumbEnemy extends Enemy {
	private ArrayList<Object> objectList;
	private Player player;
	/**
	* Create an enemy.
	*/
	public DumbEnemy(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		
		//Getting the list of objects in the map
		this.player = TrainCanvas.getPlayer();
		this.objectList = TrainCanvas.getObjects();
		this.image = Color.RED;
		drawObject();
	}
	/**
	* A method to find the shortest way to the player.
	*/
	@Override
	public void move() {
		if (player.getX() != this.x) {
			if(this.x < player.getX()) {
				for (int i = 0; i < objectList.size(); i++) {
					if (objectList.get(i).getX() == this.x + 1 && objectList.get(i).getY() == this.y && objectList.get(i).isEnemyWalkable) {
						this.x++;
					}
				}
			} else if (this.x > player.getX()){
				for (int i = 0; i < objectList.size(); i++) {
					if (objectList.get(i).getX() == this.x - 1 && objectList.get(i).getY() == this.y && objectList.get(i).isEnemyWalkable) {
						this.x++;
					}
				}
			}
		} else if(player.getY() != this.y){
			if(this.y < player.getY()){
				for (int i = 0; i < objectList.size(); i++) {
					if (objectList.get(i).getX() == this.x && objectList.get(i).getY() == this.y + 1 && objectList.get(i).isEnemyWalkable) {
						this.y++;
					}
				}
			} else if (this.y > player.getY()){
				for (int i = 0; i < objectList.size(); i++) {
					if (objectList.get(i).getX() == this.x && objectList.get(i).getY() == this.y - 1 && objectList.get(i).isEnemyWalkable) {
						this.y--;
					}
				}
			}
		}
	}
}