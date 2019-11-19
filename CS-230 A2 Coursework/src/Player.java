import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class draws the player object.
 * @author Noah Stebbings
 * @version 1.0
 */
public class Player extends Body {
	/**
	 * Creating a player object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Player(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		drawObject(Color.MAGENTA);
	}
	
	//TODO write a move method.
	@Override
	public void move() { 
		
	}
}
