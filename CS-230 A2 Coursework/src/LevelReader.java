import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.layout.Pane;

/**
 * 
 * This class reads the level information from a text file and builds a 
 * level based on this.
 * @author Noah Stebbings
 * @version 1.2
 *
 */
public class LevelReader {

	private static Scanner in = null;
	
	private static Scanner info = null;
	
	private static String detailedInformation = "";

	private  static Pane root;
	
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
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}

	/**
	 * Reading in the next piece of extra information when called.
	 * @return int
	 */
	private static int getExtraInfo() {
		int type = info.nextInt();
		detailedInformation = detailedInformation.substring(0, 1);
		return type;

	}
	/**
	 * This method reads through the level file and generates the level.
	 * @param levelNumber
	 * @param inRoot
	 */
	
	public static void createLevel(int levelNumber, Pane inRoot) {
		root = inRoot;
		setFile(levelNumber);
//		System.out.println(levelNumber);
		//Reading in the number of lines to read
		int mapHeight = in.nextInt();
		in.nextLine();
		//Saving the extra information as a string then making a scanner for it
		detailedInformation = in.nextLine();
		info = new Scanner(detailedInformation);
		//Going through each line of the file
		for (int y = 0; y < mapHeight; y++) { 
			String currentLine = in.nextLine();
			Scanner scan = new Scanner(currentLine);
			//Changing the delimeter so it only looks at one character 
			//at a time
			scan.useDelimiter(""); 
			//Going through each character in the line
			for (int x = 0; x < currentLine.length(); x++) {
				String current = scan.next();
				//A switch statement to work out the current tile being read.
				int type;
				//Using a switch statement to work out what character is being
				//read and running the correct method for that character
				switch (current) {
				case "#": addWall(x, y);
				break;
				case "-": addFloor(x, y);
				break;
				case "G": addGoal(x, y);
				break;
				case "t": addToken(x, y);
				break;
				case "K": type = getExtraInfo();
				addKeyDoor(x, y, type);
				break;
				case "k": type = getExtraInfo();
				addKey(x, y, type);
				break;
				case "H": type = getExtraInfo();
				addEnemy(x, y, type);
				break;
				case "T": type = getExtraInfo();
				addTokenDoor(x, y, type);
				break;
				case "F": addFire(x, y);
				break;
				case "P": addPlayer(x, y);
				break;
				case "W": addWater(x, y);
				break;
				case "Z": addTeleporter(x, y);
				break;
				case "I": type = getExtraInfo();
				addItem(x, y, type);
				break;
				}
			}
		}
	}
	/**
	 * This method creates a wall tile in the correct location.
	 * @param x y
	 */
	
	private static void addWall(int x, int y) {
		TrainCanvas.addWall(x, y);
	}
	/**
	 * @param x y
	 * This method creates a floor tile in the correct location.
	 */
	
	private static void addFloor(int x, int y) {
		TrainCanvas.addFloor(x, y);
	}
	/**
	 * @param x y
	 * This method creates a goal tile in the correct location.
	 */
	
	private static void addGoal(int x, int y) {
		TrainCanvas.addGoal(x, y);
	}
	/**
	 * @param x y
	 * This method creates a token tile in the correct location.
	 */
	
	private static void addToken(int x, int y) {
		TrainCanvas.addToken(x, y);
	}
	/**
	 * @param x y requirements
	 * This method creates a token door tile in the correct location.
	 */
	
	private static void addTokenDoor(int x, int y, int requirements) {
		TrainCanvas.addTokenDoor(x, y, requirements);
	}
	/**
	 * @param x y type
	 * This method creates a key tile in the correct location.
	 */
	
	private static void addKey(int x, int y, int type) {
		if (type == 1) {
			TrainCanvas.addKey(x, y, type);
		} else if (type == 2) {
			TrainCanvas.addKey(x, y, type);
		} else if (type == 3) {
			TrainCanvas.addKey(x, y, type);
		} else { 
			System.out.print("Error, invalid type");
		}
	}
	/**
	 * @param x y type
	 * This method creates a key door tile in the correct location.
	 */
	
	private static void addKeyDoor(int x, int y, int type) {

		if (type == 1) {
			TrainCanvas.addKeyDoor(x, y, type);
		} else if (type == 2) {
			TrainCanvas.addKeyDoor(x, y, type);
		} else if (type == 3) {
			TrainCanvas.addKeyDoor(x, y, type);
		} else { 
			System.out.print("Error, invalid type");
		}
	}
	/**
	 * @param x y type
	 * This method creates a enemy tile in the correct location.
	 */
	
	private static void addEnemy(int x, int y, int type) {

		if (type == 1) {
			int type2 = getExtraInfo();
			if (type2 == 1) {
				TrainCanvas.addEnemy(x, y, type, 1);
			} else if (type2 == 2) {
				TrainCanvas.addEnemy(x, y, type, 2);
			} else { 
				System.out.print("Error, invalid type enemy basic");
			}
		} else if (type == 2) {
			TrainCanvas.addEnemy(x, y, type, 0);
		} else if (type == 3) {
			TrainCanvas.addEnemy(x, y, type, 0);
		} else if (type == 4) {
			TrainCanvas.addEnemy(x, y, type, 0);
		} else { 
			System.out.print("Error, invalid type wrong enemy");
		}
	}
	/**
	 * @param x y
	 * This method creates a player tile in the correct location.
	 */
	
	private static void addPlayer(int x, int y) {
		TrainCanvas.addPlayer(x, y);
	}
	/**
	 * @param x y
	 * This method creates a fire tile in the correct location.
	 */
	
	private static void addFire(int x, int y) {
		TrainCanvas.addFire(x, y);
	}
	/**
	 * @param x y
	 * This method creates a water tile in the correct location.
	 */
	
	private static void addWater(int x, int y) {
		TrainCanvas.addWater(x, y);
	}
	/**
	 * @param x y linkX linkY
	 * This method creates a teleporter tile in the correct location.
	 */
	
	private static void addTeleporter(int x, int y) {
		TrainCanvas.addTeleporter(x, y);
	}
	/**
	 * @param x y type
	 * This method creates a item tile in the correct location.
	 */
	
	private static void addItem(int x, int y, int type) {
		if (type == 1) {
			TrainCanvas.addFlippers(x, y);
		} else if (type == 2) {
			TrainCanvas.addFireBoots(x, y);
		} else { 
			System.out.print("Error, invalid type");
		}
	}
}