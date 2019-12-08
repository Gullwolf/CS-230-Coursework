import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * 
 * @author Noah Stebbings, Lok Sang Fong
 *
 */
public class SmartEnemy extends Body {

	private ArrayList<Object> objectList;

	private Player player;
	
	private int playX;

	private int playY;


	/**
	 * The constructor for the Smart Enemy object.
	 * @param x The x Position of the Smart enemy on the map
	 * @param y The y Position of the Smart enemy on the map
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile
	 */
	public SmartEnemy(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);

		player = TrainCanvas.getPlayer();
		this.objectList = TrainCanvas.getObjects();
		this.image = new Image("file:Art/Enemy4Right.png");	
		this.tileType = "Enemy 4";
	}
	
	/*
	 * A method to find the players rough direction.
	 */
	private String findPlayer() {
		this.playX = player.getX();
		this.playY = player.getY();
		//Working out the rough direction of the player in comparison to the enemy
		if (this.playX < this.x && this.playY < this.y) {
			return "NW"; //UP and RIGHT
		} else if (this.playX == this.x && this.playY < this.y) {
			return "N"; //UP
		} else if (this.playX < this.x && this.playY > this.y) {
			return "SW"; //DOWN and RIGHT
		} else if (this.playX < this.x && this.playY == this.y) {
			return "W"; //RIGHT
		} else if (this.playX == this.x && this.playY > this.y) {
			return "S"; //DOWN
		} else if (this.playX > this.x && this.playY > this.y) {
			return "SE"; //DOWN and LEFT
		} else if (this.playX > this.x && this.playY < this.y) {
			return "NE"; //UP and LEFT
		} else {
			return "E"; //LEFT
		}
	}
	
	/*
	 * This method moves the enemy towards the player.
	 */
	@Override
	public void move() {
//		System.out.println(findPlayer());
		switch (findPlayer()) {
		case "NW": moveNW();
		break;
		case "W": moveW();
		break;
		case "SW": moveSW();
		break;
		case "S": moveS();
		break;
		case "N": moveN();
		break;
		case "E": moveE();
		break;
		case "NE": moveNE();
		break;
		case "SE": moveSE();
		break;
		}
		this.interact();
	}

	/*
	 * This method moves the object if the player is UP and RIGHT.
	 */
	private void moveNW() {
		//For every object
		for ( int i = 0; i < objectList.size(); i++ ) {
			//If the object above is a walkable tile
			if ( objectList.get(i).getX() == this.x && objectList.get(i).getY() == this.y - 1 && objectList.get(i).isEnemyWalkable ) {
				this.y--;
				return;
			//Otherwise if the object to the right is walkable
			} else if ( objectList.get(i).getX() == this.x - 1 && objectList.get(i).getY() == this.y && objectList.get(i).isEnemyWalkable ) {
				this.x--;
				this.image = new Image("file:Art/Enemy4Left.png");	
				return;
			}
		}
	}
	
	/*
	 * This method moves the object if the player is DOWN and RIGHT.
	 */
	private void moveSW() {
		//For every object
		for (int i = 0; i < objectList.size(); i++) {
			//If the object above is a walkable tile
			if (objectList.get(i).getX() == this.x && objectList.get(i).getY() == this.y + 1 && objectList.get(i).isEnemyWalkable) {
				this.y++;
				return;
			//Otherwise if the object to the right is walkable
			} else if (objectList.get(i).getX() == this.x - 1 && objectList.get(i).getY() == this.y && objectList.get(i).isEnemyWalkable) {
				this.x--;
				return;
			}
		}
	}
	
	/*
	 * This method moves the object if the player is UP and LEFT.
	 */
	private void moveNE() {
		//For every object
		for ( int i = 0; i < objectList.size(); i++ ) {
			//If the object above is a walkable tile
			if ( objectList.get(i).getX() == this.x && objectList.get(i).getY() == this.y - 1 && objectList.get(i).isEnemyWalkable ) {
				this.y--;
				return;
			//Otherwise if the object to the right is walkable
			} else if ( objectList.get(i).getX() == this.x + 1 && objectList.get(i).getY() == this.y && objectList.get(i).isEnemyWalkable ) {
				this.x++;
				this.image = new Image("file:Art/Enemy4Right.png");	
				return;
			}
		}
	}
	
	/*
	 * This method moves the object if the player is DOWN and LEFT.
	 */
	private void moveSE() {
		//For every object
		for ( int i = 0; i < objectList.size(); i++ ) {
			//If the object above is a walkable tile
			if ( objectList.get(i).getX() == this.x && objectList.get(i).getY() == this.y + 1 && objectList.get(i).isEnemyWalkable ) {
				this.y++;
				return;
			//Otherwise if the object to the right is walkable
			} else if ( objectList.get(i).getX() == this.x + 1 && objectList.get(i).getY() == this.y && objectList.get(i).isEnemyWalkable ) {
				this.x++;
				this.image = new Image("file:Art/Enemy4Right.png");	
				return;
			}
		}
	}
	
	/*
	 * This method moves the object if the player is RIGHT.
	 */
	private void moveW() {
		//For every object
		for ( int i = 0; i < objectList.size(); i++ ) {
			//If the object above is a walkable tile
			if ( objectList.get(i).getX() == this.x - 1 && objectList.get(i).getY() == this.y && objectList.get(i).isEnemyWalkable ) {
				this.x--;
				this.image = new Image("file:Art/Enemy4Left.png");	
				return;
			}
		}
	}
	
	/*
	 * This method moves the object if the player is RIGHT.
	 */
	private void moveS() {
		//For every object
		for ( int i = 0; i < objectList.size(); i++ ) {
			//If the object above is a walkable tile
			if ( objectList.get(i).getX() == this.x && objectList.get(i).getY() == this.y + 1 && objectList.get(i).isEnemyWalkable ) {
				this.y++;
				return;
			}
		}
	}
	
	/*
	 * This method moves the object if the player is RIGHT.
	 */
	private void moveE() {
		//For every object
		for ( int i = 0; i < objectList.size(); i++ ) {
			//If the object above is a walkable tile
			if ( objectList.get(i).getX() == this.x + 1 && objectList.get(i).getY() == this.y && objectList.get(i).isEnemyWalkable ) {
				this.x++;
				this.image = new Image("file:Art/Enemy4Right.png");	
				return;
			}
		}
	}
	
	/*
	 * This method moves the object if the player is UP.
	 */
	private void moveN() {
		//For every object
		for ( int i = 0; i < objectList.size(); i++ ) {
			//If the object above is a walkable tile
			if ( objectList.get(i).getX() == this.x && objectList.get(i).getY() == this.y - 1 && objectList.get(i).isEnemyWalkable ) {
				this.y--;
				return;
			}
		}
	}
}