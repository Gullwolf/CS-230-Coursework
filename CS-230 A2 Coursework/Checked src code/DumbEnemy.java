import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/** This class is create a enemy which can find the shortest way to attack the player.
*@author Hao Wu, Noah Stebbings
*@version 1.2
*/
public class DumbEnemy extends Enemy {

	private ArrayList<Object> objectList;

	private Player player;

	/**
	 * The constructor for the Dumb Enemy class.
	 * @param x The x Position of the Dumb enemy on the map
	 * @param y The y Position of the Dumb enemy on the map
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile
	 */
	public DumbEnemy(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		
		//Getting the list of objects in the map
		this.player = TrainCanvas.getPlayer();
		this.objectList = TrainCanvas.getObjects();
		this.image = new Image("file:Art/Enemy3Right.png");
		this.tileType = "Enemy 3";
	}
	/**
	* A method to find the shortest way to the player.
	*/
	@Override
	public void move() {
		if ( player.getX() != this.x ) {
			if ( this.x < player.getX() ) { //Moving Right
				for ( int i = 0; i < objectList.size(); i++ ) {
					if ( objectList.get(i).getX() == this.x + 1 && objectList.get(i).getY() == this.y && objectList.get(i).isEnemyWalkable ) {
						this.x++;
						this.image = new Image("file:Art/Enemy3Right.png");
						i = objectList.size(); //Exiting the loop
					}
				}
			} else if ( this.x > player.getX() ) { //Moving left
				for ( int i = 0; i < objectList.size(); i++ ) {
					if ( objectList.get(i).getX() == this.x - 1 && objectList.get(i).getY() == this.y && objectList.get(i).isEnemyWalkable ) {
						this.x--;
						this.image = new Image("file:Art/Enemy3Left.png");
						i = objectList.size(); //Exiting the loop
					}
				}
			}
		} else if ( player.getY() != this.y ) { //Moving Down
			if ( this.y < player.getY() ) {
				for ( int i = 0; i < objectList.size(); i++ ) {
					if ( objectList.get(i).getX() == this.x && objectList.get(i).getY() == this.y + 1 && objectList.get(i).isEnemyWalkable ) {
						this.y++;
						i = objectList.size(); //Exiting the loop
					}
				}
			} else if ( this.y > player.getY() ) { //Moving up
				for ( int i = 0; i < objectList.size(); i++ ) {
					if ( objectList.get(i).getX() == this.x && objectList.get(i).getY() == this.y - 1 && objectList.get(i).isEnemyWalkable ) {
						this.y--;
						i = objectList.size(); //Exiting the loop
					}
				}
			}
		}
	}
}