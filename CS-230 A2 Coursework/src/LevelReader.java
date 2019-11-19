import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.layout.Pane;

/**
 * 
 * This class reads the level information from a text file and builds a 
 * level based on this.
 * @author Noah Stebbings
 * @version 1.1
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
		int mapHeight = in.nextInt(); //Reading in the number of lines to read
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
				case "Z": int linkX = getExtraInfo();
				int linkY = getExtraInfo();
				addTeleporter(x, y, linkX, linkY);
				break;
				case "I": type = getExtraInfo();
				addItem(x, y, type);
				break;
				}
				System.out.print(" ");
			}
			System.out.println("");
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
		System.out.print("Token");
	}
	/**
	 * @param x y requirements
	 * This method creates a token door tile in the correct location.
	 */
	
	private static void addTokenDoor(int x, int y, int requirements) {
		System.out.print("TokenDoor " + requirements);
	}
	/**
	 * @param x y type
	 * This method creates a key tile in the correct location.
	 */
	
	private static void addKey(int x, int y, int type) {
		if (type == 1) {
			System.out.print("Green key");
		} else if (type == 2) {
			System.out.print("Red key");
		} else if (type == 3) {
			System.out.print("Blue key");
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
			System.out.print("Green door");
		} else if (type == 2) {
			System.out.print("Red door");
		} else if (type == 3) {
			System.out.print("Blue door");
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
			if (getExtraInfo() == 1) {
				System.out.print("UpDown Enemy");
			} else if (type == 2) {
				System.out.print("LeftRight Enemy");
			} else { 
				System.out.print("Error, invalid type");
			}
		} else if (type == 2) {
			System.out.print("Wall-hug Enemy");
		} else if (type == 3) {
			System.out.print("Dumb Enemy");
		} else if (type == 4) {
			System.out.print("Smart Enemy");
		} else { 
			System.out.print("Error, invalid type");
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
		System.out.print("Water");
	}
	/**
	 * @param x y linkX linkY
	 * This method creates a teleporter tile in the correct location.
	 */
	
	private static void addTeleporter(int x, int y, int linkX, int linkY) {
		System.out.print("Teleporter, Link " + linkX + " " + linkY);
	}
	/**
	 * @param x y type
	 * This method creates a item tile in the correct location.
	 */
	
	private static void addItem(int x, int y, int type) {
		if (type == 1) {
			System.out.print("Flippers");
		} else if (type == 2) {
			System.out.print("FireBoots");
		} else { 
			System.out.print("Error, invalid type");
		}
	}
}