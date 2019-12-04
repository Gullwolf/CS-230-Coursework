import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class creates the game itself.
 * @author Noah Stebbings, Cai Sidaway
 * @version 1.5
 */
public class TrainCanvas extends Application {
	//Dimensions of the window
	private static final int WINDOW_WIDTH = 800;

	private static final int WINDOW_HEIGHT = 800;

	//Dimensions of the canvas
	private static final int CANVAS_WIDTH = WINDOW_WIDTH;

	private static final int CANVAS_HEIGHT = WINDOW_HEIGHT;

	//The number of tiles along the screen at once
	private final static int TILES_ON_SCREEN = 7;

	//The size of the tiles
	private static final int TILE_SIZE = WINDOW_WIDTH / TILES_ON_SCREEN;

	//The canvas in the GUI
	private static Canvas canvas;

	//Creating an empty graphics context
	public static GraphicsContext gc;

	//The current level the game is on (gets parsed from Cai's LoadMainGame)
	private static int currentLevel;
	private static int currentLevelScore = 0;

	//A list of all objects in the game
	private static ArrayList<Object> objectList = new ArrayList<Object>();
	private static ArrayList<Object> enemyList = new ArrayList<Object>();

	//If current game's getting loaded, then this filepath will be used (also parsed)
	private static String loadFilePath = null;

	//String is only set to yes if it's coming from a savegame file, else null.
	private static String load = null;

	private static Stage primaryStage;

	//Creating an empty player object.
	private static Player player;

	private static Pane root;
	private static String[] arguments;
	


