import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class creates the game itself.
 * @author Noah Stebbings
 * @version 1.2
 */
public class TrainCanvas extends Application {
	//Dimensions of the window
	private static final int WINDOW_WIDTH = 800;

	private static final int WINDOW_HEIGHT = 800;

	//Dimensions of the canvas
	private static final int CANVAS_WIDTH = WINDOW_WIDTH;

	private static final int CANVAS_HEIGHT = WINDOW_HEIGHT;

	//The number of tiles along the screen at once (default 7)
	private final static int TILES_ON_SCREEN = 7;

	//The size of the tiles
	private static final int TILE_SIZE = WINDOW_WIDTH / TILES_ON_SCREEN;

	//The canvas in the GUI
	private static Canvas canvas;

	//Creating an empty graphics context
	public static GraphicsContext gc;

	//The current level the game is on
	private static int currentLevel = 3;

	//A list of all objects in the game
	private static ArrayList<Object> objectList = new ArrayList<Object>();
	private static ArrayList<Object> enemyList = new ArrayList<Object>();

	//Creating an empty player object.
	private static Player player;

	private static Pane root;

	/**
	 * The startup method for the game.
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		//Building the game
		root = buildGame();

		//Creating a scene
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		//Reading in from the game level file.
		LevelReader.createLevel(currentLevel, root);

		//Setting the windows title
		primaryStage.setTitle("Train Game!");

		//Making a background for the canvas.
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

		//Display the scene at the front of the stage
		primaryStage.setScene(scene);
		primaryStage.show();

		centerPlayer();

		//This makes sure all objects have been drawn when the game starts.
		drawGame();

		//When a button is pressed
		scene.setOnKeyPressed(event -> {
			player.takeInput(event.getCode().toString());
			onItem();
			drawGame();
		});
	}

	/**
	 * Checks if the player is standing on an item.
	 */
	public static void onItem() {
		for (int i = 0; i < objectList.size(); i++) {
			if ((objectList.get(i).getX() == player.getX()) && 
					(objectList.get(i).getY() == player.getY())) {
				objectList.get(i).interact();
			}
		}
		for (int j = 0; j < enemyList.size(); j++) {
			if ((enemyList.get(j).getX() == player.getX()) &&
					(enemyList.get(j).getY() == player.getY())) {
				enemyList.get(j).interact();
			}
		}
	}

	/**
	 * This method returns the player object when called.
	 * @return player
	 */
	public static Player getPlayer() {
		return player;
	}

	/**
	 * This method returns the value that allows the game to center the player.
	 * @return
	 */
	public static int getCenter() {
		return (int) TILES_ON_SCREEN / 2;
	}

	/**
	 * This method moves all objects so the player is in the 
	 * center of the screen.
	 */
	public static void centerPlayer() {
		int center = (int) TILES_ON_SCREEN / 2;
		int playerX = player.getX();
		int playerY = player.getY();
		//Moving every object so the player is at the center
		for (int i = 0; i < objectList.size(); i++) {
			objectList.get(i).setX(objectList.get(i).getX() - playerX + center);
			objectList.get(i).setY(objectList.get(i).getY() - playerY + center);
		}
		//Moving enemies with the map
		for (int n = 0; n < enemyList.size(); n++) {
			enemyList.get(n).setX(enemyList.get(n).getX() - playerX + center);
			enemyList.get(n).setY(enemyList.get(n).getY() - playerY + center);
		}
		//Setting the player to be at the center of the screen
		player.setX(center);
		player.setY(center);
	}

	/**
	 * This method redraws every object in the object list,
	 * and then the player object.
	 */
	public static void drawGame() {
		//Iterating through each object in the list.
		for (int i = 0; i < objectList.size(); i++) {
			objectList.get(i).drawObject();
		}
		//Doing the same for the list of enemies.
		for (int j = 0; j < enemyList.size(); j++) {
			enemyList.get(j).move();
			enemyList.get(j).drawObject();
		}
		player.drawObject();
	}

	/**
	 * This method allows any class to access the list of objects.
	 * @return objectList
	 */
	public static ArrayList<Object> getObjects() {
		return objectList;
	}

	/**
	 * This method allows any class to access the list of objects.
	 * @return enemyList
	 */
	public static ArrayList<Object> getEnemies() {
		return enemyList;
	}

	/**
	 * The main method that starts up the program.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Building the game GUI.
	 * @return Pane
	 */
	private static Pane buildGame() {
		//Creating a pane that will contain the game
		BorderPane root = new BorderPane();

		//Creating a canvas
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setCenter(canvas);

		//Accessing the graphic context of the game
		gc = canvas.getGraphicsContext2D();

		return root;
	}

