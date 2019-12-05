import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class draws the goal object.
 * @author Noah Stebbings
 * @version 1.2
 */
public class Goal extends Useable {
	/**
	 * Creating a goal object.
	 * @param x
	 * @param y
	 * @param gc
	 * @param TILE_SIZE
	 */
	public Goal(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		this.image = new Image("file:Art/Goal.png");
		this.tileType = "Goal";
	}

	/**
	 * This method ends the level and goes to the next one.
	 */
	@Override
	public void interact() {
		Sound.getSound("LevelCompleted");
		TrainCanvas.updateLeaderboard();
		TrainCanvas.nextLevel();
	}
	
}
