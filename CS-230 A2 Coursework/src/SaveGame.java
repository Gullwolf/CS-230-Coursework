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
 * @author Cai Sidaway, Noah Stebbings
 * @version 1.3
 *
 */
public class SaveGame {


	static char[][] map;
	private static Scanner in = null;
	private static Scanner in2 = null;	
	private static int mapHeight;
	private static int mapWidth;
	private static String extra;
	
	private static ArrayList<Object> objectList = TrainCanvas.getObjects();
	private static ArrayList<Object> enemyList = TrainCanvas.getEnemies();
	private static Player player = TrainCanvas.getPlayer();
	
	private static String extraInformationLine = "";
	
	private static int currentLevel = 1;
	private static String user = "Noah";//TODO change this
	
//	static char[][] mainMap;
//	private static Scanner info = null;

//	private static String detailedInformation = "";
//	private static String user = "Test"; //This has been brute forced - CHANGE
//	private static int currentLevel = 1; //This has been brute forced - CHANGE
 private static String filename = System.getProperty("user.dir") + "\\SaveGame\\" + 
		"Level" + currentLevel + "_" + user + ".txt";
//	private static Player player;
//	private static int heightOfarray;
//	private static int widthOfArray;
 
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

	/**
	 * This method turns the current game state into a 2d array.
	 */
	public static void buildMap() {
		//Going through each line of the file
		for (int y = 0; y < mapHeight; y++) { 
			String currentLine = in2.nextLine();
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
				case "#": map[x][y] = '#';
				break;
				case "-": map[x][y] = '-';
				break;
				case "G": map[x][y] = 'G';
				break;
				case "t": checkTile(x, y);
				break;
				case "K": checkTile(x, y);
				break;
				case "k": checkTile(x, y);
				break;
				case "H": map[x][y] = '-';
				break;
				case "T": checkTile(x, y);
				break;
				case "F": map[x][y] = 'F';
				break;
				case "P": map[x][y] = '-';
				break;
				case "W": map[x][y] = 'W';
				break;
				case "Z": map[x][y] = 'Z';
				break;
				case "I": checkTile(x, y);
				break;
				}
			}
		}
//		//Adding the enemies to the 2d array
//		for (int i = 0; i < enemyList.size(); i++) {
//			Object currEnemy = enemyList.get(i);
//			map[currEnemy.getX()][currEnemy.getY()] = 'H';
//		}
//		//Adding the player to the 2d array
//		map[player.getX()][player.getY()] = 'P';
		
		try {
			printToFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks the tile to see if has been picked up or not.
	 * @param x
	 * @param y
	 */
	public static void checkTile(int x, int y) {
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.get(i).getX() == x && objectList.get(i).getY() == y) {
				if (objectList.get(i).getTileType().equals("Floor")) {
					map[x][y] = '-';
					return;
				} else {
					switch (objectList.get(i).getTileType()) {
					case "Key Green": map[x][y] = 'k';
					extraInformationLine = extraInformationLine + " 1";
					break;
					case "Key Red": map[x][y] = 'k';
					extraInformationLine = extraInformationLine + " 2";
					break;
					case "Key Blue": map[x][y] = 'k';
					extraInformationLine = extraInformationLine + " 3";
					break;
					case "Token": map[x][y] = 't';
					break;
					case "TokenDoor": map[x][y] = 'T';
					extraInformationLine = extraInformationLine + " " + objectList.get(i).getRequirements();
					break;
					case "FireBoots": map[x][y] = 'I';
					extraInformationLine = extraInformationLine + " 2";
					break;
					case "Flippers": map[x][y] = 'I';
					extraInformationLine = extraInformationLine + " 1";
					break;
					}
				}
			}
		}
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
		
		System.out.println(Arrays.deepToString(map)); //For testing
		writer.println(mapHeight);
		System.out.println(mapHeight); //for testing
		writer.println(extra);
		System.out.println(extra); //for testing
		System.out.println(mapWidth); //for testing
		for(int i = 0;i<mapHeight;i++) {
			for(int j = 0; j < mapWidth; j++) {
				System.out.print(map[i][j]);
				writer.print(map[i][j]);
			}
			writer.println();
		}
		
		writer.close();
		
	}	
	
	public static void setCurrentLevel(int level) {
		currentLevel = level;
	}
	
	