	/**
	 * This method redraws the level when called.
	 */
	public static void redrawLevel() {
//		objectList = new ArrayList<Object>();
//		enemyList = new ArrayList<Object>();
		player = null;
		
		for (int i = 0; i < objectList.size(); i++) {
			objectList.remove(i);
		}
		for (int j = 0; j < enemyList.size(); j++) {
			enemyList.remove(j);
		}

		//Making a background for the canvas.
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

		LevelReader.createLevel(currentLevel, root);
		root = buildGame();

		centerPlayer();
		drawGame();
	}

	/**
	 * This method starts the next level.
	 */
	public static void nextLevel() {
		currentLevel++;
		redrawLevel();
	}

	/**
	 * A method for adding a wall object to the game.
	 * @param x
	 * @param y
	 */
	public static void addWall(int x, int y) {
		objectList.add(new Wall(x, y, gc, TILE_SIZE));
	}

	/**
	 * A method for adding a fire object to the game.
	 * @param x
	 * @param y
	 */
	public static void addFire(int x, int y) {
		objectList.add(new Fire(x, y, gc, TILE_SIZE));
	}

	/**
	 * A method for adding a water object to the game.
	 * @param x
	 * @param y
	 */
	public static void addWater(int x, int y) {
		objectList.add(new Water(x, y, gc, TILE_SIZE));
	}

	/**
	 * A method for adding the goal object to the game.
	 * @param x
	 * @param y
	 */
	public static void addGoal(int x, int y) {
		objectList.add(new Goal(x, y, gc, TILE_SIZE));
	}

	/**
	 * A method for adding the floor object to the game.
	 * @param x
	 * @param y
	 */
	public static void addFloor(int x, int y) {
		objectList.add(new Floor(x, y, gc, TILE_SIZE));
	}

	/**
	 * A method for adding the player object to the game.
	 * @param x
	 * @param y
	 */
	public static void addPlayer(int x, int y) {
		objectList.add(new Floor(x, y, gc, TILE_SIZE));
		player = new Player(x, y, gc, TILE_SIZE);
	}

	/**
	 * A method for adding the player object to the game.
	 * @param x
	 * @param y
	 * @param type
	 */
	public static void addKeyDoor(int x, int y, int type) {
		objectList.add(new KeyDoor(x, y, gc, TILE_SIZE, type));
	}

	/**
	 * A method for adding the key object to the game.
	 * @param x
	 * @param y
	 * @param type
	 */
	public static void addKey(int x, int y, int type) {
		objectList.add(new Key(x, y, gc, TILE_SIZE, type));
	}

	/**
	 * A method for adding the token object to the game.
	 * @param x
	 * @param y
	 */
	public static void addToken(int x, int y) {
		objectList.add(new Token(x, y, gc, TILE_SIZE));
	}

	/**
	 * A method for adding the fire boots object to the game.
	 * @param x
	 * @param y
	 */
	public static void addFireBoots(int x, int y) {
		objectList.add(new FireBoots(x, y, gc, TILE_SIZE));
	}

	/**
	 * A method for adding the flippers object to the game.
	 * @param x
	 * @param y
	 */
	public static void addFlippers(int x, int y) {
		objectList.add(new Flippers(x, y, gc, TILE_SIZE));
	}

	/**
	 * A method for adding the flippers object to the game.
	 * @param x
	 * @param y
	 * @param requirements
	 */
	public static void addTokenDoor(int x, int y, int requirements) {
		objectList.add(new TokenDoor(x, y, gc, TILE_SIZE, requirements));
	}

	/**
	 * A method for adding the teleporter object to the game.
	 * @param x
	 * @param y
	 * @param linkX
	 * @param linkY
	 */
	public static void addTeleporter(int x, int y, int linkX, int linkY) {
		objectList.add(new Teleporter(x, y, gc, TILE_SIZE, linkX, linkY));
	}

	/**
	 * A method for adding the enemy object to the game.
	 * @param x
	 * @param y
	 * @param type
	 */
	public static void addEnemy(int x, int y, int type, int extra) {
		objectList.add(new Floor(x, y, gc, TILE_SIZE));//Adding a floor below the enemy object
		if (type == 1) {
			enemyList.add(new BasicEnemy(x, y, gc, TILE_SIZE, extra));
		} else if (type == 3) {
			enemyList.add(new DumbEnemy(x, y, gc, TILE_SIZE));
		} else {
			enemyList.add(new Enemy(x, y, gc, TILE_SIZE));
		}
	}

	public static void removePickedUp() {
		//Iterating through each object in the list.
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.get(i).pickedUp) {
				objectList.remove(i);
			}
		}
	}
}