	/**
	 * The startup method for the game.
	 * @param primaryStage
	 */
	public void start(Stage stage) {
		primaryStage = stage;
		//Building the game
		root = buildGame();

		//Creating a scene
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		//Reading in from the game level file.
		LevelReader.createLevel(currentLevel, root);

		//Setting the windows title
		primaryStage.setTitle("Train Game!");

		//Display the scene at the front of the stage
		primaryStage.setScene(scene);
		primaryStage.show();

		//Centering the player to the middle of the screen
		centerPlayer();

		//This makes sure all objects have been drawn when the game starts.
		drawGame();

		//When a button is pressed
		scene.setOnKeyPressed(event -> {
			player.takeInput(event.getCode().toString());

			currentLevelScore++;
			drawGame();
			onItem();
		});
		
		//When close button is clicked this will trigger
				stage.setOnCloseRequest(event -> {
					Stage loader = new Stage();
					ButtonType ok = new ButtonType("Ok");
					ButtonType cancel = new ButtonType("Cancel");
					Alert quit = new Alert(AlertType.CONFIRMATION, "Quit", ok,cancel);
					quit.setTitle("You've quit!");
					quit.setHeaderText("You've been sent back to the load game menu");
					quit.setContentText("If you'd like to save your previous game, click ok " +
								" if you want to disgard it click cancel");
					quit.showAndWait().ifPresent(response -> {
						if (response == ok) { //If they've chosen okay, it'll run savegame
							SaveGame.setCurrentLevel(currentLevel);
							SaveGame.readOriginal();
							try {
								new LoadGameMain().start(loader);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (response == cancel) { //If they choose cancel, the game  is not saved and curscore it set = 0
							try {
								Profile.setCurScore(0);
								try {
									new LoadGameMain().start(loader);
								} catch (Exception e) {
									e.printStackTrace();
								}
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}
					});
				});
	}


	/**
	 * This method updates the leadboard on the players death.
	 */
	public static void updateLeaderboard() {
		int currHighScore = 0;
		//Getting the current levels high score for the player.
		switch (currentLevel) {
		case 1: currHighScore = Profile.getHighestScoreL1();
		break;
		case 2: currHighScore = Profile.getHighestScoreL2();
		break;
		case 3: currHighScore = Profile.getHighestScoreL3();
		break;
		case 4: currHighScore = Profile.getHighestScoreL4();
		break;
		case 5: currHighScore = Profile.getHighestScoreL5();
		break;
		case 6: currHighScore = Profile.getHighestScoreL6();
		break;
		case 7: currHighScore = Profile.getHighestScoreL7();
		break;
		case 8: currHighScore = Profile.getHighestScoreL8();
		break;
		case 9: currHighScore = Profile.getHighestScoreL9();
		break;
		case 10: currHighScore = Profile.getHighestScoreL10();
		break;
		}
		//If the users new score is lower than their old best, or there is no current best.
		if (currentLevelScore < currHighScore || currHighScore == 0) {
			try {
				//Try and update the high score
				Profile.setHighscoreToValue(currentLevel, currentLevelScore);
				//Only updates the next level if the player is on the furthest level they can reach
				if (Profile.getLevel() == currentLevel) {
					Profile.setLevel(Profile.getLevel() + 1);
				}
			} catch (FileNotFoundException e) {
				System.out.println("INVALID PROFILE ERROR");
			}
		}
	}

	/**
	 * This gets parsed a level number from LoadGameMain.
	 * @param level Is the level number that's set to be loaded
	 */
	public static void setCurrentLevel(int level) {
		currentLevel = level;
	}

	/**
	 * Just sets a string to something, allowing for checking in the main start stage.
	 */
	public static void setLoadStatus() {
		load = "Yes";
	}

	/**
	 * Sets the file path to the one players specifically saved game
	 * @param path Is the location of the file on the system
	 */
	public static void setLoadFilePath(String path) {
		loadFilePath = path;
	}

	/**
	 * Checks if the player is standing on an item.
	 */
	public static void onItem() {
		//Checking if the player is interacting with an object.
		for (int i = 0; i < objectList.size(); i++) {
			if ((objectList.get(i).getX() == player.getX()) && 
					(objectList.get(i).getY() == player.getY())) {
				objectList.get(i).interact();
			}
		}
		//Checking if the enemy is interacting with the player.
		for (int j = 0; j < enemyList.size(); j++) {
			if ((enemyList.get(j).getX() == player.getX()) &&
					(enemyList.get(j).getY() == player.getY())) {
				//GEORGE REPLACE THE CONTENTS OF THIS IF WITH YOUR CODE / A METHOD CALL
				redrawLevel();
			}
		}
	}

	/**
	 * This method returns the player object when called.
	 * @return Player
	 */
	public static Player getPlayer() {
		return player;
	}

	/**
	 * This method returns the value that allows the game to center the player.
	 * @return int
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

		//Fixing the teleporter objects.
		for (int j = 0; j < objectList.size(); j++) {
			if (objectList.get(j).isTeleporter) {
				objectList.get(j).fixLinks();
			}
		}
	}

	/**
	 * This method redraws every object in game.
	 * It first removes any picked up items, then draws the objects,
	 * then moves and then draws enemies, and finally the player.
	 */
	public static void drawGame() {
		//Making a background for the canvas.
		gc.setFill(Color.DARKGREEN);
		gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

		removePickedUp();
		//Iterating through each object in the list.
		for (int i = 0; i < objectList.size(); i++) {
			objectList.get(i).drawObject();
		}
		//Doing the same for the list of enemies.
		for (int j = 0; j < enemyList.size(); j++) {
			enemyList.get(j).move();
			enemyList.get(j).drawObject();
		}
		//Drawing the player object.
		player.drawObject();
	}

	/**
	 * This method allows any class to access the list of objects.
	 * @return ArrayList<Object>
	 */
	public static ArrayList<Object> getObjects() {
		return objectList;
	}

	/**
	 * This method allows any class to access the list of objects.
	 * @return ArrayList<Object>
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
		//Deleting all of the objects in the scene
		currentLevelScore = 0;
		player = null;

		while (objectList.size() != 0) {
			objectList.remove(0);
		}
		while (enemyList.size() != 0) {
			enemyList.remove(0);
		}

		primaryStage.close();

		//Remaking the screen
		root = buildGame();

		//Creating a scene
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		//Reading in from the game level file.
		LevelReader.createLevel(currentLevel, root);

		//Setting the windows title
		primaryStage.setTitle("Train Game!");

		//Display the scene at the front of the stage
		primaryStage.setScene(scene);
		primaryStage.show();

		//Centering the player to the middle of the screen
		centerPlayer();

		//This makes sure all objects have been drawn when the game starts.
		drawGame();

		//When a button is pressed
		scene.setOnKeyPressed(event -> {
			player.takeInput(event.getCode().toString());

			drawGame();
			onItem();
		});
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
	 */
	public static void addTeleporter(int x, int y) {
		objectList.add(new Teleporter(x, y, gc, TILE_SIZE));
	}

	/**
	 * A method for adding the enemy object to the game.
	 * @param x
	 * @param y
	 * @param type
	 * @param extra
	 */
	public static void addEnemy(int x, int y, int type, int extra) {
		objectList.add(new Floor(x, y, gc, TILE_SIZE));//Adding a floor below the enemy object
		if (type == 1) {
			enemyList.add(new BasicEnemy(x, y, gc, TILE_SIZE, extra));
		} else if (type == 2) {
			enemyList.add(new WallHugEnemy(x, y, gc, TILE_SIZE));
		} else if (type == 3) {
			enemyList.add(new DumbEnemy(x, y, gc, TILE_SIZE));
		} else if (type == 4) {
			enemyList.add(new SmartEnemy(x, y, gc, TILE_SIZE));
		}
	}

	/**
	 * This method checks to see if an object has been picked up, 
	 * and if so deletes it.
	 */
	public static void removePickedUp() {
		//Iterating through each object in the list.
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.get(i).pickedUp) {
				objectList.remove(i);
			}
		}
	}
}