//	/**
//	 * This method takes a level pathfile and opens the text file for 
//	 * that saved level and opens two scanners.
//	 * @param pahtname is the path of the file being loaded
//	 */
//	private static void setFile(String pathname) {
//		File fileLocation = new File(pathname);
//		try { //Catching a FileNotFoundException
//			in = new Scanner(fileLocation);
//			in2 = new Scanner(fileLocation);
//		} catch (FileNotFoundException e) {
//			System.out.println("File not found!");
//		}
//	}
//	
//	/**
//	 * This method is the main method which writes to the user file. It first
//	 * reads the original level file and strips out all animate objects, with the
//	 * hope of being able to add them in later.
//	 */
//	public static void readOriginal() {
//		String file = System.getProperty("user.dir") + "\\Levels\\" + "Level" + currentLevel + ".txt"; 
//		setFile(file);
//		//Reading in the number of lines to read
//		int mapHeight = in.nextInt();
//		in2.nextInt();
//		System.out.println(mapHeight);
//		in.nextLine();
//		in2.nextLine();
//		in2.nextLine();
//		//Saving the extra information as a string then making a scanner for it
//		detailedInformation = in.nextLine();
//		info = new Scanner(detailedInformation);
//		while (info.hasNextInt()) {
//			System.out.print(info.nextInt() + " ");
//		}
//		System.out.print("\n");
//		heightOfarray = mapHeight;
//		widthOfArray = in2.nextLine().length();
//		map = new char[heightOfarray][widthOfArray];
//		//Going through each line of the file
//		int arrayX = 0;
//		int arrayY = 0;
//		
//		
//		for (int y = 0; y < mapHeight; y++) { 
//			String currentLine = in.nextLine();
//			Scanner scan = new Scanner(currentLine);
//			//Changing the delimeter so it only looks at one character 
//			//at a time
//  			scan.useDelimiter(""); 
//			//Going through each character in the line
//			for (int x = 0; x < currentLine.length(); x++) {
//				String current = scan.next();
//				//A switch statement to work out the current tile being read.
//				int type;
//				//Reads original data and turns any animate objects into floor times to be replaced
//				//later on in the process
//				switch (current) {
//				case "#": map[arrayX][arrayY] = '#';
//				arrayY++;
//				break;
//				case "-": map[arrayX][arrayY] = '-';
//				arrayY++;
//				break;
//				case "G": map[arrayX][arrayY] = 'G';
//				arrayY++;
//				break;
//				case "t": map[arrayX][arrayY] = 't';
//				arrayY++;
//				break;
//				case "K": map[arrayX][arrayY] = 'K';
//				arrayY++;
//				break;
//				case "k": map[arrayX][arrayY] = 'k';
//				arrayY++;
//				break;
//				case "H": map[arrayX][arrayY] = '-';
//				arrayY++;
//				break;
//				case "T": map[arrayX][arrayY] = 'T';
//				arrayY++;
//				break;
//				case "F": map[arrayX][arrayY] = 'F';
//				arrayY++;
//				break;
//				case "P": map[arrayX][arrayY] = '-';
//				arrayY++;
//				break;
//				case "W": map[arrayX][arrayY] = 'W';
//				arrayY++;
//				break;
//				case "Z": map[arrayX][arrayY] = 'Z';
//				arrayY++;
//				break;
//				case "I": map[arrayX][arrayY] = '-';
//				arrayY++;
//				break;
//				}
//				
//			}
//			arrayY = 0;
//			arrayX++;
//		}
//		
//		//This is for testing, seeing if it's printing the correct level (original file)
//		for(int i = 0;i<heightOfarray;i++) {
//			
//			for(int j = 0;j<widthOfArray;j++) {
//				System.out.print(map[i][j]);
//		
//			}
//			System.out.println();
//		}
//		mainMap = map;
//		addItemsToArray(); //This then initiates the adding process 
//		
//	}
//	
//	
//	/**
//	 * This class will add the animate objects into the final saveGame map
//	 */
//	public static void addItemsToArray() {
//		player = TrainCanvas.getPlayer();
//		int x = player.getX();
//		int y = player.getY();
//		mainMap[y][x] = 'P';
//		
//		
//		//Only here for testing purposes
//		System.out.println();
//		for(int i = 0;i<heightOfarray;i++) {
//			for(int j = 0;j<widthOfArray;j++) {
//				System.out.print(mainMap[i][j]);
//		
//			}
//			System.out.println();
//		}	
//	}
	

}