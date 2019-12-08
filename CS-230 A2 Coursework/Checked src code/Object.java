import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This is the superclass of all objects.
 * @author Noah Stebbings
 * @version 1.1
 */
public class Object {
	
	//Used by items
	public boolean pickedUp = false;
	
	//Storing the coordinates of the object.
	protected int x;
	protected int y;
	protected GraphicsContext gc;
	protected int TILE_SIZE;
	
	protected boolean isPlayerWalkable;
	protected boolean isEnemyWalkable;
	
	protected Image image;
	
	protected String tileType = "null";
	
	//Used for teleporter objects only
	protected boolean isTeleporter = false;
	
	/**
	 * The constructor for the object class.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Object(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.TILE_SIZE = TILE_SIZE;
	}
	
	/**
	 * This method returns the tile type.
	 * @return String
	 */
	public String getTileType() {
		return this.tileType;
	}
	
	/**
	 * This method draws the object on the canvas. 
	 */
	protected void drawObject() {
		//Draw an image at the set coordinates
		gc.drawImage(this.image, (x * TILE_SIZE), (y * TILE_SIZE),
				TILE_SIZE, TILE_SIZE);
	}
	
	/**
	 * This method returns the x position of the object.
	 * @return x
	 */
	protected int getX() {
		return this.x;
	}
	
	/**
	 * This method returns the y position of the object.
	 * @return y
	 */
	protected int getY() {
		return this.y;
	}

	/**
	 * A method that is overwritten in its subclasses.
	 */
	public void move() {
		System.out.println("THIS SHOULD BE OVERWRITTEN move");
	}

	/**
	 * A method that is overwritten in its subclasses.
	 */
	public void interact() {
		System.out.println("THIS SHOULD BE OVERWRITTEN interact");
	}
	
	/**
	 * Moves the object one spot left.
	 */
	public void goLeft() {
		this.x++;
	}
	
	/**
	 * Moves the object one spot up.
	 */
	public void goUp() {
		this.y++;
	}
	
	/**
	 * Moves the object one spot right.
	 */
	public void goRight() {
		this.x--;
	}
	
	/**
	 * Moves the object one spot down.
	 */
	public void goDown() {
		this.y--;
	}
	
	/**
	 * Sets the x value of the object.
	 * @param newX
	 */
	public void setX(int newX) {
		this.x = newX;
	}
	
	/**
	 * Sets the y value of the object.
	 * @param newY
	 */
	public void setY(int newY) {
		this.y = newY;
	}

	/**
	 * This method returns the requirements of the token door.
	 * Only used by tokendoor for savegame
	 * @return int
	 */
	public int getRequirements() {
		return 0;
	}
}