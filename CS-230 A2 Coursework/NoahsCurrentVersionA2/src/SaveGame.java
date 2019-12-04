import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class SaveGame extends Object {
	
	public SaveGame(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		// TODO Auto-generated constructor stub
	}

	static char[][] map;
	static char[][] mainMap;
	private static Scanner in = null;
	private static Scanner info = null;
	private static Scanner in2 = null;
	private static String detailedInformation = "";
	private static String user = Profile.getUser(); //This has been brute forced - CHANGE
	private static int currentLevel; //This has been brute forced - CHANGE
	private static String filename = System.getProperty("user.dir") + "\\SaveGame\\" + 
			"Level" + currentLevel + "_" + user + ".txt";
	private static Player player;
	private static int heightOfarray;
	private static int widthOfArray;
	private static int mapHeight;
	

	/**
	 * This method takes a level pathfile and opens the text file for 
	 * that saved level and opens two scanners.
	 * @param pahtname is the path of the file being loaded
	 */
	private static void setFile(String pathname) {
		File fileLocation = new File(pathname);
		try { //Catching a FileNotFoundException
			in = new Scanner(fileLocation);
			in2 = new Scanner(fileLocation);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
	
	/**
	 * This method is the main method which writes to the user file. It first
	 * reads the original level file and strips out all animate objects, with the
	 * hope of being able to add them in later
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void readOriginal() throws FileNotFoundException, UnsupportedEncodingException {
		String file = System.getProperty("user.dir") + "\\Levels\\" + "Level" + currentLevel + ".txt"; 
		setFile(file);
		//Reading in the number of lines to read
		mapHeight = in.nextInt();
		in2.nextInt();
		System.out.println(mapHeight);
		in.nextLine();
		in2.nextLine();
		in2.nextLine();
		//Saving the extra information as a string then making a scanner for it
		detailedInformation = in.nextLine();
		info = new Scanner(detailedInformation);
		while (info.hasNextInt()) {
			System.out.print(info.nextInt() + " ");
		}
		System.out.print("\n");
		heightOfarray = mapHeight;
		widthOfArray = in2.nextLine().length();
		map = new char[heightOfarray][widthOfArray];
		//Going through each line of the file
		int arrayX = 0;
		int arrayY = 0;
		
		
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
				//Reads original data and turns any animate objects into floor times to be replaced
				//later on in the process
				switch (current) {
				case "#": map[arrayX][arrayY] = '#';
				arrayY++;
				break;
				case "-": map[arrayX][arrayY] = '-';
				arrayY++;
				break;
				case "G": map[arrayX][arrayY] = 'G';
				arrayY++;
				break;
				case "t": map[arrayX][arrayY] = 't';
				arrayY++;
				break;
				case "K": map[arrayX][arrayY] = 'K';
				arrayY++;
				break;
				case "k": map[arrayX][arrayY] = 'k';
				arrayY++;
				break;
				case "H": map[arrayX][arrayY] = '-';
				arrayY++;
				break;
				case "T": map[arrayX][arrayY] = 'T';
				arrayY++;
				break;
				case "F": map[arrayX][arrayY] = 'F';
				arrayY++;
				break;
				case "P": map[arrayX][arrayY] = '-';
				arrayY++;
				break;
				case "W": map[arrayX][arrayY] = 'W';
				arrayY++;
				break;
				case "Z": map[arrayX][arrayY] = 'Z';
				arrayY++;
				break;
				case "I": map[arrayX][arrayY] = '-';
				arrayY++;
				break;
				}
				
			}
			arrayY = 0;
			arrayX++;
		}
		
		//This is for testing, seeing if it's printing the correct level (original file)
		for(int i = 0;i<heightOfarray;i++) {
			
			for(int j = 0;j<widthOfArray;j++) {
				System.out.print(map[i][j]);
		
			}
			System.out.println();
		}
		mainMap = map;
		addItemsToArray(); //This then initiates the adding process 
		
	}
	
	public static void setCurrentLevel(int level) {
		currentLevel = level;
		System.out.println(currentLevel);
		}
	
	/**
	 * This class will add the animate objects into the final saveGame map
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void addItemsToArray() throws FileNotFoundException, UnsupportedEncodingException {
		//Only here for testing purposes
		System.out.println();
		for(int i = 0;i<heightOfarray;i++) {
			for(int j = 0;j<widthOfArray;j++) {
				System.out.print(mainMap[i][j]);
		
			}
			System.out.println();
		}
	
		printToFile();
	}
	
	public static void printToFile() throws FileNotFoundException, UnsupportedEncodingException {
		String filename = System.getProperty("user.dir") + "\\SaveGame\\" + 
				"Level" + currentLevel + "_" + user + ".txt";
		PrintWriter writer = new PrintWriter(filename,"UTF-8");
		writer.println(mapHeight);
		writer.println(detailedInformation);
		
		for(int i = 0;i<heightOfarray;i++) {
			for(int j = 0;j<widthOfArray;j++) {
				writer.print(mainMap[i][j]);
			}
			writer.println();
		}
		
		writer.close();
		
		
		
		
	}
	
}
