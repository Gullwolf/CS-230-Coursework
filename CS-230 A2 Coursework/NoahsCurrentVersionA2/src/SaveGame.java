import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class saves the current state of the game.
 * Im sorry to whoever has to read this code.
 * @author Cai Sidaway, Noah Stebbings
 * @version 1.4
 *
 */
public class SaveGame {


	static char[][] map;
	private static Scanner in = null;
	private static Scanner in2 = null;	
	private static int mapHeight = 0;
	private static int mapWidth = 0;
	private static String extra = "";
	
	private static ArrayList<Object> objectList;
	private static ArrayList<Object> enemyList;
	private static Player player;
	
	private static String extraInformationLine = "";
	private static String enemyExtraInfo = "";
	private static String playerInventory = "";
	
	private static int currentLevel = 1;
	private static String user = Profile.getUser();
	
 private static String filename = System.getProperty("user.dir") + "\\SaveGame\\" + 
		"Level" + currentLevel + "_" + user + ".txt";

 
	/**
	 * This method takes a level number and opens the text file for 
	 * that level.
	 * @param LevelNumber
	 */
	private static void setFile(int LevelNumber) {
		File fileLocation = new File("Levels/Level" + LevelNumber + 
				".txt");
		try { //Catching a FileNotFoundException
			in = new Scanner(fileLocation);
			in2 = new Scanner(fileLocation);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
	
	/**
	 * This method builds a 2d array version of the map.
	 */
	public static void SaveGame() {
		currentLevel = TrainCanvas.getCurrentLevel();
		setFile(currentLevel);

		objectList = TrainCanvas.getObjects();
		enemyList = TrainCanvas.getEnemies();
		player = TrainCanvas.getPlayer();

		mapHeight = in.nextInt();
		in.useDelimiter("");
		in2.useDelimiter("");

		in.nextLine(); //Goes to the next line
		extra = in.nextLine();
		String mapLine = in.nextLine();

		mapWidth = mapLine.length();
		//Going to the map.
		in2.nextLine();
		in2.nextLine();


		map = new char[mapWidth][mapHeight];
		buildMap();
	}

	public static void buildMap() {
		for (int i = 0; i < objectList.size(); i++) {
			Object currObj = objectList.get(i);
			int currX = fixX(currObj.getX());
			int currY = fixY(currObj.getY());
			//Using a switch statement to determine the current object.
			switch (currObj.tileType) {
			case "Key Green": map[currX][currY] = 'k';
			extraInformationLine = extraInformationLine + "1 ";
			break;
			case "Key Red": map[currX][currY] = 'k';
			extraInformationLine = extraInformationLine + "2 ";
			break;
			case "Key Blue": map[currX][currY] = 'k';
			extraInformationLine = extraInformationLine + "3 ";
			break;
			case "KeyDoor Green": map[currX][currY] = 'K';
			extraInformationLine = extraInformationLine + "1 ";
			break;
			case "KeyDoor Red": map[currX][currY] = 'K';
			extraInformationLine = extraInformationLine + "2 ";
			break;
			case "KeyDoor Blue": map[currX][currY] = 'K';
			extraInformationLine = extraInformationLine + "3 ";
			break;
			case "Token": map[currX][currY] = 't';
			break;
			case "TokenDoor 1": map[currX][currY] = 'T';
			extraInformationLine = extraInformationLine + "1 ";
			break;
			case "TokenDoor 2": map[currX][currY] = 'T';
			extraInformationLine = extraInformationLine + "2 ";
			break;
			case "TokenDoor 3": map[currX][currY] = 'T';
			extraInformationLine = extraInformationLine + "3 ";
			break;
			case "FireBoots": map[currX][currY] = 'I';
			extraInformationLine = extraInformationLine + "2 ";
			break;
			case "Flippers": map[currX][currY] = 'I';
			extraInformationLine = extraInformationLine + "1 ";
			break;
			case "Wall": map[currX][currY] = '#';
			break;
			case "Floor": map[currX][currY] = '-';
			break;
			case "Goal": map[currX][currY] = 'G';
			break;
			case "Fire": map[currX][currY] = 'F';
			break;
			case "Water": map[currX][currY] = 'W';
			break;
			case "Teleporter": map[currX][currY] = 'Z';
			break;
			
			default: map[currX][currY] = '0';
			System.out.println("Error saving at " + currX + " " + currY);
			break;
			}
			
			try {
				printToFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		//Adding the enemies to the list
		for (int j = 0; j < enemyList.size(); j++) {
			Object currEnemy = enemyList.get(j);
			int currX = fixX(currEnemy.getX());
			int currY = fixY(currEnemy.getY());
			
			switch (currEnemy.tileType) {
			case "Enemy 1 1": map[currX][currY] = 'H';
			enemyExtraInfo = enemyExtraInfo + "1 1"; 
			break;
			case "Enemy 1 2": map[currX][currY] = 'H';
			enemyExtraInfo = enemyExtraInfo + "1 2"; 
			break;
			case "Enemy 1 3": map[currX][currY] = 'H';
			enemyExtraInfo = enemyExtraInfo + "1 3"; 
			break;
			case "Enemy 1 4": map[currX][currY] = 'H';
			enemyExtraInfo = enemyExtraInfo + "1 4"; 
			break;
			case "Enemy 2": map[currX][currY] = 'H';
			enemyExtraInfo = enemyExtraInfo + "2"; 
			break;
			case "Enemy 3": map[currX][currY] = 'H';
			enemyExtraInfo = enemyExtraInfo + "3"; 
			break;
			case "Enemy 4": map[currX][currY] = 'H';
			enemyExtraInfo = enemyExtraInfo + "4"; 
			break;
			}
		}
		//Setting the players location
		int playX = fixX(player.getX());
		int playY = fixY(player.getY());
		map[playX][playY] = 'P';
		
		//Setting the players inventory.
		playerInventory = playerInventory + player.getTokens() + " ";
		playerInventory = playerInventory + player.hasGreenKey() + " ";
		playerInventory = playerInventory + player.hasRedKey() + " ";
		playerInventory = playerInventory + player.hasBlueKey() + " ";
		playerInventory = playerInventory + player.hasFlippers() + " ";
		playerInventory = playerInventory + player.hasFireBoots() + " ";
		
		try {
			printToFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Not found!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("Unsupportied encoding");
		}
	}

	/**
	 * Fixes the coordinates of an object to their original value.
	 * @param oldX
	 * @return int
	 */
	private static int fixX(int oldX) {
		int newX = oldX + TrainCanvas.playerOriginalX - ((int) TrainCanvas.TILES_ON_SCREEN / 2) + TrainCanvas.playerOffsetX;
		return newX;
	}

	/**
	 * Fixes the coordinates of an object to their original value.
	 * @param oldY
	 * @return int
	 */
	private static int fixY(int oldY) {
		int newY = oldY + TrainCanvas.playerOriginalY - ((int) TrainCanvas.TILES_ON_SCREEN / 2) + TrainCanvas.playerOffsetY;
		return newY;
	}
	
	/**
	 * This method prints out the save game state to a text file.
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void printToFile() throws FileNotFoundException, UnsupportedEncodingException {
		String filename = System.getProperty("user.dir") + "\\SaveGame\\" + 
				"Level" + currentLevel + "_" + user + ".txt";
		PrintWriter writer = new PrintWriter(filename,"UTF-8");
		
//		System.out.println(Arrays.deepToString(map)); //For testing
		writer.println(mapHeight);
//		System.out.println(mapHeight); //for testing
		writer.println(extraInformationLine);
//		System.out.println(extraInformationLine); //for testing
//		System.out.println(mapWidth); //for testing
		for(int i = 0; i < mapHeight; i++) {
			for(int j = 0; j < mapWidth; j++) {
//				System.out.print(map[j][i]);//For testing
				writer.print(map[j][i]);
			}
			writer.print("\n");
//			System.out.print("\n");
		}
		
		writer.println(enemyExtraInfo);
		writer.println(playerInventory);
		
		writer.close();
		
	}	
	
	/**
	 * This method sets the current level.
	 * @param level
	 */
	public static void setCurrentLevel(int level) {
		currentLevel = level;
	}

}