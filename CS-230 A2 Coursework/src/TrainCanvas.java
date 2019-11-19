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
 * @version 1.0
 */
public class TrainCanvas extends Application {
	//Dimensions of the window
	private static final int WINDOW_WIDTH = 1000;

	private static final int WINDOW_HEIGHT = 800;

	//Dimensions of the canvas
	private static final int CANVAS_WIDTH = WINDOW_WIDTH;

	private static final int CANVAS_HEIGHT = WINDOW_HEIGHT;

	//The number of tiles along the screen at once
	private final static int TILES_ON_SCREEN = 24;
	
	//The size of the tiles
	private static final int TILE_SIZE = WINDOW_WIDTH / TILES_ON_SCREEN;

	//The canvas in the GUI
	private static Canvas canvas;
	
	//Creating an empty graphics context
	public static GraphicsContext gc;
	
	//The current level the game is on
	private int currentLevel = 1;
	
	//A list of all objects in the game
	private static ArrayList<Object> objectList = new ArrayList<Object>();
	
	/**
	 * The startup method for the game.
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		//Building the game
		Pane root = buildGame();
		
		//Creating a scene
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		//Reading in from the game level file.
		LevelReader.createLevel(currentLevel, root);
		
		//Setting the windows title
		primaryStage.setTitle("Train Game!");
		
		//Display the scene at the front of the stage
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void drawGame() {
		//TODO write a method to redraw the canvas with each move
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
	private Pane buildGame() {
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
		objectList.add(new Player(x, y, gc, TILE_SIZE));
	}
}
