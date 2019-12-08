import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/**
 * A general class for all enemy objects.
 * @author Noah Stebbings
 * @version 1.4
 */
public class Enemy extends Body {

	/**
	 * The constructor for the Enemy class.
	 * @param x The x Position of the enemy on the map
	 * @param y The y Position of the enemy on the map
	 * @param gc
	 * @param TILE_SIZE Sets the size of the tile
	 */
	public Enemy(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		
		this.image = new Image("file:Art/MissingTexture.png");
		this.tileType = "Enemy"; //Overwritten in subclasses.
	}

	/**
	 * A method that is overwritten in its subclasses.
	 */
	@Override
	public void move() {
		System.out.println("THIS SHOULD BE OVERWRITTEN move enemy");
	}
	
	/**
	 * This method will detect if the enemy interacts with the player.
	 */
	@Override
	public void interact() { }




}